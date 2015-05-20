package lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OzLottoLoader implements Loader {

	@Override
	public List<int[]> load() {
		List<int[]> result = new ArrayList<>();
		int totalPages = 1;
		for (int i = 1; i <= totalPages; i++) {
			String html = Utils
					.httpGet("http://www.ozlotteries.com/lotto-results?lottery_id=8&page="
							+ i);
			if (i == 1) {
				Pattern p = Pattern.compile("of\\s(\\d+?)\\s[&]nbsp;");
				Matcher m = p.matcher(html);
				while (m.find()) {
					totalPages = Integer.valueOf(m.group(1));
					break;
				}
			}
			Pattern p = Pattern
					.compile("<div id=\"resultNumbers\">((\\s*<div class=\"main-numbers-8\"><div class=\"number\">\\d+</div></div>\\s*){7})</div>");
			Matcher m = p.matcher(html);
			while (m.find()) {
				String s = m.group(1);
				Pattern p2 = Pattern
						.compile("<div class=\"main-numbers-8\"><div class=\"number\">(\\d+)</div></div>");
				Matcher m2 = p2.matcher(s);
				int[] r = new int[7];
				int j = 0;
				while (m2.find()) {
					r[j++] = Integer.valueOf(m2.group(1));
				}
				Arrays.sort(r);
				result.add(r);
			}
		}
		Collections.reverse(result);
		return result;
	}
}
