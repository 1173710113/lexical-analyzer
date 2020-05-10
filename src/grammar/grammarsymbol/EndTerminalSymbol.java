package grammar.grammarsymbol;

public class EndTerminalSymbol implements TerminalSymbol{
	private String symbol;

	private static EndTerminalSymbol endTerminalSymbol = new EndTerminalSymbol("END");

	public static EndTerminalSymbol getInstance() {
		return endTerminalSymbol;
	}

	private EndTerminalSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof EndTerminalSymbol)
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
