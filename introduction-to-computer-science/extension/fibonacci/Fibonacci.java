package fibonacci;

public class Fibonacci {
	
	/**
	 * Below copy your solution to recursive Fibonacci from studio
	 * @param n
	 * @return fib(n), computed recursively
	 */
	public static int recursive(int n) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			return recursive(n - 1) + recursive(n - 2);
		}
	}
	
	/**
	 * Below write your solution to Fibonacci, using iteration
	 * @param n
	 * @return fib(n), computed iteratively
	 */
	public static int iterative(int n) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			int below2 = 0;
			int below1 = 1;
			int fib = 0;
			for (int i = 2; i <= n; i++) {
				fib = below2 + below1;
				below2 = below1;
				below1 = fib;
			}
			return fib;
		}

	}

}
