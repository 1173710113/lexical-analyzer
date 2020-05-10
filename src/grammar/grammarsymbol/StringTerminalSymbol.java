package grammar.grammarsymbol;

public class StringTerminalSymbol implements TerminalSymbol{

	private String symbol;

	/**
	 * @param symbol
	 */
	public StringTerminalSymbol(String symbol) {
		super();
		this.symbol = symbol;
	}

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	@Override
	public String toString() {
		return symbol;
	}

	@Override
	public String getLexeme() {
		return symbol;
	}

	@Override
	public boolean hasLexeme() {
		return true;
	}

	@Override
	public String getName() {
		return toString();
	}
	
	

}
