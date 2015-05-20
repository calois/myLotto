package lotto;

import java.util.List;

import lotto.simple.SimpleLottoService;

public class Main {

	public static void main(String[] args) throws Exception {
		Loader loader = new OzLottoLoader();
		List<int[]> seeds = loader.load();
		System.out.println("Load seeds finished! " + seeds.size());
		LottoService service = new SimpleLottoService();
		service.init();
		System.out.println("Init successfully");
		for (int i = 0; i < seeds.size(); i++) {
			service.add(seeds.get(i));
		}
		service.work();
		List<Select> result = service.getCandidates(new int[] { 10, 9, 11, 32,
				22, 24, 36, 37, 21, 12, 39, 43, 5, 7 });
		System.out.println(result.size());
		result.forEach(it -> {
			it.print();
		});
	}
}