package util.readhead;

public interface ReadHead {

	
	/**
	 * 
	 * @return
	 */
	public Character nextChar();
	
	/**
	 * 
	 * @return
	 */
	public boolean hasNextChar();
	
	
	/**
	 * 
	 * @param stepSize
	 */
	public void rollBack(int stepSize);
	
	/**
	 * 
	 */
	public void skipBlank();
	
}
