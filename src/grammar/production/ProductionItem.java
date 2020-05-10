package grammar.production;

import java.util.ArrayList;
import java.util.List;

import grammar.grammarsymbol.GrammarSymbol;
import grammar.grammarsymbol.NonterminalSymbol;
import grammar.grammarsymbol.TerminalSymbol;

public class ProductionItem {

	protected NonterminalSymbol nonterminalSymbol;
	protected List<GrammarSymbol> grammarSymbolsBeforeDot;
	protected List<GrammarSymbol> grammarSymbolsAfterDot;


	/**
	 * @param nonterminalSymbol
	 * @param grammarSymbolsBeforeDot
	 * @param grammarSymbolsAfterDot
	 */
	public ProductionItem(NonterminalSymbol nonterminalSymbol, List<GrammarSymbol> grammarSymbolsBeforeDot,
			List<GrammarSymbol> grammarSymbolsAfterDot) {
		super();
		this.nonterminalSymbol = nonterminalSymbol;
		this.grammarSymbolsBeforeDot = grammarSymbolsBeforeDot;
		this.grammarSymbolsAfterDot = grammarSymbolsAfterDot;
	}

	/**
	 * @return the nonterminalSymbol
	 */
	public NonterminalSymbol getNonterminalSymbol() {
		return nonterminalSymbol;
	}

	/**
	 * @return the grammarSymbolsBeforeDot
	 */
	public List<GrammarSymbol> getGrammarSymbolsBeforeDot() {
		return grammarSymbolsBeforeDot;
	}

	/**
	 * @return the grammarSymbolsAfterDot
	 */
	public List<GrammarSymbol> getGrammarSymbolsAfterDot() {
		return grammarSymbolsAfterDot;
	}


	/**
	 * @param nonterminalSymbol the nonterminalSymbol to set
	 */
	public void setNonterminalSymbol(NonterminalSymbol nonterminalSymbol) {
		this.nonterminalSymbol = nonterminalSymbol;
	}

	/**
	 * @param grammarSymbolsBeforeDot the grammarSymbolsBeforeDot to set
	 */
	public void setGrammarSymbolsBeforeDot(List<GrammarSymbol> grammarSymbolsBeforeDot) {
		this.grammarSymbolsBeforeDot = grammarSymbolsBeforeDot;
	}

	/**
	 * @param grammarSymbolsAfterDot the grammarSymbolsAfterDot to set
	 */
	public void setGrammarSymbolsAfterDot(List<GrammarSymbol> grammarSymbolsAfterDot) {
		this.grammarSymbolsAfterDot = grammarSymbolsAfterDot;
	}
	
	public static ProductionItem getProductionFirstItem(Production production) {
		NonterminalSymbol nonterminalSymbol = production.getNonterminalSymbol();
		List<GrammarSymbol> grammarSymbolsBeforeDot = new ArrayList<GrammarSymbol>();
		List<GrammarSymbol> grammarSymbolsAfterDot = new ArrayList<GrammarSymbol>();
		grammarSymbolsAfterDot.addAll(production.getGrammarSymbolList());
		return new ProductionItem(nonterminalSymbol, grammarSymbolsBeforeDot, grammarSymbolsAfterDot);
	}

	public boolean hasSubItem() {
		return grammarSymbolsAfterDot.size() > 0;
	}

	public ProductionItem getSubItem() {
		List<GrammarSymbol> subItemGrammarSymbolsBeforeDot = new ArrayList<GrammarSymbol>();
		subItemGrammarSymbolsBeforeDot.addAll(grammarSymbolsBeforeDot);
		subItemGrammarSymbolsBeforeDot.add(grammarSymbolsAfterDot.get(0));
		List<GrammarSymbol> subItemGrammarSymbolsAfterDot = new ArrayList<GrammarSymbol>();
		int grammarSymbolsAfterDotSize = grammarSymbolsAfterDot.size();
		for (int i = 1; i < grammarSymbolsAfterDotSize; i++) {
			subItemGrammarSymbolsAfterDot.add(grammarSymbolsAfterDot.get(i));
		}
		ProductionItem subProductionItem = new ProductionItem(nonterminalSymbol, subItemGrammarSymbolsBeforeDot, subItemGrammarSymbolsAfterDot);
		return subProductionItem;
	}

	public boolean isReducedItem() {
		return grammarSymbolsAfterDot.size() == 0;
	}

	public boolean isShiftInItem() {
		return grammarSymbolsAfterDot.get(0) instanceof TerminalSymbol;
	}

	public GrammarSymbol getFirstGrammarSymbolAfterDot() {
		return grammarSymbolsAfterDot.get(0);
	}
	
	public Production getPruduction() {
		List<GrammarSymbol> grammarSymbols = new ArrayList<GrammarSymbol>();
		grammarSymbols.addAll(grammarSymbolsBeforeDot);
		grammarSymbols.addAll(grammarSymbolsAfterDot);
		return new Production(nonterminalSymbol, grammarSymbols);
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(nonterminalSymbol.toString() + "->");
		for (GrammarSymbol grammarSymbol : grammarSymbolsBeforeDot) {
			stringBuilder.append(grammarSymbol.toString());
		}
		stringBuilder.append("¡¤");
		for (GrammarSymbol grammarSymbol : grammarSymbolsAfterDot) {
			stringBuilder.append(grammarSymbol.toString());
		}
		return stringBuilder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((grammarSymbolsAfterDot == null) ? 0 : grammarSymbolsAfterDot.hashCode());
		result = prime * result + ((grammarSymbolsBeforeDot == null) ? 0 : grammarSymbolsBeforeDot.hashCode());
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
		ProductionItem other = (ProductionItem) obj;
		if (grammarSymbolsAfterDot == null) {
			if (other.grammarSymbolsAfterDot != null)
				return false;
		} else if (!grammarSymbolsAfterDot.equals(other.grammarSymbolsAfterDot))
			return false;
		if (grammarSymbolsBeforeDot == null) {
			if (other.grammarSymbolsBeforeDot != null)
				return false;
		} else if (!grammarSymbolsBeforeDot.equals(other.grammarSymbolsBeforeDot))
			return false;
		if (nonterminalSymbol == null) {
			if (other.nonterminalSymbol != null)
				return false;
		} else if (!nonterminalSymbol.equals(other.nonterminalSymbol))
			return false;
		return true;
	}

	
}
