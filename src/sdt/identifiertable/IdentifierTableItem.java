package sdt.identifiertable;

import lexer.token.Type;

public class IdentifierTableItem {

	private String id;
	private Type type;
	private int offset;

	/**
	 * @param id
	 * @param type
	 * @param offset
	 */
	public IdentifierTableItem(String id, Type type, int offset) {
		super();
		this.id = id;
		this.type = type;
		this.offset = offset;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * @param offset the offset to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		IdentifierTableItem other = (IdentifierTableItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IdentifierTableItem [id=" + id + ", type=" + type + ", offset=" + offset + "]";
	}
	
	

}
