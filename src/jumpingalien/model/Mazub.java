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
	
	@Basic
	public double getLocationY() {
		return this.locationY;
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

	private double locationX;
	private double locationY;
	private Sprite[] sprites;
	private double velocityX;
	private double velocityY;
}
