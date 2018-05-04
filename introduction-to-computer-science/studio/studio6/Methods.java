package studio6;

public class Methods {

	// Your methods go below this line
	
	/**
	 * calculate the factorial of n
	 * 
	 * @param n
	 *            a positive integer
	 * @return the factorial of n
	 */
	public static int fact(int n) {
		if (n <= 0) {
			return 1;
		} else {
			return n * fact(n - 1);
		}
	}
	
	/**
	 * calculate the n-th Fibonacci number
	 * 
	 * @param n
	 *            a positive integer
	 * @return the n-th Fibonacci number
	 */
	public static int fib(int n) {
		if (n <= 1) {
			return n;
		} else {
			return fib(n - 1) + fib(n - 2);
		}
	}

	/**
	 * determine whether n is odd
	 * 
	 * @param n
	 *            a positive integer
	 * @return whether n is odd
	 */
	public static boolean isOdd(int n) {
		if (n <= 0) {
			return false;
		} else {
			return !isOdd(n - 1);
		}
	}

	/**
	 * calculate a + b
	 * 
	 * @param a
	 *            an integer
	 * @param b
	 *            a non-negative integer
	 * @return a + b
	 */
	public static int sum(int a, int b) {
		if (b <= 0) {
			return a;
		} else {
			return sum(a + 1, b - 1);
		}
	}

	/**
	 * calculate n + (n-2) + (n-4) + ...
	 * 
	 * @param n
	 *            a non-negative integer
	 * @return n + (n-2) + (n-4) + ...
	 */
	public static int sumDownBy2(int n) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			return n + sumDownBy2(n - 2);
		}
	}

	/**
	 * calculate 1 + 1/2 + 1/3 + ... + 1/n
	 * 
	 * @param n
	 *            a positive integer
	 * @return 1 + 1/2 + 1/3 + ... + 1/n
	 */
	public static double harmonicSum(int n) {
		if (n == 1) {
			return 1.0;
		} else {
			return harmonicSum(n - 1) + 1.0 / n;
		}
	}

	/**
	 * calculate a * b
	 * 
	 * @param a
	 *            an integer
	 * @param b
	 *            a non-negative integer
	 * @return a * b
	 */
	public static int mult(int a, int b) {
		if (b == 0) {
			return 0;
		} else {
			return a + mult(a, b - 1);
		}
	}

}
