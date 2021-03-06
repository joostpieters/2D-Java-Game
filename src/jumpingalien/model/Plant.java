package jumpingalien.model;

import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * 
 * @author Stijn Caerts en Pieter-Jan Coenen
 /**
 * 
 * @author Pieter-Jan Coenen (1ste Bacherlor Informatica) en Stijn Caerts (1ste Bacherlor Informatica)
 * 
 * @invar 	...
 * 			| 0 <= getHitPoints() && getHitPoints() <= getMaxHitpoints()
 * @invar	...
 * 			| getSchool() != null
 * @invar	...
 * 			| getWorld() != null
 * @invar	...
 * 			| getVelocityX() <= getMaxHorizontalVelocity()
 * @invar	...
 * 			| isValidSpriteArray(getSprites())
 * @invar	...
 * 			| isValidLocationInWorld(getLocationX(), getLocationY())
 *
 */
public class Plant extends GameObject {
	
	/**
	 * @param x
	 *            the x location of this Plant
	 * @param y
	 *            the y location of this Plant
	 * @param sprites
	 *            the sprites for this Plant
	 * @effect	call the constructor of the superclass
	 * 			| super(x, y, sprites)
	 * @effect 	... 
	 * 			|setHorizontalVelocity(-0.5)
	 */
	@Raw
	public Plant(int x, int y, Sprite[] sprites) throws IllegalArgumentException {
		super(x, y, sprites);
		setVelocityX(-0.5);
	}
	
	/**
	 * @param 	x
	 *            the x location of this Plant
	 * @param 	y
	 *            the y location of this Plant
	 * @param 	sprites
	 *            the sprites for this Plant
	 * @param 	program
	 * 				the program for this plant 
	 * @effect	call the constructor of the superclass
	 * 			| super(x, y, sprites)
	 */
	@Raw
	public Plant(int x, int y, Sprite[] sprites, Program program) throws IllegalArgumentException {
		super(x, y, sprites, program);
	}

	/**
	 * Returns the current sprite of this Plant
	 */
	@Basic
	public Sprite getCurrentSprite() {
		if(this.isMovingLeft()){
			return getSprites()[0];
		} else {
			return getSprites()[1];
		}
	}

	
	/**
	 * @return 	...
	 * 			| result == 2
	 */
	@Override
	int getRequiredLengthSpriteArray() {
		return 2;
	}

	/**
	 * @return the horizontalVelocity
	 */
	@Basic
	public double getVelocityX() {
		return velocityX;
	}

	/**
	 * @param horizontalVelocity
	 *            the horizontalVelocity to set
	 * @post ... | new.getHorizontalVelocity() == horizontalVelocity
	 */
	protected void setVelocityX(double horizontalVelocity) {
		this.velocityX = horizontalVelocity;
	}

	/**
	 * This variable contains the current horizontal velocity of this Plant
	 */
	private double velocityX;

	/**
	 * Sets the timer of this Plant to the given time
	 * 
	 * @param time
	 *            the value to set to the timer
	 * @post | new.getTimer() == time
	 */
	private void setTimer(double time) {
		this.timer = time;
	}

	/**
	 * Returns the value of the timer of this Plant
	 */
	@Basic
	private double getTimer() {
		return this.timer;
	}

	/**
	 * This variable holds the value of the timer.
	 */
	private double timer;

	/**
	 * 
	 * @param 	dt
	 *          The time interval (in seconds) by which to advance the given
	 *            world's time.
	 * @effect 	... 
	 * 			| setTimer(getTimer()+dt)
	 * @effect 	...
	 * 			| while (dt > 0)
	 * 			|	if (getHorizontalVelocity() != 0) then
	 * 			| 		if(dt < (0.01) / (Math.abs(getHorizontalVelocity()))) then
	 * 			|			advanceTimeCollision(dt);
	 * 			|			dt = 0
	 * 			|		else then
	 * 			| 			advanceTimeCollision(dt1);
	 * 			| 			dt -= dt1
	 * 			
	 */
	public void advanceTime(double dt) {
		if(!isTerminated()){
			setTimer(getTimer() + dt);
			if(hasAProgram()){
				getProgram().run((int) Math.ceil(dt/0.001));
			}
			updateLocationAndVelocity(dt);
		}
	}

	/**
	 * 
	 * @param 	dt
	 *          The time interval (in seconds) by which to advance the given
	 *            world's time.
	 * @effect 	... 
	 * 			| updateLocationX(dt)
	 * @effect 	... 
	 * 			| setTimer(getTimer()+dt)
	 * @effect 	... 
	 * 			| if (getTimer() > 0.5) then | if(getHorizontalVelocity() > 0) then 
	 * 			| setHorizontalVelocity(-0.5) | setCurrentSpriteIndex(0);
	 *         	| else then 
	 *         	| setHorizontalVelocity(0.5) 
	 *         	| setCurrentSpriteIndex(0); 
	 *         	| setTimer(0);
	 */
	protected void advanceTimeCollisionDetect(double dt) {
		if(hasAProgram()){
						
		} else if (getTimer() > 0.5) {
			if (getVelocityX() > 0) {
				setVelocityX(-0.5);
			} else {
				setVelocityX(0.5);
			}
			setTimer(0);
		}
		updateLocationX(dt);
		handleCollisionMazub();
	}

	/**
	 * 
	 * @param 	dt
	 * @effect	...
	 * 			| if(hasCollisionX((int)locationX, (int)getLocationY())) then
	 * 			|	setLocationX(getLocationX())
	 * @effect 	... 
	 * 			| if(locationX >= getWorld().getWorldSizeInPixels()[0]) then
	 *         	| setLocationX(getWorld().getWorldSizeInPixels()[0] - 1)
	 * @effect 	... 
	 * 			| if(locationX < 0) then | setLocationX(0)
	 * @effect ... 
	 * 			| if((locationX < getWorld().getWorldSizeInPixels()[0]) &&
	 *         		(locationX >= 0)) then | setLocationX(locationX)
	 */
	private void updateLocationX(double dt) {
		double locationX = getLocationX() + (getVelocityX() * dt) * 100;
		if(hasCollisionX((int)locationX, (int)getLocationY())){
			locationX = getLocationX();
		}
		if (locationX >= getWorld().getWorldSizeInPixels()[0]) {
			setLocationX(getWorld().getWorldSizeInPixels()[0] - 1);
		} else if (locationX < 0) {
			setLocationX(locationX);
		} else {
			setLocationX(locationX);
		}
	}
	
	/**
	 * @effect	...
	 * 			| this.setWorld(null)
	 * @effect	...
	 * 			| setTerminated(true)
	 */
	@Override
	void terminate(){
		removeWorld();
		setTerminated(true);
	}

	/**
	 * @effect  ...
	 * 			| terminate()
	 */
	void hadCollisionMazub() {
		terminate();		
	}
	
	/**
	 * @effect	...
	 * 			| if (getWorld().collisionMazubInPerimeters((int)getLocationX(), (int)getLocationY(), 
	 * 			|	(int)getLocationX()+getCurrentSprite().getWidth(), (int)getLocationY()+getCurrentSprite().getHeight())) then
	 * 			|		getWorld().getMazub().handleCollisionPlant(this)
	 */
	private void handleCollisionMazub(){
		if(getWorld().collisionMazubInPerimeters((int)getLocationX(), (int)getLocationY(), (int)getLocationX()+getCurrentSprite().getWidth(), (int)getLocationY()+getCurrentSprite().getHeight())){
			getWorld().getMazub().handleCollisionPlant(this);
		}
	}

	/**
	 * @return	...
	 * 			| result == 0
	 */
	@Override
	int getMaxHitPoints() {
		return 0;
	}

	/**
	 * @param	world
	 * @return 	...
	 * 			| result == (world != null && getWorld().hasAsPlant(this))
	 */
	@Override
	protected boolean isValidWorld(World world) {
		return (world != null);
	}

	/**
	 * @return	...
	 * 			| result == 0
	 */
	@Override
	protected double getAccelerationY() {
		return 0;
	}

	/**
	 * @return	...
	 * 			| result == 0
	 */
	@Override
	public double getVelocityY() {
		return 0;
	}


	@Override
	protected double getInitialHorizontalAcceleration() {
		return 0;
	}

	@Override
	protected double getInitialHorizontalVelocity() {
		return -0.5;
	}

	@Override
	protected double getMaximumHorizontalVelocity() {
		return 0.5;
	}

	@Override
	protected boolean isDucking() {
		return false;
	}

}
