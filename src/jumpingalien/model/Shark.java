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
	private void advanceTimeCollisionDetect(double dt){
		setMovementTime(getMovementTime()-dt);
		//TODO vergelijking van doubles
		if(getMovementTime() <= 0){
			newMovement();
		}
		updateLocation(dt);	
		updateVelocity(dt);
	}
	
	private void updateLocation(Double dt){
		double accelerationX = getAccelerationX();
		if(getMovement() == Direction.LEFT){
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
		if(getMovement() == Direction.LEFT){
			accelerationX *= -1;
		}
		setVelocityX(getVelocityX() + accelerationX*dt);
	}
	
	
	private void newMovement(){
		setVelocityX(0);
		setVelocityY(0);
		setJumping(false);
		int random = (int)(Math.random()*2);
		switch (random){
			case 0: setMovement(Direction.RIGHT);
						break;
			case 1: setMovement(Direction.LEFT);
						break;
		}
		random = (int)(Math.random()*3);
		switch (random){
			case 0: setVelocityY(0);
						break;
			case 1: setVelocityY(0.1);
						break;
			case 2: setVelocityY(0.2);
						break;
		}
		if(random != 0){
			random = (int)(Math.random()*2);
			if(random == 1){
				setVelocityY(getVelocityY()*-1);
			}			
		}
		if((getMovementCounter() == 4)&&(canJump())){
			setVelocityY(2);
			setJumping(true);
		}
		
		double time = 1 + (int)(Math.random()*4);
		setMovementTime(time);
		addToMovementCounter(1);
		
	}
		
	private boolean canJump() {
		// TODO Auto-generated method stub
		return true;
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
				getWorld().getTilePositionsIn(x+1, endY-2, endX-2, endY-2);
		for(int[] tile : tiles){
			if (getWorld().getGeologicalFeatureOfTile(tile[0], tile[1]) == 1){
				return true;
			}
		}
		return false;
	}
	
	private boolean hasCollisionBottom(int x, int y){
		int endX = x + getCurrentSprite().getWidth();
		int[][] tiles = 
				getWorld().getTilePositionsIn(x+1, y+1, endX-2, y+1);
		for(int[] tile : tiles){
			if (getWorld().getGeologicalFeatureOfTile(tile[0], tile[1]) == 1){
				return true;
			}
		}
		return false;
	}
	
	private boolean hasCollisionRight(int x, int y){
		int endX = x + getCurrentSprite().getWidth();
		int endY = y + getCurrentSprite().getHeight();
		int[][] tiles = 
				getWorld().getTilePositionsIn(endX-2, y+2, endX-2, endY-3);
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
				getWorld().getTilePositionsIn(x+1, y+2, x+1, endY-3);
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

	/**
	 * 
	 * @return 	...
	 * 			|result == this.movement
	 */
	private Direction getMovement() {
		return movement;
	}

	/**
	 * 
	 * @param 	movement
	 * 			...
	 * @post 	...
	 * 			| new.getMovement() = movement
	 */
	@Basic
	private void setMovement(Direction movement) {
		this.movement = movement;
	} 
	
	/**
	 * This variable contains the current movement for this shark
	 */
	private Direction movement;
	
	/**
	 * 
	 * @param value
	 * @effect 	...
	 * 			if (getMovementCounter() + value >= 4) then
	 *				setMovementCounter(4);
	 * @effect 	...
	 * 			if (getMovementCounter() + value < 4) then
	 *				setMovementCounter(value);
	 */
	private void addToMovementCounter(int value){
		if(getMovementCounter() + value >= 4)
			setMovementCounter(4);
		else
			setMovementCounter(value);
	}
	

	/**
	 * 
	 * @return 	...
	 * 			| result == this.movementCounter
	 */
	private int getMovementCounter() {
		return movementCounter;
	}

	/**
	 * 
	 * @param movementCounter
	 * @post 	...
	 * 			| new.getMovementCounter() = movementCounter;
	 */
	private void setMovementCounter(int movementCounter) {
		this.movementCounter = movementCounter;
	}
	
	/**
	 * This variable contains the amount of movements after a jump
	 */
	private int movementCounter;
	
	/**
	 * 
	 * @return 	...
	 * 			| result == this.isJumping
	 */
	private boolean isJumping() {
		return isJumping;
	}

	/**
	 * 
	 * @param isJumping
	 * @post 	...
	 * 			| new.isJumping() == isJumping
	 */
	private void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}
	
	/**
	 * This variable contains wheter this shark is jumping or not
	 */
	private boolean isJumping;
	
	private boolean hasCollisionTopWithWater(int x, int y){
		int endX = x + getCurrentSprite().getWidth();
		int endY = y + getCurrentSprite().getHeight();
		return detectGeologicalFeature(x+1, endY-2, endX-2, endY-2, 2);
	}

	private boolean hasCollisionBottomWithWater(int x, int y){
		int endX = x + getCurrentSprite().getWidth();
		int[][] tiles = 
				getWorld().getTilePositionsIn(x+1, y+1, endX-2, y+1);
		for(int[] tile : tiles){
			if (getWorld().getGeologicalFeatureOfTile(tile[0], tile[1]) == 1){
				return true;
			}
		}
		return false;
	}
	
	private boolean hasCollisionRightWithWater(int x, int y){
		int endX = x + getCurrentSprite().getWidth();
		int endY = y + getCurrentSprite().getHeight();
		return detectGeologicalFeature(endX-2, y+2, endX-2, endY-3, 2);
	}
	
	private boolean hasCollisionLeftWithWater(int x, int y){
		int endY = y + getCurrentSprite().getHeight();
		return detectGeologicalFeature(x+1, y+2, x+1, endY-3, 2);
	}
	
	private boolean detectGeologicalFeature(int i, int j, int k, int l, int geologicalFeature) {
		int[][] tiles = 
				getWorld().getTilePositionsIn(i, j, k, l);
		for(int[] tile : tiles){
			if (getWorld().getGeologicalFeatureOfTile(tile[0], tile[1]) == geologicalFeature){
				return true;
			}
		}
		return false;		
	}
	
}
