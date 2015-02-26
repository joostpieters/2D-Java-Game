package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Basic;
import jumpingalien.util.Sprite;

public class Mazub {

	public Mazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites){
		this.setLocation(pixelLeftX, pixelBottomY);
		this.setSprites(sprites);
		this.setDefaultCurrentSprite();
		this.spriteindex = 0;
		this.time1 = System.nanoTime();
	}
	
	@Basic
	public Sprite[] getSprites() {
		return sprites;
	}
	
	public void setSprites(Sprite[] sprites) {
		this.sprites = sprites;
	}
	
	public void setLocation(int pixelLeftX, int pixelBottomY) {
		this.locationX = pixelLeftX;
		this.locationY = pixelBottomY;
	}
	
	
	@Basic
	public double getLocationX() {
		return this.locationX;
	}
	
	public void setLocationX(double locationX) {
		this.locationX = locationX;
	}
	
	@Basic
	public double getLocationY() {
		return this.locationY;
	}
	
	public void setLocationY(double locationY) {
		this.locationY = locationY;
	}
	
	@Basic
	public double getVelocityX() {
		return this.velocityX;
	}
	
	public void setVelocityX(double velocityX) {
		this.velocityX = velocityX;
	}
	
	@Basic
	public double getVelocityY() {
		return this.velocityY;
	}

	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}

	@Basic
	public double getAccelerationX() {
		return accelerationX;
	}

	public void setAccelerationX(double accelerationX) {
		this.accelerationX = accelerationX;
	}

	@Basic
	public double getAccelerationY() {
		return accelerationY;
	}

	public void setAccelerationY(double accelerationY) {
		this.accelerationY = accelerationY;
	}
	
	@Basic
	public Sprite getCurrentSprite() {
		if (System.nanoTime() - 75000000 >= this.time1) {
			// update sprite
			if (this.spriteindex == 18)
				this.spriteindex = 8;
			else
				this.spriteindex += 1;
			this.time1 = System.nanoTime();
		} else {
			// return old sprite
		}
		
		return sprites[spriteindex];
	}
	
	public void setCurrentSprite(Sprite sprite){
		this.currentSprite = sprite;
	}
	
	public void setDefaultCurrentSprite(){
		this.currentSprite = getSprites()[0];
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
			setCurrentSprite(getSprites()[2]);
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

	private double locationX;
	private double locationY;
	
	private double velocityX;
	private double velocityY;
	private double accelerationX;
	private double accelerationY;
	
	private Sprite[] sprites;
	private Sprite currentSprite;
	private int spriteindex;
	
	private long time1;
}
