package jumpingalien.model;

import jumpingalien.util.Sprite;

public class Buzam extends Mazub {
	public Buzam (int pixelLeftX, int pixelBottomY, Sprite[] sprites) throws IllegalArgumentException{
		super(pixelLeftX, pixelBottomY, sprites);
	}
	
	public Buzam (int pixelLeftX, int pixelBottomY, Sprite[] sprites, Program program) throws IllegalArgumentException{
		super(pixelLeftX, pixelBottomY, sprites, program);
	}
	
	/**
	 * Checks wether the given world is valid or not
	 * @param 	world
	 * 			the world which needs to be checked
	 * @return	true if the given mazub is no null pointer and if the mazub of that world is this mazub
	 * 			| result == ((world != null) && (world.getBuzam()==this));
	 */
	@Override
	protected boolean isValidWorld(World world){
		return (world != null) && (world.getBuzam()==this);
	}
	
	@Override
	protected int startHitpoints() {
		return 500;
	}
	
	@Override
	public void advanceTime(double dt){
		if(hasAProgram()){
			getProgram().run((int)Math.ceil(dt/0.001));
		}
		super.advanceTime(dt);
	}
	
}
