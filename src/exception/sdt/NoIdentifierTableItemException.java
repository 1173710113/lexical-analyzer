package exception.sdt;

public class NoIdentifierTableItemException extends IdentifierTableException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoIdentifierTableItemException(String id) {
		super(id + " not found");
	}

}
