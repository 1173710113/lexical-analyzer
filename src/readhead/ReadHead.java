package readhead;

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
	 * @param c
	 * @return
	 */
	public boolean isNextLineChar(Character c);
	
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
