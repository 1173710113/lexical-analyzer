package exception.grammar;

import grammar.grammarsymbol.TerminalSymbol;

public class TerminalSymbolNotMatchException extends GrammarException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TerminalSymbolNotMatchException(TerminalSymbol terminalSymbolExpected, TerminalSymbol terminalSymbolActual) {
		super("expected " + terminalSymbolExpected.toString() + " but " + terminalSymbolActual.toString());
		// TODO Auto-generated constructor stub
	}

}
