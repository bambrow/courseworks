package project;

import java.awt.Color;

import sedgewick.StdDraw;

public class Player implements Moveable {
	
	private double posX; // the x-coordinate of player
	private double posY; // the y-coordinate of player
	private final double startPosX; // the starting x-coordinate of player
	private final double startPosY; // the starting y-coordinate of player
	private final double width = 0.1; // the width of player
	private final double height = 0.1; // the height of player
	private Color color; // the color of player
	private int lives; // the number of lives of player
	private double speed; // the speed of player
	private final int id; // the id of player
	private final Color [] colors = {StdDraw.RED, StdDraw.YELLOW, StdDraw.WHITE}; // the color array to determine the color of the player
	
	/**
	 * Creates a Player object to be implemented in the game
	 * @param id- id of player
	 * @param x- x-coordinate of player (center)
	 * @param y- y-coordinate of player (center)
	 * @param speed- speed at which the player moves 
	 * @param lives- number of lives the player starts with
	 */
	public Player(int id, double x, double y, double speed, int lives) {
		this.id = id;
		this.posX = x;
		this.posY = y;
		this.startPosX = x;
		this.startPosY = y;
		this.speed = speed;
		this.lives = lives;
		this.color = colors[(this.lives-1) % 3];
	}
	
	/**
	 * Draw the player on the canvas.
	 */
	public void draw() {
		if (isAlive()) {
			StdDraw.setPenColor(this.color);
			StdDraw.filledRectangle(this.posX, this.posY, this.width/2, this.height/2);
			StdDraw.filledRectangle(this.posX, (this.posY + this.height/1.5), this.width/6, this.height/2);
			StdDraw.setPenColor(StdDraw.BLACK);
			if (this.id == 0) {
				StdDraw.text(this.posX, this.posY, "A");
			} else if (this.id == 1) {
				StdDraw.text(this.posX, this.posY, "B");
			}
		}
	}
	
	/**
	 * Move the player if possible (certain key is pressed).
	 */
	public void move() {
		if (isAlive()) {
			//If movements are possible:
			if ((ArcadeKeys.isKeyPressed(this.id, 1)) && (this.posX - this.speed > -1)) {
				this.posX -= this.speed;
			}
			else if ((ArcadeKeys.isKeyPressed(this.id, 3)) && (this.posX + this.speed < 1)) {
				this.posX += this.speed;
			}
		}	
	}
	
	/**
	 * Get x-coordinate of player.
	 * @return		x-coordinate
	 */
	public double getPosX() {
		return this.posX;
	}
	
	/**
	 * Get y-coordinate of player.
	 * @return 		y-coordinate
	 */
	public double getPosY() {
		return this.posY;
	}
	
	/**
	 * Get number of lives of player.
	 * @return		number of lives
	 */
	public int getLives() {
		return this.lives;
	}
	
	/**
	 * Determine if the player should fire.
	 * @return-		true if the player should fire
	 */
	public boolean fire() {
		if (isAlive()) {
			return (ArcadeKeys.isKeyPressed(this.id, 0)); //key w pushed
		} else {
			return false;
		}
	}
	
	/**
	 * Kills player by reducing life and changing colors
	 */
	public void die() {
		this.lives--;
		if (lives != 0) {
			this.color = colors[lives - 1];
		}
		this.posX = this.startPosX;
		this.posY = this.startPosY; //doesn't do anything but good form
	}
	
	/**
	 * Determine if the player and alien collide based on comparing upper left and bottom right coordinates of each
	 * @param a- alien that player potentially collided with
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
	
	/**
	 * Determine if the player is alive.
	 * @return		true if the player is alive
	 */
	private boolean isAlive() {
		return this.lives >= 1;
	}

	/**
	 * Set the current x-coordinate of player.
	 * @param posX		x-coordinate
	 */
	public void setPosX(double posX) {
		this.posX = posX;
	}

	/**
	 * Set the current y-coordinate of player.
	 * @param posY		y-coordinate
	 */
	public void setPosY(double posY) {
		this.posY = posY;
	}

	/**
	 * Set the speed of player.
	 * @param speed		speed of player
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}

}
