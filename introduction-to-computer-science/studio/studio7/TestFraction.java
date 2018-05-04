package studio7;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestFraction {

	@Test
	public void testSimplify() {
		Fraction f = new Fraction(6, 12);
		f = f.simplify();
		assertTrue(f.equals(new Fraction(1, 2)));
	}

	@Test
	public void testTakeReciprocal() {
		Fraction f = new Fraction(6, 12);
		f = f.takeReciprocal();
		assertTrue(f.equals(new Fraction(12, 6)));
	}

	@Test
	public void testAdd() {
		Fraction f1 = new Fraction(1, 2);
		Fraction f2 = new Fraction(1, 3);
		Fraction f3 = f1.add(f2);
		assertTrue(f3.equals(new Fraction(5, 6)));
	}

	@Test
	public void testMultiply() {
		Fraction f1 = new Fraction(1, 2);
		Fraction f2 = new Fraction(1, 3);
		Fraction f3 = f1.multiply(f2);
		assertTrue(f3.equals(new Fraction(1, 6)));
	}

}
