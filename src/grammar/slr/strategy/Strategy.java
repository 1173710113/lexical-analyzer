package grammar.slr.strategy;

import java.util.Set;
import java.util.Stack;

import grammar.grammarsymbol.GrammarSymbol;
import grammar.production.ProductionItem;

public interface Strategy {

	public void execute(Stack<Set<ProductionItem>> stateStack, Stack<GrammarSymbol> grammarSymbolStack);
}
