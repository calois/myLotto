package lotto;

import java.util.List;

public interface LottoService {
	public void init() throws Exception;

	public void add(int[] num);

	public void work() throws Exception;

	public List<Select> getCandidates(int limit);
}
