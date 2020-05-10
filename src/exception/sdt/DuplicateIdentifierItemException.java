package exception.sdt;

import sdt.identifiertable.IdentifierTableItem;

public class DuplicateIdentifierItemException extends IdentifierTableException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateIdentifierItemException(IdentifierTableItem item) {
		super("Duplicate item:" + item.toString());
	}

}
