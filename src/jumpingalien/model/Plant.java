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
	 */
	public Plant(int x, int y, Sprite[] sprites) {
		setLocationX(x);
		setLocationY(y);
		setSprites(sprites);
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
}
