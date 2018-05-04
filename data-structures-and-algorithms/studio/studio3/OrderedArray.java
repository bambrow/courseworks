package studio3;

public class OrderedArray<T extends Comparable<T>> implements PriorityQueue<T> {

	public T[] array;
	private int size;
	
	@SuppressWarnings("unchecked")
	public OrderedArray(int maxSize) {
		array = (T[]) new Comparable[maxSize];
		size = 0;
	}
	
	@Override
	public boolean isEmpty() {
		//
		// FIXME
		//
		return size == 0;
	}

	@Override
	public void insert(T thing) {
		//
		// FIXME
		//
		if (size == array.length) {
			return;
		}
		if (this.isEmpty()) {
			array[0] = thing;
			size++;
			return;
		}
		for (int i = 0; i < size; i++) {
			if (thing.compareTo(array[i]) < 0) {
				for (int j = size - 1; j >= i; j--) {
					array[j + 1] = array[j];
				}
				array[i] = thing;
				size++;
				return;
			}
		}
		array[size] = thing;
		size++;
		return;
	}
	
	@Override
	public T extractMin() {
		//
		// FIXME
		//
		if (this.isEmpty()) {
			return null;
		}
		T min = array[0];
		for (int i = 0; i < size; i++) {
			array[i] = array[i + 1];
		}
		size--;
		array[size] = null;
		return min;
	}

}
