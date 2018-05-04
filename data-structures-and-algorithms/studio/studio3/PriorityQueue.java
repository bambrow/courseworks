package studio3;

public interface PriorityQueue<T extends Comparable<T>> {
	
	public boolean isEmpty();
	public void insert(T thing);
	public T extractMin();

}
