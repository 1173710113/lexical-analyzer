package grammar.grammarsymbol;

public class EmptyTerminalSymbol implements TerminalSymbol {

	private String symbol;
	
	private static EmptyTerminalSymbol emptyTerminalSymbol = new EmptyTerminalSymbol("EMPTY");

	public static EmptyTerminalSymbol getInstance() {
		return emptyTerminalSymbol;
	}

	private EmptyTerminalSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof EmptyTerminalSymbol)
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		return 0;
	}
	
	@Override
	public String toString() {
		return symbol;
	}

	@Override
	public Object getLexeme() {
		return null;
	}

	@Override
	public boolean hasLexeme() {
		return false;
	}

	@Override
	public String getName() {
		return toString();
	}
}
