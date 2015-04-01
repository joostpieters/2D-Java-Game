package jumpingalien.model;

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
	}
	
	/**
	 * Returns the current X coordinate of this shark
	 */
	private int getLocationX() {
		return locationX;
	}
	/**
	 * 
	 * @param 	locationX
	 * 			the new X coordinate for this shark
	 * @post 	...
	 * 			| new.getLocationX() = locationX 
	 */
	private void setLocationX(int locationX) {
		this.locationX = locationX;
	}
	/**
	 * this variable contains the current X coordinate for this Shark
	 */
	private int locationX;
	/**
	 * Returns the current Y coordinate of this shark
	 */
	private int getLocationY() {
		return locationY;
	}
	/**
	 * 
	 * @param 	locationY
	 * 			the new T coordinate for this shark
	 * @post 	...
	 * 			| new.getLocationY() = locationY
	 */
	private void setLocationY(int locationY) {
		this.locationY = locationY;
	}
	
	/**
	 * this variable contains the current Y coordinate for this Shark
	 */
	private int locationY;
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
	 * 
	 * @param 	world
	 * @post 	...
	 * 			| new.getWorld() = world			
	 */
	void setWorld(World world) {
		if((world != null) && (world.hasAsShark(this)))
			this.world = world;		
	}
	
	private World world;
	
}
