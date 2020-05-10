package grammar.slr;

import java.util.Set;

import grammar.grammarsymbol.GrammarSymbol;
import grammar.production.ProductionItem;

public class ProductionItemGrammarSymbol {

	private Set<ProductionItem> productionItems;
	private GrammarSymbol grammarSymbol;
	
	/**
	 * @param productionItems
	 * @param grammarSymbol
	 */
	public ProductionItemGrammarSymbol(Set<ProductionItem> productionItems, GrammarSymbol grammarSymbol) {
		super();
		this.productionItems = productionItems;
		this.grammarSymbol = grammarSymbol;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((grammarSymbol == null) ? 0 : grammarSymbol.hashCode());
		result = prime * result + ((productionItems == null) ? 0 : productionItems.hashCode());
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
		ProductionItemGrammarSymbol other = (ProductionItemGrammarSymbol) obj;
		if (grammarSymbol == null) {
			if (other.grammarSymbol != null)
				return false;
		} else if (!grammarSymbol.equals(other.grammarSymbol))
			return false;
		if (productionItems == null) {
			if (other.productionItems != null)
				return false;
		} else if (!productionItems.equals(other.productionItems))
			return false;
		return true;
	}
	
	
}
