package studio2;

import cse131.ArgsProcessor;

public class Ruin {

	public static void main(String[] args) {
		ArgsProcessor ap = new ArgsProcessor(args);
		int startAmount = ap.nextInt("The amount of money you start with:");
		double winChance = ap.nextDouble("The probability you win a gamble:");
		int winAmount = ap.nextInt("If you reach this amount of money, then you won:");
		int totalPlays = ap.nextInt("The number of times you simulate the problem:");
		
		double lossChance = 1.0 - winChance;
		double ruin;
		
		if (lossChance != winChance)
			ruin = ((Math.pow((lossChance / winChance), startAmount) - Math.pow((lossChance / winChance), winAmount)) / (1 - Math.pow((lossChance / winChance), winAmount)));
		else
			ruin = 1 - (startAmount / winAmount);
		
		int winTime = 0;
		for (int i = 0; i < totalPlays; i++) {
			int simulationAmount = startAmount;
			int round = 0;
			while (simulationAmount > 0 && simulationAmount < winAmount) {
				if (Math.random() < winChance) simulationAmount++;
				else simulationAmount--;
				round++;
			}
			if (simulationAmount == 0)  System.out.println("\t Simulation " + (i + 1) + ": " + round + " rounds \t \t LOSE");
			else {
				System.out.println("\t Simulation " + (i + 1) + ": " + round + " rounds \t \t WIN");
				winTime++;
			}
		}
		
		System.out.println("\t Losses: " + (totalPlays - winTime) + "   Simulations: " + totalPlays);
		System.out.println("\t Actual Ruin Rate: " + ((totalPlays - winTime + 0.0) / totalPlays) + "   Expected Ruin Rate: " + ruin);
	}
}
