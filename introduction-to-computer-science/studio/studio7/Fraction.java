package studio7;

public class Fraction {

	private int numerator;
	private int denominator;

	public Fraction(int n, int d) {
		if (d == 0) {
			throw new IllegalArgumentException("denomiantor should not be 0");
		}
		this.numerator = n;
		this.denominator = d;
	}

	private int gcd(int a, int b) {
		if (b == 0) {
			return a;
		}
		return a % b == 0 ? b : gcd(b, a % b);
	}

	private int lcm(int a, int b) {
		return a * b / gcd(a, b);
	}

	public Fraction simplify() {
		int g = gcd(numerator, denominator);
		return new Fraction(numerator / g, denominator / g);
	}

	public Fraction takeReciprocal() {
		return new Fraction(denominator, numerator);
	}

	public Fraction add(Fraction that) {
		int d = lcm(this.denominator, that.denominator);
		int n = this.numerator * (d / this.denominator) + that.numerator * (d / that.denominator);
		return new Fraction(n, d);
	}

	public Fraction multiply(Fraction that) {
		return new Fraction(this.numerator * that.numerator, this.denominator * that.denominator);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fraction other = (Fraction) obj;
		if (denominator != other.denominator)
			return false;
		if (numerator != other.numerator)
			return false;
		return true;
	}

}
