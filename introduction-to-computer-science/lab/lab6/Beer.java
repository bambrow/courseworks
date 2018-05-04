package lab6;

public class Beer {

	/**
	 * Compute the old camp song of beers.
	 * 
	 * @param n
	 *            the maximum number of beers
	 * @return the string of the camp song
	 */
	public static String bottlesOfBeer(int n) {
		if (n <= 0) {
			return "";
		} else {
			String s = n + " bottles of beer on the wall, " + n
					+ " bottles of beer; you take one down, pass it around, " + (n - 1)
					+ " bottles of beer on the wall.\n";
			return s + bottlesOfBeer(--n);
		}
	}

	public static void main(String[] args) {
		System.out.println(bottlesOfBeer(5));
	}

}
