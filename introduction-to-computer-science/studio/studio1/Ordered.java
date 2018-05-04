package studio1;

import cse131.ArgsProcessor;

public class Ordered {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArgsProcessor ap = new ArgsProcessor(args);
		int x = ap.nextInt("Value for x?");
		int y = ap.nextInt("Value for y?");
		int z = ap.nextInt("Value for z?");
		boolean isOrdered = ((x > y) && (y > z) || (x < y) && (y < z));
		System.out.println(isOrdered);
	}

}
