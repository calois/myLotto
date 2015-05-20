package lotto;

import java.math.BigDecimal;
import java.util.Arrays;

public class Select {
	public int[] getNum() {
		return num;
	}

	private int[] num;
	private int score;

	private BigDecimal mark = new BigDecimal(1);

	public void appendMark(double d) {
		mark = mark.multiply(new BigDecimal(d));
	}

	public int getScore() {
		return score;
	}

	public Select(int[] num) {
		this.num = num;
		score = 0;
	}

	public Select(int[] num, int score) {
		this.num = num;
		this.score = score;
	}

	public void setScore(int score) {
		this.score = score;
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
		s.append("," + score);
		return s.toString();
	}

	public void print() {
		StringBuilder s = new StringBuilder();
		for (int n : num) {
			if (s.toString().length() != 0) {
				s.append("," + n);
			} else {
				s.append(n);
			}
		}
		s.append("(" + score + ")");
		System.out.println(s.toString());
	}
}
