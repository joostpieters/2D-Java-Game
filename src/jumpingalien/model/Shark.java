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
		setHitpoints(100);
		newMovement();
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
	
	@Basic
	public Sprite getCurrentSprite() {
		if(getVelocityX() > 0)
			return getSprites()[1];
		else
			return getSprites()[0];
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
		if(velocityX > getMaximumVelocityX())
			this.velocityX = getMaximumVelocityX();
		else if(velocityX < -getMaximumVelocityX())
			this.velocityX = -getMaximumVelocityX();
		else
			this.velocityX = velocityX;
	}
	
	private static double getMaximumVelocityX(){
		return 4;
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
	 * 			| result == getInitialHorizontalAcceleration()
	 */
	private static double getAccelerationX() {
		return getInitialHorizontalAcceleration();
	}
	
	
	/**
	 * 
	 * @return	...
	 * 			| result == 1.5
	 */
	private static double getInitialHorizontalAcceleration(){
		return 1.5;
	}

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
	//TODO vergelijking van doubles
	private void advanceTimeCollisionDetect(double dt){
		setMovementTime(getMovementTime()-dt);
		if(getMovementTime() <= 0){
			newMovement();
		}
		updateLocation(dt);	
		updateVelocity(dt);
	}
	
	private void updateLocation(Double dt){
		double accelerationX = getAccelerationX();
		if(getVelocityX() < 0){
			accelerationX *= -1;
		}
		double locationX = getLocationX() + (getVelocityX()*dt + accelerationX*dt*dt/2)*100;
		double locationY = getLocationY() + (getVelocityY()*dt + getAccelerationY()*dt*dt/2)*100;
		if(hasCollisionX((int)locationX,(int) locationY)){
			locationX = getLocationX();
		}
		if(hasCollisionY((int)locationX,(int) locationY)){
			locationY = getLocationY();
			locationX = getLocationX() + (getVelocityX()*dt + accelerationX*dt*dt/2)*100;
			if(hasCollisionX((int)locationX,(int) locationY)){
				locationX = getLocationX();
			}
		}
		setLocationX(locationX);
		setLocationY(locationY);
	}
	
	private void updateVelocity(Double dt){
		double accelerationX = getAccelerationX();
		if(getVelocityX() < 0){
			accelerationX *= -1;
		}
		setVelocityX(getVelocityX() + accelerationX*dt);
	}
	
	private static double getInitialHorizontalVelocity(){
		return 8;
	}
	
	private void newMovement(){
		int random = (int)(Math.random()*2);
		switch (random){
			case 0: setVelocityX(getInitialHorizontalVelocity());
						break;
			case 1: setVelocityX(-getInitialHorizontalVelocity());
						break;
		}
		random = (int)(Math.random()*4);
		switch (random){
			case 0: setVelocityY(1);
						break;
			case 1: setVelocityY(1);
						break;
			case 3: setVelocityY(0);
						break;
		}
		double time = 1 + (int)(Math.random()*5);
		if(time < 4){
			time+= (int)(Math.random()*10)/10;
		}
		setMovementTime(time);
		
	}
		
	/**
	 * 
	 * @return	...
	 * 			| result == this.movementTimer
	 */
	private double getMovementTime() {
		return movementTime;
	}

	/**
	 * 
	 * @param time
	 * @post	...
	 * 			| new.getMovementTimer() == time
	 */
	private void setMovementTime(double time) {
		this.movementTime = time;
	}
	
	/**
	 * This variable contains the time that the current movement will last
	 */
	private double movementTime;
		
	private boolean hasCollisionTop(int x, int y){
		int endX = x + getCurrentSprite().getWidth();
		int endY = y + getCurrentSprite().getHeight();
		int[][] tiles = 
				getWorld().getTilePositionsIn(x, endY, endX, endY);
		for(int[] tile : tiles){
			if (getWorld().getGeologicalFeatureOfTile(tile[0], tile[1]) == 1){
				return true;
			}
		}
		return false;
	}
	
	private boolean hasCollisionBottom(int x, int y){
		int startX = x;
		int endX = startX + getCurrentSprite().getWidth();
		int[][] tiles = 
				getWorld().getTilePositionsIn(startX, y+1, endX, y+1);
		for(int[] tile : tiles){
			if (getWorld().getGeologicalFeatureOfTile(tile[0], tile[1]) == 1){
				return true;
			}
		}
		return false;
	}
	
	private boolean hasCollisionRight(int x, int y){
		int startX = x;
		int endX = startX + getCurrentSprite().getWidth();
		int endY = y + getCurrentSprite().getHeight();
		int[][] tiles = 
				getWorld().getTilePositionsIn(endX, y+1, endX, endY-1);
		for(int[] tile : tiles){
			if (getWorld().getGeologicalFeatureOfTile(tile[0], tile[1]) == 1){
				return true;
			}
		}
		return false;
	}
	
	private boolean hasCollisionLeft(int x, int y){
		int endY = y + getCurrentSprite().getHeight();
		int[][] tiles = 
				getWorld().getTilePositionsIn(x, y+1, x, endY-1);
		for(int[] tile : tiles){
			if (getWorld().getGeologicalFeatureOfTile(tile[0], tile[1]) == 1){
				return true;
			}
		}
		return false;
	}
	
	private boolean hasCollisionX(int x, int y){
		return hasCollisionLeft(x, y) || hasCollisionRight(x, y);
	}
	
	private boolean hasCollisionY(int x, int y){
		return hasCollisionTop(x, y) || hasCollisionBottom(x, y);
	}
	
}
