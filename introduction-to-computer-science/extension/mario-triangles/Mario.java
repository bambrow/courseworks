package mario;

import cse131.ArgsProcessor;

public class Mario {

	public static void main(String[] args) {
		ArgsProcessor ap = new ArgsProcessor(args);
		int size = 0;
		while (size <= 0 || size > 100) {
			size = ap.nextInt("Enter the size of the montains (1-100):");
			if (size <= 0) System.out.println("The size must be greater than 0. Enter again.");
			else if (size > 100) System.out.println("The size is too big. Enter again.");
		}
		
		System.out.println("Print mountain that ascends to the right:");
		for (int i = 0; i < size; i++) {
			// to avoid another loop in the loop
			String blank = new String(new char[size - i - 1]).replace("\0", " ");
			String sharp = new String(new char[i + 1]).replace("\0", "#");
			System.out.println(blank + sharp);
		}
		
		System.out.println("Print mountain that descends to the right:");
		for (int i = 0; i < size; i++) {
			String sharp = new String(new char[i + 1]).replace("\0", "#");
			System.out.println(sharp);
		}
		
		System.out.println("Print mountain that resembles the first mountain flipped upside down:");
		for (int i = 0; i < size; i++) {
			String blank = new String(new char[i]).replace("\0", " ");
			String sharp = new String(new char[size - i]).replace("\0", "#");
			System.out.println(blank + sharp);
		}
		
		System.out.println("Print mountain that resembles the second mountain flipped upside down:");
		for (int i = 0; i < size; i++) {
			String sharp = new String(new char[size - i]).replace("\0", "#");
			System.out.println(sharp);
		}
	}

}
