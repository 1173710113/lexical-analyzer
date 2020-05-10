package grammar.slr.strategy;

import java.util.Set;
import java.util.Stack;

import grammar.grammarsymbol.GrammarSymbol;
import grammar.production.Production;
import grammar.production.ProductionItem;

public class ReduceStrategy implements Strategy{
	
	private Production production;

	/**
	 * @param production
	 */
	public ReduceStrategy(Production production) {
		super();
		this.production = production;
	}



	@Override
	public void execute(Stack<Set<ProductionItem>> stateStack, Stack<GrammarSymbol> grammarSymbolStack) {
		int size = production.getGrammarSymbolList().size();
		for(int i = 0; i < size; i++) {
			stateStack.pop();
			grammarSymbolStack.pop();
		}
		grammarSymbolStack.add(production.getNonterminalSymbol());
		
	}

}
