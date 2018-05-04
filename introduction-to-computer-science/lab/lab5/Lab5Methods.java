package lab5;

public class Lab5Methods {

	/**
	 * calculate the sum of positive integers n + (n-2) + (n-4) + ...
	 * 
	 * @param n
	 *            an integer
	 * @return the sum of positive integers n + (n-2) + (n-4) + ...
	 */
	public static int sumDownBy2(int n) {
		if (n <= 0)
			return 0;
		else {
			int sum = 0;
			for (int i = n; i > 0; i -= 2)
				sum += i;
			return sum;
		}
	}

	/**
	 * calculate the sum of 1 + 1/2 + 1/3 + ... + 1/n
	 * 
	 * @param n
	 *            a positive integer
	 * @return the sum of 1 + 1/2 + 1/3 + ... + 1/n
	 */
	public static double harmonicSum(int n) {
		double sum = .0;
		for (int i = 1; i <= n; i++)
			sum += (1.0 / i);
		return sum;
	}

	/**
	 * calculate the sum of 1 + 1/2 + 1/4 + ... + 1/Math.pow(2, k)
	 * 
	 * @param k
	 *            a non-negative integer
	 * @return the sum of 1 + 1/2 + 1/4 + ... + 1/Math.pow(2, k)
	 */
	public static double geometricSum(int k) {
		double sum = .0;
		for (int i = 0; i <= k; i++)
			sum += (1.0 / Math.pow(2, i));
		return sum;
	}

	/**
	 * calculate the product of j and k (positive integers)
	 * 
	 * @param j
	 *            a positive integer
	 * @param k
	 *            a positive integer
	 * @return the product of j and k (positive integers)
	 */
	public static int multPos(int j, int k) {
		int sum = 0;
		for (int i = 1; i <= k; i++) {
			sum += j;
		}
		return sum;
	}

	/**
	 * calculate the product of j and k
	 * 
	 * @param j
	 *            an integer
	 * @param k
	 *            an integer
	 * @return the product of j and k
	 */
	public static int mult(int j, int k) {
		if (j == 0 || k == 0)
			return 0;
		else if (j < 0 && k > 0)
			return -multPos(-j, k);
		else if (k < 0 && j > 0)
			return -multPos(j, -k);
		else if (j < 0 && k < 0)
			return multPos(-j, -k);
		else
			return multPos(j, k);
	}

	/**
	 * calculate the value of n to the power k
	 * 
	 * @param n
	 *            an integer
	 * @param k
	 *            a non-negative integer
	 * @return the value of n to the power k
	 */
	public static int expt(int n, int k) {
		if (k == 0)
			return 1;
		int prod = n;
		for (int i = 1; i < k; i++)
			prod *= n;
		return prod;
	}

}
