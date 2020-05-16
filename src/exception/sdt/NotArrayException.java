package exception.sdt;

public class NotArrayException extends SDTException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotArrayException(Object object) {
		super(object + " is not array");
		// TODO Auto-generated constructor stub
	}

}
