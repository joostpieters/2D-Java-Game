package jumpingalien.model;

import java.util.Collection;

import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
import be.kuleuven.cs.som.annotate.*;

/**
 * 
 * @author Pieter-Jan Coenen (1ste Bacherlor Informatica) en Stijn Caerts (1ste Bacherlor Informatica)
 * @invar 	...
 * 			|getHitPoints() >= 0
 * @invar	...
 * 			| getSchool() != null
 * @invar	...
 * 			| getWorld() != null
 *
 */
public class Slime extends GameObject {
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
	 * 			| school.addSlime(this)
	 * @effect	...
	 * 			| setHitPoints(100)
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
	 * 			| setInMagmaTimer(0.2)
	 * @effect	...
	 * 			| setInWaterTimer(-1) 
	 */
	public Slime (int x, int y, Sprite[] sprites, School school) throws IllegalArgumentException{
		super(x, y);
		if(sprites.length != 2){
			throw new IllegalArgumentException();
		}
		if (!isValidSchool(school)) {
			throw new IllegalArgumentException();
		}
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
		setInWaterTimer(-1);
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
	@Basic @Override
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
	
	
	@Basic @Override
	protected double getVelocityX() {
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
	
	@Basic @Override
	protected double getVelocityY() {
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
	
	@Basic @Override
	protected double getAccelerationX() {
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
	 */
	private static double getInitialAccelerationX() {
		return 0.7;
	}
	
	@Basic @Override
	protected double getAccelerationY() {
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
	 * @return 	true when the movement direction is LEFT
	 * 			| if getMovementDirection() == Direction.LEFT then
	 * 			|	return true
	 * 			| else then
	 * 			| 	return false
	 */
	private boolean isMovingLeft() {
		if (this.getMovementDirection() == Motion.LEFT) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Updates the location of this Slime given a certain amount of seconds
	 * @param 	seconds
	 * 			The seconds to compute the new position.
	 * @pre		seconds needs to be positive.
	 * 			| seconds >= 0
	 * @effect	...
	 * 			| setIsOnGameObject(false)
	 * @effect	...
	 * 			| double[] location = calculateLocation(seconds)
	 * 			| if (locationIsValidInWorld((int) location[0], (int) location[1]) then
	 * 			|	calculateLocationCollisionTerrain(seconds, location)
	 * 			|	calculateLocationCollisionObjects(location)
	 * 			| 	calculateLocationCollisionSlime(location)
	 * 			|	setLocation(location)
	 */
	private void updateLocation(double seconds) {
		assert (seconds >= 0);
		setIsOnGameObject(false);
		double[] location = calculateLocation(seconds);
		if(locationIsValidInWorld((int)location[0],(int) location[1])){
			calculateLocationCollisionTerrain(seconds, location);
			calculateLocationCollisionObjects(location);
			calculateLocationCollisionSlime(location);
			setLocation(location);
		}
	}

	/**
	 * @param location
	 * @effect	...
	 * 			| if(hasCollisionSlime(location[0], location[1], location[0] + this.getCurrentSprite().getWidth(), location[1] + this.getCurrentSprite().getHeight())) then
	 *			|	if(!hasCollisionSlime(getLocationX(), location[1], getLocationX() + this.getCurrentSprite().getWidth(), location[1] + this.getCurrentSprite().getHeight())) then
	 *			|		location[0] = getLocationX()
	 *			|	else then
	 *			|		if(!hasCollisionSlime(location[0], getLocationY(), location[0] + this.getCurrentSprite().getWidth(), getLocationY() + this.getCurrentSprite().getHeight())) then
	 *			|			location[1] = getLocationY();	
	 *			|			setIsOnGameObject(true);
	 *			|		else then
	 *			|			location[0] = getLocationX();
	 *			|			location[1] = getLocationY();
	 *			|			setIsOnGameObject(true);
	 */
	public void calculateLocationCollisionSlime(double[] location) {
		if(hasCollisionSlime(location[0], location[1], location[0] + this.getCurrentSprite().getWidth(), location[1] + this.getCurrentSprite().getHeight())){
			if(!hasCollisionSlime(getLocationX(), location[1], getLocationX() + this.getCurrentSprite().getWidth(), location[1] + this.getCurrentSprite().getHeight())){
				location[0] = getLocationX();				
			} else {
				if(!hasCollisionSlime(location[0], getLocationY(), location[0] + this.getCurrentSprite().getWidth(), getLocationY() + this.getCurrentSprite().getHeight())){
					location[1] = getLocationY();	
					setIsOnGameObject(true);
				} else {
					location[0] = getLocationX();
					location[1] = getLocationY();
					setIsOnGameObject(true);
				}
			}
		}
	}

	/**
	 * 
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 * @return	...
	 * 			| result == getWorld().collisionSlimes((int)startX, (int)startY, (int)endX, (int)endY, this).size() > 0
	 */
	private boolean hasCollisionSlime(double startX, double startY, double endX, double endY) {
		return getWorld().collisionSlimes((int)startX, (int)startY, (int)endX, (int)endY, this).size() > 0;
	}
	
	/**
	 * 
	 * @return 	...
	 * 			| result == 2.5
	 */
	private double getMaximumHorizontalVelocity() {
		return 2.5;
	}
	
	/**
	 * This function returns whether this Slime is on Solid ground or not
	 * @return	...
	 * 			| hasCollisionBottom((int)getLocationX(), (int)getLocationY()-1)
	 */
	private boolean isOnSolidGround(){
		return hasCollisionBottom((int)getLocationX(), (int)getLocationY()-1);
	}
	
	/**
	 * 
	 * @param dt
	 * @throws IllegalArgumentException
	 * 			| (dt < 0 || dt >= 0.2)
	 * @effect	...
	 * 			| if (not isDead()) then
	 * 			|	updateLocationAndVelocity(dt)
	 * @effect 	...
	 * 			| if (isDead()) then
	 * 			|	setTimeDead(getTimeDead() + dt)
	 * 			|		if (getTimeDead() >= 0.6) then
	 * 			|			terminate()
	 */
	public void advanceTime(double dt) throws IllegalArgumentException {
		if (dt < 0 || Util.fuzzyGreaterThanOrEqualTo(dt, 0.2)) 
			throw new IllegalArgumentException();
		if(!isDead()){
			updateLocationAndVelocity(dt);
		} else {
			setTimeDead(getTimeDead() + dt);
			if(Util.fuzzyGreaterThanOrEqualTo(getTimeDead(), 0.6)){
				terminate();
			}
		}
	}
	
	/**
	 * @param dt
	 * @effect 	...
	 * 			| if (! isOnSolidGround()) then
	 * 			|	setAccelerationY(-10)
	 * @effect	...
	 * 			| setMovementTime(getMovementTime() - dt)
	 * @effect	...
	 * 			| if (getMovementTime() <= 0) then
	 * 			|	newMovement()
	 * @effect	...
	 * 			| updateVelocity(dt)
	 * @effect	...
	 * 			| updateLocation(dt)
	 * @effect	...
	 * 			| handleMagma(dt)
	 * @effect	...
	 * 			| handleCollisionMazub()
	 * @effect	...
	 * 			| handleCollisionShark()
	 * @effect	...
	 * 			| handleCollisionSlime()
	 * @effect	...
	 * 			| handleWater(dt)
	 */
	@Override
	protected void advanceTimeCollisionDetect(double dt){
		if (! isOnSolidGround()) {
			setAccelerationY(-10);
		}
		setMovementTime(getMovementTime()-dt);
		if(getMovementTime() <= 0){
			newMovement();
		}
		updateVelocity(dt);
		updateLocation(dt);	
		handleMagma(dt);
		handleCollisionMazub();
		handleCollisionShark();		
		handleCollisionSlime();
		handleWater(dt);
	}
	
	/**
	 * 
	 * @param dt
	 * @effect	...
	 * 			| if (isMovingLeft()) then
	 * 			|	if (getVelocityX() + getAccelerationX()*(-1)*dt < -getMaximumHorizontalVelocity()) then
	 * 			|		setVelocityX(-getMaximumHorizontalVelocity())
	 * 			|	else then
	 * 			|		setVelocityX(getVelocityX() + getAccelerationX()*(-1)*dt)
	 * @effect	...
	 * 			| if (!isMovingLef()) then
	 * 			|	if (getVelocityX() + getAccelerationX()*dt > getMaximumHorizontalVelocity()) then
	 * 			|		setVelocityX(getMaximumHorizontalVelocity())
	 * 			|	else then
	 * 			|		setVelocityX(getVelocityX + getAccelerationX()*dt)
	 * @effect	...
	 * 			| if (isOnSolidGround()) then
	 * 			|	setVelocityY(0)
	 * 			|	setAccelerationY(0)
	 * 			| else if (isOnGameObject()) then
	 * 			|	setVelocityY(0)
	 * 			| else then
	 * 			|	setVelocityY(getVelocityY() + getAccelerationY()*dt)
	 */
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
		} else if(isOnGameObject()){
			setVelocityY(0);
		} else {
			setVelocityY(getVelocityY() + getAccelerationY()*dt);
		}
	}
	
	/**
	 * @effect	...
	 * 			| switch ((int)(Math.random()*2)) {
	 * 			| 	case 0:	setMovementDirection(Direction.LEFT)
	 * 			|			setCurrentSpriteIndex(0)
	 * 			|			break
	 * 			|	case 1: setMovementDirection(Direction.RIGHT)
	 * 			|			setCurrentSpriteIndex(1)
	 * 			|			break
	 * @effect	...
	 * 			| setVelocityX(0)
	 * @effect	...
	 * 			| setAccelerationX(getInitialAccelerationX())
	 * @effect	...
	 * 			| setMovementTime(2 + (int) (Math.random()*5))
	 */
	private void newMovement(){
		int random = (int)(Math.random()*2);
		switch (random){
			case 0: setMovementDirection(Motion.LEFT);
					setCurrentSpriteIndex(0);
					break;
			case 1: setMovementDirection(Motion.RIGHT);
					setCurrentSpriteIndex(1);
					break;
		}
		double time = 2 + (int)(Math.random()*5);
		
		setVelocityX(0);
		setAccelerationX(getInitialAccelerationX());
		
		setMovementTime(time);
	}
	
	@Basic
	private Motion getMovementDirection() {
		return this.movementDirection;
	}
	
	/**
	 * 
	 * @param direction
	 * @post	...
	 * 			| new.getMovementDirection() == direction
	 */
	private void setMovementDirection(Motion direction) {
		this.movementDirection = direction;
	}
	
	/**
	 * This variable contains the current movement Direction for this slime
	 */
	private Motion movementDirection;
		
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
	
	/**
	 * @effect	...
	 * 			| if (!getWorld().getMazub().isImmune() && getWorld().collisionMazubInPerimeters((int)getLocationX(), (int)getLocationY(), (int)getLocationX()+getCurrentSprite().getWidth(), (int)getLocationY()+getCurrentSprite().getHeight()) then
	 * 			|	getWorld().getMazub().hadCollisionSlime()
	 * 			|	hadCollisionMazub()
	 */
	private void handleCollisionMazub(){
		if(!getWorld().getMazub().isImmune() && getWorld().collisionMazubInPerimeters((int)getLocationX(), (int)getLocationY(), (int)getLocationX()+getCurrentSprite().getWidth(), (int)getLocationY()+getCurrentSprite().getHeight())){
			getWorld().getMazub().hadCollisionSlime();
			hadCollisionMazub();
		}
	}
	
	/**
	 * @effect	...
	 * 			| for each shark in getWorld().collisionSharksInPerimeters((int) getLovationX(), (int) getLocationY(), (int) getLocationX() + getCurrentSprite().getWidth(), (int) getLocationY() + getCurrentSprite().getHeight()
	 * 			| 	hadCollisionShark()
	 * 			|	shark.hadCollisionSlime()
	 * 			|	if (shark.isTerminated()) then
	 * 			| 		getWorld.removeShark(shark)
	 */
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
	
	/**
	 * @effect	...
	 * 			| for each slime in getWorld().collisionSlimesInPerimeters((int) getLocationX(), (int) getLocationY(), (int) getLocationX()+getCurrentSprite().getWidth(), (int) getLocationY()+getCurrentSprite().getHeight())
	 * 			|	if ((this.getSchool() != null) && (slime.getSchool() != null) && !(this.getSchool() == slime.getSchool() && (slime.getSchool().getAmountSlimes() > this.getSchool().getAmountSlimes())) then
	 * 			|		this.changeSchool(slime.getSchool())
	 */
	private void handleCollisionSlime() {
		Collection<Slime> collection = getWorld().collisionSlimesInPerimeters((int) getLocationX(), (int) getLocationY(), (int) getLocationX()+getCurrentSprite().getWidth(), (int) getLocationY()+getCurrentSprite().getHeight());
		for (Slime slime: collection) {
			if ((this.getSchool() != null) && (slime.getSchool() != null) && !(this.getSchool() == slime.getSchool()) && (slime.getSchool().getAmountSlimes() > this.getSchool().getAmountSlimes())) {
				this.changeSchool(slime.getSchool());
			}
		}
	}
	
	/**
	 * 
	 * @param newSchool
	 * @pre	...
	 * 		| isValidNewSchool(newSchool)
	 * @effect	...
	 * 			| school.removeSlime(this)
	 * @effect	...
	 * 			| setNewSchool(newSchool)
	 * @effect	...
	 * 			| newSchool.addNewSchoolMember(this)
	 */
	private void changeSchool(School newSchool){
		getSchool().removeSlime(this);
		setNewSchool(newSchool);	
		try {
			newSchool.addNewSchoolMember(this);
		} catch (IllegalAccessException e) {
			assert(false);
		}
	}
	
	/**
	 * @effect	...
	 * 			| getWorld().removeSlime(this)
	 * @effect	...
	 * 			| removeWorld()
	 * @effect	...
	 * 			| getSchool().removeSlime(this)
	 * @effect	...
	 * 			| removeSchool()
	 * @effect	...
	 * 			| setTerminated(true)
	 */
	@Override
	void terminate() {
		// remove world
		this.removeWorld();
		getSchool().removeSlime(this);
		removeSchool();
		// set boolean
		setTerminated(true);
	}

	/**
	 * @effect	...
	 * 			| setHitPoints(getHitPoints() - 50)
	 */
	public void hadCollisionShark() {
		setHitPoints(getHitPoints()-50);		
	}

	/**
	 * @return	...
	 * 			| result == Integer.MAX_VALUE
	 */
	@Override
	int getMaxHitPoints() {
		return Integer.MAX_VALUE;
	}

	/**
	 * @return	...
	 * 			| result == ((world != null) && (world.hasAsSlime(this)))
	 */
	@Override
	protected boolean isValidWorld(World world) {
		return (world != null) && (world.hasAsSlime(this));
	}

	/**
	 * @return	...
	 * 			| if (getMovementDirection() == Direction.LEFT) then
	 * 			|	result == getAccelerationX()*(-1)
	 * 			| else then
	 * 			| 	result == getAccelerationX()
	 */
	@Override
	double getActualAccelerationX() {
		if (getMovementDirection() == Motion.LEFT) {
			return getAccelerationX()*(-1);
		} else {
			return getAccelerationX();
		}
	}
}
