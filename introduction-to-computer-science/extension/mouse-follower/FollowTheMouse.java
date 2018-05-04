package mousefollower;

import sedgewick.StdDraw;

public class FollowTheMouse {

	public static double[] getMousePosition() {
		double[] position = new double[2];
		position[0] = StdDraw.mouseX();
		position[1] = StdDraw.mouseY();
		return position;
	}

	public static void drawBall(double[] position, double radius) {
		StdDraw.setPenColor(StdDraw.DARK_GRAY);
		StdDraw.filledCircle(position[0], position[1], radius);
	}

	public static double[] changePosition(double[] position, double[] mousePosition, double speed) {
		double[] newPosition = new double[2];
		double dist = dist(mousePosition[0] - position[0], mousePosition[1] - position[1]);
		if (dist < speed) {
			return mousePosition;
		}
		newPosition[0] = (mousePosition[0] - position[0]) / dist * speed + position[0];
		newPosition[1] = (mousePosition[1] - position[1]) / dist * speed + position[1];
		return newPosition;
	}

	public static double dist(double x, double y) {
		return Math.sqrt(x * x + y * y);
	}

	public static void main(String[] args) {

		final double RADIUS = .03;
		final double SPEED = .02;

		StdDraw.clear(StdDraw.LIGHT_GRAY);

		double[] position = { Math.random(), Math.random() };
		drawBall(position, RADIUS);
		StdDraw.pause(30);

		while (true) {

			StdDraw.clear(StdDraw.LIGHT_GRAY);

			position = changePosition(position, getMousePosition(), SPEED);

			drawBall(position, RADIUS);

			StdDraw.pause(30);

		}

	}

}
