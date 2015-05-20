package lotto;

import java.util.List;

import lotto.simple.SimpleLottoService;

public class Main {

	public static void main(String[] args) throws Exception {
		// Loader loader = new OzLottoLoader();
		// List<int[]> seeds = loader.load();
		// System.out.println("Load seeds finished! " + seeds.size());
		LottoService service = new SimpleLottoService();
		service.init();
		System.out.println("Init successfully");
		// for (int i = 0; i < seeds.size(); i++) {
		// service.add(seeds.get(i));
		// }
		service.work();
		/*
		 * int[] n = new int[45]; for (int i = 0; i < n.length; i++) { n[i] = i
		 * + 1; }
		 */
		/*
		 * List<Select> result = service.getCandidates(n);
		 */
		List<Select> result = service.getCandidates(new int[] { 10, 9, 30, 13,
				11, 38, 42, 32, 18, 31, 14, 4, 22, 44, 24, 36, 37, 21, 8, 23,
				41, 12, 26, 39, 43, 1, 15, 34, 45, 20, 40, 5, 6 });
		System.out.println(result.size());
		result.forEach(it -> {
			it.print();
		});
	}
}