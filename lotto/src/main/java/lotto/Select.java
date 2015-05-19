package lotto;

import java.util.Arrays;

public class Select {
	private int[] num;
	private int score;

	public int getScore() {
		return score;
	}

	public Select(int[] num) {
		this.num = num;
		score = 0;
	}

	public boolean check(int[] n) {
		for (int i = 0; i < n.length; i++) {
			if (-1 == Arrays.binarySearch(num, n[i])) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (int n : num) {
			if (s.toString().length() != 0) {
				s.append("," + n);
			}
		}
		return s.toString();
	}
}
