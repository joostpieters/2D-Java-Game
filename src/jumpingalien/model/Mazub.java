package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Basic;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;

public class Mazub {

	public Mazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites){
		this.setLocation(pixelLeftX, pixelBottomY);
		this.setSprites(sprites);
		this.setSpriteIndex(0);
		this.timer = 0;
		this.spritesForMovement = (sprites.length - 8) / 2 - 1;
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
	public void setLocationX(double locationX) throws ModelException {
		if ((locationX < 0) || (locationX > getWindowWidth())){
			throw new ModelException("X coordinate is out of window range");
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
	public void setLocationY(double locationY) throws ModelException {
		if((locationY < 0)||(locationY > getWindowHeight())){
			throw new ModelException("Y coordinate is out of window range");
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
	 */
	public double getMaximumHorizontalVelocity() {
		return 3;
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
	 * 
	 * ??? hoe uitwerken ???
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
	
	@Basic
	public Sprite getCurrentSprite() {
					// update sprite
		if (this.isJumping()) {
			if (this.isMovingRight()){
				setSpriteIndex(4);
			} else if (this.isMovingLeft()){
				setSpriteIndex(5);
			} else{
				setSpriteIndex(0);
			}
		} else if (this.isDucking()) {
			if (this.isMovingRight()){
				setSpriteIndex(6);
				this.timer = 0;
			} else if (this.isMovingLeft()){
				setSpriteIndex(7);
				this.timer = 0;
			} else{
				if (this.timer < 1) {
					if (getSpriteIndex() == 6) {
						setSpriteIndex(6);
					} else if (getSpriteIndex() == 7) {
						setSpriteIndex(7);
					}
				} else {
					setSpriteIndex(1);
				}
			}		
		} else if (this.isMovingRight()){
				spritesMovingRightNormal();
		}else if (this.isMovingLeft()){
				spritesMovingLeftNormal();
		}else{
			if (this.timer < 1) {
				if (this.wasMovingRight()) {
					setSpriteIndex(2);
				} else if (this.wasMovingLeft()) {
					setSpriteIndex(3);
				}
			} else {
				setSpriteIndex(0);
			}
		}
		
		
		// otherwise keep old sprite
		
		return sprites[this.getSpriteIndex()];
	}
	
	private void spritesMovingRightNormal() {
		if (this.timer >= 0.075){
			if ((this.getSpriteIndex() >= 8 + this.spritesForMovement) || (this.getSpriteIndex() < 8))
				this.setSpriteIndex(8);
			else
				this.setSpriteIndex(this.getSpriteIndex() + 1);
			this.timer = 0;
		}
	}
	
	private void spritesMovingLeftNormal() {
		if (this.timer >= 0.075){
			if ((this.getSpriteIndex() >= 9 + this.spritesForMovement * 2) || this.getSpriteIndex() < 9 + this.spritesForMovement)
				this.setSpriteIndex(9 + this.spritesForMovement);
			else
				this.setSpriteIndex(this.getSpriteIndex() + 1);
			this.timer = 0;
		}
	}
	
	private boolean wasMovingRight() {
		if ((this.getSpriteIndex() >= 8 && this.getSpriteIndex() <= 8 + this.spritesForMovement))
			return true;
		return false;
	}
	
	private boolean wasMovingLeft() {
		if ((this.getSpriteIndex() >= 9 && this.getSpriteIndex() <= 9 + this.spritesForMovement*2))
			return true;
		return false;
	}
	
	
	/**
	 * @pre	direction == 'r' || direction == 'l' || direction =='u'
	 * @param direction
	 */
	public void startMove(char direction) {
		assert(direction == 'r' || direction == 'l' || direction =='u');
		if (direction == 'r') {
			setVelocityX(1);
			setAccelerationX(0.9);
		} else if (direction == 'l'){
			setVelocityX(-1);
			setAccelerationX(0.9);
		} else if (direction == 'u'){
			setVelocityY(8);
			setAccelerationY(-10);
		}
	}
	
	public void stopMoveX(){
		setVelocityX(0);
		setAccelerationX(0);
	}
	
	
	public void advanceTime(double seconds) {
		this.timer += seconds;
		double accelerationX = getAccelerationX(); 
		if (this.isMovingLeft())
			accelerationX *= -1;
		
		double locationX = getLocationX() + (getVelocityX()*seconds + accelerationX*seconds*seconds/2)*100;
		double locationY = getLocationY() + (getVelocityY()*seconds + getAccelerationY()*seconds*seconds/2)*100;
		if (locationX > 1023){
			locationX = 1023;
		}
		if (locationX < 0){
			locationX = 0;
		}
		if (locationY > 767){
			locationY = 767;
		}
		if (locationY < 0){
			locationY = 0;
		}
		setLocationX(locationX);
		setLocationY(locationY);
		
		double velocityX = getVelocityX() + accelerationX*seconds;
		double velocityY = getVelocityY() + getAccelerationY()*seconds;
		
		if (velocityX < getMaximumHorizontalVelocity()){
			if (velocityX < -getMaximumHorizontalVelocity()){
				setVelocityX(-getMaximumHorizontalVelocity());
			} else {
			setVelocityX(velocityX);
			}
		}
		else{
			setVelocityX(getMaximumHorizontalVelocity());
		}
		if (getLocationY() == 0) {
			setVelocityY(0);
			setAccelerationY(0);
		} else {
			setVelocityY(velocityY);
		}
	}
	
	public boolean isMovingRight() {
		if (this.getVelocityX() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
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
	
	public void setSpriteIndex(int spriteIndex) {
		this.spriteIndex = spriteIndex;
	}
	
	/**
	 * Returns the current sprite index.
	 */
	@Basic
	public int getSpriteIndex() {
		return this.spriteIndex;
	}
	
	
	public void setDucking(boolean ducking){
		this.ducking = ducking;
		setTimer(1);;				
	}
	
	/**
	 * Returns whether Mazub is ducking.
	 */
	@Basic
	public boolean isDucking() {
		return this.ducking;
	}
	
	
	
	private Sprite[] sprites;
	
	private int spriteIndex;
	
	/**
	 * Returns the current value of the timer.
	 */
	@Basic
	public double getTimer() {
		return timer;
	}

	public void setTimer(double time) {
		this.timer = time;
	}

	private double timer;
		
	private int spritesForMovement;
	
	private boolean ducking;
}
