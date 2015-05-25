package jumpingalien.model;

import jumpingalien.util.Sprite;

/**
 * 
 * @author Pieter-Jan Coenen (1ste Bacherlor Informatica) en Stijn Caerts (1ste Bacherlor Informatica)
 *
 * @invar	the Buzam's location is always in the game window
 * 			|  isValidLocationInWorld(getLocationX(), getLocationY())
 * 
 * @invar	the Buzam's hitpoints are always between 0 and 500
 * 			| (0 <= getHitPoints()) && (getHitPoints() <= 500)
 * 
 * @invar	the Buzam has always a world
 * 			| getWorld() != null
 * 
 * @invar	the Buzam has always a sprite array containing minimum 10 sprites
 * 				and no null pointers
 * 			| isValidSpriteArray(getSprites())
 * 
 * @invar	the Buzam's velocity is always equal to or lower than the maximum velocity of this Buzam
 * 			| getVelocityX() <= getMaxHorizontalVelocity()
 */
public class Buzam extends Mazub {
	
	/**
	 * 
	 * @param pixelLeftX
	 * @param pixelBottomY
	 * @param sprites
	 * @throws IllegalArgumentException
	 * @effect	...
	 * 			| super(pixelLeftX, pixelBottomY, sprites)
	 */
	public Buzam (int pixelLeftX, int pixelBottomY, Sprite[] sprites) throws IllegalArgumentException{
		super(pixelLeftX, pixelBottomY, sprites);
	}
	
	/**
	 * 
	 * @param pixelLeftX
	 * @param pixelBottomY
	 * @param sprites
	 * @param program
	 * @throws IllegalArgumentException
	 * @effect	...
	 * 			| super(pixelLeftX, pixelBottomY, sprites, program)
	 */
	public Buzam (int pixelLeftX, int pixelBottomY, Sprite[] sprites, Program program) throws IllegalArgumentException{
		super(pixelLeftX, pixelBottomY, sprites, program);
	}
	
	/**
	 * Checks wether the given world is valid or not
	 * @param 	world
	 * 			the world which needs to be checked
	 * @return	true if the given World is not null and if the Buzam of that World is this Buzam
	 * 			| result == ((world != null) && (world.getBuzam()==this));
	 */
	@Override
	protected boolean isValidWorld(World world){
		return (world != null) && (world.getBuzam()==this);
	}
	
	/**
	 * @return 	...
	 * 			| result == 500
	 */
	@Override
	protected int startHitpoints() {
		return 500;
	}

	/**
	 * @param	dt
	 * @effect	...
	 * 			| if (hasAProgram() && !isDead()) then
	 * 			|	getProgram.run((int)Math.ceil(dt/0.001))
	 * @effect	...
	 * 			| super.advanceTime(dt)
	 */
	@Override
	public void advanceTime(double dt){
		if(hasAProgram() && !isDead()){
			getProgram().run((int)Math.ceil(dt/0.001));
		}
		super.advanceTime(dt);
	}
	
	/**
	 * @effect	removes this Buzam from his World
	 * 			| getWorld().removeBuzam()
	 * @effect 	removes the world for this Buzam
	 * 			|removeWorld()
	 * @effect 	sets this Buzam terminated
	 * 			|setTerminated(true)
	 */
	@Override
	void terminate(){
		getWorld().removeBuzam();
		this.removeWorld();
		this.setTerminated(true);
	}
	
}
