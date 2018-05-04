package studio9;

import java.util.Iterator;

public class SinglyLinkedListOfInts implements IntList {
	
	// This form of a linked-list has-a head (Will B in class)
	
	private ListItem head;
	
	// Eclipse did not generate the following, but we need a
	//  a constructor:
	
	public SinglyLinkedListOfInts() {
		// for this implementation...
		this.head = null;   // I say this explicitly to be clear
		
	}
	
	// Eclipse did generate stubs for what follows

	@Override
	public void addFirst(int item) {
		// This unfortunately has a special case
		ListItem node = new ListItem(item, null, null);
		
		if (this.size()== 0) {
			//   If the list is empty, just have head point to the
			//   new item
			this.head = node;
		}
		else {
			// insert at the beginning
			
			// change what node points to, from null,
			//   to whatever our head points to
			node.setNext(this.head);
			this.head = node;
		}
		// FIXME Auto-generated method stub
		this.head = node;

	}

	@Override
	public void addLast(int item) {
		
		if (this.size() == 0) {
			// then treat this as addFirst since it's the same thing
			this.addFirst(item);
			return;  // we are done
		}
		ListItem node = new ListItem(item, null, null);
		// Find the item whose next is null
		// and insert after that item
		ListItem p = this.head;
		while (p.getNext() != null) {
			p = p.getNext();
		}
		//
		// If the code reaches this point, p.getNext() == null
		//
		// insert node after p
		p.setNext(node);

	}

	@Override
	public int size() {
		int ans = 0;
		for (ListItem p = this.head; p != null; p = p.getNext()) {
			ans = ans + 1;
		}
		return ans;
	}

	@Override
	public int indexOf(int item) {
		int ans = 0;
		for (ListItem p = this.head; p != null; p = p.getNext()) {
			if (p.getValue() == item)
				return ans;
			++ans;
		}
		return -1;
	}

	@Override
	public boolean remove(int item) {
		// 3 cases
		// 1. empty list
		if (this.isEmpty())
			return false;
		
		// 2. the thing I want to remove happens to be the first
		//    thing in  the list
		
		// the list must be nonempty
		
		if (this.head.getValue() == item) {
			// make the head point to the next thing;
			this.head = this.head.getNext();
			return true;
		}
		
		ListItem p = this.head;
		while (p.getNext() != null) {
			// is the next item what I want?
			if (p.getNext().getValue() == item) {
				// get rid of it
				// p points around what it currently points to
				p.setNext(p.getNext().getNext());
				return true;
			}
			p = p.getNext();
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}
	
	public String toString() {
		String ans = "[";
		// put the stuff in
		// this could be a "while" loop but let's use for
		//
		for (ListItem p = this.head; p != null; p = p.getNext()) {
			ans = ans + p.getValue() + " ";
		}
		ans = ans + "]";
		return ans;
	}
	
	public static void main(String[] args) {
		SinglyLinkedListOfInts list = new SinglyLinkedListOfInts();
		list.addLast(666); System.out.println(list);
		list.addFirst(3);  System.out.println(list);
		list.addFirst(10);
		System.out.println(list);
		SinglyLinkedListOfInts list2 = new SinglyLinkedListOfInts();
		for (int i=0; i < 1000; ++i) {
			if (Math.random() < 0.5)
				list2.addLast(i);
			else 
				list2.addFirst(i);
		}
		System.out.println(list2);
		SinglyLinkedListOfInts list3 = new SinglyLinkedListOfInts();
		list3.addLast(1);  list3.addLast(2); list3.addLast(3);
		System.out.println(list3);
		list3.remove(1);
		System.out.println(list3);
		list3.remove(3);
		System.out.println(list3);
		list3.remove(4);
		System.out.println(list3);
		list3.remove(2);
		System.out.println(list3);
	}

	@Override
	public Iterator<Integer> iterator() {
		// FIXME Auto-generated method stub
		return null;
	}

}
