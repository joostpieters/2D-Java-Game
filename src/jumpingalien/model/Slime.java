package jumpingalien.model;

import java.util.Collection;

import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.*;

public class Slime extends GameObjects {
	/**
	 * @param x
	 * @param y
	 * @param sprites
	 * @throws	IllegalArgumentException()
	 * 			...
	 * 			|((sprites.length != 2) || (!school.canHaveAsSlime(this)))
	 * @throws 	IllegalArgumentException()
	 * 			...	
	 * 			| (!isValidSchool())
	 * @effect 	...
	 * 			| setLocationX(x)
	 * @effect 	...
	 * 			| setLocationY(y)
	 * @effect 	...
	 * 			| setSprites(sprites)
	 * @effect	...
	 * 			| setSchool(school)
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
	 * @effect	...
	 * 			| setMagmaTimer(0.2)
	 */
	public Slime (int x, int y, Sprite[] sprites, School school) throws IllegalArgumentException{
		if(sprites.length != 2){
			throw new IllegalArgumentException();
		}
		if (!isValidSchool(school)) {
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
		setInMagmaTimer(0.2);
	}
	
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
	 * Return the school in which this slime is
	 */
	public School getSchool() {
		return school;
	}
	
	/**
	 * 
	 * @param 	school
	 * @return 	...
	 * 			| result == (getSchool() == school)
	 */
	public boolean hasAsSchool(School school) {
		return (getSchool() == school);
	}
	
	/**
	 * 
	 * @param newSchool
	 * @return	...
	 * 			| result == (isValidSchool(newSchool) && (newSchool != getSchool()) && (getSchool().getAmountSlimes() < newSchool.getAmountSlimes()))
	 */
	private boolean isValidNewSchool(School newSchool) {
		return isValidSchool(newSchool) && (newSchool != getSchool()) && (getSchool().getAmountSlimes() < newSchool.getAmountSlimes());
	}
	
	/**
	 * 
	 * @param school
	 * @return	...
	 * 			| result == ((school != null) && (not school.isTerminated()))
	 */
	private boolean isValidSchool(School school) {
		return ((school != null) && (!school.isTerminated()));
	}
	
	/**
	 * @post	...
	 * 			| new.getSchool() == null
	 */
	private void removeSchool() {
		this.school = null;
	}

	/**
	 * @param 	school
	 * @throws	IllegalArgumentException
	 * 			...
	 * 			| ! isValidSchool(school)
	 * @post 	...
	 * 			| new.getSchool() == school;
	 */
	private void setSchool(School school) throws IllegalArgumentException {
		if (!isValidSchool(school))
			throw new IllegalArgumentException();
		this.school = school;
	}
	
	/**
	 * 
	 * @param school
	 * @pre	...
	 * 		| isValidNewSchool(school)
	 * @effect	...
	 * 			| setSchool(school)
	 */
	private void setNewSchool(School school) {
		assert(isValidNewSchool(school));
		setSchool(school);
	}
	
	/**
	 * This variable contains the school in which this slime is
	 */
	private School school;
	
	
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
	
	/**
	 * This variable contains the current horizontal velocity of this Slime
	 */
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
	
	/**
	 * This variable contains the current vertical velocity of this Slime
	 */
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
	
	/**
	 * This variable contains the current horizontal acceleration
	 */
	private double accelerationX;
	
	/**
	 * 
	 * @return	...
	 * 			| result == 0.7
	 * 			
	 */
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
	
	
	/**
	 * This variable contains the current vertical acceleration of this Slime
	 */
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
	
	public void advanceTime(double dt) throws IllegalArgumentException {
		if(!isDead()){
			double seconds = dt;
			if (seconds < 0 || seconds >= 0.2) 
				throw new IllegalArgumentException();
			if (isInMagma()) {
				setInMagmaTimer(getInMagmaTimer() + dt);
			} else {
				// immediately lose points when in magma
				setInMagmaTimer(0.2);
			}
			if (isInWater()) {
				setInWaterTimer(getInWaterTimer() + dt);
			} else {
				setInWaterTimer(0);
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
				if (getInWaterTimer() >= 0.2) {
					setHitPoints(getHitPoints()-2);
					setInWaterTimer(getInWaterTimer()-0.2);
				}
			} else {
				setInWaterTimer(0);
			}
			if (isInMagma()) {
				if (getInMagmaTimer() >= 0.2) {
					setInMagmaTimer(getInMagmaTimer()-0.2);
					setHitPoints(getHitPoints()-50);
				}
			} else {
				// immediately lose points when in magma
				setInMagmaTimer(0.2);
			}
		} else {
			setTimeDead(getTimeDead() + dt);
			if(getTimeDead() > 0.6){
				terminate();
			}
		}
	}
	
	private void advanceTimeCollisionDetect(double dt){
		setMovementTime(getMovementTime()-dt);
		if(getMovementTime() <= 0){
			newMovement();
		}
		updateVelocity(dt);
		updateLocation(dt);	
		handleCollisionMazub();
		handleCollisionShark();		
		handleCollisionSlime();
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
		} else if(isSetVelocityYZero()){
			setVelocityY(0);
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
	 * 
	 * @effect 	...
	 * 			| setHitPoints(getHitPoints() - 50)
	 * @effect 	...
	 * 			| school.reducePoint(this)
	 */
	void hadCollisionMazub() {
		setHitPoints(getHitPoints() - 50);
		getSchool().reducePoint(this);
	}
	
	private void handleCollisionMazub(){
		if(!getWorld().getMazub().isImmune() && getWorld().collisionMazubInPerimeters((int)getLocationX(), (int)getLocationY(), (int)getLocationX()+getCurrentSprite().getWidth(), (int)getLocationY()+getCurrentSprite().getHeight())){
			getWorld().getMazub().hadCollissionSlime();
			hadCollisionMazub();
		}
	}
	
	private void handleCollisionShark() {
		Collection<Shark> collection = getWorld().collisionSharksInPerimeters((int) getLocationX(), (int) getLocationY(), (int) getLocationX()+getCurrentSprite().getWidth(), (int) getLocationY()+getCurrentSprite().getHeight());
		for (Shark shark : collection) {
			hadCollisionShark();
			shark.hadCollisionSlime();
			if(shark.isTerminated()){
				getWorld().removeShark(shark);				
			}
		}
	}
	
	private void handleCollisionSlime() {
		Collection<Slime> collection = getWorld().collisionSlimesInPerimeters((int) getLocationX(), (int) getLocationY(), (int) getLocationX()+getCurrentSprite().getWidth(), (int) getLocationY()+getCurrentSprite().getHeight());
		for (Slime slime: collection) {
			//TODO soms NullPointerException?
			if (!(this.getSchool().equals(slime.getSchool())) && (slime.getSchool().getAmountSlimes() > this.getSchool().getAmountSlimes())) {
				this.changeSchool(slime.getSchool());
			}
		}
	}
	
	private void changeSchool(School newSchool) {
		assert (isValidNewSchool(newSchool));
		school.removeSlime(this);
		// TODO
		setNewSchool(newSchool);
		newSchool.addNewSchoolMember(this);
	}
	
	/**
	 * @effect	...
	 * 			| setWorld(null)
	 * @effect	...
	 * 			| setTerminated(true)
	 */
	void terminate() {
		// remove world
		this.removeWorld();
		getSchool().removeSlime(this);
		removeSchool();
		// set boolean
		setTerminated(true);
	}

	public void hadCollisionShark() {
		setHitPoints(getHitPoints()-50);		
	}

	@Override
	int getMaxHitPoints() {
		return Integer.MAX_VALUE;
	}

	@Override
	protected boolean isValidWorld(World world) {
		// TODO niet volledig
		return world != null;
	}
}
