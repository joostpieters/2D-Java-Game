package jumpingalien.part1.facade;

import jumpingalien.model.Motion;
import jumpingalien.model.Mazub;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;

public class Facade implements IFacade {

	@Override
	public Mazub createMazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites) throws ModelException {
		try{
			Mazub alien = new Mazub(pixelLeftX, pixelBottomY, sprites);
			return alien;
		} catch (IllegalArgumentException e){
			throw new ModelException("Illegal Sprite argment");
		}
	}

	@Override
	public int[] getLocation(Mazub alien) {
		return alien.getLocation();
	}

	@Override
	public double[] getVelocity(Mazub alien) {
		double[] velocity = new double[2];
		velocity[0] = alien.getVelocityX();
		velocity[1] = alien.getVelocityY();
		return velocity;
		
	}

	@Override
	public double[] getAcceleration(Mazub alien) {
		double[] acceleration = new double[2];
		acceleration[0] = alien.getAccelerationX();
		acceleration[1] = alien.getAccelerationY();
		return acceleration;
	}
	
	@Override
	public int[] getSize(Mazub alien) throws ModelException {
		try{
			return alien.getSize();
		} catch (ModelException e){
			throw new ModelException("Unvalid current sprite");
		}
	}

	@Override
	public Sprite getCurrentSprite(Mazub alien) {
		return alien.getCurrentSprite();
	}

	
	
	@Override
	public void startJump(Mazub alien) {
		alien.startJump();
	}

	@Override
	public void endJump(Mazub alien) {
		alien.endJump();
		
	}

	@Override
	public void startMoveLeft(Mazub alien) {
		alien.startMove(Motion.LEFT);
	}

	@Override
	public void endMoveLeft(Mazub alien) {
		alien.endMove(Motion.LEFT);
	}

	@Override
	public void startMoveRight(Mazub alien) {
		alien.startMove(Motion.RIGHT);
	}

	@Override
	public void endMoveRight(Mazub alien) {
		alien.endMove(Motion.RIGHT);
		
	}

	@Override
	public void startDuck(Mazub alien) {
		try{
			alien.startDucking();
		} catch (IllegalStateException e){
			throw new ModelException("Mazub is already ducking");
		}
	}

	@Override
	public void endDuck(Mazub alien) {
		try{
			alien.endDucking();
		} catch (IllegalStateException e){
			throw new ModelException("Mazub is not ducking");
		}		
	}

	@Override
	public void advanceTime(Mazub alien, double dt) {
		try {
			alien.advanceTime(dt);
		} catch (IllegalArgumentException e) {
			throw new ModelException("The elapsed time can never be negative.");
		}
		
	}

}
