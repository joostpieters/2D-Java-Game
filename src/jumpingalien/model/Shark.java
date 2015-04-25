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
	 * 			| setHitPoints(100)
	 * @effect	...
	 * 			| setMagmaTimer(0.2)
	 */
	public Shark (int x, int y, Sprite[] sprites){
		setLocationX(x);
		setLocationY(y);
		setSprites(sprites);
		setHitPoints(100);
		setMovementCounter(4);
		setMagmaTimer(0.2);
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
	
	// TODO hoe geprogrammeerd ?
	/**
	 * 
	 * @param 	world
	 * @post 	...
	 * 			| new.getWorld() = world
	 * @effect 	...
	 * 			|newMovement()			
	 */
	void setWorld(World world) {
		if((world != null) && (world.hasAsShark(this) && !isTerminated())){
			this.world = world;
			newMovement();
		}		
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
	private double getAccelerationX() {
		if(getMovement() != null)
			return getInitialHorizontalAcceleration();
		else
			return 0;
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
	
	void advanceTime(double dt) throws IllegalArgumentException {
		if(!isDeath()){
			double seconds = dt;
			if (seconds < 0 || seconds >= 0.2) 
				throw new IllegalArgumentException();
			if(!isInWater()){
				setNotInWaterTimer(getNotInWaterTimer() + seconds);
			} else {
				setNotInWaterTimer(0);
			}
			if (isInMagma()) {
				setMagmaTimer(getMagmaTimer() + dt);
			} else {
				// immediately lose points when in magma
				setMagmaTimer(0.2);
			}
			//TODO double vergelijking
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
			if(getNotInWaterTimer() >= 0.2){
				setNotInWaterTimer(getNotInWaterTimer() - 0.2);
				setHitPoints(getHitPoints() - 1);
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
	private void advanceTimeCollisionDetect(double dt){
		if(!isInWater()){
			setAccelerationY(-10);
		}
		setMovementTime(getMovementTime()-dt);
		//TODO vergelijking van doubles
		if(isBottomPerimeterInSolidGround() && !isInWater()){
			setAccelerationY(0);
			setVelocityY(0);
			setVelocityX(0);
			setMovement(null);
		}
		if(getMovementTime() <= 0){
			newMovement();
		}
		if(!isInWater()){
			setAccelerationY(-10);
		}
		if(isTopPerimeterInWater() && getAccelerationY()<-9){
			setAccelerationY(0);
			setVelocityY(0);
		}
		updateLocation(dt);	
		updateVelocity(dt);
		handleCollisionMazub();
	}
	
	private void updateLocation(Double dt){
		setVelocityYZero(false);
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
		boolean hasCollisionShark = getWorld().collisionSharks((int)locationX, (int)locationY, (int) locationX + this.getCurrentSprite().getWidth(), (int) locationY + this.getCurrentSprite().getHeight(), this).size() > 0;
		if(hasCollisionShark){
			hasCollisionShark = getWorld().collisionSharks((int)getLocationX(), (int)locationY, (int) getLocationX() + this.getCurrentSprite().getWidth(), (int) locationY + this.getCurrentSprite().getHeight(), this).size() > 0;
			if(!hasCollisionShark){
				locationX = getLocationX();				
			} else {
				hasCollisionShark = getWorld().collisionSharks((int)locationX, (int)getLocationY(), (int) locationX + this.getCurrentSprite().getWidth(), (int) getLocationY() + this.getCurrentSprite().getHeight(), this).size() > 0;
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
		boolean hasCollisionSlime = getWorld().collisionSlimes((int)locationX, (int)locationY, (int) locationX + this.getCurrentSprite().getWidth(), (int) locationY + this.getCurrentSprite().getHeight()).size() > 0;
		if(hasCollisionSlime){
			hasCollisionSlime = getWorld().collisionSlimes((int)getLocationX(), (int)locationY, (int) getLocationX() + this.getCurrentSprite().getWidth(), (int) locationY + this.getCurrentSprite().getHeight()).size() > 0;
			if(!hasCollisionSlime){
				locationX = getLocationX();				
			} else {
				hasCollisionSlime = getWorld().collisionSlimes((int)locationX, (int)getLocationY(), (int) locationX + this.getCurrentSprite().getWidth(), (int) getLocationY() + this.getCurrentSprite().getHeight()).size() > 0;
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
		setLocationX(locationX);
		setLocationY(locationY);
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
	 * @post 	setVelocityZero of this shark will equal the given setVelocityYZero
	 * 			|new.isSetVelocityYZero() == setVelocityYZero
	 */
	private void setVelocityYZero(boolean setVelocityYZero) {
		this.setVelocityYZero = setVelocityYZero;
	}
	
	/**
	 * This boolean indicades if this sharks vertical velocity needs to be set to zero
	 */
	private boolean setVelocityYZero;
	
	private void updateVelocity(double dt){
		double accelerationX = getAccelerationX();
		if(getMovement() == Direction.LEFT){
			accelerationX *= -1;
		}
		setVelocityX(getVelocityX() + accelerationX*dt);
		setVelocityY(getVelocityY() + getAccelerationY()*dt);
		if(isSetVelocityYZero())
			setVelocityY(0);
	}
	
	
	private void newMovement(){
		boolean isJumping = false;
		setVelocityX(0);
		setMovement(null);
		//Todo double vergelijking
		if(getAccelerationY() > 0)
			setAccelerationY(0);
		if(isInWater())
			setVelocityY(0);
		int random;
		if((getMovementCounter() == 4)){
			if(isBottomPerimeterInWater() || isBottomPerimeterInSolidGround()){
				random = (int)(Math.random()*2);
				if(random == 1){
					setVelocityY(2);
					isJumping = true;
					setMovementCounter(-1);
				}
			}
		}
		if(isInWater() || isJumping){
			random = (int)(Math.random()*2);
			switch (random){
				case 0: setMovement(Direction.RIGHT);
							break;
				case 1: setMovement(Direction.LEFT);
							break;
			}
		}
		if(!isJumping && isInWater()){
			random = (int)(Math.random()*3);
			switch (random){
				case 0: setAccelerationY(0);
							break;
				case 1: setAccelerationY(0.1);
							break;
				case 2: setAccelerationY(0.2);
							break;
			}
			if(random != 0){
				random = (int)(Math.random()*2);
				if(random == 1){
					setAccelerationY(getAccelerationY()*-1);
				}			
			}
		}
		
		double time = 1 + (int)(Math.random()*4);
		setMovementTime(time);
		addToMovementCounter(1);
		
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
		return getWorld().detectGeologicalFeature(x+1, endY-2, endX-2, endY-2, 1);
	}
	
	private boolean hasCollisionBottom(int x, int y){
		int endX = x + getCurrentSprite().getWidth();
		return getWorld().detectGeologicalFeature(x+1, y+1, endX-2, y+1, 1);
	}
	
	private boolean hasCollisionRight(int x, int y){
		int endX = x + getCurrentSprite().getWidth();
		int endY = y + getCurrentSprite().getHeight();
		return getWorld().detectGeologicalFeature(endX-2, y+2, endX-2, endY-3, 1);
	}
	
	private boolean hasCollisionLeft(int x, int y){
		int endY = y + getCurrentSprite().getHeight();
		return getWorld().detectGeologicalFeature(x+1, y+2, x+1, endY-3, 1);
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
			setMovementCounter(getMovementCounter() + value);
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
	
	private boolean isTopPerimeterInWater(){
		int[] position = getLocation();
		int x = position[0];
		int y = position[1];
		int endX = x + getCurrentSprite().getWidth();
		int endY = y + getCurrentSprite().getHeight();
		return getWorld().detectGeologicalFeature(x+1, endY-1, endX-2, endY-1, 2);
	}

	private boolean isBottomPerimeterInWater(){
		int[] position = getLocation();
		int x = position[0];
		int y = position[1];
		int endX = x + getCurrentSprite().getWidth();
		return getWorld().detectGeologicalFeature(x+1, y, endX-2, y, 2);
	}
	
	private boolean isRightPerimeterInWater(){
		int[] position = getLocation();
		int x = position[0];
		int y = position[1];
		int endX = x + getCurrentSprite().getWidth();
		int endY = y + getCurrentSprite().getHeight();
		return getWorld().detectGeologicalFeature(endX-1, y+2, endX-1, endY-3, 2);
	}
	
	private boolean isLeftPerimeterInWater(){
		int[] position = getLocation();
		int x = position[0];
		int y = position[1];
		int endY = y + getCurrentSprite().getHeight();
		return getWorld().detectGeologicalFeature(x, y+2, x, endY-3, 2);
	}
	
	private boolean isInWater(){
		return (isRightPerimeterInWater() || isLeftPerimeterInWater() || isBottomPerimeterInWater() || isTopPerimeterInWater());
	}
	
	private boolean isBottomPerimeterInSolidGround(){
		int[] position = getLocation();
		int x = position[0];
		int y = position[1];
		int endX = x + getCurrentSprite().getWidth();
		return getWorld().detectGeologicalFeature(x+1, y, endX-2, y, 1);		
	}
	
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
	 * @param magmaTimer the magmaTimer to set
	 */
	private void setMagmaTimer(double magmaTimer) {
		this.magmaTimer = magmaTimer;
	}
	
	private double magmaTimer;
	
	
	
	/**
	 * 
	 * @return 	...
	 * 			| result == this.notInWaterTimer
	 */
	private double getNotInWaterTimer() {
		return notInWaterTimer;
	}

	/**
	 * 
	 * @param notInWaterTimer
	 * @post 	...
	 * 			|new.getNotInWaterTimer() == notInWaterTimer
	 */
	private void setNotInWaterTimer(double notInWaterTimer) {
		this.notInWaterTimer = notInWaterTimer;
	}
	
	/**
	 * This variable contains the time that the shark was not in the water
	 */
	private double notInWaterTimer;	
	
	/**
	 * 
	 * @return 	...
	 * 			| result == this.hitPoints
	 */
	private int getHitPoints() {
		return hitPoints;
	}

	/**
	 * 
	 * @param hitPoints
	 * @post 	...
	 * 			|new.getHitPoints = hitPoints
	 */
	private void setHitPoints(int hitPoints) {
 		if(hitPoints <= 0){
			this.hitPoints = 0;
 			setDeath(true);
 		} else if(
 			hitPoints > 100) {
			this.hitPoints = 100;
 		} else {
			this.hitPoints = hitPoints;
 		}
 	}
	
	private void terminate() {
		this.setWorld(null);
		setTerminated(true);
	}
	/**
	 * This variable contains the amount of hitpoints for this shark
	 */
	private int hitPoints;
	
	public boolean isTerminated() {
		return isTerminated;
	}

	public void setTerminated(boolean isTerminated) {
		this.isTerminated = isTerminated;
	}
	
	private boolean isTerminated;
	
	private void handleCollisionMazub(){
		if(!getWorld().getMazub().isImmune() && getWorld().collisionMazubInPerimeters((int)getLocationX(), (int)getLocationY(), (int)getLocationX()+getCurrentSprite().getWidth(), (int)getLocationY()+getCurrentSprite().getHeight())){
			getWorld().getMazub().hadCollissionShark();
			hadCollisionMazub();
		}
	}

	void hadCollisionMazub() {
		setHitPoints(getHitPoints()-50);
	}

	
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