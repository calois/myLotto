package lotto.simple;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import lotto.Select;
import lotto.Utils;

public class Seed {
	private List<Select> seedList = new ArrayList<>();
	private int n;
	private boolean dirty = false;
	private int count = 0;
	private List<Select> sequenceList = new ArrayList<>();
	private static final Comparator<Select> C = new Comparator<Select>() {
		@Override
		public int compare(Select o1, Select o2) {
			int[] num1 = o1.getNum();
			int[] num2 = o2.getNum();
			for (int i = 0; i < num1.length; i++) {
				if (num1[i] < num2[i]) {
					return -1;
				} else if (num1[i] > num2[i]) {
					return 1;
				}
			}
			return 0;
		}
	};

	public Seed(int n) throws Exception {
		this.n = n;
		File file = new File("seed_" + n + ".txt");
		if (file.exists()) {
			seedList = Utils.readSelect("seed_" + n + ".txt");
		} else {
			Utils.getCombination(45, n).forEach(it -> {
				seedList.add(new Select(it));
			});
		}
	}

	public void add(int[] sample) {
		dirty = true;
		count++;
		Utils.getCombination(sample, n).stream().forEach(num -> {
			seedList.stream().forEach(n1 -> {
				n1.check(num);
			});
		});
		System.out.println("Seed " + n + " " + count);
	}

	public void work() throws Exception {
		sequenceList.clear();
		sequenceList.addAll(seedList);
		Collections.sort(sequenceList, C);
		if (!dirty) {
			return;
		}
		Collections.sort(seedList, new Comparator<Select>() {
			@Override
			public int compare(Select o1, Select o2) {
				return o2.getScore() - o1.getScore();
			}
		});
		Utils.writeSelect("seed_" + n + ".txt", seedList);
		dirty = false;
	}

	public void check(final Select select) {
		select.setScore(0);
		Utils.getCombination(select.getNum(), n).forEach(
				it -> {
					int index = Collections.binarySearch(sequenceList,
							new Select(it), C);
					select.setScore(select.getScore()
							+ sequenceList.get(index).getScore());
				});
	}
}
