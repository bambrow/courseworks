package lab10;

import sedgewick.StdRandom;
import sedgewick.StdDraw;

/**
 * Space Invader.
 * 
 * This is the Power class.
 * 
 * Some parts of the code are from my GitHub repo:
 * https://github.com/bambrow/java-notes/tree/master/games
 * 
 * I developed three games in this semester and put them in this repo.
 * Although not exactly the same, these games have parts similar.
 * 
 * @author bambrow
 *
 */

public class Power implements ISpaceInvador{

	private double rx;
	private double ry;
	private double vx;
	private double vy;
	private final double radius;
	
	/**
	 * initiator.
	 */
	public Power() {
		this.rx = initLocation();
		this.ry = initLocation();
		this.vx = initVelocity();
		this.vy = initVelocity();
		this.radius = 0.02;
	}
	
	/**
	 * return random velocity.
	 * @return
	 */
	private double initVelocity() {
		if (Math.random() < 0.5) {
			return -0.002;
		} else {
			return 0.002;
		}
	}
	
	/**
	 * return random location.
	 * @return
	 */
	private double initLocation() {
		return StdRandom.uniform(0.3, 0.7);
	}
	
	/**
	 * update the position of power.
	 */
	public void update() {
		if (rx + vx < 0.0 || rx + vx > 1.0) {
			vx = -vx;
		}
		if (ry + vy < 0.0 || ry + vy > 1.0) {
			vy = -vy;
		}
		rx += vx;
		ry += vy;
	}
	
	/**
	 * draw the power.
	 */
	public void draw() {
		StdDraw.setPenColor(StdDraw.GREEN);
		StdDraw.filledSquare(rx, ry, radius);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.text(rx, ry, "P");
	}

	/**
	 * getters and setters.
	 */
	public double getRx() {
		return rx;
	}

	public double getRy() {
		return ry;
	}

	public double getVx() {
		return vx;
	}

	public double getVy() {
		return vy;
	}

	public double getRadius() {
		return radius;
	}
	
}
