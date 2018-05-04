package studio7;

public class Rectangle {

	private double length;
	private double width;

	public Rectangle(double l, double w) {
		this.length = l;
		this.width = w;
	}

	public double getArea() {
		return length * width;
	}

	public double getPerimeter() {
		return 2 * length + 2 * width;
	}

	public boolean isSquare() {
		return Math.abs(width - length) < 1e-5;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public int compareTo(Rectangle that) {
		if (Math.abs(this.getArea() - that.getArea()) < 1e-5) {
			return 0;
		} else if (this.getArea() > that.getArea()) {
			return 1;
		} else {
			return -1;
		}
	}

	public String toString() {
		return "length: " + this.length + " width: " + this.width;
	}

}
