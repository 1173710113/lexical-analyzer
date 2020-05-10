package grammar.slr.strategy;

import java.util.Set;
import java.util.Stack;

import grammar.grammarsymbol.GrammarSymbol;
import grammar.production.ProductionItem;

public class GotoStrategy implements Strategy{

	private Set<ProductionItem> productionItems;
	
	public GotoStrategy(Set<ProductionItem> productionItems) {
		this.productionItems = productionItems;
	}
	
	
	
	
	/**
	 * @return the productionItems
	 */
	public Set<ProductionItem> getProductionItems() {
		return productionItems;
	}




	/**
	 * @param productionItems the productionItems to set
	 */
	public void setProductionItems(Set<ProductionItem> productionItems) {
		this.productionItems = productionItems;
	}




	@Override
	public void execute(Stack<Set<ProductionItem>> stateStack, Stack<GrammarSymbol> grammarSymbolStack) {
		stateStack.add(productionItems);
		
	}

}
