package exception.grammar;

import exception.MyException;
import grammar.grammarsymbol.NonterminalSymbol;
import grammar.grammarsymbol.TerminalSymbol;

public class NullPredictionException extends MyException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NullPredictionException(NonterminalSymbol nonterminalSymbol, TerminalSymbol terminalSymbol) {
		super("预测分析表没有对应的项 " + nonterminalSymbol.toString() + " " + terminalSymbol.toString());
	}

}
