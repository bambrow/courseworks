package lab10;

import sedgewick.StdDraw;
import sedgewick.StdRandom;

/**
 * Space Invader.
 * 
 * This is the Alien class.
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

public class Alien implements ISpaceInvador{
	
	private double rx;
	private double ry;
	private double vx;
	private double vy;
	private final double radius;
	private boolean shoot;
	
	/**
	 * initiator.
	 */
	public Alien() {
		this.rx = 1.1;
		this.ry = initLocation();
		this.vx = -0.015;
		this.vy = initVelocity();
		this.radius = 0.02;
		this.shoot = false;
	}

	/**
	 * return random velocity.
	 * @return
	 */
	private double initVelocity() {
		double dest = StdRandom.uniform(0.0, 1.0);
		double time = (0.0 - rx) / vx;
		return (dest - ry) / time;
	}

	/**
	 * return random location.
	 * @return
	 */
	private double initLocation() {
		return StdRandom.uniform(0.20, 0.80);
	}

	/**
	 * update the position of alien.
	 */
	public void update() {
		rx += vx;
		ry += vy;
	}

	/**
	 * draw the alien.
	 */
	public void draw() {
		StdDraw.setPenColor(StdDraw.CYAN);
		StdDraw.filledCircle(rx, ry, radius);
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

	public boolean isShoot() {
		return shoot;
	}

	public void setShoot(boolean shoot) {
		this.shoot = shoot;
	}
	
}
