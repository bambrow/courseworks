package studio2;

import cse131.ArgsProcessor;

public class Pi {

	public static void main(String[] args) {
		ArgsProcessor ap = new ArgsProcessor(args);
		int loopTime = ap.nextInt("Enter loop time:");
		
		int inside = 0;
		for (int i = 0; i < loopTime; i++) {
			double x = Math.random() - 0.5;
			double y = Math.random() - 0.5;
			if (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) <= 0.5) inside++;
		}
		
		double insideProb = (inside + 0.0) / loopTime;
		double pi = insideProb * 4;
		
		System.out.println(pi);
	}
}
