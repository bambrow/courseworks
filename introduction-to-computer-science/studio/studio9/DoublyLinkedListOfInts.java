package studio9;

import java.util.Iterator;

import exercises9.ListNode;

public class DoublyLinkedListOfInts implements IntList {

	private ListItem head;
	private ListItem tail;
	private int size;
	
	public DoublyLinkedListOfInts() {
		this.head = new ListItem(0, null, null);
		this.tail = new ListItem(0, null, null);
		this.head.setNext(tail);
		this.tail.setPrev(head);
		this.size = 0;
	}
	
	@Override
	public String toString() {
		String ans = "[";
		for (ListItem p = head.getNext(); p != tail; p = p.getNext()){
			ans += p.getValue() + " ";
		}
		ans = ans + "]" + " Size Is:" + size();
		return ans;
	}
	
	@Override
	public Iterator<Integer> iterator() {
		return new DoublyLinkedListOfIntsIterator(this);
	}

	@Override
	public void addFirst(int item) {
		ListItem i = new ListItem(item, head.getNext(), head);
		head.setNext(i);
		i.getNext().setPrev(i);
		size++;
	}

	@Override
	public void addLast(int item) {
		ListItem i = new ListItem(item, tail, tail.getPrev());
		tail.setPrev(i);
		i.getPrev().setNext(i);
		size++;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public int indexOf(int item) {
		int i = 0;
		for (ListItem p = head.getNext(); p != tail; p = p.getNext()) {
			if (item == p.getValue()) {
				return i;
			}
			i++;
		}
		return -1;
	}

	@Override
	public boolean remove(int item) {
		for (ListItem p = head.getNext(); p != tail; p = p.getNext()) {
			if (item == p.getValue()) {
				p.getPrev().setNext(p.getNext());
				p.getNext().setPrev(p.getPrev());
				p.setNext(null);
				p.setPrev(null);
				size--;
				return true;
			}
		}
		return false;
	}
	
	public IntList reverse() {
		IntList newList = new DoublyLinkedListOfInts();
		for (ListItem p = tail.getPrev(); p != head; p = p.getPrev()) {
			newList.addFirst(p.getValue());
		}
		return newList;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	public ListItem getHead() {
		return head;
	}

	public ListItem getTail() {
		return tail;
	}

}
