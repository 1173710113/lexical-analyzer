package sdt.identifiertable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import exception.sdt.DuplicateIdentifierItemException;
import exception.sdt.NoIdentifierTableItemException;

public class IdentifierTable {

	private Map<String, IdentifierTableItem> map;
	
	public IdentifierTable() {
		map = new LinkedHashMap<String, IdentifierTableItem>();
	}
	
	public IdentifierTableItem findItem(String id) throws NoIdentifierTableItemException {
		if(!map.containsKey(id))throw new NoIdentifierTableItemException(id);
		return map.get(id);
	}
	
	public void addItem(IdentifierTableItem item) throws DuplicateIdentifierItemException {
		if(map.containsKey(item.getId())) throw new DuplicateIdentifierItemException(item);
		map.put(item.getId(), item);
		System.out.println(item.toString());
	}

	public List<IdentifierTableItem> getTableItems(){
		List<IdentifierTableItem> list = new ArrayList<IdentifierTableItem>();
		for(Map.Entry<String, IdentifierTableItem> entry : map.entrySet()) {
			list.add(entry.getValue());
		}
		return list;
	}
	
	/**
	 * @return the map
	 */
	public Map<String, IdentifierTableItem> getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(Map<String, IdentifierTableItem> map) {
		this.map = map;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((map == null) ? 0 : map.hashCode());
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
		IdentifierTable other = (IdentifierTable) obj;
		if (map == null) {
			if (other.map != null)
				return false;
		} else if (!map.equals(other.map))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IdentifierTable [map=" + map + "]";
	}
	
	
}
