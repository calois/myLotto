package lotto;

import lotto.simple.SimpleLottoService;

public class Main {

	public static void main(String[] args) {
		LottoService service = new SimpleLottoService();
		service.init();
		service.add(new int[] { 21, 35, 40, 20, 14, 5, 8 });
		service.add(new int[] { 16, 44, 2, 17, 1, 32, 34 });
		System.out.println(service.getCandidates(10).size());
	}

}
