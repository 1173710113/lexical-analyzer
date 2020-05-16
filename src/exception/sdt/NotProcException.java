package exception.sdt;

public class NotProcException extends SDTException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotProcException(Object id) {
		super(id  + " is not proc");
		// TODO Auto-generated constructor stub
	}

}
