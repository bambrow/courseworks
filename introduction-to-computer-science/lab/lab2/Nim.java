package lab2;

import cse131.ArgsProcessor;

public class Nim {
	public static void main(String[] args) {
		
		ArgsProcessor ap = new ArgsProcessor(args);
		boolean playerTurn = ap.nextBoolean("Do you want to play the first move? (true or false)");
		
		int sticks = 7 + (int)(Math.random() * 9); // random number of sticks from 7 to 15
		int rounds = 0;
		
		if (playerTurn) System.out.println("Player starts");
		else System.out.println("Computer starts");
		System.out.println("Total sticks: " + sticks);
		
		while (sticks > 0) {
			if (playerTurn) {
				int sticksToTake = ap.nextInt("Enter how many sticks you want to remove. (1 or 2)");
				if (sticksToTake < 1 || sticksToTake > 2) {
					System.out.println("Invalid number. Please enter again.");
					continue;
				}
				else if (sticksToTake > sticks) {
					System.out.println("The number you entered is bigger than the number of remaining sticks. Please enter again.");
					continue;
				}
				else {
					System.out.print("Round " + rounds + ", " + sticks + " sticks at start,");
					sticks -= sticksToTake;
					System.out.println(" human took " + sticksToTake + ", so " + sticks + " sticks remain");
					if (sticks == 0) System.out.println("Player wins");
					playerTurn = false;
					rounds++;
					continue;
				}
			}
			if (!playerTurn) {
				int sticksToTake = 1 + (int)(Math.random() * 2); // random number of sticks fron 1 to 2
				if (sticksToTake > sticks) continue;
				System.out.print("Round " + rounds + ", " + sticks + " sticks at start,");
				sticks -= sticksToTake;
				System.out.println(" computer took " + sticksToTake + ", so " + sticks + " sticks remain");
				if (sticks == 0) System.out.println("Computer wins");
				playerTurn = true;
				rounds++;
				continue;
			}
		}
	}
}
