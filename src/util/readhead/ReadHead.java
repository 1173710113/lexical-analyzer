package util.readhead;

public interface ReadHead<T> {

	
	/**
	 * 
	 * @return
	 */
	public T next();
	
	/**
	 * 
	 * @return
	 */
	public boolean hasNext();
	
	
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
