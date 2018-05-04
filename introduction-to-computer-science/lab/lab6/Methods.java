package lab6;

public class Methods {
	
	//
	// In this class, implement the f and g functions described on the lab page
	//

	/**
	 * Compute the McCarthy 91 function.
	 * 
	 * @param x
	 *            an integer
	 * @return the result of McCarthy 91 function
	 */
	public static int f(int x) {
		if (x > 100) {
			return x - 10;
		} else {
			return f(f(x + 11));
		}
	}

	/**
	 * Compute the Ackermann function.
	 * 
	 * @param x
	 *            a positive integer
	 * @param y
	 *            a positive integer
	 * @return the result of Ackermann function
	 */
	public static int g(int x, int y) {
		assert x >= 0 && y >= 0;
		if (x == 0) {
			return y + 1;
		} else if (x > 0 && y == 0) {
			return g(x - 1, 1);
		} else if (x > 0 && y > 0) {
			return g(x - 1, g(x, y - 1));
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//
		// from here, call f or g with the appropriate parameters
		//

		System.out.println("f():");
		for (int i = -9; i <= 110; i++) {
			System.out.print("f(" + i + "): " + f(i) + "\t");
			if (i % 5 == 0) {
				System.out.println();
			}
		}

		System.out.println("\ng():");
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print("g(" + i + "," + j + "): " + g(i, j) + "\t");
			}
			System.out.println();
		}
		
		for (int j = 0; j < 5; j++) {
			System.out.print("g(4," + j + "): " + g(4, j) + "\t");
			// this will cause StackOverflowError
		}
		System.out.println();
		
	}

}
