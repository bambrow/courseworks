package studio7;

public class Food {
	
	// food has-a
	private final double carbs, fat, protein;
	// private final double calories;
	// assume for pedagogical purposes that cals can be computed
	
	/**
	 * 
	 * @param carbs number of grams of carbohydrates
	 * @param fat number of grams of fat
	 * @param protein number of grams of protein
	 */
	public Food(double carbs, double fat, double protein) {
		this.carbs = carbs;
		this.fat   = fat;
		this.protein = protein;
	}
	
	/**
	 * 
	 * @return the number of Kcalories computed from the carbs, fat, and protein
	 */
	public double getCals() {
		return 4*(carbs+protein) + 9*fat;
	}
	
	public String toString() {
		return "A food with " + getCals() + " calories";
	}
	
	public static void main(String[] args) {
		Food snickers = new Food(10, 10000, 1);
		System.out.println("Want a snickers? " + snickers);
	}

}
