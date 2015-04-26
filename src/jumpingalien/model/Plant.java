package jumpingalien.model;

import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.Basic;

/**
 * 
 * @author Stijn Caerts en Pieter-Jan Coenen
 * @invar de lengte van de spirtes is gelijk aan 2
 *
 */
public class Plant extends GameObjects {
	/**
	 * 
	 * @param x
	 *            the x location of this Plant
	 * @param y
	 *            the y location of this Plant
	 * @param sprites
	 *            the sprites for this Plant
	 * @effect ... | setLocationX(x)
	 * @effect ... | setLocationY(y)
	 * @effect ... | setSprites(sprites)
	 * @effect ... | setCurrentSprite(getSprites()[0])
	 * @effect ... | setHorizontalVelocity(-0.5)
	 * @throws IllegalArgumentException
	 *             ... | sprites.length != 2
	 */
	public Plant(int x, int y, Sprite[] sprites)
			throws IllegalArgumentException {
		if (sprites.length != 2) {
			throw new IllegalArgumentException();
		}
		setLocationX(x);
		setLocationY(y);
		setSprites(sprites);
		setCurrentSpriteIndex(0);
		setHorizontalVelocity(-0.5);
	}

	/**
	 * Returns the current sprite of this Plant
	 */
	@Basic
	public Sprite getCurrentSprite() {
		return getSprites()[getCurrentSpriteIndex()];
	}

	private int getCurrentSpriteIndex() {
		return this.currentSpriteIndex;
	}

	/**
	 * Sets the current sprite to the given sprite
	 * 
	 * @param index
	 *            the sprite to be set as current sprite
	 * @post new.getCurrentSprite() = sprite
	 */
	private void setCurrentSpriteIndex(int index) {
		this.currentSpriteIndex = index;
	}

	/**
	 * This variable contains the index of the current sprite
	 */
	private int currentSpriteIndex;

	/**
	 * @return the horizontalVelocity
	 */
	@Basic
	private double getHorizontalVelocity() {
		return horizontalVelocity;
	}

	/**
	 * @param horizontalVelocity
	 *            the horizontalVelocity to set
	 * @post ... | new.getHorizontalVelocity() == horizontalVelocity
	 */
	private void setHorizontalVelocity(double horizontalVelocity) {
		this.horizontalVelocity = horizontalVelocity;
	}

	/**
	 * This variable contains the current horizontal velocity of this Plant
	 */
	private double horizontalVelocity;

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
		setTimer(getTimer() + dt);
		while (dt > 0) {
			double dt1 = 0.2;
			if (getHorizontalVelocity() != 0) {
				dt1 = (0.01) / (Math.abs(getHorizontalVelocity()));
			}
			if ((dt1 != 0.2)) {
				if (dt < dt1) {
					advanceTimeCollision(dt);
					dt = 0;
				} else {
					advanceTimeCollision(dt1);
					dt -= dt1;
				}
			} else {
				dt = 0;
			}
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
	public void advanceTimeCollision(double dt) {
		if (getTimer() > 0.5) {
			if (getHorizontalVelocity() > 0) {
				setHorizontalVelocity(-0.5);
				setCurrentSpriteIndex(0);
			} else {
				setHorizontalVelocity(0.5);
				setCurrentSpriteIndex(1);
			}
			setTimer(0);
		}
		updateLocationX(dt);
		handleCollisionMazub();
	}

	/**
	 * 
	 * @param dt
	 * @effect ... | if(locationX >= getWorld().getWorldSizeInPixels()[0]) then
	 *         | setLocationX(getWorld().getWorldSizeInPixels()[0] - 1)
	 * @effect ... | if(locationX < 0) then | setLocationX(0)
	 * @effect ... | if((locationX < getWorld().getWorldSizeInPixels()[0]) &&
	 *         (locationX >= 0)) then | setLocationX(locationX)
	 */
	private void updateLocationX(double dt) {
		double locationX = getLocationX() + (getHorizontalVelocity() * dt) * 100;
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
	
	private void teminate(){
		this.setWorld(null);
		// set boolean
		setTerminated(true);
	}

	/**
	 * @effect  ...
	 * 			| terminate()
	 */
	void hadCollisionMazub() {
		teminate();		
	}
	
	private void handleCollisionMazub(){
		if(getWorld().collisionMazubInPerimeters((int)getLocationX(), (int)getLocationY(), (int)getLocationX()+getCurrentSprite().getWidth(), (int)getLocationY()+getCurrentSprite().getHeight())){
			getWorld().getMazub().handleCollisionPlant(this);
		}
	}

	@Override
	int getMaxHitPoints() {
		return 0;
	}

	@Override
	protected boolean isValidWorld(World world) {
		// TODO nog niet klaar
		return world != null;
	}
}
