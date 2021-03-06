package lexer.dfa;

import exception.dfa.InValidInputException;
import exception.dfa.NullConvertionException;

public interface ConversionTable {

	/**
	 * 给出当前状态和输入，给出跳转的状态
	 * 
	 * @param state  当前状态
	 * @param symbol 当前输入
	 * @return
	 */
	public String convert(String state, Character symbol) throws InValidInputException, NullConvertionException;

}
