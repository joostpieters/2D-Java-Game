package jumpingalien.model;

import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.Basic;

public abstract class GameObjects implements CollisionDetect {
	
	/**
	 * @return will return terminated
	 * 			|result == this.terminated
	 */
	boolean isTerminated() {
		return this.terminated;
	}
	
	/**
	 * 
	 * @param value
	 * @post	terminated will equal the given value
	 * 			|new.isTerminated() = value
	 */
	protected void setTerminated(boolean value) {
		this.terminated = value;
	}
	
	/*
	 * This variable contains whether the given Game Object is terminated or not
	 */
	private boolean terminated;
	
	/**
	 * Returns the x location of this Game Object
	 */
	@Basic
	protected double getLocationX() {
		return this.locationX;
	}

	/**
	 * Sets the x location of this Game Object to the given x
	 * 
	 * @param x
	 *            the new x location for this Game Object
	 * @post the x location of this Game Object is equal to the given x |
	 *       new.getLocationX() = x
	 */
	protected void setLocationX(double x) {
		this.locationX = x;
	}

	/**
	 * This variable contains the x location of this Game Object.
	 */
	private double locationX;

	/**
	 * Returns the y location of this Game Object
	 */
	@Basic
	protected double getLocationY() {
		return this.locationY;
	}

	/**
	 * Sets the y location of this Game Object to the given y
	 * 
	 * @param y
	 *            the new y location for this Game Object
	 * @post the y location of this Game Object is equal to the given y |
	 *       new.getLocationY() = y
	 */
	protected void setLocationY(double y) {
		this.locationY = y;
	}

	/**
	 * This variable contains the y location of this Game Object.
	 */
	private double locationY;

	/**
	 * Returns the location of this Game Object
	 * 
	 * @return returns an array of integers, the first element is the x
	 *         location, the second element is the y location | {(int)
	 *         getLocationX(), (int) getLocationY()}
	 */
	public int[] getLocation() {
		int[] location = new int[2];
		location[0] = (int) getLocationX();
		location[1] = (int) getLocationY();
		return location;
	}
	
	/**
	 * Return the sprites of this Game Object
	 */
	@Basic
	protected Sprite[] getSprites() {
		return this.sprites;
	}

	/**
	 * Set the sprites of this Game Object to the given sprites
	 * 
	 * @param sprites
	 *            the new sprites for this Game Object
	 * @post the sprites of this Game Object is equal to the given sprites |
	 *       new.getSprites() = sprites
	 */
	protected void setSprites(Sprite[] sprites) {
		this.sprites = sprites;
	}

	/**
	 * This array holds the sprites of this Game Object
	 */
	private Sprite[] sprites;
	
	/**
	 * This variable contains the amount of hitpoints for this Game Object
	 */
	private int hitPoints;
	
	/**
	 * 
	 * @return 	...
	 * 			| result == this.hitPoints
	 */
	protected int getHitPoints() {
		return hitPoints;
	}

	/**
	 * 
	 * @param hitPoints
	 * @post 	...
	 * 			|new.getHitPoints = hitPoints
	 */
	protected void setHitPoints(int hitPoints) {
 		if(hitPoints <= 0){
			this.hitPoints = 0;
 			setDeath(true);
 		} else if(
 			hitPoints > getMaxHitPoints()) {
			this.hitPoints = getMaxHitPoints();
 		} else {
			this.hitPoints = hitPoints;
 		}
 	}
	
	/**
	 * 
	 * @return 	...
	 * 			| result == this.isDeath
	 */
	protected boolean isDead() {
		return isDead;
	}

	/**
	 * 
	 * @param isDead
	 * @post 	...
	 * 			|new.isDead() = isDead
	 */
	void setDeath(boolean isDead) {
		this.isDead = isDead;
	}
	
	/**
	 * This variable indicates whether this Game Object is dead or not
	 */
	private boolean isDead;

	/**
	 * 
	 * @return	...
	 * 			| result == this.timeDeath
	 */
	protected double getTimeDead() {
		return timeDead;
	}

	/**
	 * 
	 * @param timeDead
	 * @post 	...
	 * 			|new.getTimeDead() == timeDead
	 */
	protected void setTimeDead(double timeDead) {
		this.timeDead = timeDead;
	}
	/**
	 * This variable containts the time that this Game Object is dead
	 */
	private double timeDead;

	abstract int getMaxHitPoints();
}
