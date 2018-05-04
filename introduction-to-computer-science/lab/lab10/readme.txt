/**
 * Space Invader.
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