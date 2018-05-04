package project;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.LinkedList;

import sedgewick.StdDraw;

/**
 * Plays game by using all created objects and Moveable interface
 * @author Zachary Polsky
 *
 */

public class Game {
	
	private LinkedList<Alien> aliens; // the list of aliens
	private LinkedList<Moveable> move; // the list of moveables
	private LinkedList<Bullet> bulletsA; // the list of bullets from playerA
	private LinkedList<Bullet> bulletsB; // the list of bullets from playerB
	private Player playerA; // the playerA
	private Player playerB; // the playerB
	private double alienSpeed; // the speed of aliens
	private int scoreA; // the score of playerA
	private int scoreB; // the score of playerB
	/**
	 * Creates a new game.
	 */
	public Game() {
		aliens = new LinkedList<Alien>(); // initiate the aliens
		move = new LinkedList<Moveable>(); // initiate the movables
		bulletsA = new LinkedList<Bullet>(); // initiate the bullets from playerA
		bulletsB = new LinkedList<Bullet>(); // initiate the bullets from playerB
		playerA = new Player(0, -.3, -.9, .04, 3); // initiate the playerA
		playerB = new Player(1, .3, -.9, .04, 3); // initiate the playerB
		move.add(playerA); // add the playerA to movables
		move.add(playerB); // add the playerB to movables
		alienSpeed = 0.04; // set the speed of aliens
		addAliens(); // add aliens to the game
		scoreA = 0; // initiate the scoreA
		scoreB = 0; // initiate the scoreB
	}
	
	/**
	 * Draw the game board on the canvas.
	 * @param scoreA 	the score of playerA
	 * @param scoreB 	the score of playerB
	 */
	public void drawBoard(int scoreA, int scoreB) {
		StdDraw.clear();
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledRectangle(0, 0, 1, 1);
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.textLeft(-0.95, 0.9, "Player A Score: " + scoreA);
		StdDraw.textRight(0.95, 0.9, "Player B Score: " + scoreB);
	}
	
	/**
	 * Determine if the game is over.
	 * @return		true if both players' live is equal to or less than 0
	 */
	public boolean isOver() {
		return (!(playerA.getLives() > 0 || playerB.getLives() > 0));
	}
	
	/**
	 * Add three more aliens to the game.
	 */
	public void addAliens(){
		addAlien(0.5, 0.5, alienSpeed, true);
		addAlien(-0.5, 0.5, alienSpeed, true);
		addAlien(-0.9, 0.5, alienSpeed, false);
	}
	
	/**
	 * Add one more alien to the game.
	 * @param x		x-coordinate of alien
	 * @param y		y-coordinate of alien
	 * @param speed		speed of alien
	 * @param upDown	if the alien moves in up-down pattern
	 */
	private void addAlien(double x, double y, double speed, boolean upDown)
	{
		Alien a = new Alien(x, y, speed, upDown);
		aliens.add(a);
		move.add(a);
	}
	
	/**
	 * Handle the fire behavior of the player.
	 * @param player 	the player (either A or B)
	 * @param bullets	the bullets corresponding to this player
	 */
	private void fire(Player player, LinkedList<Bullet> bullets) {
		if (player.fire() && bullets.size() < 3) { // if the player should shoot and the number of bullets on the screen is less than 3
			Bullet b = new Bullet(player.getPosX(), player.getPosY() + .15, .05); // create a new bullet
			move.add(b); // add the bullet to the movables so it can be moved and drew
			bullets.add(b); // add the bullet to the list of bullets
		}
	}
	
	/**
	 * Handle the collision between player and alien.
	 * @param player	the player (either A or B)
	 * @param a			the alien
	 * @return 			true if the collision happens
	 */
	private boolean playerCollide(Player player, Alien a) {
		if (player.collide(a)) { // if the player collides with this alien
			player.die(); // kills the player
			if (player.getLives() <= 0) { // if the player loses its all lives
				move.remove(player); // remove player from moveables
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Handle the collision between bullet and alien, and remove the bullets if it is out of the screen.
	 * @param bullets	the bullets from one player (either A or B)
	 * @param a			the alien
	 * @return			true if the collision happens
	 */
	private boolean bulletCollide(LinkedList<Bullet> bullets, Alien a) {
		for (Bullet b : bullets) { // for each bullet in the bullets
			if (b.collide(a)) { // if the bullet collies with this alien
				a.die(); // kill the alien
				b.setOffScreen(); // remove the bullet on the canvas
				return true;
			}
			else if (b.getPosY() >= 1){ // if the bullet flies out of the screen
				b.setOffScreen(); // remove the bullet on the canvas
			}
		}
		return false;
	}
	
	/**
	 * Check the status of bullets to prevent concurrent modification errors.
	 * @param bullets	the bullets list
	 */
	private void bulletCheck(LinkedList<Bullet> bullets) {
		Iterator<Bullet> bulletIter = bullets.iterator(); // create an iterator of bullets list
		while (bulletIter.hasNext()) { // while there's still bullet in the iterator
		    Bullet b = bulletIter.next(); // get the next bullet
		    if (b.getIsOffScreen()) { // if this bullet is off the screen
		    	bulletIter.remove(); // remove this bullet from iterator
		    	move.remove(b); // remove this bullet from movables
		    }
		}
	}
	
	/**
	 * Play the game.
	 */
	public void play(){
		// draw the scores on the canvas
		drawBoard(scoreA, scoreB);
		// for elements in the list of movables, update its position and draw it on canvas
		for (Moveable m : move) { // for elements in the list of movables (aka. players, aliens, bullets)
			m.move(); // update the element's new position
			m.draw(); // draw the element on the canvas
		}
		
		// shoot the bullets if the player is shooting; maximum bullet number is 3
		// the fire method is used to handle this behavior of two players
		fire(playerA, bulletsA);
		fire(playerB, bulletsB);

		// handle the collisions between player and aliens, also between aliens and bullets
		// the playerCollide and bulletCollide methods are used to handle these behaviors
		// if the player collides with any alien, kills the player, decrease the score and 
		// 		remove the player out of the screen if the player runs out of lives
		// if the bullet from one player collides with any alien, kills the alien, increase the score and 
		//		remove the bullets from canvas; if the bullets flies out of the screen itself, also remove
		//		it out of the canvas
		/*CODE A*/ // start
		for (Alien a : aliens) { // for each alien in the aliens
			// handle the collision event between player and this alien 
			// (see the javadoc and comments of this method)
			// and decrease the score corresponding to the player
			if (playerCollide(playerA, a)) scoreA -= 10; 
			if (playerCollide(playerB, a)) scoreB -= 10;
			// handle the collision event between bullets and this alien 
			// (see the javadoc and comments of this method)
			// and increase the score corresponding to the player
			if (bulletCollide(bulletsA, a)) scoreA += 50;
			if (bulletCollide(bulletsB, a)) scoreB += 50; 
		}
		/*CODE A*/ //end
		
		// Used to prevent concurrent modification errors
		Iterator<Alien> alienIter = aliens.iterator(); // create an iterator of aliens list
		while (alienIter.hasNext()) { // while there's still alien in the iterator
		    Alien a = alienIter.next(); // get the next alien
		    if (!a.isAlive()) { // if this alien is dead
		    	alienIter.remove(); // remove this alien from iterator
		    	move.remove(a); // remove this alien from movables
		    }
		}
		
		// use the same method used to check aliens to check bullets
		bulletCheck(bulletsA);
		bulletCheck(bulletsB);
		
		// level up the game if all the aliens are dead, and show everything on the canvas
		levelUp(); // level up the game if all the aliens are dead, and add new aliens
		StdDraw.show(60); // show everything on the canvas every 60ms
	}
	
	/**
	 * Level up the game by increasing the speed of aliens, if all the aliens are dead.
	 */
	public void levelUp() {
		if (aliens.isEmpty()) {
			alienSpeed *= 1.5;
			addAliens();
		}
	}
	
	/**
	 * Draw the game over screen on the canvas.
	 * @param highestScoreA		highest score of playerA
	 * @param highestScoreB		highest score of playerB
	 * @param numPlay			total number of gameplay
	 */
	public void drawGameEnd(int highestScoreA, int highestScoreB, int numPlay) {
		StdDraw.clear();
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledRectangle(0, 0, 1, 1);
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.text(0, 0.3, "GAME OVER");
		StdDraw.textLeft(-0.9, 0.2, "Player A Score: " + scoreA);
		StdDraw.textRight(0.9, 0.2, "Player B Score: " + scoreB);
		StdDraw.textLeft(-0.9, 0.1, "Player A Highest: " + highestScoreA);
		StdDraw.textRight(0.9, 0.1, "Player B Highest: " + highestScoreB);
		StdDraw.text(0, 0, "Total Game Play: " + numPlay);
		StdDraw.text(0, -0.1, "PRESS R TO REPLAY");
		StdDraw.show(100);
	}
	
	/**
	 * Draw the game start screen on the canvas.
	 */
	public void drawGameStart() {
		StdDraw.clear();
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledRectangle(0, 0, 1, 1);
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.text(0, 0, "PRESS ANY KEY TO START");
	}

	public static void main(String[] args) {
		
		int highestScoreA = -30;
		int highestScoreB = -30; // the lowest possible score is -30
		int numPlay = 0; // the number of gameplay
		
		StdDraw.setScale(-1, 1); // set canvas scales
		Game game = new Game(); // create a new game
		game.drawGameStart(); // draw the welcome screen
		
		while (!StdDraw.hasNextKeyTyped()) {
			StdDraw.pause(100); // waiting for the next key typed
		}
		
		while (true) {
			
			while (!game.isOver()){ // while the game is not over
				game.play(); // continue to play the game
			}
			
			highestScoreA = game.scoreA > highestScoreA ? game.scoreA : highestScoreA;
			highestScoreB = game.scoreB > highestScoreB ? game.scoreB : highestScoreB; // record the highest score
			numPlay++; // increase the number of gameplay by one
			
			game.drawGameEnd(highestScoreA, highestScoreB, numPlay); // draw the game over screen if the game is over
			
			while (!StdDraw.hasNextKeyTyped()) {
				StdDraw.pause(100); // waiting for the next key typed
			}
			
			while (StdDraw.hasNextKeyTyped()) {
				if (StdDraw.isKeyPressed(KeyEvent.VK_R)) {
					game = new Game();
					break; // if R typed, create a new game and restart
				}
				StdDraw.pause(100); // if R is not typed, continue waiting
			}
			
		}
		
	}

}
