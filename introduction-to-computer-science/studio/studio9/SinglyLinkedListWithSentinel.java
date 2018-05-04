package studio9;

import java.util.Iterator;

public class SinglyLinkedListWithSentinel implements IntList {

	private ListItem head;
	
	public SinglyLinkedListWithSentinel() {
		//
		// This list item is in the list, always as the list's first element
		//    but it serves only to get rid of special cases
		//    as you will see in the code below
		// The 666666 value is there because we have to specify some int
		// It plays the role of the "Joker" card I used at the beginning of
		//    this studio
		ListItem sentinel = new ListItem(666666, null, null);
		
		this.head = sentinel;
	}

	@Override
	public void addFirst(int item) {
		// no special case thanks to the sentinel
		ListItem newbie = new ListItem(item, this.head.getNext(), null);
		this.head.setNext(newbie);
	}

	@Override
	public void addLast(int item) {
		// no special case thanks to sentinel
		ListItem p = this.head;
		while (p.getNext() != null) {
			p = p.getNext();
		}
		
		//
		// p must point to a ListItem whose next is null, so it's the
		//   last item in the list
		
		p.setNext(new ListItem(item,null,null));

	}

	/**
	 * Like in class, but we start one past the sentinel
	 * @return
	 */
	@Override
	public int size() {
		int ans = 0;
		for (ListItem p = this.head.getNext(); p != null; p = p.getNext()) {
			ans = ans + 1;
		}
		return ans;
	}

	/**
	 * Like in class, but we start one past sentinel
	 * @param item
	 * @return
	 */
	@Override
	public int indexOf(int item) {
		int ans = 0;
		for (ListItem p = this.head.getNext(); p != null; p = p.getNext()) {
			if (p.getValue() == item)
				return ans;
			++ans;
		}
		return -1;
	}

	@Override
	public boolean remove(int item) {
		// no special cases, but we still have to look to the right.
		// special cases are gone because the list always has something,
		//   even if it's just the sentinel
		//
		for (ListItem p = this.head; p.getNext() != null; p = p.getNext()) {
			if (p.getNext().getValue() == item) {
				// the thing we want to remove is one to the right of p
				//
				p.setNext(p.getNext().getNext());
				return true;
			}
		}
		return false;
	}

	/**
	 * Just as we did in class.
	 * @return
	 */
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}
	
	/**
	 * Like the one we did in class, but we start past the sentinel
	 * @return
	 */
	public String toString() {
		String ans = "[";
		// put the stuff in
		// this could be a "while" loop but let's use for
		//
		for (ListItem p = this.head.getNext(); p != null; p = p.getNext()) {
			ans = ans + p.getValue() + " ";
		}
		ans = ans + "]";
		return ans;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// FIXME Auto-generated method stub

	}
	
	@Override
	public Iterator<Integer> iterator() {
		// FIXME Auto-generated method stub
		return null;
	}


}
