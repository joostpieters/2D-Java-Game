package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Basic;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;

public class Mazub {

	public Mazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites){
		this.setLocation(pixelLeftX, pixelBottomY);
		this.setSprites(sprites);
		this.setSpriteIndex(0);
		setTimer(0);
		setAmountSpritesForMovement( (sprites.length - 8) / 2 - 1);
	}
	
	/**
	 * Return locationX of this Mazub
	 * 	locationX expresses the current X coordinate of this Mazub
	 */
	@Basic
	public double getLocationX() {
		return this.locationX;
	}

	
	/**
	 * Set locationX of this Mazub to a given value
	 * 
	 * @param 	locationX
	 * 			The new locationY coordinate for this Mazub
	 * @pre		The given LocationX needs to be bigger or equal to 0
	 * 			| locationX >= 0
	 * @pre		The given LocationX needs to smaller or equal to the current game window width
	 * 			| locationX <= getWindowWidth()
	 * @post 	The LocationX of this Mazub is equal to the given LocationX
	 * 			| new.getLocationX() == locationX  
	 */
	public void setLocationX(double locationX) throws IllegalArgumentException {
		if ((locationX < 0) || (locationX > getWindowWidth()-1)){
			throw new IllegalArgumentException("X coordinate is out of window range");
		}
		this.locationX = locationX;
	}
	
	/**
	 * This variable contains the current X coordinate of this Mazub
	 */	
	private double locationX;
	
	/**
	 * Return the width of the game window 	
	 */
	public int getWindowWidth() {
		return 1024;
	}
	
	/**
	 * Return locationY of this Mazub
	 * 	locationY expresses the current Y coordinate of this Mazub
	 */
	@Basic
	public double getLocationY() {
		return this.locationY;
	}
	
	/**
	 * Set locationY of this Mazub to a given value
	 * 
	 * @param 	locationY
	 * 			The new locationY for this Mazub
	 * @pre		The given LocationY needs to be bigger or equal to 0
	 * 			| locationY >= 0
	 * @pre		The given LocationY needs to smaller or equal to the current game widow height
	 * 			| locationY <= getWindowHeight()
	 * @post 	The LocationY of this Mazub is equal to the given LocationY
	 * 			| new.getLocationY() == locationY  
	 */
	public void setLocationY(double locationY) throws IllegalArgumentException {
		if((locationY < 0)||(locationY > getWindowHeight()-1)){
			throw new IllegalArgumentException("Y coordinate is out of window range");
		};
		this.locationY = locationY;
	}
	
	/**
	 * This variable contains the current Y coordinate of this Mazub
	 */
	private double locationY;
	
	/**
	 * Return the height of the game window 	
	 */
	public int getWindowHeight() {
		return 768;
	}
	
	/**
	 * Set locationX and locationY of this Mazub to a given value
	 * 
	 * @param 	pixelLeftX
	 * 			The new locationX for this Mazub
	 * @param 	pixelBottomY
	 * 			The new locationY for this Mazub
	 * @pre		The given pixelLeftX needs to be bigger or equal to 0
	 * 			| pixelLeftX >= 0
	 * @pre		The given pixelLeftX needs to smaller or equal to the current game window width
	 * 			| pixelLeftX <= getWindowWidth()
	 * @pre		The given pixelBottomY needs to be bigger or equal to 0
	 * 			| pixelBottomY >= 0
	 * @pre		The given pixelBottomY needs to smaller or equal to the current game widow height
	 * 			| pixelBottomY <= getWindowHeight()
	 * @post 	The LocationX of this Mazub is equal to the given pixelLeftX
	 * 			| new.getLocationX() == pixelLeftX
	 * @post 	The LocationY of this Mazub is equal to the given pixelBottomY
	 * 			| new.getLocationY() == pixelBottomY  
	 */
	public void setLocation(int pixelLeftX, int pixelBottomY) {
		setLocationX(pixelLeftX);
		setLocationY(pixelBottomY);
	}
	
	/**
	 * Return velocityX of this Mazub
	 * 	velocityX expresses the current horizontal velocity of this Mazub
	 */
	@Basic
	public double getVelocityX() {
		return this.velocityX;
	}
	
	/**
	 * Set velocityX of this Mazub to a given value
	 * 
	 * @param 	velocityX
	 * 			The new velocityX for this Mazub
	 * @pre		The given velocityX needs to be bigger or equal to the initial horizontal velocity of this Mazub
	 * 			| velocityX >= getInitialHorizontalVelocity()
	 * @pre		The given velocityX needs to smaller or equal to the maximum horizontal velocity of this Mazub
	 * 			| velocityX <= getMaximumHorizontalVelocity()
	 * @post 	The velocityX of this Mazub is equal to the given velocityX
	 * 			| new.getvelocityX() == velocityX
	 */
	public void setVelocityX(double velocityX) {
		assert((velocityX <= getMaximumHorizontalVelocity()) || (velocityX >= getInitialHorizontalVelocity()));
		this.velocityX = velocityX;
	}
	
	/**
	 * This variable contains the current horizontal velocity of this Mazub
	 */
	private double velocityX;
	
	/**
	 * Return the maximum Horizontal Velocity of this Mazub
	 * @return	returns 1 if Mazub is ducking, otherwise 3.
	 * 			| if this.isDucking() then
	 * 			|	return 1
	 * 			| else then
	 * 			|	return 3
	 */
	public double getMaximumHorizontalVelocity() {
		if (this.isDucking()) {
			return 1;
		} else {
			return 3;
		}
	}
	
	/**
	 * Return the initial Horizontal Velocity of this Mazub
	 * 
	 * @note 	the initial velocity is always bigger or equal to one
	 * 			| getInitialHorizontalVelocity() >= 1
	 */
	public double getInitialHorizontalVelocity() {
		return 1;
	}
	
	/**
	 * Return accelerationX of this Mazub
	 * 	accelerationX expresses the current horizontal acceleration of this Mazub
	 */
	@Basic
	public double getAccelerationX() {
		return accelerationX;
	}

	/**
	 * 
	 * @param 	accelerationX
	 * 			the new accelerationX for this Mazub
	 * @post 	if the given accelerationX is bigger or equal to zero, 
	 * 			the accelerationX of this Mazub is equal to the given accelerationX
	 * 			|if (accelerationX >= 0)
	 * 			|	new.getAcceleration() == accelerationX
	 * @post 	if the given accelerationX is lower than zero, 
	 * 			the accelerationX of this Mazub is equal to zero
	 * 			|if (accelerationX < 0)
	 * 			|	new.getAcceleration() == 0
	 */
	public void setAccelerationX(double accelerationX) {
		if (accelerationX < 0)
				accelerationX = 0;
		this.accelerationX = accelerationX;
	}
	
	/**
	 * this variable contains the current horizontal acceleration for this Mazub
	 */	
	private double accelerationX;
	
	
	/**
	 * Return velocityY of this Mazub
	 * 	velocityY expresses the current vertical velocity of this Mazub
	 */
	@Basic
	public double getVelocityY() {
		return this.velocityY;
	}

	/**
	 * 
	 * @param 	velocityY
	 * 			the new velocityY for this Mazub
	 * @post 	The velocityY of this Mazub is equal to the given velocityY
	 * 			| new.getvelocityY() == velocityY
	 */
	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}
	
	/**
	 * This variable contains the current vertical velocity of this Mazub
	 */
	private double velocityY;
	
	/**
	 * Return accelerationY of this Mazub
	 * 	accelerationY expresses the current vertical acceleration of this Mazub
	 */
	@Basic
	public double getAccelerationY() {
		return accelerationY;
	}

	/**
	 * 
	 * @param 	accelerationY
	 * 			the new accelerationY for this Mazub
	 * @post 	The accelerationY of this Mazub is equal to the given accelerationY
	 * 			| new.getAccelerationY() == accelerationY
	 */
	public void setAccelerationY(double accelerationY) {
		this.accelerationY = accelerationY;
	}
	
	/**
	 * this variable contains the current vertical acceleration for this Mazub
	 */
	private double accelerationY;
	
	/**
	 * Return sprites of this Mazub
	 * 	sprites is an array of all possible sprites for movements of this Mazub
	 */	
	@Basic
	public Sprite[] getSprites() {
		return sprites;
	}
	/**
	 * 
	 * @param 	sprites
	 * 			the new sprites for this Mazub
	 * @pre		sprites needs to contain at least 10 possible sprites
	 * 			| sprites.length >= 10
	 * @post 	The sprites of this Mazub is equal to the given sprites
	 * 			| new.getSprites() == sprites  	
	 */
	public void setSprites(Sprite[] sprites) throws ModelException {
		if (sprites.length < 10)
			throw new ModelException("Sprites needs to contain at least ten sprites");
		this.sprites = sprites;
	}
	
	
	/**
	 * Returns the right sprite for the current movements
	 */
	public Sprite getCurrentSprite() {
		
		// for all moves to the right
		if (this.isMovingRight()){
			if (this.isJumping())
				setSpriteIndex(4);
			else if (this.isDucking())
				setSpriteIndex(6);
			else
				setSpriteIndex(spritesMovingRightNormal());
		}
		
		// for all moves to the left
		else if (this.isMovingLeft()){
			if (this.isJumping())
				setSpriteIndex(5);
			else if (this.isDucking())
				setSpriteIndex(7);
			else
				setSpriteIndex(spritesMovingLeftNormal());
		}
		
		// if the character is Ducking
		else if(this.isDucking()){
			if ((this.lastMoveDirection == Direction.RIGHT_AND_DUCKING) &&(getTimer() - this.lastMoveTimer < 1)){
					setSpriteIndex(6);
			}
			else if ((this.lastMoveDirection == Direction.LEFT_AND_DUCKING) &&(getTimer() - this.lastMoveTimer < 1)){
				setSpriteIndex(7);
			}
			else{
				setSpriteIndex(1);
			}
		}
				
		// if the character is not moving
		else{
			if (((this.lastMoveDirection == Direction.RIGHT)|| (this.lastMoveDirection == Direction.RIGHT_AND_DUCKING)) &&(getTimer() - this.lastMoveTimer < 1))
				setSpriteIndex(2);
			else if (((this.lastMoveDirection == Direction.LEFT)|| (this.lastMoveDirection == Direction.LEFT_AND_DUCKING)) && (getTimer() - this.lastMoveTimer < 1))
				setSpriteIndex(3);
			else
				setSpriteIndex(0);
		}
		
		return sprites[this.getSpriteIndex()];
	}
	
	/**
	 * Return the number of the sprite for the right moving animation
	 * 		when this Mazub is moving to the right, the function will return every 75 ms an other sprite index
	 * 		so that it will look like this Mazub walks to the right 
	 */
	private int spritesMovingRightNormal() {
		if ((this.getSpriteIndex() > 8 + this.getAmountSpritesForMovement()) || (this.getSpriteIndex() < 8))
			return 8;
		if (this.timer >= 0.075){
			this.resetTimer();
			if ((this.getSpriteIndex() >= 8 + this.getAmountSpritesForMovement()) || (this.getSpriteIndex() < 8))
				return 8;
			else
				return this.getSpriteIndex() + 1;
		}
		return this.getSpriteIndex();
	}
	
	/**
	 * Return the number of the sprite for the left moving animation
	 * 		when this Mazub is moving to the left, the function will return every 75 ms an other sprite index
	 * 		so that it will look like this Mazub walks to the left 
	 */
	private int spritesMovingLeftNormal() {
		if (this.timer >= 0.075){
			this.resetTimer();
			if ((this.getSpriteIndex() >= 9 + getAmountSpritesForMovement() * 2) || this.getSpriteIndex() < 9 + this.getAmountSpritesForMovement())
				return 9 + getAmountSpritesForMovement();
			else
				return this.getSpriteIndex() + 1;
		}
		return getSpriteIndex();
	}	
	
	/**
	 * 
	 * @param 	direction
	 * 			the direction in which this Mazub needs to start moving
	 * @pre		The given direction needs to be, RIGHT, LEFT or UP
	 * 			|direction == Direction.RIGHT || direction == Direction.LEFT || direction == Direction.UP
	 * @effect 	if the given direction is RIGHT, the horizontal velocity will be set to the 
	 * 				initial horizontalvelocity
	 * 				and the acceleration will be equal to the initial horizontal acceleration
	 * 			|if (direction == Direction.RIGHT) then
	 * 			|	setVelocityX(getInitialHorizontalVelocity())
				|	setAccelerationX(getInitialHorizontalAcceleration())
	 * @effect 	if the given direction is LEFT, the horizontal velocity will be set to 
	 * 				the negative of the initial horizontal velocity
	 * 				and the acceleration will be set to the initial horizontal acceleration
	 * 			|if (direction == Direction.LEFT) then
	 * 			|	setVelocityX(getInitialHorizontalVelocity()*-1)
	 * 			|	setAccelerationX(getInitialHorizontalAcceleration())
	 * @effect	if the given direction is UP, the vertical velocity will be set to 8 and the
	 * 				vertical acceleration will be set to minus ten
	 * 			|if (direction == Direction.UP) then
	 * 			|	setVelocityY(8)
	 * 			| 	setAccelerationY(-10) 				
	 */
	public void startMove(Direction direction) {
		assert(direction == Direction.RIGHT || direction == Direction.LEFT || direction == Direction.UP);
		if (direction == Direction.RIGHT) {
			setVelocityX(getInitialHorizontalVelocity());
			setAccelerationX(getInitialHorizontalAcceleration());
		} else if (direction == Direction.LEFT){
			setVelocityX(getInitialHorizontalVelocity()*-1);
			setAccelerationX(getInitialHorizontalAcceleration());
		} else if (direction == Direction.UP){
			setVelocityY(8);
			setAccelerationY(-10);
		}
	}
	
	/**
	 * Return the initial Horizontal acceleration of this Mazub
	 */
	public double getInitialHorizontalAcceleration() {
		return 0.9;
	}
	
	/**
	 * Stops Mazub's horizontal movement and stores the direction of its last movement
	 * @POST 	the lastMoveTimer equals to the current value of timer
	 * 			| new.getLastMoveTimer() == getTimer()
	 * @POST	velocityX (horizontal velocity) equals to zero
	 * 			| new.getVelocityX() == 0
	 * @POST	accelerationX equals to zero
	 * 			| new.getAccelerationX() == 0
	 * @POST	if the last movement was to the right, and this Mazub is not ducking, then the lastMoveDirection equals Direction.RIGHT
	 * 			| if isMovingRight() then
	 * 			|	if not isDucking() then
	 * 			|		new.getLastMoveDirection(Direction.RIGHT)
	 * @POST	if the last movement was to the right, and this Mazub is ducking, then the lastMoveDirection equals Direction.RIGHT_AND_DUCKING
	 * 			| if isMovingRight() then
	 * 			|	if isDucking() then
	 * 			|		new.getLastMoveDirection(Direction.RIGHT_AND_DUCKING)
	 * @POST	if the last movement was to the left, and this Mazub is not ducking, then the lastMoveDirection equals Direction.LEFT
	 * 			| if isMovingLeft() then
	 * 			|	if not isDucking() then
	 * 			|		new.getLastMoveDirection(Direction.LEFT)
	 * @POST	if the last movement was to the left, and this Mazub is ducking, then the lastMoveDirection equals Direction.LEFT_AND_DUCKING
	 * 			| if isMovingLeft() then
	 * 			|	if isDucking() then
	 * 			|		new.getLastMoveDirection(Direction.LEFT_AND_DUCKING)
	 */
	public void stopMoveX(){
		if (isMovingRight()){
			if (isDucking())
				this.setLastMoveDirection(Direction.RIGHT_AND_DUCKING);
			else
				this.setLastMoveDirection(Direction.RIGHT);	
		}
		else if (isMovingLeft()){
			if (isDucking())
				this.setLastMoveDirection(Direction.LEFT_AND_DUCKING);
			else
				this.setLastMoveDirection(Direction.LEFT);
		}
		this.setLastMoveTimer(getTimer());
		setVelocityX(0);
		setAccelerationX(0);		
	}
	
	/**
	 * Return lastMoveDirection of this Mazub
	 * 		lastMoveDirection indicates the direction of the last move of this Mazub
	 */
	@Basic
	public Direction getLastMoveDirection() {
		return lastMoveDirection;
	}

	/**
	 * 
	 * @param 	lastMoveDirection
	 * 			the new direction in which this Mazub has last moved
	 * @post 	the new lastMoveDirection of this Mazub will equal to lastMoveDirection
	 * 			|new.getLastMoveDirection() = lastMoveDirection
	 */
	public void setLastMoveDirection(Direction lastMoveDirection) {
		this.lastMoveDirection = lastMoveDirection;
	}
	
	/**
	 * This variable contains the last direction in which this Mazub has moved
	 */
	private Direction lastMoveDirection;

	/**
	 * Return the value of timer
	 */
	@Basic
	public double getLastMoveTimer() {
		return lastMoveTimer;
	}

	/**
	 * 
	 * @param 	time
	 * 			the new time for this Mazub's lastMoveTimer
	 * @post	lastMoveTimer of this Mazub will be equal to the given time
	 * 			| new.getLastMoveTimer() = time
	 * 			
	 */
	public void setLastMoveTimer(double time) {
		this.lastMoveTimer = time;
	}
	
	/**
	 * This variable contains the time since when this Mazub did his last move
	 */
	private double lastMoveTimer;

	/**
	 * End this Mazub's jump
	 * @POST	if velocityY is greater than 0, velocityY will be zero
	 * 			| if getVelocityY() > 0 then
	 * 			|	new.getVelocityY == 0
	 */
	public void endJump() {
		if (this.getVelocityY() > 0) {
			this.setVelocityY(0);
		}
	}
	
	/**
	 * 
	 * @param 	seconds
	 * 			the elapsed seconds
	 * @pre 	seconds is a positive number
	 * 			| seconds >= 0
	 * @throws 	IllegalArgumentException
	 * @effect	Timer will be set to his current value plus the given seconds
	 * 			| setTimer(getTimer() + seconds)
	 * @effect	If this Mazub is Moving to the left, updateLocation() will be used
	 * 				to update the location, with the given seconds as parameter
	 * 				and the negative of the value of getAcceleration() as second parameter
	 * 				also the velocityX will be updated using the function getAcceleration()
	 * 				with the given seconds as parameter	and the negative of the
	 * 				value of getAcceleration() as second parameter
	 * 			| if isMovingLeft() then
	 * 			|	updateLocation(seconds, (-1)*getAccelerationX())
	 * 			|	updateVelocityX(seconds, (-1)*getAccelerationX())
	 * @effect	If this Mazub is Moving to the right, updateLocation() will be used
	 * 				to update the location, with the given seconds as parameter
	 * 				and the value of getAcceleration() as second parameter
	 * 				also the velocityX will be updated using the function getAcceleration()
	 * 				with the given seconds as parameter	and the value of getAcceleration() 
	 * 				as second parameter
	 * 			| if !isMovingLeft() then
	 * 			|	updateLocation(seconds, getAccelerationX())
	 * 			|	updateVelocityX(seconds, getAccelerationX())
	 * @effect	Also the vertical velocity and acceleration will be updated using
	 * 				updateVelocityAcceleration() with the given seconds as parameter
	 * 			| updateVelocityAcceleration(seconds)
	 */
	public void advanceTime(double seconds) throws IllegalArgumentException {
		if (seconds < 0) throw new IllegalArgumentException();
		this.setTimer(this.getTimer() + seconds);
		double accelerationX = getAccelerationX(); 
		if (this.isMovingLeft())
			accelerationX *= -1;
		
		updateLocation(seconds, accelerationX);
				
		updateVelocityX(seconds, accelerationX);
		
		updateVelocityAccelerationY(seconds);
	}

	
	/**
	 * 
	 * this methode update's the vertical velocity and checks the accelereation
	 *		given a time in seconds
	 * @param 	seconds
	 * 			the seconds to compute the new velocity
	 * @effect 	if the Y coordinate of this Mazub is greater than zero
	 * 				the velocityY will be set to the new vertical velocity calculated with
	 * 				the given seconds and acceleration
	 * 			| if getLocationY > 0 then
	 * 			|	setVelocityY(getVelocityY() + getAccelerationY()*seconds)
	 * @effect 	if the Y coordinate of this Mazub is equal or lower than zero
	 * 				the velocityY will be set to zero 
	 * 				and the accelerationY will be set to zero
	 * 			| if getLocationY <= 0 then
	 * 			|	setVelocityY(0)
	 * 			|	setAccelerationY(0)
	 */
	private void updateVelocityAccelerationY(double seconds) {
		if (getLocationY() <= 0) {
			setVelocityY(0);
			setAccelerationY(0);
		} else {
			double velocityY = getVelocityY() + getAccelerationY()*seconds;
			setVelocityY(velocityY);
		}
	}

	/**
	 * 
	 * @param 	seconds
	 * 			The seconds to compute the new velocity.
	 * @param 	accelerationX
	 * 			The horizontal acceleration.
	 * @pre		seconds needs to be positive
	 * 			| seconds >= 0
	 * @effect	If the calculated velocity is smaller than the maximum horizontal velocity,
	 * 				and not smaller than minus the maximum horizontal velocity, set the new velocity to the calculated velocity.
	 *				If the calculated velocity is smaller than minus the maximum horizontal velocity, set the new velocity to minus the maximum horizontal velocity.
	 *				If the calculated velocity is greater than the maximum horizontal velocity, set the new velocity to the maximum horizontal velocity.
	 * 			| if ((getVelocityX() + accelerationX*seconds) < getMaximumHorizontalVelocity()) then
	 * 			|	if ((getVelocityX() + accelerationX*seconds) < -getMaximumHorizontalVelocity()) then
	 * 			|		setVelocityX(-getMaximumHorizontalVelocity)
	 * 			|	else then
	 * 			|		setVelocityX(getVelocityX() + accelerationX*seconds)
	 * 			| else then
	 * 			|	setVelocityX(getMaximumHorizontalVelocity())
	 */
	private void updateVelocityX(double seconds, double accelerationX) {
		assert(seconds >= 0);
		double velocityX = getVelocityX() + accelerationX*seconds;
		if (velocityX < getMaximumHorizontalVelocity()){
			if (velocityX < -getMaximumHorizontalVelocity()){
				setVelocityX(-getMaximumHorizontalVelocity());
			} else {
				setVelocityX(velocityX);
			}
		} else{
			setVelocityX(getMaximumHorizontalVelocity());
		}
	}

	/**
	 * Updates the location of this Mazub given a certain amount of seconds and the horizontal acceleration.
	 * @param 	seconds
	 * 			The seconds to compute the new position.
	 * @param 	accelerationX
	 * 			The horizontal acceleration.
	 * @pre		seconds needs to be positive.
	 * 			| seconds >= 0
	 * @effect 	Calculates the new horizontal position, using the given seconds and acceleration.
	 * 			If the calculated position isn't valid, the position will be adjusted.
	 * 			new.getLocationX() ==  calculateValidLocationX(getLocationX() + (getVelocityX()*seconds + accelerationX*seconds*seconds/2)*100);
	 * @effect 	Calculates the new vertical position, using the given seconds and acceleration.
	 * 			If the calculated position isn't valid, the position will be adjusted.
	 * 			new.getLocationY() ==  calculateValidLocationY(getLocationY() + (getVelocityY()*seconds + getAccelerationY()*seconds*seconds/2)*100);
	 * 
	 */
	private void updateLocation(double seconds, double accelerationX) {
		assert (seconds >= 0);
		double locationX = getLocationX() + (getVelocityX()*seconds + accelerationX*seconds*seconds/2)*100;
		double locationY = getLocationY() + (getVelocityY()*seconds + getAccelerationY()*seconds*seconds/2)*100;
		
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
	 * @param 	locationX
	 * 			the X coordinate wich needs to be corrected
	 * 
	 * Return locationX if the given locationX is valid, 
	 * 		returns the corrected locationX is if is unvalid
	 * @return	if locationX is lower than the window width and greater or equal than zero
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
	public double calculateValidLocationX(double locationX) {
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
	public double calculateValidLocationY(double locationY) {
		if (locationY > getWindowHeight()-1){
			locationY = getWindowHeight()-1;
		} else if (locationY < 0){
			locationY = 0;
		}
		return locationY;
	}
	
	/**
	 * Returns whether Mazub is moving right or not.
	 * @return 	true when the horizontal velocity is greater than zero, false otherwise.
	 * 			| if getVelocityX() > 0 then
	 * 			|	return true
	 * 			| else then
	 * 			| 	return false
	 */
	public boolean isMovingRight() {
		if (this.getVelocityX() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns whether Mazub is moving left or not.
	 * @return 	true when the horizontal velocity is less than zero, false otherwise.
	 * 			| if getVelocityX() < 0 then
	 * 			|	return true
	 * 			| else then
	 * 			| 	return false
	 */
	public boolean isMovingLeft() {
		if (this.getVelocityX() < 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns whether Mazub is jumping.
	 * @return	true when the vertical acceleration is not zero, false otherwise.
	 * 			| if getAccelerationY() != 0 then 
	 * 			|	return true
	 * 			| else then 
	 * 			|	return false
	 */
	public boolean isJumping() {
		if (this.getAccelerationY() != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param 	spriteIndex
	 * 			The new spriteIndex for this Mazub
	 * @post	The new spriteIndex for this Mazub is equal to the given spriteIndex.
	 * 			| new.getSpriteIndex() == spriteIndex
	 */
	public void setSpriteIndex(int spriteIndex) {
		this.spriteIndex = spriteIndex;
	}
	
	/**
	 * Returns the current spriteIndex for this Mazub.
	 */
	@Basic
	public int getSpriteIndex() {
		return this.spriteIndex;
	}
	
	/**
	 * This variable contains the number of the current sprite for this Mazub.
	 */
	private int spriteIndex;
	
	
	/**
	 * 
	 * @param 	ducking
	 * 			The new ducking state for this Mazub.
	 * @post	The new ducking state of this Mazub is equal to ducking.
	 * 			| new.isDucking() == ducking
	 */
	public void setDucking(boolean ducking){
		this.ducking = ducking;				
	}
	
	/**
	 * Returns whether Mazub is ducking.
	 */
	@Basic
	public boolean isDucking() {
		return this.ducking;
	}
	
	/**
	 * This boolean indicates whether Mazub is ducking or not.
	 */
	private boolean ducking;
	
	
	private Sprite[] sprites;
	
	
	/**
	 * Returns the current value of the timer.
	 */
	@Basic
	public double getTimer() {
		return timer;
	}

	/**
	 * Sets the timer at the given value.
	 * @param 	time
	 * 		  	The new time for this timer.
	 * @post	The timer of this Mazub is equal to the given time.
	 * 			| new.getTimer() == time
	 */
	public void setTimer(double time) {
		this.timer = time;
	}
	
	/**
	 * @post	Sets the timer to zero
	 * 			| new.getTimer() == 0
	 */
	public void resetTimer() {
		this.setTimer(0);
	}

	/**
	 * This variable contains the current time for this Mazub.
	 */
	private double timer;
		
	/**
	 * This variable contains the amout of sprites available 
	 * 		for a movement to the left or right
	 */
	private int amountSpritesForMovement;

	/**
	 * Return the amount of sprites available for a movement to the left or right
	 */
	private int getAmountSpritesForMovement() {
		return amountSpritesForMovement;
	}

	/**
	 * 
	 * @param 	amountSpritesForMovement
	 * 			the new value for amountSpritesForMovement
	 * @post	amountSpritesForMovement will be set to the given amountSpritesForMovement
	 * 			| new.getAmountSpritesForMovement() = amountSpritesForMovement
	 */
	private void setAmountSpritesForMovement(int amountSpritesForMovement) {
		this.amountSpritesForMovement = amountSpritesForMovement;
	}
}
