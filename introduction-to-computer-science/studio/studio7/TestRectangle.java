package studio7;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestRectangle {

	@Test
	public void testGetArea() {
		Rectangle r = new Rectangle(4, 3);
		assertEquals(4 * 3, r.getArea(), 1e-5);
	}

	@Test
	public void testGetPerimeter() {
		Rectangle r = new Rectangle(4, 3);
		assertEquals(2 * 4 + 2 * 3, r.getPerimeter(), 1e-5);
	}

	@Test
	public void testIsSquare() {
		Rectangle r1 = new Rectangle(4, 3);
		Rectangle r2 = new Rectangle(4, 4);
		assertFalse(r1.isSquare());
		assertTrue(r2.isSquare());
	}

	@Test
	public void testCompareTo() {
		Rectangle r1 = new Rectangle(4, 3);
		Rectangle r2 = new Rectangle(4, 4);
		Rectangle r3 = new Rectangle(3, 4);
		assertEquals(-1, r1.compareTo(r2));
		assertEquals(1, r2.compareTo(r3));
		assertEquals(0, r1.compareTo(r3));
	}

}
