package lotto.simple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import lotto.LottoService;
import lotto.Select;
import lotto.Utils;

public class SimpleLottoService implements LottoService {

	private List<Select> selectList = new ArrayList<>();

	@Override
	public void init() {
		/*
		 * List<int[]> r = Utils.getCombination(45, 7); selectList = r;
		 * System.out.println("Init completed: " + selectList.size());
		 */
		Utils.getCombination(45, 4).forEach(it -> {
			selectList.add(new Select(it));
		});
	}

	@Override
	public void add(int[] num) {
		selectList.stream().forEach(n1 -> {
			Utils.getCombination(num, 4).forEach(n2 -> {
				n1.check(n2);
			});
		});
	}

	@Override
	public List<Select> getCandidates(int limit) {
		Collections.sort(selectList, new Comparator<Select>() {
			@Override
			public int compare(Select o1, Select o2) {
				return o2.getScore() - o1.getScore();
			}
		});
		int max = Integer.MIN_VALUE;
		List<Select> options = new ArrayList<>();
		for (Select s : selectList) {
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
