package grammar.grammarsymbol;

import java.util.List;

public class Production {

	private NonterminalSymbol nonterminalSymbol;
	private List<GrammarSymbol> grammarSymbolList;
	/**
	 * @param nonterminalSymbol
	 * @param grammarSymbolList
	 */
	public Production(NonterminalSymbol nonterminalSymbol, List<GrammarSymbol> grammarSymbolList) {
		super();
		this.nonterminalSymbol = nonterminalSymbol;
		this.grammarSymbolList = grammarSymbolList;
	}
	/**
	 * @return the nonterminalSymbol
	 */
	public NonterminalSymbol getNonterminalSymbol() {
		return nonterminalSymbol;
	}
	/**
	 * @return the grammarSymbolList
	 */
	public List<GrammarSymbol> getGrammarSymbolList() {
		return grammarSymbolList;
	}
	/**
	 * @param nonterminalSymbol the nonterminalSymbol to set
	 */
	public void setNonterminalSymbol(NonterminalSymbol nonterminalSymbol) {
		this.nonterminalSymbol = nonterminalSymbol;
	}
	/**
	 * @param grammarSymbolList the grammarSymbolList to set
	 */
	public void setGrammarSymbolList(List<GrammarSymbol> grammarSymbolList) {
		this.grammarSymbolList = grammarSymbolList;
	}
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof Production) {
			Production production = (Production)object;
			if(production.nonterminalSymbol.equals(nonterminalSymbol) && production.grammarSymbolList.equals(grammarSymbolList)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return nonterminalSymbol.hashCode() + grammarSymbolList.hashCode();
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for(GrammarSymbol grammarSymbol : grammarSymbolList) {
			stringBuilder.append(grammarSymbol.toString());
		}
		return nonterminalSymbol.toString() + "->" + stringBuilder.toString();
	}
}
