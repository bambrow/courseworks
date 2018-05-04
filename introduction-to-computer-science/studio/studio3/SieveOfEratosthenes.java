package studio3;

import cse131.ArgsProcessor;

public class SieveOfEratosthenes {

	public static void main(String[] args) {
		ArgsProcessor ap = new ArgsProcessor(args);
		int n = ap.nextInt("Enter the maximum number:");
		
		int[] numbers = new int[n + 1];
		for (int i = 0; i < n + 1; i++) numbers[i] = i;
		for (int i = 2; i < (int)(Math.sqrt(n + 1) + 1); i++) {
			if (numbers[i] != 0) {
				for (int j = 2; j * i < n + 1 ; j++) numbers[j * i] = 0;
			}
		}
		
		System.out.print("Primes under " + n + ": ");
		for (int i = 2; i < n + 1; i++) {
			if (numbers[i] != 0) System.out.print(numbers[i] + ", ");
		}
		System.out.println();
	}
}
