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
	 * @effect	...
	 * 			| setHitpoints(100)
	 */
	public Shark (int x, int y, Sprite[] sprites){
		setLocationX(x);
		setLocationY(y);
		setSprites(sprites);
		setCurrentSpriteIndex(0);
		setHitpoints(100);
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
	
	@Basic
	private int getHitpoints() {
		return this.hitpoints;
	}
	
	/**
	 * 
	 * @param points
	 * @post	...
	 * 			| new.getHitpoints() = points
	 */
	private void setHitpoints(int points) {
		this.hitpoints = points;
	}
	
	/**
	 * this variable contains the amount of hitpoints for this shark
	 */
	private int hitpoints;
	
	/**
	 * @return 	...
	 * 			|result == this.velocityX
	 */
	private double getVelocityX() {
		return velocityX;
	}

	/**
	 * 
	 * @param 	velocityX
	 * @post	...
	 * 			|new.getVelocityX() == velocityX
	 */
	private void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}
	
	/**
	 * This variable contains the horizontal velocity of this shark
	 */
	private double velocityX;
	
	/**
	 * 
	 * @return	...
	 * 			| result == this.velocityX
	 */
	private double getVelocityY() {
		return velocityY;
	}

	/**
	 * 
	 * @param velocityY
	 * @post 	...
	 * 			| new.getVelocityY() == velocityY
	 */
	private void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}
	
	/**
	 * This variable contains the vertical velocity for this shark
	 */
	private double velocityY;
	
	
	/**
	 * 
	 * @return	...
	 * 			| result == this.acceleration
	 */
	private double getAccelerationX() {
		return accelerationX;
	}

	/**
	 * 
	 * @param accelerationX
	 * @post 	...
	 * 			| this.getAccelerationX() == accelerationX
	 */
	private void setAccelerationX(double accelerationX) {
		this.accelerationX = accelerationX;
	}
	
	/**
	 * This variable contains the current horizontal acceleration for this Mazub
	 */
	private double accelerationX;

	/**
	 * @return	...
	 * 			| result == this.accelerationY;
	 */
	private double getAccelerationY() {
		return accelerationY;
	}

	/**
	 * 
	 * @param accelerationY
	 * 
	 * @post	...
	 * 			|new.getAccelerationY == accelerationY
	 */
	private void setAccelerationY(double accelerationY) {
		this.accelerationY = accelerationY;
	}
	
	/**
	 * This variable contains the vertical acceleration of this shark
	 */
	private double accelerationY;
	
	void advanceTime(double seconds) throws IllegalArgumentException {
		if (seconds < 0 || seconds >= 0.2) 
			throw new IllegalArgumentException();
		this.setTimer(this.getTimer() + seconds);
		while(seconds > 0){
			double dt1 = 0.2;
			double dt2 = 0.2;
			if((getVelocityX() != 0) || (getAccelerationX() != 0)){
				dt1 = (0.01)/(Math.abs(getVelocityX())+Math.abs(getAccelerationX())*seconds);
			}
			if((getVelocityY() != 0) || (getAccelerationY() != 0)){
				dt2 = (0.01)/(Math.abs(getVelocityY())+Math.abs(getAccelerationY())*seconds);
			}
			if((dt1 != 0.2) || (dt2 != 0.2)){
				if(dt1 >= seconds && dt2 >= seconds){
					advanceTimeCollisionDetect(seconds);
					seconds = 0;
				} else if(dt1 < dt2){
					advanceTimeCollisionDetect(dt1);
					seconds -= dt1;
				} else if(dt2 <= dt1){
					advanceTimeCollisionDetect(dt2);
					seconds -= dt2;
				}
			} else {
				seconds = 0;
			}
			
		}
	}
	private void advanceTimeCollisionDetect(double dt){
		// TODO 		
	}
	
	/**
	 * 
	 * @return	...
	 * 			| result == this.timer
	 */
	private double getTimer() {
		return timer;
	}

	/**
	 * 
	 * @param time
	 * @post	...
	 * 			| new.getTimer() == time
	 */
	private void setTimer(double time) {
		this.timer = time;
	}
	
	/**
	 * This variable contains the timer for the movements of this shark
	 */
	private double timer;
		
	
}
