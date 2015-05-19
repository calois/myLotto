package lotto.simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lotto.LottoService;
import lotto.Utils;

public class SimpleLottoService implements LottoService {

	private List<int[]> selectList = new ArrayList<>();

	@Override
	public void init() {
		List<int[]> r = Utils.getCombination(45, 7);
		selectList = r;
		System.out.println("Init completed: " + selectList.size());
	}

	@Override
	public void add(int[] num) {
		Utils.getCombination(num, 4).forEach(it -> {
			selectList = selectList.stream().filter(s -> {
				for (int i = 0; i < it.length; i++) {
					if (Arrays.binarySearch(s, it[i]) < 0) {
						return true;
					}
				}
				return false;
			}).collect(Collectors.toList());
		});
		System.out.println(selectList.size());
	}

	@Override
	public List<int[]> getCandidates(int limit) {
		return selectList;
	}

}
