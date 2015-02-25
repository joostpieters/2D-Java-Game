package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Basic;
import jumpingalien.util.Sprite;

public class Mazub {

	public Mazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites){
		this.setLocation(pixelLeftX, pixelBottomY);
		this.setSprites(sprites);	
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
		return this.currentSprite;
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
		double locationX = getLocationX() + getVelocityX()*seconds + getAccelerationX()*seconds*seconds/2;
		double locationY = getLocationY() + getVelocityY()*seconds + getAccelerationY()*seconds*seconds/2;
		if (locationX > 1024){
			locationX = 1024;
		}
		if (locationX < 0){
			locationX = 0;
		}
		if (locationY > 768){
			locationY = 768;
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
		if (getLocationY() == 0)
			setVelocityY(0);
		setVelocityY(velocityY);
	}

	private double locationX;
	private double locationY;
	private Sprite[] sprites;
	private double velocityX;
	private double velocityY;
	private double accelerationX;
	private double accelerationY;
	
	private Sprite currentSprite;
}
