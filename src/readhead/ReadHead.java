package readhead;

public interface ReadHead {

	
	/**
	 * 
	 * @return
	 */
	public Character nextChar();
	
	/**
	 * 
	 * @param stepSize
	 */
	public void rollBack(int stepSize);
	
	/**
	 * 
	 */
	public void skipBlank();
	
	/**
	 * 
	 * @return 
	 */
	public boolean isEnd();
	
	
}
