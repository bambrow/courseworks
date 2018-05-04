package lab4;

import cse131.ArgsProcessor;
import sedgewick.StdAudio;
import sedgewick.StdDraw;

public class BumpingBalls {
	
	private double rx;
	private double ry;
	private double vx;
	private double vy;
	private final double radius;

	public BumpingBalls() {
		rx = Math.random() < 0.5 ? Math.random() / 1.05 : -Math.random() / 1.05;
		ry = Math.random() < 0.5 ? Math.random() / 1.05 : -Math.random() / 1.05;
		vx = Math.random() < 0.5 ? Math.random() / 75 : -Math.random() / 75;
		vy = Math.random() < 0.5 ? Math.random() / 75 : -Math.random() / 75;
		radius = 0.04;
	}

	public void update() {
		if (Math.abs(rx + vx) > 1.0 - radius) {
			vx = -vx;
		}
		if (Math.abs(ry + vy) > 1.0 - radius) {
			vy = -vy;
		}
		rx += vx;
		ry += vy;
	}

	public void draw() {
		StdDraw.picture(rx, ry, "labs/lab4/earth.gif");
		// StdDraw.filledCircle(rx, ry, radius);
	}

	public static double distance(double x1, double y1, double x2, double y2) {
		return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
	}

	public static boolean collide(BumpingBalls a, BumpingBalls b) {
		if (distance(a.rx, a.ry, b.rx, b.ry) <= a.radius + b.radius) {
			return true;
		}
		return false;
	}

	public static void collideGroup(BumpingBalls[] balls) {
		for (int i = 0; i < balls.length - 1; i++) {
			for (int j = i + 1; j < balls.length; j++) {
				if (collide(balls[i], balls[j])) {
					if (distance(balls[i].rx, balls[i].ry, balls[j].rx,
							balls[j].ry) < distance(balls[i].rx + balls[i].vx, balls[i].ry + balls[i].vy,
									balls[j].rx + balls[j].vx, balls[j].ry + balls[j].vy)) {
						continue;
					}
					StdAudio.play("labs/lab4/pop.wav");
					balls[i].vx = -balls[i].vx;
					balls[i].vy = -balls[i].vy;
					balls[j].vx = -balls[j].vx;
					balls[j].vy = -balls[i].vy;
				}
			}
		}
	}

	public static void updateBalls(BumpingBalls[] balls) {
		for (int i = 0; i < balls.length; i++) {
			balls[i].update();
		}
	}

	public static void drawBalls(BumpingBalls[] balls) {
		StdDraw.setPenColor(StdDraw.DARK_GRAY);
		for (int i = 0; i < balls.length; i++) {
			balls[i].draw();
		}
	}

	public static void main(String[] args) {
		
		ArgsProcessor ap = new ArgsProcessor(args);

		int ballNum = 0;
		while (ballNum <= 0) {
			ballNum = ap.nextInt("Enter the number of balls (greater than 0) : ");
			if (ballNum <= 0)
				System.out.println("Invalid number.");
		}

		StdDraw.setXscale(-1.0, 1.0);
		StdDraw.setYscale(-1.0, 1.0);

		BumpingBalls[] balls = new BumpingBalls[ballNum];
		for (int i = 0; i < balls.length; i++) {
			balls[i] = new BumpingBalls();
		}

		while (true) {

			StdDraw.clear(StdDraw.LIGHT_GRAY);

			updateBalls(balls);
			collideGroup(balls);
			drawBalls(balls);

			StdDraw.show(20);

		}

	}

}
