package grammar.slr;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import grammar.grammarsymbol.GrammarSymbol;
import grammar.production.ProductionItem;

public class ProductionItemCollection {

	private Set<ProductionItem> productionItems;
	private Map<GrammarSymbol, ProductionItemCollection> map;

	/**
	 * @param productionItems
	 */
	public ProductionItemCollection(Set<ProductionItem> productionItems) {
		super();
		this.productionItems = productionItems;
		this.map = new HashMap<GrammarSymbol, ProductionItemCollection>();
	}
	
	public void addChild(GrammarSymbol grammarSymbol, Set<ProductionItem> productionItems) {
		if(map.containsKey(grammarSymbol)) {
			map.get(grammarSymbol).getProductionItems().addAll(productionItems);
		}else {
			map.put(grammarSymbol, new ProductionItemCollection(productionItems));
		}
	}
	
	public Set<ProductionItemCollection> getChildProductionItems(){
		return new LinkedHashSet<>(map.values());
	}

	/**
	 * @return the productionItems
	 */
	public Set<ProductionItem> getProductionItems() {
		return productionItems;
	}

	/**
	 * @return the map
	 */
	public Map<GrammarSymbol, ProductionItemCollection> getMap() {
		return map;
	}

	/**
	 * @param productionItems the productionItems to set
	 */
	public void setProductionItems(Set<ProductionItem> productionItems) {
		this.productionItems = productionItems;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(Map<GrammarSymbol, ProductionItemCollection> map) {
		this.map = map;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((map == null) ? 0 : map.hashCode());
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
		ProductionItemCollection other = (ProductionItemCollection) obj;
		if (map == null) {
			if (other.map != null)
				return false;
		} else if (!map.equals(other.map))
			return false;
		if (productionItems == null) {
			if (other.productionItems != null)
				return false;
		} else if (!productionItems.equals(other.productionItems))
			return false;
		return true;
	}
	
	

}
