package jumpingalien.model;

import be.kuleuven.cs.som.annotate.*;
import jumpingalien.util.Sprite;

public class Shark {
	/**
	 * @param x
	 * @param y
	 * @param sprites
	 * @effect 	...
	 * 			| setLocationX(x)
	 * @effect 	...
	 * 			| setLocationY(y)
	 * @effect 	...
	 * 			| setSprites(sprites)
	 */
	public Shark (int x, int y, Sprite[] sprites){
		setLocationX(x);
		setLocationY(y);
		setSprites(sprites);
		setCurrentSpriteIndex(0);
	}
	
	/**
	 * Returns the current X coordinate of this shark
	 */
	@Basic
	private double getLocationX() {
		return locationX;
	}
	/**
	 * 
	 * @param 	locationX
	 * 			the new X coordinate for this shark
	 * @post 	...
	 * 			| new.getLocationX() = locationX 
	 */
	private void setLocationX(double locationX) {
		this.locationX = locationX;
	}
	/**
	 * this variable contains the current X coordinate for this Shark
	 */
	private double locationX;
	
	/**
	 * Returns the current Y coordinate of this shark
	 */
	@Basic
	private double getLocationY() {
		return locationY;
	}
	/**
	 * 
	 * @param 	locationY
	 * 			the new T coordinate for this shark
	 * @post 	...
	 * 			| new.getLocationY() = locationY
	 */
	private void setLocationY(double locationY) {
		this.locationY = locationY;
	}
	
	/**
	 * this variable contains the current Y coordinate for this Shark
	 */
	private double locationY;
	
	/**
	 * Returns the location of this Shark
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
	 * Return the current list of sprites for this shark
	 */
	private Sprite[] getSprites() {
		return sprites;
	}
	/**
	 * 
	 * @param 	sprites
	 * 			the list of sprites for this shark
	 * @post 	...
	 * 			| new.getSprites() = sprites
	 */
	private void setSprites(Sprite[] sprites) {
		this.sprites = sprites;
	}

	private Sprite[] sprites;
	
	/**
	 * Returns the current sprite index of this shark
	 */
	@Basic
	private int getCurrentSpriteIndex() {
		return this.currentSpriteIndex;
	}
	
	/**
	 * 
	 * @param index
	 * @post	
	 * 			| new.getCurrentSpriteIndex() = index
	 */
	private void setCurrentSpriteIndex(int index) {
		this.currentSpriteIndex = index;
	}
	
	private int currentSpriteIndex;
	
	/**
	 * Returns the current sprite of this Shark
	 */
	@Basic
	public Sprite getCurrentSprite() {
		return getSprites()[getCurrentSpriteIndex()];
	}
	
	/**
	 * Returns the world where this shark is in
	 * @return 	...
	 * 			|result == this.world
	 */
	@Basic
	private World getWorld() {
		return world;
	}
	
	/**
	 * 
	 * @param 	world
	 * @post 	...
	 * 			| new.getWorld() = world			
	 */
	void setWorld(World world) {
		if((world != null) && (world.hasAsShark(this)))
			this.world = world;		
	}
	
	/**
	 * Returns whether this shark is already in a world or not
	 * @return	...
	 * 			| getWorld() != null
	 */
	boolean hasAWorld(){
		return getWorld() != null;
	}
	
	/**
	 * This variable contains the world where this shark is in
	 */
	private World world;
	
}
