package grammar.grammarsymbol;

public class StringNonterminalSymbol implements NonterminalSymbol {

	private String symbol;

	/**
	 * @param symbol
	 */
	public StringNonterminalSymbol(String symbol) {
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
	public boolean equals(Object object) {
		if(object instanceof StringNonterminalSymbol) {
			StringNonterminalSymbol stringNonterminalSymbol = (StringNonterminalSymbol)object;
			if(stringNonterminalSymbol.getSymbol().equals(symbol))return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return symbol.hashCode();
	}
	
	@Override
	public String toString() {
		return symbol;
	}
}
