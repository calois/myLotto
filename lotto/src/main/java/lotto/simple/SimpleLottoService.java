package lotto.simple;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lotto.LottoService;
import lotto.Select;
import lotto.Utils;

public class SimpleLottoService implements LottoService {

	private static final String EXISTING_TXT = "existing.txt";
	private List<int[]> existingNumbers = new ArrayList<>();
	private List<int[]> newNumbers = new ArrayList<>();
	private List<Seed> seedList = new ArrayList<>();

	@Override
	public void init() throws Exception {
		IntStream.range(1, 5).forEach(it -> {
			try {
				seedList.add(new Seed(it));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		File existingFile = new File(EXISTING_TXT);
		if (existingFile.exists()) {
			existingNumbers = Utils.readNumbers(EXISTING_TXT);
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
			seedList.forEach(it -> {
				it.add(num);
			});
		}
	}

	@Override
	public void work() throws Exception {
		seedList.forEach(it -> {
			try {
				it.work();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		Utils.writeNumbers(EXISTING_TXT, existingNumbers);
	}

	@Override
	public List<Select> getCandidates(int[] pickNumber) {
		Arrays.sort(pickNumber);
		List<Select> result = Utils.getCombination(pickNumber, 7).stream()
				.map(it -> new Select(it)).collect(Collectors.toList());
		for (int i = 4; i > 0; i--) {
			Seed it = seedList.get(i - 1);
			result.forEach(r -> {
				it.check(r);
			});
			Collections.sort(result, new Comparator<Select>() {
				@Override
				public int compare(Select o1, Select o2) {
					return o2.getScore() - o1.getScore();
				}
			});
			result = result.stream().limit(i * i).collect(Collectors.toList());
		}
		return result;
	}

}
