package arrays;
import cse131.ArgsProcessor;

public class Birthday {
	
	public static void main(String[] args) {
		ArgsProcessor ap = new ArgsProcessor(args);
		int number = ap.nextInt("Enter the number of people:");
		
		int[][] cal = new int[12][31];
		for (int i = 0; i < number; i++) {
			int month = (int)(Math.random() * 12);
			int day = (int)(Math.random() * 31);
			cal[month][day]++;
		}
		
		// print month summary
		System.out.println("Month Summary:");
		System.out.println("          Jan       Feb       Mar       Apr       May       Jun       Jul       Aug       Sep       Oct       Nov       Dec");
		System.out.print("#:  ");
		int[] monthSum = new int[12];
		int monthTotal = 0;
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 31; j++) monthSum[i] += cal[i][j];
			System.out.printf("%10d", monthSum[i]);
			monthTotal += monthSum[i];
		}
		System.out.println();
		System.out.print("%:  ");
		for (int i = 0; i < 12; i++) System.out.printf("%9.2f%%", (100.0 * monthSum[i] / monthTotal));
		System.out.println();
		System.out.printf("Average: %.2f (%.2f%%) \n\n\n", (1.0 * monthTotal / 12), (100.0 * 1 / 12));
		
		// print day summary
		System.out.println("Day Summary:");	
		System.out.print("    ");
		for (int i = 0; i < 31; i++) System.out.printf("%9d", i + 1);
		System.out.println();
		System.out.print("#:  ");
		int[] daySum = new int[31];
		int dayTotal = 0;
		for (int i = 0; i < 31; i++) {
			for (int j = 0; j < 12; j++) daySum[i] += cal[j][i];
			System.out.printf("%9d", daySum[i]);
			dayTotal += daySum[i];
		}
		System.out.println();
		System.out.print("%:  ");
		for (int i = 0; i < 31; i++) System.out.printf("%8.2f%%", (100.0 * daySum[i] / dayTotal));
		System.out.println();
		System.out.printf("Average: %.2f (%.2f%%) \n\n\n", (1.0 * dayTotal / 31), (100.0 * 1 / 31));
		
		// print year summary and calculate the percentage of people born on exactly the same day
		System.out.println("Year Summary:");
		System.out.println("    " + "     Jan     Feb     Mar     Apr     May     Jun     Jul     Aug     Sep     Oct     Nov     Dec");
		int sameBirthday = 0;
		for (int i = 0; i < 31; i++) {
			System.out.printf("%-4d", i + 1);
			for (int j = 0; j < 12; j++){
				System.out.printf("%8d", cal[j][i]);
				if (cal[j][i] >= 2) sameBirthday += cal[j][i];
			}
			System.out.println();
		}
		System.out.printf("Percentage of people born on exactly the same day: %.2f%%", (100.0 * sameBirthday / number));
	}
}
