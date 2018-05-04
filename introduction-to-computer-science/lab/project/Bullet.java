package project;

import sedgewick.StdDraw;

public class Bullet implements Moveable{
	
	private double posX; // the x-coordinate of bullet
	private double posY; // the y-coordinate of bullet
	private double width = 0.05; // the width of bullet
	private double height = 0.05; // the height of bullet
	private double speed; // the speed of bullet
	private boolean isOffScreen = false; // if the bullet is out of the bound of canvas
	
	/**
	 * Creates a Bullet object to be implemented in the game
	 * @param x- x-coordinate of bullet (center)
	 * @param y- y-coordinate of bullet (center)
	 * @param speed- speed at which the bullet moves 
	 */
	public Bullet(double x, double y, double speed) {
		this.posX = x;
		this.posY = y;
		this.speed = speed;
	}

	/**
	 * Draw the single bullet on the canvas.
	 */
	public void draw() {
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledRectangle(this.posX, this.posY, this.width/2, this.height/2);
	}
	
	/**
	 * Move the bullet based on the speed.
	 */
	public void move() {
		this.posY += speed;
	}
	
	/**
	 * Get x-coordinate of bullet.
	 * @return		x-coordinate 
	 */
	public double getPosX() {
		return this.posX;
	}
	
	/**
	 * Get y-coordinate of bullet.
	 * @return		y-coordinate
	 */
	public double getPosY() {
		return this.posY;
	}
	
	/**
	 * Determine if bullet is out of bounds
	 */
	public boolean getIsOffScreen() {
		return this.isOffScreen;	
	}
	
	/**
	 * Remove bullet if off screen or collides with alien
	 */
	public void setOffScreen() {
		this.isOffScreen = true;
	}
	
	/**
	 * Determine if a bullet and alien collide based on comparing upper left and bottom right coordinates of each
	 * @param a- alien that bullet potentially collided with
	 * @return true if collision occurred
	 */
	public boolean collide(Alien a) {	
		double myTopLeftX = posX - width/2;
		double myTopLeftY = posY + height/2;
		double myBottomRightX = posX + width/2;
		double myBottomRightY = posY - height/2;
		
		double otherTopLeftX = a.getPosX() - a.getWidth()/2;
		double otherTopLeftY = a.getPosY() + a.getHeight()/2;
		double otherBottomRightX = a.getPosX() + a.getWidth()/2;
		double otherBottomRightY = a.getPosY() - a.getHeight()/2;
		
		return (myTopLeftY >= otherBottomRightY && myBottomRightY <= otherTopLeftY && myBottomRightX >= otherTopLeftX && myTopLeftX <= otherBottomRightX);
	}

}
