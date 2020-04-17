package exception.grammar;

import grammar.grammarsymbol.NonterminalSymbol;
import grammar.grammarsymbol.TerminalSymbol;

public class SynchException extends GrammarException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SynchException(NonterminalSymbol nonterminalSymbol, TerminalSymbol terminalSymbol) {
		super(nonterminalSymbol.toString() + " synch " + terminalSymbol.toString());
		// TODO Auto-generated constructor stub
	}

}
