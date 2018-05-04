package project;

import sedgewick.StdDraw;

public class Alien implements Moveable {
	
	private double posX; // the x-coordinate of alien
	private double posY; // the y-coordinate of alien
	private final double startX; // the starting x-coordinate of alien
	private final double startY; // the starting y-coordinate of alien
	private final double width = 0.15; // the width of alien
	private final double height = 0.15; // the height of alien
	private double speed; // the speed of alien
	private boolean upDown; // if the alien moves in up-down pattern
	private boolean isAlive = true; // if the alien is still alive
	
	/**
	 * Creates an Alien object to be implemented in the game
	 * @param x- x-coordinate of alien (center)
	 * @param y- y-coordinate of alien (center)
	 * @param speed- speed at which the alien moves 
	 * @param upDown- true if alien moves up/down pattern; false if alien moves side-to-side pattern
	 */
	public Alien(double x, double y, double speed, boolean upDown) {
		this.posX = x;
		this.posY = y;
		this.startX = x;
		this.startY = y;
		this.speed = -speed;
		this.upDown = upDown;
	}
	
	/**
	 * Get x-coordinate of alien.
	 * @return		x-coordinate
	 */
	public double getPosX() {
		return this.posX;
	}
	
	/**
	 * Get y-coordinate of alien.
	 * @return 		y-coordinate
	 */
	public double getPosY() {
		return this.posY;
	}
	
	/**
	 * Get width of alien.
	 * @return		width
	 */
	public double getWidth() {
		return this.width;
	}
	
	/**
	 * Get height of alien.
	 * @return 		height
	 */
	public double getHeight() {
		return this.height;
	}
	
	/**
	 * Draw the alien on the canvas.
	 */
	public void draw() {
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.filledRectangle(this.posX, this.posY, this.width/2, this.height/2);
	}
	
	/**
	 * Move the alien on the canvas based on speed. Also, update the speed if needed.
	 */
	public void move() {
		if (isOffScreen()) {
			speed *= -1;
		}
		if (upDown) {
			this.posY += speed;
		} else {
			this.posX += speed;
		}

	}
	
	/**
	 * Determine if the alien is out of the bound of canvas.
	 * @return		true if alien is out of bound
	 */
	public boolean isOffScreen() {
		return (this.posX > 1 || this.posX < -1 || this.posY > 1 || this.posY < -1);
	}
		
	/**
	 * Move the alien to its starting position.
	 */
	public void moveToStart() {
		this.posX = startX;
		this.posY = startY;
	}
	
	/**
	 * Determine if the alien is alive.
	 * @return		true if the alien is alive
	 */
	public boolean isAlive() {
		return this.isAlive;
	}
	
	/**
	 * Kill the alien.
	 */
	public void die() {
		this.isAlive = false;
	}
	

}
