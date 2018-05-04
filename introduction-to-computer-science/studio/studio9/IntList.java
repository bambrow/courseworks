package studio9;

public interface IntList extends Iterable<Integer> {
	/**
	 * Adds the specified item to the beginning of the list
	 * @param item
	 */
	public void addFirst(int item);
	/**
	 * Adds the specified item to the end of the list
	 * @param item
	 */
	public void addLast(int item);
	
	/**
	 * How big is this IntList?
	 * @return the size as an integer
	 */
	public int size();
	
	/**
	 * Where does the item occur in this List?  Note that index 0 is the first item.
	 * @param item
	 * @return the index of the item, -1 if not found
	 */
	public int indexOf(int item);
	
	/**
	 * Get rid of the first occurrence of the specified item, if it exists in the list
	 * @param item
	 * @return true if an item was found and removed, false otherwise
	 */
	public boolean remove(int item);
	
	/**
	 * Is this IntList empty?
	 * @return true if the list is empty, false otherwise
	 */
	public boolean isEmpty();
	

}
