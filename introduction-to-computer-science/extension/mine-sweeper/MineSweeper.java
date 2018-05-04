package minesweeper;

import cse131.ArgsProcessor;

public class MineSweeper {

	public static void main (String[] args) {
		
		//
		// Do not change anything between here ...
		//
		ArgsProcessor ap = new ArgsProcessor(args);
		int cols = ap.nextInt("How many columns?");
		int rows = ap.nextInt("How many rows?");
		double percent = ap.nextDouble("What is the probability of a bomb?");
		//
		// ... and here
		//
		//  Your code goes below these comments
		//
		
		boolean[][] bombs = new boolean[rows + 2][cols + 2];

		for (int i = 1; i < rows + 1; i++) {
			for (int j = 1; j < cols + 1; j++) {
				if (Math.random() < percent) {
					bombs[i][j] = true;
				}
			}
		}

		int[][] sweeper = new int[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (bombs[i + 1][j + 1]) {
					sweeper[i][j] = -1;
				} else {
					for (int k = i; k < i + 3; k++) {
						for (int l = j; l < j + 3; l++) {
							if (bombs[k][l]) {
								sweeper[i][j]++;
							}
						}
					}
				}
			}
		}

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (bombs[i + 1][j + 1]) {
					System.out.print("* ");
				} else {
					System.out.print(". ");
				}
			}
			System.out.print("\t");
			for (int j = 0; j < cols; j++) {
				if (bombs[i + 1][j + 1]) {
					System.out.print("* ");
				} else {
					System.out.print(sweeper[i][j] + " ");
				}
			}
			System.out.println();
		}
		
	}
	
}
