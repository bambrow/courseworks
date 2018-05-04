package studio9;

import static org.junit.Assert.*;

import org.junit.Test;


public class TestLinkedListOfInts {
	
	private IntList genNewList() {
		// If you wish, uncomment the line below to test the code I developed in class
		// return new SinglyLinkedListWithSentinel();
		//
		//  but be sure to test with the line below, so it runs your code, not mine!
		//
		return new DoublyLinkedListOfInts();
	}
	
	@Test
	public void basic() {
		IntList flist = genNewList();
		IntList rlist = genNewList();
		assertEquals(0, flist.size());
		//
		// flist:  0, 1, 2, ... 9
		// rlist:  9, 8, 7, ... 0
		for (int i=0; i < 10; ++i) {
			flist.addLast(i);
			rlist.addFirst(i);
		}
		assertEquals(10, flist.size());
		assertEquals(10, rlist.size());
		for (int i=0; i < 10; ++i) {
			assertEquals(i, flist.indexOf(i));
			assertEquals(9-i, rlist.indexOf(i));
		}
		assertEquals(-1, flist.indexOf(23));  // something that is not there
		for (int i=0; i < 10; ++i) {
			assertTrue(flist.remove(i));
			assertEquals(9-i, flist.size());
			assertFalse(flist.remove(i));
			assertEquals(9-i, flist.size());
		}
	}

	// Uncomment below when ready
	// @Test
	public void iterator() {
		IntList list = genNewList();
		for (int i=0; i < 10; ++i) {
			list.addLast(i);
		}
		for (Integer i : list) {
			assertEquals(i.intValue(), list.indexOf(i.intValue()));
		}
	}

	// Uncomment below when ready
	// @Test
	public void nestedIterator() {
		IntList list = genNewList();
		IntList products = genNewList();
		for (int i=0; i < 10; ++i) {
			list.addLast(i);
		}
		for (Integer i : list) {
			for (Integer j : list) {
				products.addLast(i.intValue()*10+j.intValue());
			}
		}
		assertEquals(100, products.size());
		for (int i=99; i >= 0; --i) {
			assertEquals(i, products.indexOf(i));
			assertEquals(true, products.remove(i));
			assertEquals(-1, products.indexOf(i));
		}
	}
	
	// Uncomment below when ready
	// @Test
	public void nestedIterator2() {
		
		// Two lists of prime numbers
		IntList plist1 = genNewList();
		IntList plist2 = genNewList();
		for (int i : new int[] { 2, 3, 5 }) plist1.addLast(i);
		for (int i : new int[] { 7, 11, 13, 17}) plist2.addLast(i);

		// Their product
		IntList products = genNewList();
		// and product in reverse
		IntList productsR = genNewList();
		
		
		// 
		// Test nested iterators:  compute the product each pair of ints from the two lsits
		//
		for (Integer i : plist1) {
			for (Integer j : plist2) {
				products.addLast(i.intValue() * j.intValue());
				productsR.addFirst(i.intValue() * j.intValue());
			}
		}
		
		assertEquals(12, products.size());
		assertEquals(12, productsR.size());


		//
		// because each list had primes, each product should appear exactly once
		//
		for (int i : new int[] { 2, 3, 5 }) {
			for (int j : new int[] {7, 11, 13, 17}) {
				assertEquals(0, products.indexOf(i*j));
				assertEquals(true, products.remove(i*j));
				assertEquals(-1, products.indexOf(i*j));

				assertEquals(productsR.size()-1, productsR.indexOf(i*j));
				assertEquals(true, productsR.remove(i*j));
				assertEquals(-1, productsR.indexOf(i*j));
			}
		}

	}

}
