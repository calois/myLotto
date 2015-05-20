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
			if (Arrays.binarySearch(num, n[i]) < 0) {
				return false;
			}
		}
		score--;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (int n : num) {
			if (s.toString().length() != 0) {
				s.append("," + n);
			} else {
				s.append(n);
			}
		}
		s.append("(" + score + ")");
		return s.toString();
	}
}
