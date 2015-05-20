package lotto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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

	public static String httpGet(String uri) {
		StringBuilder s = new StringBuilder();
		try {
			URL yahoo = new URL(uri);
			HttpURLConnection yc = (HttpURLConnection) yahoo.openConnection();
			yc.setRequestMethod("GET");
			yc.setRequestProperty("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			yc.setRequestProperty(
					"User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.152 Safari/537.36");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yc.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				s.append(inputLine);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s.toString();
	}

	public static List<Select> readSelect(String file) throws Exception {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			List<Select> result = new ArrayList<>();
			String line;
			while (null != (line = br.readLine())) {
				String[] temp = line.split(",");
				int[] num = new int[temp.length - 1];
				for (int i = 0; i < num.length; i++) {
					num[i] = Integer.valueOf(temp[i]);
				}
				int score = Integer.valueOf(temp[temp.length - 1]);
				Select s = new Select(num, score);
				result.add(s);
			}
			return result;
		}
	}

	public static void writeSelect(String file, List<Select> selects)
			throws Exception {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
				file)))) {
			for (int i = 0; i < selects.size(); i++) {
				if (i != 0) {
					bw.newLine();
				}
				bw.append(selects.get(i).toString());
			}
		}
	}

	public static List<int[]> readNumbers(String file) throws Exception {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			List<int[]> result = new ArrayList<>();
			String line;
			while (null != (line = br.readLine())) {
				String[] temp = line.split(",");
				int[] num = new int[temp.length];
				for (int i = 0; i < num.length; i++) {
					num[i] = Integer.valueOf(temp[i]);
				}
				result.add(num);
			}
			return result;
		}
	}

	public static void writeNumbers(String file, List<int[]> numbers)
			throws Exception {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
				file)))) {
			for (int i = 0; i < numbers.size(); i++) {
				if (i != 0) {
					bw.newLine();
				}
				for (int j = 0; j < numbers.get(i).length; j++) {
					if (j != 0) {
						bw.append(",");
					}
					bw.append(String.valueOf(numbers.get(i)[j]));
				}
			}
		}
	}

	public static void main(String[] args) {
		System.out
				.println(Utils
						.httpGet("http://www.ozlotteries.com/lotto-results?lottery_id=8&page=1"));
	}
}
