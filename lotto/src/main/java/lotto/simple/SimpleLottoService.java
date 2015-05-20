package lotto.simple;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import lotto.LottoService;
import lotto.Select;
import lotto.Utils;

public class SimpleLottoService implements LottoService {

	private static final String EXISTING_TXT = "existing.txt";
	private static final String SEED4_TXT = "seed4.txt";
	private List<Select> seed4List = new ArrayList<>();
	private List<int[]> existingNumbers = new ArrayList<>();
	private List<int[]> newNumbers = new ArrayList<>();

	private int count = 0;

	@Override
	public void init() throws Exception {
		File file = new File(SEED4_TXT);
		if (file.exists()) {
			seed4List = Utils.readSelect(SEED4_TXT);
		} else {
			Utils.getCombination(45, 4).forEach(it -> {
				seed4List.add(new Select(it));
			});
		}

		File existingFile = new File(EXISTING_TXT);
		if (existingFile.exists()) {
			existingNumbers = Utils.readNumber(EXISTING_TXT);
		}
	}

	private boolean isOld(int[] num) {
		return existingNumbers.stream().anyMatch(it -> {
			for (int i = 0; i < it.length; i++) {
				if (num[i] != it[i]) {
					return false;
				}
			}
			return true;
		});
	}

	@Override
	public void add(int[] num) {
		if (isOld(num)) {
			return;
		} else {
			existingNumbers.add(num);
			newNumbers.add(num);
		}
	}

	@Override
	public void work() throws Exception {
		newNumbers.stream().forEach(num -> {
			count++;
			seed4List.stream().forEach(n1 -> {
				Utils.getCombination(num, 4).forEach(n2 -> {
					n1.check(n2);
				});
			});
			System.out.println(count);
		});
		Collections.sort(seed4List, new Comparator<Select>() {
			@Override
			public int compare(Select o1, Select o2) {
				return o2.getScore() - o1.getScore();
			}
		});
		Utils.writeNumber(EXISTING_TXT, existingNumbers);
		Utils.writeSelect(SEED4_TXT, seed4List);
	}

	@Override
	public List<Select> getCandidates(int limit) {
		int max = Integer.MIN_VALUE;
		List<Select> options = new ArrayList<>();
		for (Select s : seed4List) {
			if (s.getScore() > max) {
				max = s.getScore();
			} else if (s.getScore() < max) {
				break;
			}
			options.add(s);
			System.out.println(s);
		}
		return options;
	}

}
