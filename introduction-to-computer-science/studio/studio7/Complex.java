package studio7;

public class Complex {

	private double real;
	private double imaginary;

	public Complex(double r, double i) {
		this.real = r;
		this.imaginary = i;
	}

	public Complex add(Complex that) {
		return new Complex(this.real + that.real, this.imaginary + that.imaginary);
	}

	public Complex multiply(Complex that) {
		return new Complex(this.real * that.real - this.imaginary * that.imaginary,
				this.real * that.imaginary + this.imaginary * that.real);
	}

	public String toString() {
		return "Complex: " + this.real + " + " + this.imaginary + "i";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Complex other = (Complex) obj;
		if (Double.doubleToLongBits(imaginary) != Double.doubleToLongBits(other.imaginary))
			return false;
		if (Double.doubleToLongBits(real) != Double.doubleToLongBits(other.real))
			return false;
		return true;
	}

}
