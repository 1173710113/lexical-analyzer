package grammar.grammarsymbol;

public class NonterminalSymbolSyn implements GrammarSymbol {

	private NonterminalSymbol nonterminalSymbol;

	public NonterminalSymbolSyn(NonterminalSymbol nonterminalSymbol) {
		this.nonterminalSymbol = nonterminalSymbol;
	}

	/**
	 * @return the nonterminalSymbol
	 */
	public NonterminalSymbol getNonterminalSymbol() {
		return nonterminalSymbol;
	}

	/**
	 * @param nonterminalSymbol the nonterminalSymbol to set
	 */
	public void setNonterminalSymbol(NonterminalSymbol nonterminalSymbol) {
		this.nonterminalSymbol = nonterminalSymbol;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nonterminalSymbol == null) ? 0 : nonterminalSymbol.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NonterminalSymbolSyn other = (NonterminalSymbolSyn) obj;
		if (nonterminalSymbol == null) {
			if (other.nonterminalSymbol != null)
				return false;
		} else if (!nonterminalSymbol.equals(other.nonterminalSymbol))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nonterminalSymbol.toString();
	}

}
