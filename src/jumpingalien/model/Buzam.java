package jumpingalien.model;

import jumpingalien.util.Sprite;

public class Buzam extends Mazub {
	public Buzam (int pixelLeftX, int pixelBottomY, Sprite[] sprites) throws IllegalArgumentException{
		super(pixelLeftX, pixelBottomY, sprites);
	}
	
	@Override
	protected int startHitpoints() {
		return 500;
	}
	
}
