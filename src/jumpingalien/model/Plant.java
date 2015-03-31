package jumpingalien.model;

import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.Basic;

public class Plant {

	/**
	 * 
	 * @param x
	 * 			the x location of this Plant
	 * @param y
	 * 			the y location of this Plant
	 * @param sprites
	 * 			the sprites for this Plant
	 * @effect 	...
	 * 			| setLocationX(x)
	 * @effect	...
	 * 			| setLocationY(y)
	 * @effect	...
	 * 			| setSprites(sprites)
	 * @effect 	...
	 * 			| setCurrentSprite(getSprites()[0])
	 * @effect	...
	 * 			| setHorizontalVelocity(-0.5)
	 */
	public Plant(int x, int y, Sprite[] sprites) {
		setLocationX(x);
		setLocationY(y);
		setSprites(sprites);
		setCurrentSprite(sprites[0]);
		setHorizontalVelocity(-0.5);
	}
	
	/**
	 * Returns the x location of this Plant
	 */
	@Basic
	private double getLocationX() {
		return this.locationX;
	}
	
	/**
	 * Sets the x location of this Plant to the given x
	 * @param x
	 * 			the new x location for this Plant
	 * @post	the x location of this Plant is equal to the given x
	 * 			| new.getLocationX() = x
	 */
	private void setLocationX(double x) {
		this.locationX = x;
	}
	
	/**
	 * This variable contains the x location of this plant.
	 */
	private double locationX;
	
	
	/**
	 * Returns the y location of this Plant
	 */
	@Basic
	private double getLocationY() {
		return this.locationY;
	}
	
	/**
	 * Sets the y location of this Plant to the given y
	 * @param y
	 * 			the new y location for this Plant
	 * @post	the y location of this Plant is equal to the given y
	 * 			| new.getLocationY() = y
	 */
	private void setLocationY(double y) {
		this.locationY = y;
	}
	
	/**
	 * This variable contains the y location of this plant.
	 */
	private double locationY;
	
	/**
	 * Returns the location of this Plant
	 * @return	returns an array of integers, the first element is the x location, the second element is the y location
	 * 			| {(int) getLocationX(), (int) getLocationY()}
	 */
	public int[] getLocation() {
		int[] location = new int[2];
		location[0] = (int) getLocationX();
		location[1] = (int) getLocationY();
		return location;
	}
	
	/**
	 * Return the sprites of this Plant
	 */
	@Basic
	private Sprite[] getSprites() {
		return this.sprites;
	}
	
	/**
	 * Set the sprites of this Plant to the given sprites
	 * @param sprites
	 * 			the new sprites for this Plant
	 * @post	the sprites of this Plant is equal to the given sprites
	 * 			| new.getSprites() = sprites
	 */
	private void setSprites(Sprite[] sprites) {
		this.sprites = sprites;
	}
	
	/**
	 * This array holds the sprites of this Plant
	 */
	private Sprite[] sprites;
	
	/**
	 * Returns the current sprite of this Plant
	 */
	@Basic
	public Sprite getCurrentSprite() {
		return this.currentSprite;
	}
	
	/**
	 * Sets the current sprite to the given sprite
	 * @param sprite
	 * 			the sprite to be set as current sprite
	 * @post
	 * 			new.getCurrentSprite() = sprite
	 */
	private void setCurrentSprite(Sprite sprite) {
		this.currentSprite = sprite;
	}
	
	/**
	 * This variable contains the current sprite
	 */
	private Sprite currentSprite;
	
	
	/**
	 * @return the horizontalVelocity
	 */
	@Basic
	private double getHorizontalVelocity() {
		return horizontalVelocity;
	}

	/**
	 * @param horizontalVelocity the horizontalVelocity to set
	 * @post	...
	 * 			| new.getHorizontalVelocity() == horizontalVelocity
	 */
	private void setHorizontalVelocity(double horizontalVelocity) {
		this.horizontalVelocity = horizontalVelocity;
	}
	
	/**
	 * This variable contains the current horizontal velocity of this Plant
	 */
	private double horizontalVelocity;
	
	/**
	 * Sets the timer of this Plant to the given time
	 * @param time
	 * 			the value to set to the timer
	 * @post
	 * 			| new.getTimer() == time
	 */
	private void setTimer(double time) {
		this.timer = time;
	}
	
	/**
	 * Returns the value of the timer of this Plant
	 */
	@Basic
	private double getTimer() {
		return this.timer;
	}
	
	/**
	 * This variable holds the value of the timer.
	 */
	private double timer;

	public void advanceTime(double dt) {
		setTimer(getTimer()+dt);	
	}
}
