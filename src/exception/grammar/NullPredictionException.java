package exception.grammar;

import grammar.grammarsymbol.NonterminalSymbol;
import grammar.grammarsymbol.TerminalSymbol;

public class NullPredictionException extends GrammarException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NullPredictionException(NonterminalSymbol nonterminalSymbol, TerminalSymbol terminalSymbol) {
		super("Ԥ�������û�ж�Ӧ���� " + nonterminalSymbol.toString() + " " + terminalSymbol.toString());
	}

}
