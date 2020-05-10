package grammar.slr;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import grammar.grammarsymbol.NonterminalSymbol;
import grammar.production.Production;
import grammar.production.ProductionItem;

public class ClosureGenerator {

	private Set<Production> productions;

	/**
	 * @param productions
	 */
	public ClosureGenerator(Set<Production> productions) {
		super();
		this.productions = productions;
	}

	public Set<ProductionItem> getClosure(Set<ProductionItem> productionItems) {
		Queue<ProductionItem> queue = new LinkedList<ProductionItem>();
		queue.addAll(productionItems);
		while (!queue.isEmpty()) {
			ProductionItem productionItem = queue.poll();
			if (hasEqualProductionItem(productionItem)) {
				NonterminalSymbol nonterminalSymbol = (NonterminalSymbol) productionItem
						.getFirstGrammarSymbolAfterDot();
				for (Production production : productions) {
					if (production.getNonterminalSymbol().equals(nonterminalSymbol)) {
						ProductionItem equalProductionItem = ProductionItem.getProductionFirstItem(production);
						if (!productionItems.contains(equalProductionItem)) {
							productionItems.add(equalProductionItem);
							queue.offer(equalProductionItem);
						}
					}
				}
			}
		}
		return productionItems;
	}

	private boolean hasEqualProductionItem(ProductionItem productionItem) {
		return productionItem.hasSubItem()
				&& productionItem.getFirstGrammarSymbolAfterDot() instanceof NonterminalSymbol;
	}
}
