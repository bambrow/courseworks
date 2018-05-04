package studio3;

import java.util.LinkedList;
import java.util.List;

public class UnorderedList<T extends Comparable<T>> implements PriorityQueue<T> {

	public List<T> list;
	
	public UnorderedList() {
		list = new LinkedList<T>();
	}
	
	@Override
	public boolean isEmpty() {
		//
		// FIXME
		//
		return list.isEmpty();
	}

	@Override
	public void insert(T thing) {
		//
		// FIXME
		//
		list.add(thing);
	}

	@Override
	public T extractMin() {
		//
		// FIXME
		//
		T min = null;
		for (T thing : list) {
			if (min == null) {
				min = thing;
			} else {
				if (min.compareTo(thing) > 0) {
					min = thing;
				}
			}
		}
		list.remove(min);
		return min;
	}

}
