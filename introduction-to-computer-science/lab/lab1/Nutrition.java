package lab1;

import cse131.ArgsProcessor;

public class Nutrition {

	public static void main(String[] args) {
		ArgsProcessor ap = new ArgsProcessor(args);
		String name = ap.nextString("Input the name of this food:");
		double carbs = ap.nextDouble("Input number of grams of carbohydrates in this food:");
		double fat = ap.nextDouble("Input number of grams of fat in this food:");
		double protein = ap.nextDouble("Input number of grams of protein in this food:");
		int statedCals = ap.nextInt("Input number of calories stated on this food's label:");
		
		double carbsTotal = ((double)Math.round(10 * carbs * 4)) / 10;
		double fatTotal = ((double)Math.round(10 * fat * 9)) / 10;
		double proteinTotal = ((double)Math.round(10 * protein * 4)) / 10;

		System.out.println(name + " has");
		System.out.println(carbs + " grams of carbohydrates = " + carbsTotal + " Calories");
		System.out.println(fat + " grams of fat = " + fatTotal + " Calories");
		System.out.println(protein + " grams of protein = " + proteinTotal + " Calories\n");
		System.out.println("This food is said to have " + statedCals + " (avaliable) Calories.");
		
		double unavailableCarbs = ((double)Math.round(((carbsTotal + fatTotal + proteinTotal - statedCals) * 10))) / 10;
		
		System.out.println("With " + unavailableCarbs + " unavailable Calories, this food has " + unavailableCarbs / 4 + " grams of fiber.\n");
		System.out.println("Approximately");
		
		double carbsPercentage = ((double)Math.round(1000 * carbsTotal / statedCals)) / 10;
		double fatPercentage = ((double)Math.round(1000 * fatTotal / statedCals)) / 10;
		double proteinPercentage = ((double)Math.round(1000 * proteinTotal / statedCals)) / 10;

		System.out.println("\t" + carbsPercentage + "% of your food is carbohydrates");
		System.out.println("\t" + fatPercentage + "% of your food is fat");
		System.out.println("\t" + proteinPercentage + "% of your food is protein\n");
		
		boolean lowCarb = carbsPercentage <= 25.0;
		boolean lowFat = fatPercentage <= 15.0;
		System.out.println("This food is acceptable for a low-carb diet? " + lowCarb);
		System.out.println("This food is acceptable for a low-fat diet? " + lowFat);
		
		boolean heads = Math.random() <= 0.5;
		System.out.println("By coin flip, you should eat this food? " + heads);
	}

}
