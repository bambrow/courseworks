package studio9;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedListOfIntsIterator implements Iterator<Integer> {

	private ListItem current;
	private ListItem tail;
	
	public DoublyLinkedListOfIntsIterator(DoublyLinkedListOfInts d) {
		current = d.getHead();
		tail = d.getTail();
	}
	
	@Override
	public boolean hasNext() {
		return current.getNext() != tail;
	}

	@Override
	public Integer next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		current = current.getNext();
		return new Integer(current.getValue());
	}
	
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	
}
