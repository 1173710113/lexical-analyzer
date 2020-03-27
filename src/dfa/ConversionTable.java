package dfa;

public interface ConversionTable {

	/**
	 * 给出当前状态和输入，给出跳转的状态
	 * @param state 当前状态
	 * @param symbol 当前输入
	 * @return 如果没有该项则返回null
	 */
	public String convert(String state, Character symbol);
	
}
