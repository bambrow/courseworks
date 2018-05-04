package arrays;
import cse131.ArgsProcessor;

public class PascalsTriangle {
	
	public static void main(String[] args) {
		ArgsProcessor ap = new ArgsProcessor(args);
		int rows;
		for (;;) {
			rows = ap.nextInt("Enter the number of rows: (0-34)");
			if (rows >= 1 && rows <= 34) break;
			else System.out.println("Number out of range. Please enter again.");
		}
		
		int[][] triangle = new int[rows][rows];
		
		// fill in the numbers
		for (int i = 0; i < rows; i++) {
			triangle[i][0] = 1;
			triangle[i][i] = 1;
			for (int j = 1; j < (i / 2 + 1); j++) {
				triangle[i][j] = triangle[i-1][j-1] + triangle[i-1][j];
				triangle[i][i-j] = triangle[i-1][j-1] + triangle[i-1][j];
			}
		}
		
		// calculate the maximum digits of number for better formatting
		int maxDigit;
		if (rows == 1) maxDigit = 3;
		else maxDigit = ((int) Math.log10((triangle[rows - 1][(rows - 1) / 2 + 1]))) + 3;
		System.out.println("        column");
		System.out.print("        ");
		for (int i = 0; i < rows; i++) {
			String tempCol = String.format("%-" + maxDigit + "d", i);
			System.out.print(tempCol);
		}
		System.out.println();
		System.out.println("row");
		
		// print the triangle
		for (int i = 0; i < rows; i++) {
			System.out.printf("%-8d", i);
			for (int j = 0; j <= i; j++) {
				String tempRow = String.format("%-" + maxDigit + "d", triangle[i][j]);
				System.out.printf(tempRow);
			}
			System.out.println();
		}
		
		System.out.println("\n\n\n\n\n");
		
		// print centered triangle
		int maxLength = 8 + maxDigit * rows;
		for (int i = 0; i < rows; i++) {
			System.out.printf("%-8d", i);
			String center = new String();
			for (int j = 0; j <= i; j++) {
				String formatNum = String.format( "%-" + (maxDigit + (int)Math.log10(triangle[i][j])) / 2 + "s", triangle[i][j]);
				center += String.format("%" + maxDigit + "s", formatNum);
			}
			String formatCenter = String.format("%-" + (maxLength + center.length()) / 2 + "s", center);
			String formatFinal = String.format("%" + maxLength + "s", formatCenter);
			System.out.println(formatFinal);
		}
	}

}
