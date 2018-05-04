package studio6;

import static org.junit.Assert.*;

import org.junit.Test;

public class Tests {

	@Test
	public void testFactorial() {
		assertEquals(1, Methods.fact(0));
		assertEquals(1, Methods.fact(1));
		assertEquals(120, Methods.fact(5));
	}
	
	@Test
	public void testFib() {
		assertEquals(0, Methods.fib(0));
		assertEquals(1, Methods.fib(1));
		assertEquals(144, Methods.fib(12));
	}
	
	@Test
	public void testOdd() {
		for (int i=0; i < 1000; ++i) {
			assertEquals("Testing " + i, i%2==1, Methods.isOdd(i));
		}
	}
	
	@Test
	public void testSum() {
		for (int a=0; a < 1000; ++a) {
			for (int b=0; b < 1000; ++b) {
				assertEquals("Testing " + a + "+" + "b", a+b, Methods.sum(a,b));
			}
		}
	}
	
	private static int mysdb2(int n) {
		int ans = 0;
		for (int i=n; i >= 0; i=i-2) {
			ans += i;
		}
		return ans;
	}

	private double myhs(int n) {
		double ans = 0.0;
		for (int i=1; i <= n; ++i) {
			ans += 1.0/i;
		}
		return ans;
	}
		
	@Test
	public void testSumDownBy2() {
		for (int n=0; n < 1000; ++n) {
			assertEquals("Value for " + n, mysdb2(n), Methods.sumDownBy2(n));
		}
	}
	
	@Test
	public void testHarmonicSum() {
		for (int i=1; i < 1000; ++i) {
			assertEquals("Value for " + i, myhs(i), Methods.harmonicSum(i), .01);
		}
	}

	@Test
	public void testMult(){
		for (int a = 1; a < 100; ++a) {
			for (int b = 1; b < 100; ++b){
				assertEquals("Testing " + a + " * " + b, a*b, Methods.mult(a, b) );
			}
		}
	}

}
