package jumpingalien.model;

import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.*;

public class Slime {
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
	 * 			| setSchool(school)
	 * @throws	IllegalArgumentException()
	 * 			...
	 * 			|((sprites.length != 2) || (!school.canHaveAsSlime(this)))
	 * @effect	...
	 * 			| setHitPoints(50)
	 * @effect	...
	 * 			| setVelocityX(0)
	 * @effect	...
	 * 			| setVelocityY(0)
	 * @effect	...
	 * 			| setAccelerationX(0)
	 * @effect	...
	 * 			| setAccelerationY(0)
	 * @effect	...
	 * 			| newMovement()
	 */
	public Slime (int x, int y, Sprite[] sprites, School school) throws IllegalArgumentException{
		if(sprites.length != 2){
			throw new IllegalArgumentException();
		}
		setLocationX(x);
		setLocationY(y);
		setSprites(sprites);
		setCurrentSpriteIndex(0);
		setSchool(school);
		school.addSlime(this);
		setHitPoints(100);
		setVelocityX(0);
		setVelocityY(0);
		setAccelerationX(0);
		setAccelerationY(0);
		newMovement();
		setMagmaTimer(0.2);
	}
	
	/**
	 * Returns the current X coordinate of this slime
	 */
	@Basic
	private double getLocationX() {
		return locationX;
	}
	/**
	 * 
	 * @param 	locationX
	 * 			the new X coordinate for this slime
	 * @post 	...
	 * 			| new.getLocationX() == locationX 
	 */
	private void setLocationX(double locationX) {
		this.locationX = locationX;
	}
	/**
	 * this variable contains the current X coordinate for this slime
	 */
	private double locationX;
	
	/**
	 * Returns the current Y coordinate of this slime
	 */
	@Basic
	private double getLocationY() {
		return locationY;
	}
	/**
	 * 
	 * @param 	locationY
	 * 			the new T coordinate for this slime
	 * @post 	...
	 * 			| new.getLocationY() == locationY
	 */
	private void setLocationY(double locationY) {
		this.locationY = locationY;
	}
	
	/**
	 * this variable contains the current Y coordinate for this Slime
	 */
	private double locationY;
	
	/**
	 * Returns the location of this Slime
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
	 * Return the current list of sprites for this slime
	 */
	private Sprite[] getSprites() {
		return sprites;
	}
	/**
	 * 
	 * @param 	sprites
	 * 			the list of sprites for this slime
	 * @post 	...
	 * 			| new.getSprites() == sprites
	 */
	private void setSprites(Sprite[] sprites) {
		this.sprites = sprites;
	}

	/**
	 * Contains the sprites of this slime.
	 */
	private Sprite[] sprites;
	
	/**
	 * Returns the current sprite index of this slime
	 */
	@Basic
	private int getCurrentSpriteIndex() {
		return this.currentSpriteIndex;
	}
	
	/**
	 * 
	 * @param index
	 * @post	...
	 * 			| new.getCurrentSpriteIndex() == index
	 */
	private void setCurrentSpriteIndex(int index) {
		this.currentSpriteIndex = index;
	}
	
	/**
	 * Contains the index of the current sprite of this Slime
	 */
	private int currentSpriteIndex;
	
	/**
	 * Returns the current sprite of this Slime
	 */
	@Basic
	public Sprite getCurrentSprite() {
		return getSprites()[getCurrentSpriteIndex()];
	}
	
	/**
	 * Returns the world where this slime is in
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
	 * 			| new.getWorld() == world			
	 */
	void setWorld(World world) {
		if((world != null) && (world.hasAsSlime(this)))
			this.world = world;		
	}
	
	/**
	 * Returns whether this slime is already in a world or not
	 * @return	...
	 * 			| getWorld() != null
	 */
	boolean hasAWorld(){
		return getWorld() != null;
	}
	
	/**
	 * This variable contains the world where this Slime is in
	 */
	private World world;
	
	/**
	 * Return the school in which this slime is
	 */
	public School getSchool() {
		return school;
	}
	
	/**
	 * 
	 * @param 	school
	 * @return 	...
	 * 			| return (getSchool() == school)
	 */
	public boolean hasAsSchool(School school) {
		return (getSchool() == school);
	}

	/**
	 * @param 	school
	 * @post 	...
	 * 			|new.getSchool() == school;
	 */
	private void setSchool(School school) {
		this.school = school;
	}
	
	/**
	 * This variable contains the school in which this slime is
	 */
	private School school;
	
	@Basic
	private int getHitPoints() {
		return this.hitPoints;
	}
	
	/**
	 * 
	 * @param points
	 * @post	...
	 * 			| new.getHitPoints() == points
	 */
	private void setHitPoints(int points) {
		if (points <= 0) {
			hitPoints = 0;
			setDeath(true);
		} else if (points > 100) {
			hitPoints = 100;
		} else {
			this.hitPoints = points;
		}
	}
	
	/**
	 * This variable contains the amount of hitPoints of this Slime
	 */
	private int hitPoints;
	
	@Basic
	private double getVelocityX() {
		return this.velocityX;
	}
	
	/**
	 * 
	 * @param velocityX
	 * @post	...
	 * 			| new.getVelocityX() == velocityX
	 */
	private void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}
	
	private double velocityX;
	
	@Basic
	private double getVelocityY() {
		return this.velocityY;
	}
	
	/**
	 * 
	 * @param velocityY
	 * @post	...
	 * 			| new.getVelocityY() == velocityY
	 */
	private void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}
	
	private double velocityY;
	
	@Basic
	private double getAccelerationX() {
		return accelerationX;
	}
	
	/**
	 * 
	 * @param accelerationX
	 * @post	...
	 * 			| new.getAccelerationX() == accelerationX
	 */
	private void setAccelerationX(double accelerationX) {
		this.accelerationX = accelerationX;
	}
	
	private double accelerationX;
	
	private static double getInitialAccelerationX() {
		return 0.7;
	}
	
	@Basic
	private double getAccelerationY() {
		return accelerationY;
	}
	
	/**
	 * 
	 * @param accelerationY
	 * @post	...
	 * 			| new.getAccelerationY() == accelerationY
	 */
	private void setAccelerationY(double accelerationY) {
		this.accelerationY = accelerationY;
	}
	
	private double accelerationY;
	
	/**
	 * Returns whether this Slime is moving left or not.
	 * @return 	true when the horizontal velocity is less than zero, false otherwise.
	 * 			| if getVelocityX() < 0 then
	 * 			|	return true
	 * 			| else then
	 * 			| 	return false
	 */
	private boolean isMovingLeft() {
		if (this.getMovementDirection() == Direction.LEFT) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Updates the location of this Slime given a certain amount of seconds and a horizontal acceleration.
	 * @param 	seconds
	 * 			The seconds to compute the new position.
	 * @param 	accelerationX
	 * 			The horizontal acceleration.
	 * @pre		seconds needs to be positive.
	 * 			| seconds >= 0
	 * @effect 	Calculates the new horizontal position, using the given seconds and acceleration.
	 * 			If the calculated position isn't valid, the position will be adjusted.
	 * 			|new.getLocationX() ==  calculateValidLocationX(getLocationX() + 
	 * 				(getVelocityX()*seconds + accelerationX*seconds*seconds/2)*100);
	 * @effect 	Calculates the new vertical position, using the given seconds and acceleration.
	 * 			If the calculated position isn't valid, the position will be adjusted.
	 * 			|new.getLocationY() ==  calculateValidLocationY(getLocationY() + 
	 * 				(getVelocityY()*seconds + getAccelerationY()*seconds*seconds/2)*100);
	 * 
	 */
	private void updateLocation(double seconds) {
		assert (seconds >= 0);
		setVelocityYZero(false);
		double accelerationX = getAccelerationX();
		if (isMovingLeft()) {
			accelerationX *= -1;
		}
		double locationX = getLocationX() + (getVelocityX()*seconds + accelerationX*seconds*seconds/2)*100;
		double locationY = getLocationY() + (getVelocityY()*seconds + getAccelerationY()*seconds*seconds/2)*100;
		if(hasCollisionX((int)locationX,(int) locationY)){
			locationX = getLocationX();
		}
		if(hasCollisionY((int)locationX,(int) locationY)){
			locationY = getLocationY();
			locationX = getLocationX() + (getVelocityX()*seconds + accelerationX*seconds*seconds/2)*100;
			if(hasCollisionX((int)locationX,(int) locationY)){
				locationX = getLocationX();
			}
		}
		boolean hasCollisionSlime = getWorld().collisionSlimes((int)locationX, (int)locationY, (int) locationX + this.getCurrentSprite().getWidth(), (int) locationY + this.getCurrentSprite().getHeight(), this).size() > 0;
		if(hasCollisionSlime){
			hasCollisionSlime = getWorld().collisionSlimes((int)getLocationX(), (int)locationY, (int) getLocationX() + this.getCurrentSprite().getWidth(), (int) locationY + this.getCurrentSprite().getHeight(), this).size() > 0;
			if(!hasCollisionSlime){
				locationX = getLocationX();				
			} else {
				hasCollisionSlime = getWorld().collisionSlimes((int)locationX, (int)getLocationY(), (int) locationX + this.getCurrentSprite().getWidth(), (int) getLocationY() + this.getCurrentSprite().getHeight(), this).size() > 0;
				if(!hasCollisionSlime){
					locationY = getLocationY();	
					setVelocityYZero(true);
				} else {
					locationX = getLocationX();
					locationY = getLocationY();
					setVelocityYZero(true);
				}
			}
		}
		boolean hasCollisionMazub = getWorld().collisionMazub((int)locationX, (int)locationY, (int) locationX + this.getCurrentSprite().getWidth(), (int) locationY + this.getCurrentSprite().getHeight());
		if(hasCollisionMazub){
			hasCollisionMazub = getWorld().collisionMazub((int)getLocationX(), (int)locationY, (int) getLocationX() + this.getCurrentSprite().getWidth(), (int) locationY + this.getCurrentSprite().getHeight());
			if(!hasCollisionMazub){
				locationX = getLocationX();				
			} else {
				hasCollisionMazub = getWorld().collisionMazub((int)locationX, (int)getLocationY(), (int) locationX + this.getCurrentSprite().getWidth(), (int) getLocationY() + this.getCurrentSprite().getHeight());
				if(!hasCollisionMazub){
					locationY = getLocationY();	
					setVelocityYZero(true);
				} else {
					locationX = getLocationX();
					locationY = getLocationY();
					setVelocityYZero(true);
				}
			}
		}
		boolean hasCollisionShark = getWorld().collisionSharks((int)locationX, (int)locationY, (int) locationX + this.getCurrentSprite().getWidth(), (int) locationY + this.getCurrentSprite().getHeight()).size() > 0;
		if(hasCollisionShark){
			hasCollisionShark = getWorld().collisionSharks((int)getLocationX(), (int)locationY, (int) getLocationX() + this.getCurrentSprite().getWidth(), (int) locationY + this.getCurrentSprite().getHeight()).size() > 0;
			if(!hasCollisionShark){
				locationX = getLocationX();				
			} else {
				hasCollisionShark = getWorld().collisionSharks((int)locationX, (int)getLocationY(), (int) locationX + this.getCurrentSprite().getWidth(), (int) getLocationY() + this.getCurrentSprite().getHeight()).size() > 0;
				if(!hasCollisionShark){
					locationY = getLocationY();	
					setVelocityYZero(true);
				} else {
					locationX = getLocationX();
					locationY = getLocationY();
					setVelocityYZero(true);
				}
			}
		}
		try {
			setLocationX(locationX);
		} catch (IllegalArgumentException e1){
			locationX = calculateValidLocationX(locationX);
			setLocationX(locationX);
		}
		
		try {
			setLocationY(locationY);
		} catch (IllegalArgumentException e2){
			locationY = calculateValidLocationY(locationY);
			setLocationY(locationY);
		}
	}
	
	/**
	 * @return 	if the velocity needs to be set to zero
	 *			|result == setVelocityYZero
	 */
	private boolean isSetVelocityYZero() {
		return setVelocityYZero;
	}


	/**
	 * 
	 * @param setVelocityYZero
	 * 			this boolean indicades if the velocityY needs to be set to zero or not
	 * @post 	setVelocityZero of this slime will equal the given setVelocityYZero
	 * 			|new.isSetVelocityYZero() == setVelocityYZero
	 */
	private void setVelocityYZero(boolean setVelocityYZero) {
		this.setVelocityYZero = setVelocityYZero;
	}
	
	/**
	 * This boolean indicades if this slime's vertical velocity needs to be set to zero
	 */
	private boolean setVelocityYZero;

	/**
	 * 
	 * @return	...
	 * 			| getWorld().getWorldSizeInPixels[0]
	 */
	private int getWindowWidth() {
		return getWorld().getWorldSizeInPixels()[0];
	}
	
	/**
	 * 
	 * @return	...
	 * 			| getWorld().getWorldSizeInPixels[1]
	 */
	private int getWindowHeight() {
		return getWorld().getWorldSizeInPixels()[1];
	}
	
	
	/**
	 * @param 	locationX
	 * 			the X coordinate wich needs to be corrected
	 * 
	 * Return locationX if the given locationX is valid, 
	 * 		returns the corrected locationX is if is unvalid
	 * @return	if locationX is lower than the window width and greater or equal to zero
	 * 				locationX will be returned
	 * 			| if ((locationX < getWindowWidth()) && (locationX >= 0)) then
	 * 			| 	return locationX
	 * @return 	if locationX is greater than the window width minus one,
	 * 				the window width minus one will be returned
	 * 			| if (locationX > getWindowWidth()-1) then
	 * 			| 	return getWindowWidth()-1
	 * @return 	if locationX is less than zero, zero will be returned
	 * 			| if (locationX < 0) then
	 * 			| 	return 0
	 */
	private double calculateValidLocationX(double locationX) {
		if (locationX > getWindowWidth() - 1){
			locationX = getWindowWidth() - 1;
		} else if (locationX < 0){
			locationX = 0;
		}
		return locationX;
	}
	
	/**
	 * @param 	locationY
	 * 			the Y coordinate wich needs to be corrected
	 * 
	 * Return locationY if the given locationY is valid, 
	 * 		returns the corrected locationY if it is unvalid
	 * @return	if locationY is lower than the window height and greater or equal than zero
	 * 				locationY will be returned
	 * 			| if ((locationY < getWindowHeight()) && (locationY >= 0)) then
	 * 			| 	return locationY
	 * @return 	if locationY is greater than the window height minus one,
	 * 				the window height minus one will be returned
	 * 			| if (locationY > getWindowHeight()-1) then
	 * 			| 	return getWindowHeight()-1
	 * @return 	if locationY is less than zero, zero will be returned
	 * 			| if (locationY < 0) then
	 * 			| 	return 0
	 */
	private double calculateValidLocationY(double locationY) {
		if (locationY > getWindowHeight()-1){
			locationY = getWindowHeight()-1;
		} else if (locationY < 0){
			locationY = 0;
		}
		return locationY;
	}

	private double getMaximumHorizontalVelocity() {
		return 2.5;
	}
	
	/**
	 * This function returns whether this Slime is on Solid ground or not
	 * @return
	 */
	private boolean isOnSolidGround(){
		return hasCollisionBottom((int)getLocationX(), (int)getLocationY()-1);
	}
	
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
		int startX = x;
		int endX = startX + getCurrentSprite().getWidth();
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
	
	public void advanceTime(double dt) throws IllegalArgumentException {
		if(!isDeath){
			double seconds = dt;
			if (seconds < 0 || seconds >= 0.2) 
				throw new IllegalArgumentException();
			this.setTimer(this.getTimer() + seconds);
			if (isInMagma()) {
				setMagmaTimer(getMagmaTimer() + dt);
			} else {
				// immediately lose points when in magma
				setMagmaTimer(0.2);
			}
			if (isInWater()) {
				setWaterTimer(getWaterTimer() + dt);
			} else {
				setWaterTimer(0);
			}
			while(seconds > 0){
				double dt1 = 0.2;
				double dt2 = 0.2;
				if (! isOnSolidGround()) {
					setAccelerationY(-10);
				}
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
			if (isInWater()) {
				if (getWaterTimer() >= 0.2) {
					setHitPoints(getHitPoints()-2);
					setWaterTimer(getWaterTimer()-0.2);
				}
			} else {
				setWaterTimer(0);
			}
			if (isInMagma()) {
				if (getMagmaTimer() >= 0.2) {
					setMagmaTimer(getMagmaTimer()-0.2);
					setHitPoints(getHitPoints()-50);
				}
			} else {
				// immediately lose points when in magma
				setMagmaTimer(0.2);
			}
		} else {
			setTimeDeath(getTimeDeath() + dt);
			//TODO vergelijking met double
			if(getTimeDeath() > 0.6){
				terminate();
			}
		}
	}
	
	//TODO vergelijking van doubles
	private void advanceTimeCollisionDetect(double dt){
		setMovementTime(getMovementTime()-dt);
		if(getMovementTime() <= 0){
			newMovement();
		}
		updateVelocity(dt);
		updateLocation(dt);	
	}
	
	private void updateVelocity(double dt){
		double accelerationX = getAccelerationX();
		double velocityX;
		if(isMovingLeft()){
			accelerationX *= -1;
			velocityX = getVelocityX() + accelerationX*dt;
			if (velocityX < -getMaximumHorizontalVelocity()) {
				setVelocityX(-getMaximumHorizontalVelocity());
			} else {
				setVelocityX(velocityX);
			}
		} else {
			velocityX = getVelocityX() + accelerationX*dt;
			if (velocityX > getMaximumHorizontalVelocity()) {
				setVelocityX(getMaximumHorizontalVelocity());
			} else {
				setVelocityX(velocityX);
			}
		}
		
		if (isOnSolidGround()) {
			setVelocityY(0);
			setAccelerationY(0);
		} else {
			setVelocityY(getVelocityY() + getAccelerationY()*dt);
		}
	}
	
	
	private void newMovement(){
		int random = (int)(Math.random()*2);
		switch (random){
			case 0: setMovementDirection(Direction.LEFT);
					setCurrentSpriteIndex(0);
					break;
			case 1: setMovementDirection(Direction.RIGHT);
					setCurrentSpriteIndex(1);
					break;
		}
		double time = 2 + (int)(Math.random()*5);
		
		setVelocityX(0);
		setAccelerationX(getInitialAccelerationX());
		
		setMovementTime(time);
		
	}
	
	@Basic
	private Direction getMovementDirection() {
		return this.movementDirection;
	}
	
	/**
	 * 
	 * @param direction
	 * @post	...
	 * 			| new.getMovementDirection() == direction
	 */
	private void setMovementDirection(Direction direction) {
		this.movementDirection = direction;
	}
	
	private Direction movementDirection;
		
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
	
	/**
	 * Returns the value of the timer
	 */
	@Basic
	private double getTimer() {
		return this.timer;
	}
	
	/**
	 * 
	 * @param timer
	 * @post	Sets the timer to the given value
	 * 			| new.getTimer() == timer
	 */
	private void setTimer(double timer) {
		this.timer = timer;
	}
	
	/**
	 * this variable contains the current value of the timer
	 */
	private double timer; 
	
	/**
	 * 
	 * @effect 	...
	 * 			| setHitPoints(getHitPoints() - 50)
	 */
	void handleCollisionMazub() {
		setHitPoints(getHitPoints() - 50);
	}
	
	/**
	 * @effect	...
	 * 			| setWorld(null)
	 * @effect	...
	 * 			| setTerminated(true)
	 */
	void terminate() {
		// remove world
		this.setWorld(null);
		// set boolean
		setTerminated(true);
	}
	
	/**
	 * Returns whether this Slime is terminated
	 */
	@Basic
	boolean isTerminated() {
		return this.terminated;
	}
	
	/**
	 * sets the terminated state to the given value
	 * @param value
	 * @post	...
	 * 			| new.isTerminated() == value
	 */
	private void setTerminated(boolean value) {
		this.terminated = value;
	}
	
	private boolean terminated;
	
	private boolean isInMagma() {
		int x = (int) getLocationX();
		int y = (int) getLocationY();
		int endX = x + getCurrentSprite().getWidth();
		int endY = y + getCurrentSprite().getHeight();
		return getWorld().detectGeologicalFeature(x, y, endX-1, endY-1, 3);
	}
	
	/**
	 * @return the magmaTimer
	 */
	private double getMagmaTimer() {
		return magmaTimer;
	}


	/**
	 * @param magmaTimer
	 */
	private void setMagmaTimer(double magmaTimer) {
		this.magmaTimer = magmaTimer;
	}
	
	private double magmaTimer;
	
	private boolean isInWater() {
		int x = (int) getLocationX();
		int y = (int) getLocationY();
		int endX = x + getCurrentSprite().getWidth();
		int endY = y + getCurrentSprite().getHeight();
		return getWorld().detectGeologicalFeature(x, y, endX-1, endY-1, 2);
	}
	
	/**
	 * @return the waterTimer
	 */
	private double getWaterTimer() {
		return waterTimer;
	}


	/**
	 * @param waterTimer the waterTimer to set
	 */
	private void setWaterTimer(double waterTimer) {
		this.waterTimer = waterTimer;
	}
	
	private double waterTimer;
	
	private boolean isDeath() {
		return isDeath;
	}

	private void setDeath(boolean isDeath) {
		this.isDeath = isDeath;
	}
	
	private boolean isDeath;

	private double getTimeDeath() {
		return timeDeath;
	}

	private void setTimeDeath(double timeDeath) {
		this.timeDeath = timeDeath;
	}
	private double timeDeath;
}
