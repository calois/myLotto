package lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {
	private static void getCombination(List<int[]> result, int[] num,
			int index, int[] n, int nIndex) {
		if (index == num.length) {
			result.add(Arrays.copyOf(num, num.length));
			return;
		}
		for (int i = nIndex; n.length - i >= num.length - index; i++) {
			num[index] = n[i];
			getCombination(result, num, index + 1, n, i + 1);
		}

	}

	public static List<int[]> getCombination(int n, int m) {
		List<int[]> result = new ArrayList<>();
		int[] num = new int[m];
		int[] all = new int[n];
		for (int i = 0; i < n; i++) {
			all[i] = i + 1;
		}
		getCombination(result, num, 0, all, 0);
		return result;
	}

	public static List<int[]> getCombination(int[] n, int m) {
		List<int[]> result = new ArrayList<>();
		int[] num = new int[m];
		getCombination(result, num, 0, n, 0);
		return result;
	}

	public static void main(String[] args) {
		List<int[]> result = getCombination(45, 4);
		System.out.println(result.size());
	}
}
