package RockPaperScissors;

import cse131.ArgsProcessor;

public class RPS {

	public static void main(String[] args) {
		/**
		 * in this program, we assume the following:
		 * number 0 stands for rock
		 * number 1 stands for paper
		 * number 2 stands for scissors
		 */
		
		ArgsProcessor ap = new ArgsProcessor(args);
		int rounds = ap.nextInt("Enter the rounds:");
		
		int playerAWins = 0;
		int playerBWins = 0;
		int playerAMove;
		int playerBMove = 0;
		
		for (int i = 0; i < rounds; i++) {
			playerAMove = (int) (Math.random() * 3);
			System.out.print("Round " + (i + 1) +": \t");
			printChoice("A", playerAMove);
			printChoice("B", playerBMove);
			if (playerAMove == playerBMove) {
				System.out.println("Replay this round.");
				i--;
				playerBMove = ++playerBMove % 3;
				continue;
			}
			else if (playerAMove == ++playerBMove % 3) {
				System.out.println("Player A wins.");
				playerAWins++;
			}
			else {
				System.out.println("Player B wins.");
				playerBWins++;
			}
			playerBMove %= 3;
		}
		System.out.println("Total rounds: " + rounds + ", A wins " + playerAWins + " times, B wins " + playerBWins + " times.");
		System.out.printf("Fractions: A %.4f; B %.4f \n", (1.0 * playerAWins / rounds), (1.0 * playerBWins / rounds));
	}

	private static void printChoice(String s, int choice) {
		switch (choice) {
		case 0:
			System.out.print(s + " chooses rock.\t\t");
			break;
		case 1:
			System.out.print(s + " chooses paper.\t");
			break;
		case 2:
			System.out.print(s + " chooses scissors.\t");
			break;
		default:
			break;		
		}
	}
}
