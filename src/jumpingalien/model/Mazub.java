package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Basic;
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
	 * Return the locationX of this character
	 */
	@Basic
	public double getLocationX() {
		return this.locationX;
	}

	
	/**
	 * Set the current X coordinate of Mazub
	 * 
	 * @param 	locationX
	 * 			The new X coordinate for the character
	 * @pre		The given LocationX needs to be bigger or equal to 0
	 * 			| locationX >= 0
	 * @pre		The given LocationX needs to smaller or equal to the game widow width
	 * 			| locationX <= getMaxWindowWidth
	 * @post 	The LocationX of this character is equal to the given LocationX
	 * 			| new.getLocationX() == locationX  
	 */
	private void setLocationX(double locationX) {
		assert ((locationX < 0) || (locationX > getMaxWindowWidth()));
		this.locationX = locationX;
	}
	
	/**
	 * This variable contains the current X coordinate of the character
	 */	
	private double locationX;
	
	/**
	 * Return the maximum width of the game window 	
	 */
	public int getMaxWindowWidth() {
		return 1024;
	}
	
	@Basic
	public double getLocationY() {
		return this.locationY;
	}
	
	public void setLocationY(double locationY) {
		this.locationY = locationY;
	}
	
	private double locationY;
	
	public void setLocation(int pixelLeftX, int pixelBottomY) {
		setLocationX(pixelLeftX);
		setLocationY(pixelBottomY);
	}
	
	@Basic
	public double getVelocityX() {
		return this.velocityX;
	}
	
	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}
	
	private double velocityX;
	
	@Basic
	public double getVelocityY() {
		return this.velocityY;
	}

	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}
	
	private double velocityY;

	@Basic
	public double getAccelerationX() {
		return accelerationX;
	}

	public void setAccelerationX(double accelerationX) {
		this.accelerationX = accelerationX;
	}
	
	private double accelerationX;

	@Basic
	public double getAccelerationY() {
		return accelerationY;
	}

	public void setAccelerationY(double accelerationY) {
		this.accelerationY = accelerationY;
	}
	
	private double accelerationY;
	
	@Basic
	public Sprite[] getSprites() {
		return sprites;
	}
	
	public void setSprites(Sprite[] sprites) {
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
			setAccelerationX(-0.9);
		} else if (direction == 'u'){
			setVelocityY(8);
			setAccelerationY(-10);
		}
	}
	
	public void stopMoveX(){
		setVelocityX(0);
		setAccelerationX(0);
	}
	
	public static double getMaximumHorizontalVelocity() {
		return 3;
	}
	
	
	public void advanceTime(double seconds) {
		this.timer += seconds;
		double locationX = getLocationX() + (getVelocityX()*seconds + getAccelerationX()*seconds*seconds/2)*100;
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
		
		double velocityX = getVelocityX() + getAccelerationX()*seconds;
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
