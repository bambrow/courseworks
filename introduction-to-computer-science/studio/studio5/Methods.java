package studio5;

public class Methods {
	
	/**
	 * Implemented correctly
	 * @param x one thing to add
	 * @param y the other thing to add
	 * @return the sum
	 */
	public static int sum(int x, int y) {
		return x + y;
	}
	
	/**
	 * 
	 * @param x one factor
	 * @param y another factor
	 * @return the product of the factors
	 */
	public static int mpy(int x, int y) {
		int mpy = 0;
		for (int i = 0; i < y; i++) {
			mpy += x;
		}
		return mpy;
	}

	/**
	 * 
	 * @param d1 first double
	 * @param d2 second double
	 * @param d3 third double
	 * @return average of three doubles
	 */
	public static double avg3(double d1, double d2, double d3) {
		return (d1 + d2 + d3) / 3;
	}

	/**
	 * 
	 * @param array
	 * @return sum of the array
	 */
	public static double sumArray(double[] array) {
		int sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		return sum;
	}

	/**
	 * 
	 * @param array
	 * @return aveage of the array
	 */
	public static double average(double[] array) {
		return sumArray(array) / array.length;
	}

	/**
	 * 
	 * @param s input string
	 * @return pig latin
	 */
	public static String pig(String s) {
		return s.substring(1) + s.substring(0, 1) + "ay";
	}

	/**
	 * 
	 * @param array
	 * @return max of the array
	 */
	public static double maxArray(double[] array) {
		double max = array[0];
		for (int i = 0; i < array.length; i++) {
			if (array[i] > max)
				max = array[i];
		}
		return max;
	}

}
