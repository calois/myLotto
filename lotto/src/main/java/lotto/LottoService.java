package lotto;

import java.util.List;

public interface LottoService {
	public void init();

	public void add(int[] num);

	public List<Select> getCandidates(int limit);
}
