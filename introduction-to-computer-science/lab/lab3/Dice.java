package lab3;

import cse131.ArgsProcessor;

public class Dice {

	public static void main(String[] args) {
		
		ArgsProcessor argsProcessor = new ArgsProcessor(args);
		int numDice = 0;
		int numTimes = 0;
		int numSides = 0;

		while (numDice <= 0) {
			numDice = argsProcessor.nextInt("Input the number of dice used in this simulation: ");
			if (numDice <= 0)
				System.out.println("Invalid input. Number of dice must be greater than 0.");
		}
		while (numTimes <= 0) {
			numTimes = argsProcessor.nextInt("Input the number of times the dice are thrown: ");
			if (numTimes <= 0)
				System.out.println("Invalid input. Number of times must be greater than 0.");
		}
		while (numSides <= 1) {
			numSides = argsProcessor.nextInt("Input the number of sides of dice: ");
			if (numSides <= 1)
				System.out.println("Invalid input. Number of sides must be greater than 1.");
		}

		int[] diceValue = new int[numDice];
		int numAllSame = 0;
		int[] diceValueTotalTimes = new int[numDice * (numSides - 1) + 1];
		
		for (int i = 0; i < numTimes; i++) {
			System.out.print("No. " + (i + 1) + "          Values: ");
			int diceValueTotal = 0;
			for (int j = 0; j < numDice; j++) {
				diceValue[j] = (int) (Math.random() * numSides + 1);
				diceValueTotal += diceValue[j];
				System.out.print(diceValue[j] + " ");
			}
			if (isAllSame(diceValue))
				numAllSame++;
			System.out.print("         Sum: " + diceValueTotal + "\n");
			diceValueTotalTimes[diceValueTotal - numDice]++;
		}

		System.out.print("\n");
		System.out.printf("Percentage when all the dice values are the same: %.4f%%", 100.0 * numAllSame / numTimes);
		System.out.print("\n\n");

		System.out.print("Possible Sums:     ");
		for (int i = 0; i < diceValueTotalTimes.length; i++) {
			System.out.printf("%7d", numDice + i);
		}
		System.out.print("\n" + "Occurrence Times:  ");
		for (int i = 0; i < diceValueTotalTimes.length; i++) {
			System.out.printf("%7d", diceValueTotalTimes[i]);
		}
		System.out.print("\n" + "Percentage (%):    ");
		for (int i = 0; i < diceValueTotalTimes.length; i++) {
			System.out.printf("%6.2f%%", 100.0 * diceValueTotalTimes[i] / numTimes);
		}
	}

	public static boolean isAllSame(int[] array) {
		for (int i = 1; i < array.length; i++) {
			if (array[i] != array[i - 1])
				return false;
		}
		return true;
	}

}
