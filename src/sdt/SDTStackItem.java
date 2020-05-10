package sdt;

import java.util.HashMap;
import java.util.Map;

import grammar.grammarsymbol.GrammarSymbol;

public class SDTStackItem {

	private GrammarSymbol grammarSymbol;
	private Map<String, Object> valueMap;

	public SDTStackItem(GrammarSymbol grammarSymbol) {
		this.grammarSymbol = grammarSymbol;
		this.valueMap = new HashMap<String, Object>();
	}

	public void addValue(String key, Object value) {
		if (!valueMap.containsKey(key)) {
			valueMap.put(key, value);
		}else {
			valueMap.put(key+ "'", value);
		}
	}

	public Object getValue(String key) {
		return valueMap.get(key);
	}

	/**
	 * @return the grammarSymbol
	 */
	public GrammarSymbol getGrammarSymbol() {
		return grammarSymbol;
	}

	/**
	 * @return the valueMap
	 */
	public Map<String, Object> getValueMap() {
		return valueMap;
	}

	/**
	 * @param grammarSymbol the grammarSymbol to set
	 */
	public void setGrammarSymbol(GrammarSymbol grammarSymbol) {
		this.grammarSymbol = grammarSymbol;
	}

	/**
	 * @param valueMap the valueMap to set
	 */
	public void setValueMap(Map<String, Object> valueMap) {
		this.valueMap = valueMap;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((grammarSymbol == null) ? 0 : grammarSymbol.hashCode());
		result = prime * result + ((valueMap == null) ? 0 : valueMap.hashCode());
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
		SDTStackItem other = (SDTStackItem) obj;
		if (grammarSymbol == null) {
			if (other.grammarSymbol != null)
				return false;
		} else if (!grammarSymbol.equals(other.grammarSymbol))
			return false;
		if (valueMap == null) {
			if (other.valueMap != null)
				return false;
		} else if (!valueMap.equals(other.valueMap))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SDTStackItem [grammarSymbol=" + grammarSymbol + ", valueMap=" + valueMap + "]";
	}

}
