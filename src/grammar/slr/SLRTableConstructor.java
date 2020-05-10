package grammar.slr;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import grammar.grammarsymbol.GrammarSymbol;
import grammar.production.ProductionItem;

public class SLRTableConstructor {
	
	private ClosureGenerator closureGenerator;

	public void construct(Set<ProductionItem> startProductionItems) {
		Set<ProductionItem> closure = closureGenerator.getClosure(startProductionItems);
		Set<Set<ProductionItem>> closureSet = new LinkedHashSet<Set<ProductionItem>>();
		Queue<Set<ProductionItem>> queue = new LinkedList<Set<ProductionItem>>();
		queue.offer(closure);
		closureSet.add(closure);
		while(!queue.isEmpty()) {
			closure = queue.poll();
		}
	}
	
	public Map<GrammarSymbol, Set<ProductionItem>> getProductionItem(Set<ProductionItem> productionItems){
		Set<ProductionItem> copyProductionItems = new LinkedHashSet<ProductionItem>();
		copyProductionItems.addAll(productionItems);
		Map<GrammarSymbol, Set<ProductionItem>> map = new LinkedHashMap<GrammarSymbol, Set<ProductionItem>>();
		Iterator<ProductionItem> iterator = copyProductionItems.iterator();
		while(iterator.hasNext()) {
			ProductionItem productionItem = iterator.next();
			GrammarSymbol grammarSymbol = productionItem.getFirstGrammarSymbolAfterDot();
			
		}
	}
}
