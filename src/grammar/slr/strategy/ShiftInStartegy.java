package grammar.slr.strategy;

import java.util.Set;
import java.util.Stack;

import grammar.grammarsymbol.GrammarSymbol;
import grammar.grammarsymbol.TerminalSymbol;
import grammar.production.ProductionItem;

public class ShiftInStartegy implements Strategy {

	private TerminalSymbol terminalSymbol;
	private Set<ProductionItem> productionItems;

	/**
	 * @param terminalSymbol
	 * @param productionItems
	 */
	public ShiftInStartegy(TerminalSymbol terminalSymbol, Set<ProductionItem> productionItems) {
		super();
		this.terminalSymbol = terminalSymbol;
		this.productionItems = productionItems;
	}
	
	

	/**
	 * @return the terminalSymbol
	 */
	public TerminalSymbol getTerminalSymbol() {
		return terminalSymbol;
	}



	/**
	 * @return the productionItems
	 */
	public Set<ProductionItem> getProductionItems() {
		return productionItems;
	}



	/**
	 * @param terminalSymbol the terminalSymbol to set
	 */
	public void setTerminalSymbol(TerminalSymbol terminalSymbol) {
		this.terminalSymbol = terminalSymbol;
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
		grammarSymbolStack.add(terminalSymbol);

	}

}
