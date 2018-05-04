package studio7;

public class Die {

	private int sides;

	public Die(int s) {
		this.sides = s;
	}

	public int throwDice() {
		return (int) (Math.random() * sides) + 1;
	}

	public static void main(String[] args) {
		Die die = new Die(6);
		for (int i = 0; i < 20; i++) {
			int n = die.throwDice();
			System.out.print(n + " ");
		}
	}

}
