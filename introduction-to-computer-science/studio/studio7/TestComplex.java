package studio7;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestComplex {

	@Test
	public void testAdd() {
		Complex c1 = new Complex(3, 4);
		Complex c2 = new Complex(1, -1);
		Complex c3 = c1.add(c2);
		assertTrue(c3.equals(new Complex(4, 3)));
	}

	@Test
	public void testMultiply() {
		Complex c1 = new Complex(3, 4);
		Complex c2 = new Complex(1, -1);
		Complex c3 = c1.multiply(c2);
		assertTrue(c3.equals(new Complex(7, 1)));
	}

}
