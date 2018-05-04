package lab10;

/**
 * Space Invader.
 * 
 * This is the ISpaceInvader interface for Player, Alien, Power and Bullet classes.
 * 
 * @author bambrow
 *
 */

public interface ISpaceInvador {

	/**
	 * get rx.
	 * @return
	 */
	public double getRx();
	
	/**
	 * get ry.
	 * @return
	 */
	public double getRy();
	
	/**
	 * get vx.
	 * @return
	 */
	public double getVx();
	
	/**
	 * get vy.
	 * @return
	 */
	public double getVy();
	
	/**
	 * get radius.
	 * @return
	 */
	public double getRadius();
	
	/**
	 * update the position.
	 */
	public void update();
	
	/**
	 * draw the object.
	 */
	public void draw();
	
}
