package jumpingalien.model;

import jumpingalien.util.Sprite;

public class Mazub {

	public Mazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites){
		this.location[0] = pixelLeftX;
		this.location[1] = pixelBottomY;
		this.setSprites(sprites);	
	}
	
	public Sprite[] getSprites() {
		return sprites;
	}
	public void setSprites(Sprite[] sprites) {
		this.sprites = sprites;
	}
	
	public void setLocation(int pixelLeftX, int pixelBottomY) {
		this.location[0] = pixelLeftX;
		this.location[1] = pixelBottomY;
	}
	
	public int[] getLocation() {
		return this.location;
	}

	// location[0] is x location, location[1] is y location
	private int[] location = new int[2];
	private Sprite[] sprites;
}
