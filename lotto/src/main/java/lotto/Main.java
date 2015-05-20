package lotto;

import java.util.List;

import lotto.simple.SimpleLottoService;

public class Main {

	public static void main(String[] args) {
		Loader loader = new OzLottoLoader();
		List<int[]> seeds = loader.load();
		System.out.println("Load seeds finished! " + seeds.size());
		LottoService service = new SimpleLottoService();
		service.init();
		System.out.println("Init successfully");
		for (int i = 0; i < seeds.size(); i++) {
			service.add(seeds.get(i));
			System.out.println(i + " Add successfully");
		}
		System.out.println(service.getCandidates(10).size());
	}
}
