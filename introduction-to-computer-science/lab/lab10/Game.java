package lab10;

import sedgewick.StdDraw;
import sedgewick.StdRandom;

import java.util.ArrayList;
import java.util.List;
import lab10.ArcadeKeys;

/**
 * Space Invader.
 * 
 * This is the main program.
 * 
 * Some parts of the code are from my GitHub repo:
 * https://github.com/bambrow/java-notes/tree/master/games
 * 
 * I developed three games in this semester and put them in this repo.
 * Although not exactly the same, these games have parts similar.
 * 
 * In this game, there are two players. Each player can move and shoot separately.
 * Player 1: Up: W 			Down: S 			Left: A 			Right: D				Shoot: F
 * Player 2: Up: Up Arrow	Down: Down Arrow	Left: Left Arrow	Right: Right Arrow		Shoot: K
 * 
 * The players have 5 levels. The default level is 2, which means the player can shoot 2 bullets once.
 * The bottom level is 1, and the top level is 5.
 * 
 * The players have 5 lives at the beginning. There are 10 lives at most for each player.
 * 
 * There are two kinds of enemies. Small and Big.
 * Small enemies can shoot missile only one time, at random direction. Small enemies only have 1 life.
 * Big enemies can shoot 5 missiles once, and have 25 lives. Which means, players should hit it 25 times to destroy it.
 * 
 * When a player hits an enemy or a missile from enemies, he/she loses one life. 
 * At the same time, all the enemies (only small ones) and bullets (only from the enemies) are cleared.
 * The player will be put at the birthplace once loses one life.
 * 
 * When either one of the players loses all his/her lives, and if the other player still have lives, the game continues.
 * The game ends only when all lives of all players are lost.
 * 
 * There's more. There are power ups showing in the battlefield.
 * When a player pick it up, he/she will receive one of the following affect randomly:
 * ship level + 1;
 * lives + 1;
 * score + 50.
 * 
 * Some other facts:
 * 1) When a player loses one life, he/she also loses one ship level.
 * 2) When a player hits a big enemy, he/she loses one life, and the big enemy loses 5 lives.
 * 3) You cannot hurt your partner.
 * 4) The enemies will appear faster when your score gets higher.
 * 5) Power up does not expire until no one picks it up. One power up at most at the same time.
 * 6) One big enemy at most at the same time.
 * 
 * Have fun!
 * 
 * @author bambrow
 *
 */

public class Game {
	
	/**
	 * move the player based on the key pressed.
	 * @param player
	 * @param i		player number
	 */
	public static void keyMove(Player player, int i) {
		if (ArcadeKeys.isKeyPressed(i, ArcadeKeys.KEY_UP)) {
			player.setVy(0.018);
		} else if (ArcadeKeys.isKeyPressed(i, ArcadeKeys.KEY_DOWN)) {
			player.setVy(-0.018);
		} else if (ArcadeKeys.isKeyPressed(i, ArcadeKeys.KEY_LEFT)) {
			player.setVx(-0.018);
		} else if (ArcadeKeys.isKeyPressed(i, ArcadeKeys.KEY_RIGHT)) {
			player.setVx(0.018);
		} 
	}
	
	/**
	 * let the player shoot based on the key pressed.
	 * @param player
	 * @param i		player number
	 * @param isKeyPressed
	 * @param bullets
	 */
	public static void keyShoot(Player player, int i, boolean[] isKeyPressed, List<Bullet> bullets) {
		if (ArcadeKeys.isKeyPressed(i, ArcadeKeys.KEY_B) && (!isKeyPressed[i])) {
			if (player.getRank() == 1) {
				bullets.add(new Bullet(player.getRx() + player.getRadius() + 0.005, player.getRy()));
			} else if (player.getRank() == 2) {
				bullets.add(new Bullet(player.getRx() + player.getRadius() + 0.005, player.getRy() - 0.01));
				bullets.add(new Bullet(player.getRx() + player.getRadius() + 0.005, player.getRy() + 0.01));
			} else if (player.getRank() == 3) {
				bullets.add(new Bullet(player.getRx() + player.getRadius() + 0.005, player.getRy() - 0.015));
				bullets.add(new Bullet(player.getRx() + player.getRadius() + 0.005, player.getRy()));
				bullets.add(new Bullet(player.getRx() + player.getRadius() + 0.005, player.getRy() + 0.015));
			} else if (player.getRank() == 4) {
				bullets.add(new Bullet(player.getRx() + player.getRadius() + 0.005, player.getRy() - 0.008));
				bullets.add(new Bullet(player.getRx() + player.getRadius() + 0.005, player.getRy() + 0.008));
				bullets.add(new Bullet(player.getRx() + player.getRadius() + 0.005, player.getRy() - 0.0015, 0.02, 0.002));
				bullets.add(new Bullet(player.getRx() + player.getRadius() + 0.005, player.getRy() + 0.0015, 0.02, -0.002));
			} else if (player.getRank() == 5) {
				bullets.add(new Bullet(player.getRx() + player.getRadius() + 0.005, player.getRy() - 0.0012, 0.02, 0.001));
				bullets.add(new Bullet(player.getRx() + player.getRadius() + 0.005, player.getRy()));
				bullets.add(new Bullet(player.getRx() + player.getRadius() + 0.005, player.getRy() + 0.0012, 0.02, -0.001));
				bullets.add(new Bullet(player.getRx() + player.getRadius() + 0.005, player.getRy() - 0.0018, 0.02, 0.002));
				bullets.add(new Bullet(player.getRx() + player.getRadius() + 0.005, player.getRy() + 0.0018, 0.02, -0.002));
			}
			isKeyPressed[i] = true;
		}
		if (!ArcadeKeys.isKeyPressed(i, ArcadeKeys.KEY_B) ) {
			isKeyPressed[i] = false;
		}
	}
	
	/**
	 * return the distance between two dots.
	 * @param mx
	 * @param my
	 * @param rx
	 * @param ry
	 * @return
	 */
	public static double distance(double mx, double my, double rx, double ry) {
		return Math.sqrt(Math.pow((mx - rx), 2) + Math.pow((my - ry), 2));
	}
	
	/**
	 * draw all aliens.
	 * @param aliens
	 */
	public static void drawAliens(List<Alien> aliens) {
		for (Alien alien : aliens) {
			alien.draw();
		}
	}
	
	/**
	 * draw all bullets.
	 * @param bullets
	 */
	public static void drawBullets(List<Bullet> bullets) {
		for (Bullet bullet : bullets) {
			bullet.draw();
		}
	}
	
	/**
	 * draw all powers.
	 * @param powers
	 */
	public static void drawPowers(List<Power> powers) {
		if (powers.isEmpty()) {
			return;
		} else {
			for (Power power : powers) {
				power.draw();
			}
		}
	}
	
	/**
	 * draw alienB.
	 * @param alienB
	 */
	public static void drawAlienB(List<AlienB> alienB) {
		if (alienB.isEmpty()) {
			return;
		} else {
			for (AlienB alien : alienB) {
				alien.draw();
			}
		}
	}
	
	/**
	 * update all aliens and determine if they should shoot.
	 * @param aliens
	 * @param bullets
	 */
	public static void updateAliens(List<Alien> aliens, List<Bullet> bullets) {
		for (Alien alien : aliens) {
			alien.update();
			if (Math.abs(alien.getRx() - 0.75) < 0.02 && !alien.isShoot()) {
				double dest = StdRandom.uniform(0.0, 1.0);
				double time = alien.getRx() / 0.018;
				bullets.add(new Bullet(alien.getRx() - alien.getRadius() - 0.005, alien.getRy(), -0.018, (dest - alien.getRy()) / time));
				alien.setShoot(true);
			}
		}
		for (Alien alien : aliens) {
			if (alien.getRx() < -0.25 || alien.getRy() < -0.25 || alien.getRy() > 1.25) {
				aliens.remove(alien);
				break;
			}
		}
	}
	
	/**
	 * update the position of all bullets.
	 * @param bullets
	 */
	public static void updateBullets(List<Bullet> bullets) {
		for (Bullet bullet : bullets) {
			bullet.update();
		}
		for (Bullet bullet : bullets) {
			if (bullet.getRx() < -0.25 || bullet.getRx() > 1.25 || bullet.getRy() < -0.25 || bullet.getRy() > 1.25) {
				bullets.remove(bullet);
				break;
			}
		}
	}
	
	/**
	 * update the position of powers.
	 * @param powers
	 */
	public static void updatePowers(List<Power> powers) {
		if (powers.isEmpty()) {
			return;
		} else {
			for (Power power : powers) {
				power.update();
			}
		}
	}
	
	/**
	 * update alienB and determine if it should shoot.
	 * @param alienB
	 * @param bullets
	 * @param time	time of game passed
	 */
	public static void updateAlienB(List<AlienB> alienB, List<Bullet> bullets, int time) {
		if (alienB.isEmpty()) {
			return;
		} else {
			for (AlienB alien : alienB) {
				alien.update();
				if (time % 30 == 0) {
					bullets.add(new Bullet(alien.getRx() - alien.getRadius() - 0.005, alien.getRy(), -0.015, 0));
					bullets.add(new Bullet(alien.getRx() - alien.getRadius() - 0.005, alien.getRy() + 0.012, -0.015, 0.0025));
					bullets.add(new Bullet(alien.getRx() - alien.getRadius() - 0.005, alien.getRy() + 0.012, -0.015, -0.0025));
					bullets.add(new Bullet(alien.getRx() - alien.getRadius() - 0.005, alien.getRy() + 0.022, -0.015, 0.005));
					bullets.add(new Bullet(alien.getRx() - alien.getRadius() - 0.005, alien.getRy() + 0.022, -0.015, -0.005));
				}
			}
		}
	}
	
	/**
	 * determine if an alien collides with player.
	 * @param alien
	 * @param player
	 * @return
	 */
	public static boolean collide(Alien alien, Player player) {
		if (distance(player.getRx(), player.getRy(), alien.getRx(), alien.getRy()) < player.getRadius() + alien.getRadius()) {
			return true;
		}
		return false;
	}

	/**
	 * determine if any alien collides with player.
	 * @param aliens
	 * @param player
	 * @return
	 */
	public static boolean collideGroup(List<Alien> aliens, Player player) {
		for (Alien alien : aliens) {
			if (collide(alien, player)) {
				aliens.remove(alien);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * determine if alienB collides with player.
	 * @param alien
	 * @param player
	 * @return
	 */
	public static boolean collide(AlienB alien, Player player) {
		if (distance(player.getRx(), player.getRy(), alien.getRx(), alien.getRy()) < player.getRadius() + alien.getRadius()) {
			return true;
		}
		return false;
	}
	
	/**
	 * determine if alienB collides with player.
	 * @param alien
	 * @param player
	 * @return
	 */
	public static boolean collideAlienB(List<AlienB> alien, Player player) {
		if (alien.isEmpty()) {
			return false;
		}
		for (AlienB alienB : alien) {
			if (collide(alienB, player)) {
				if (alienB.getLives() <= 5) {
					alien.clear();
					return true;
				}
				alienB.setLives(alienB.getLives() - 5);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * determine if a bullet from alien collides with player.
	 * @param bullet
	 * @param player
	 * @return
	 */
	public static boolean collide(Bullet bullet, Player player) {
		if (distance(player.getRx(), player.getRy(), bullet.getRx(), bullet.getRy()) < player.getRadius() + bullet.getRadius()) {
			return true;
		}
		return false;
	}
	
	/**
	 * determine if any bullet from aliens collides with player.
	 * @param bullets
	 * @param player
	 * @return
	 */
	public static boolean collidePlayer(List<Bullet> bullets, Player player) {
		for (Bullet bullet : bullets) {
			if (collide(bullet, player)) {
				bullets.remove(bullet);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * determine if power collides with player.
	 * @param power
	 * @param player
	 * @return
	 */
	public static boolean collide(Power power, Player player) {
		if (distance(player.getRx(), player.getRy(), power.getRx(), power.getRy()) < player.getRadius() + power.getRadius()) {
			return true;
		}
		return false;
	}
	
	/**
	 * determine if power collides with player.
	 * @param power
	 * @param player
	 * @return
	 */
	public static boolean collidePower(List<Power> power, Player player) {
		if (power.isEmpty()) {
			return false;
		}
		for (Power p : power) {
			if (collide(p, player)) {
				power.remove(p);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * determine if an alien collides with a bullet.
	 * @param alien
	 * @param bullet
	 * @return
	 */
	public static boolean collide(Alien alien, Bullet bullet) {
		if (distance(bullet.getRx(), bullet.getRy(), alien.getRx(), alien.getRy()) < bullet.getRadius()
				+ alien.getRadius()) {
			return true;
		}
		return false;
	}
	
	/**
	 * determine if any alien collides with a bullet.
	 * @param aliens
	 * @param bullets
	 * @return
	 */
	public static boolean collideGroup(List<Alien> aliens, List<Bullet> bullets) {
		for (Alien alien : aliens) {
			for (Bullet bullet : bullets) {
				if (collide(alien, bullet)) {
					aliens.remove(alien);
					bullets.remove(bullet);
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * determine if alienB collides with a bullet.
	 * @param alien
	 * @param bullet
	 * @return
	 */
	public static boolean collide(AlienB alien, Bullet bullet) {
		if (distance(bullet.getRx(), bullet.getRy(), alien.getRx(), alien.getRy()) < bullet.getRadius()
				+ alien.getRadius()) {
			return true;
		}
		return false;
	}
	
	/**
	 * determine if alienB collides with a bullet.
	 * @param alien
	 * @param bullets
	 * @return
	 */
	public static boolean collideAlienB(List<AlienB> alien, List<Bullet> bullets) {
		if (alien.isEmpty()) {
			return false;
		}
		for (AlienB alienB : alien) {
			for (Bullet bullet : bullets) {
				if (collide(alienB, bullet)) {
					bullets.remove(bullet);
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * update the score of players.
	 * @param score
	 */
	public static void updateScore(int score) {
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(0.5, 0.0, "Score: " + score);
	}
	
	/**
	 * update the lives of players.
	 * @param playerALives
	 * @param playerBLives
	 */
	public static void updateLives(int playerALives, int playerBLives) {
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.textRight(1.0, 0.0, "Player 1");
		StdDraw.textRight(1.0, 1.0, "Player 2");
		if (playerALives == 0) {
			StdDraw.textRight(1.0, 0.05, "Game Over");
		} else {
			StdDraw.textRight(1.0, 0.05, "Lives: " + playerALives);
		}
		if (playerBLives == 0) {
			StdDraw.textRight(1.0, 0.95, "Game Over");
		} else {
			StdDraw.textRight(1.0, 0.95, "Lives: " + playerBLives);
		}	
	}
	
	/**
	 * determine how often the aliens generate.
	 * @param score
	 * @return
	 */
	public static int getInterval(int score) {
		if (score >= 1000)
			return 12;
		else if (score >= 600)
			return 15;
		else if (score >= 400)
			return 17;
		else if (score >= 300)
			return 20;
		else if (score >= 200)
			return 25;
		else if (score >= 100)
			return 30;
		else
			return 40;
	}
	
	/**
	 * main function.
	 * @param args
	 */
	public static void main(String[] args) {
		
		StdDraw.setXscale(0, 1.0);
		StdDraw.setYscale(0, 1.0);

		StdDraw.clear(StdDraw.LIGHT_GRAY);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.text(0.5, 0.8, "SPACE INVADER");
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(0.5, 0.7, "Press any key to start");
		StdDraw.textLeft(0.2, 0.5, "Player 1");
		StdDraw.textLeft(0.2, 0.45, "Up: W");
		StdDraw.textLeft(0.2, 0.4, "Down: S");
		StdDraw.textLeft(0.2, 0.35, "Left: A");
		StdDraw.textLeft(0.2, 0.3, "Right: D");
		StdDraw.textLeft(0.2, 0.25, "Shoot: F");
		StdDraw.textLeft(0.6, 0.5, "Player 2");
		StdDraw.textLeft(0.6, 0.45, "Up: Up Arrow");
		StdDraw.textLeft(0.6, 0.4, "Down: Down Arrow");
		StdDraw.textLeft(0.6, 0.35, "Left: Left Arrow");
		StdDraw.textLeft(0.6, 0.3, "Right: Right Arrow");
		StdDraw.textLeft(0.6, 0.25, "Shoot: K");
		StdDraw.text(0.5, 0.1, "By Weiqiang Li & Mingyu Cao");
		
		while (true) {

			while (!StdDraw.hasNextKeyTyped()) {
				StdDraw.pause(100);
			}

			StdDraw.clear(StdDraw.LIGHT_GRAY);

			Player playerA = new Player(0.05, 0.25, StdDraw.BLACK);
			Player playerB = new Player(0.05, 0.75, StdDraw.WHITE);
			List<Alien> aliens = new ArrayList<Alien>();
			List<Bullet> bullets = new ArrayList<Bullet>();
			List<Bullet> alienBullets = new ArrayList<Bullet>();
			List<Power> powers = new ArrayList<Power>();
			List<AlienB> alienB = new ArrayList<AlienB>();
			
			int score = 0;
			int playerALives = 5;
			int playerBLives = 5;
			int spawn = 0;
			
			boolean[] isKeyPressed = new boolean[2];
			isKeyPressed[0] = isKeyPressed[1] = true;

			while (true) {

				StdDraw.clear(StdDraw.LIGHT_GRAY);
				
				if (playerALives == 0 && playerBLives == 0) {
					break;
				}
				
				if (collideGroup(aliens, bullets)) {
					score += 3;
				}
				
				if (collideAlienB(alienB, bullets)) {
					score += 2;
					for (AlienB alien : alienB) {
						if (alien.getLives() <= 1) {
							alienB.clear();
							break;
						}
						alien.setLives(alien.getLives() - 1);
					}
				}
				
				if (collideGroup(aliens, playerA) || collidePlayer(alienBullets, playerA) || collideAlienB(alienB, playerA)) {
					playerALives -= 1;
					playerA.setRx(0.05);
					playerA.setRy(0.25);
					aliens.clear();
					alienBullets.clear();
					if (playerA.getRank() > 1) {
						playerA.setRank(playerA.getRank() - 1);
					}
				}
				
				if (collideGroup(aliens, playerB) || collidePlayer(alienBullets, playerB) || collideAlienB(alienB, playerB)) {
					playerBLives -= 1;
					playerB.setRx(0.05);
					playerB.setRy(0.75);
					aliens.clear();
					alienBullets.clear();
					if (playerB.getRank() > 1) {
						playerB.setRank(playerB.getRank() - 1);
					}
				}
				
				if (collidePower(powers, playerA)) {
					if (playerA.getRank() >= 5 && playerALives >= 10) {
						score += 50;
					} else if (spawn % 2 == 0) {
						if (playerA.getRank() >= 5) {
							score += 50;
						} else {
							playerA.setRank(playerA.getRank() + 1);
						}
					} else if (spawn % 2 == 1) {
						if (playerALives >= 10) {
							score += 50;
						} else {
							playerALives++;
						}
					}
				}
				
				if (collidePower(powers, playerB)) {
					if (playerB.getRank() >= 5 && playerBLives >= 10) {
						score += 50;
					} else if (spawn % 2 == 0) {
						if (playerB.getRank() >= 5) {
							score += 50;
						} else {
							playerB.setRank(playerB.getRank() + 1);
						}
					} else if (spawn % 1 == 1) {
						if (playerBLives >= 10) {
							score += 50;
						} else {
							playerBLives++;
						}
					}
				}
				
				if (spawn % getInterval(score) == 0) {
					aliens.add(new Alien());
				}
				
				if (spawn > 0 && spawn % 300 == 0 && powers.isEmpty()) {
					powers.add(new Power());
				}
				
				if (spawn > 0 && spawn % 500 == 0 && alienB.isEmpty()) {
					alienB.add(new AlienB());
				}
				
				if (playerALives == 0) {
					playerA.setRx(-0.5);
					playerA.setRy(-0.5);
				} else {
					keyShoot(playerA, 0, isKeyPressed, bullets);
					playerA.reset();
					keyMove(playerA, 0);
					playerA.update();
					playerA.draw();
				}
				
				if (playerBLives == 0) {
					playerB.setRx(-0.5);
					playerB.setRy(-0.5);
				} else {
					keyShoot(playerB, 1, isKeyPressed, bullets);	
					playerB.reset();		
					keyMove(playerB, 1);
					playerB.update();
					playerB.draw();
				}
				
				updateAliens(aliens, alienBullets);
				updateBullets(bullets);
				updateBullets(alienBullets);
				updatePowers(powers);
				updateAlienB(alienB, alienBullets, spawn);
				
				drawAliens(aliens);
				drawBullets(bullets);
				drawBullets(alienBullets);
				drawPowers(powers);
				drawAlienB(alienB);
				
				updateScore(score);
				updateLives(playerALives, playerBLives);

				spawn++;
				
				StdDraw.show(30);

			}

			StdDraw.clear(StdDraw.LIGHT_GRAY);
			StdDraw.setPenColor(StdDraw.BLACK);
			updateScore(score);
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.text(0.5, 0.85, "SPACE INVADER");
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(0.5, 0.75, "Game over");
			StdDraw.text(0.5, 0.7, "Press any key to restart");
			StdDraw.textLeft(0.2, 0.5, "Player 1");
			StdDraw.textLeft(0.2, 0.45, "Up: W");
			StdDraw.textLeft(0.2, 0.4, "Down: S");
			StdDraw.textLeft(0.2, 0.35, "Left: A");
			StdDraw.textLeft(0.2, 0.3, "Right: D");
			StdDraw.textLeft(0.2, 0.25, "Shoot: F");
			StdDraw.textLeft(0.6, 0.5, "Player 2");
			StdDraw.textLeft(0.6, 0.45, "Up: Up Arrow");
			StdDraw.textLeft(0.6, 0.4, "Down: Down Arrow");
			StdDraw.textLeft(0.6, 0.35, "Left: Left Arrow");
			StdDraw.textLeft(0.6, 0.3, "Right: Right Arrow");
			StdDraw.textLeft(0.6, 0.25, "Shoot: K");
			StdDraw.show();

			while (StdDraw.hasNextKeyTyped()) {
				StdDraw.nextKeyTyped();
			}
		}
	}

}
