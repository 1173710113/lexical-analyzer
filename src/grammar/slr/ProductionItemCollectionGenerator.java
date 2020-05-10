package grammar.slr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import grammar.grammarsymbol.EndTerminalSymbol;
import grammar.grammarsymbol.GrammarSymbol;
import grammar.grammarsymbol.NonterminalSymbol;
import grammar.grammarsymbol.StringNonterminalSymbol;
import grammar.grammarsymbol.TerminalSymbol;
import grammar.predictinganalysis.First;
import grammar.production.Production;
import grammar.production.ProductionItem;
import grammar.production.ProductionItemSet;
import grammar.slr.strategy.GotoStrategy;
import grammar.slr.strategy.ReduceStrategy;
import grammar.slr.strategy.ShiftInStartegy;
import grammar.slr.strategy.Strategy;
import lexer.token.Token;

public class ProductionItemCollectionGenerator {

	private Map<NonterminalSymbol, Set<TerminalSymbol>> firstMap;
	private Map<NonterminalSymbol, Set<TerminalSymbol>> followMap;
	private Map<Set<ProductionItem>, Map<TerminalSymbol, Strategy>> actionMap;
	private Map<Set<ProductionItem>, Map<NonterminalSymbol, Strategy>> gotoMap;

	/**
	 * @param productionItemSets
	 */
	public ProductionItemCollectionGenerator(Set<ProductionItemSet> productionItemSets,
			Map<NonterminalSymbol, Set<TerminalSymbol>> firstMap,
			Map<NonterminalSymbol, Set<TerminalSymbol>> followMap) {
		super();
		this.productionItemSets = productionItemSets;
		this.followMap = followMap;
		this.firstMap = firstMap;
		actionMap = new LinkedHashMap<Set<ProductionItem>, Map<TerminalSymbol, Strategy>>();
		gotoMap = new LinkedHashMap<Set<ProductionItem>, Map<NonterminalSymbol, Strategy>>();
	}

	public void getCollection() {
		ProductionItem productionItem = productionItemSets.iterator().next().getProductionItems().iterator().next();
		productionItem.setLookahead(EndTerminalSymbol.getInstance());
		Set<ProductionItem> productionItems = new LinkedHashSet<ProductionItem>();
		productionItems.add(productionItem);
		Set<ProductionItem> closure = getClosure(productionItems);
		Set<Set<ProductionItem>> closureSet = new LinkedHashSet<Set<ProductionItem>>();
		Queue<Set<ProductionItem>> queue = new LinkedList<>();
		closureSet.add(closure);
		queue.offer(closure);
		actionMap.put(closure, new LinkedHashMap<TerminalSymbol, Strategy>());
		gotoMap.put(closure, new LinkedHashMap<NonterminalSymbol, Strategy>());
		while (!queue.isEmpty()) {
			closure = queue.poll();
			for (ProductionItem closureProductionItem : closure) {
				if (closureProductionItem.hasSubItem()) {
					GrammarSymbol grammarSymbol = closureProductionItem.getFirstGrammarSymbolAfterDot();
					Set<ProductionItem> productionItemsWithSameSubGrammarSymbol = getProductionItemsWithSameSubGrammarSymbol(
							grammarSymbol, closure);
					Set<ProductionItem> subClosure = getClosure(productionItemsWithSameSubGrammarSymbol);
					if (!closureSet.contains(subClosure)) {
						closureSet.add(subClosure);
						queue.offer(subClosure);
						actionMap.put(subClosure, new LinkedHashMap<TerminalSymbol, Strategy>());
						gotoMap.put(subClosure, new LinkedHashMap<NonterminalSymbol, Strategy>());
					}
					GrammarSymbol firstGrammarSymbolAfterDot = closureProductionItem.getFirstGrammarSymbolAfterDot();
					if (firstGrammarSymbolAfterDot instanceof NonterminalSymbol) {
						gotoMap.get(closure).put((NonterminalSymbol) firstGrammarSymbolAfterDot,
								new GotoStrategy(subClosure));
					} else {
						TerminalSymbol terminalSymbol = (TerminalSymbol) firstGrammarSymbolAfterDot;
						if (actionMap.get(closure).containsKey(terminalSymbol)) {
							// TODO 移入规约冲突
							System.out.println(closure);
						} else {
							actionMap.get(closure).put(terminalSymbol, new ShiftInStartegy(terminalSymbol, subClosure));
						}
					}
				} else {
					// 规约项目
					Production production = new Production(new StringNonterminalSymbol("E"), Arrays.asList(
							new StringNonterminalSymbol("E"), new Token('+'), new StringNonterminalSymbol("E")));
					if (!production.equals(productionItem.getPruduction())) {
						TerminalSymbol terminalSymbol = closureProductionItem.getLookahead();
						actionMap.get(closure).put(terminalSymbol,
								new ReduceStrategy(closureProductionItem.getPruduction()));
					}
				}
			}
		}
		hashError(closureSet);
	}

	private void hashError(Set<Set<ProductionItem>> closureSet) {
		for (Set<ProductionItem> i : closureSet) {
			boolean isError = false;
			Set<TerminalSymbol> terminalSymbols = new LinkedHashSet<TerminalSymbol>();
			for (ProductionItem j : i) {
				if (j.isReducedItem()) {
					terminalSymbols.add(j.getLookahead());

				} else if (j.isShiftInItem()) {
					TerminalSymbol terminalSymbol = (TerminalSymbol) j.getFirstGrammarSymbolAfterDot();
					if (terminalSymbols.contains(terminalSymbol)) {
						isError = true;
						break;
					}
				}
			}
			if (isError) {
				//System.out.println(i);
			}
		}
	}

	private Set<ProductionItem> getProductionItemsWithSameSubGrammarSymbol(GrammarSymbol grammarSymbol,
			Set<ProductionItem> productionItems) {
		Set<ProductionItem> productionItemsWithSameSubGrammarSymbol = new LinkedHashSet<ProductionItem>();
		for (ProductionItem productionItem : productionItems) {
			if (productionItem.hasSubItem()) {
				if (productionItem.getGrammarSymbolsAfterDot().get(0).equals(grammarSymbol)) {
					productionItemsWithSameSubGrammarSymbol.add(productionItem.getSubItem());
				}
			}
		}
		return productionItemsWithSameSubGrammarSymbol;
	}

	public Set<ProductionItem> getClosure(Set<ProductionItem> productionItems) {
		Queue<ProductionItem> queue = new LinkedList<ProductionItem>();
		for (ProductionItem productionItem : productionItems) {
			queue.offer(productionItem);
		}
		while (!queue.isEmpty()) {
			ProductionItem productionItem = queue.poll();
			List<GrammarSymbol> grammarSymbols = productionItem.getGrammarSymbolsAfterDot();
			if (grammarSymbols.size() > 0 && grammarSymbols.get(0) instanceof NonterminalSymbol) {
				Set<ProductionItem> equalProductionItems = getEqualProductionItems(productionItem);
				for (ProductionItem equalProductionItem : equalProductionItems) {
					if (!productionItems.contains(equalProductionItem)) {
						productionItems.add(equalProductionItem);
						queue.offer(equalProductionItem);
					}
				}
			}
		}
		return productionItems;
	}

	/**
	 * 求所有满足B->lamda
	 * 
	 * @param productionItem 形如A->α·Bβ,a的项
	 * @return
	 */
	public Set<ProductionItem> getEqualProductionItems(ProductionItem productionItem) {
		NonterminalSymbol nonterminalSymbol = (NonterminalSymbol) productionItem.getFirstGrammarSymbolAfterDot();
		List<GrammarSymbol> grammarSymbols = new ArrayList<GrammarSymbol>();
		grammarSymbols.addAll(productionItem.getGrammarSymbolsAfterDot().subList(1,
				productionItem.getGrammarSymbolsAfterDot().size()));
		grammarSymbols.add(productionItem.getLookahead());
		Set<ProductionItem> productionItems = new LinkedHashSet<ProductionItem>();
		// 找到产生式左部为B的
		Set<ProductionItemSet> productionItemSetsWithSpecificNonterminalSymbol = findAllProductionItemSetWith(
				nonterminalSymbol);
		for (ProductionItemSet productionItemSet : productionItemSetsWithSpecificNonterminalSymbol) {
			ProductionItem equalProductionItem = productionItemSet.getProductionItems().get(0);
			Set<TerminalSymbol> firstSet = First.getGrammarSymbolsFirstSet(grammarSymbols, firstMap);
			for (TerminalSymbol terminalSymbol : firstSet) {
				ProductionItem item = new ProductionItem(nonterminalSymbol,
						equalProductionItem.getGrammarSymbolsBeforeDot(),
						equalProductionItem.getGrammarSymbolsAfterDot()).setLookahead(terminalSymbol);
				productionItems.add(item);
			}
		}
		return productionItems;
	}

	private Set<ProductionItemSet> findAllProductionItemSetWith(NonterminalSymbol nonterminalSymbol) {
		Set<ProductionItemSet> set = new LinkedHashSet<>();
		for (ProductionItemSet productionItemSet : productionItemSets) {
			Production production = productionItemSet.getProduction();
			if (nonterminalSymbol.equals(production.getNonterminalSymbol())) {
				set.add(productionItemSet);
			}
		}
		return set;
	}
}
