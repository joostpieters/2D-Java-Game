package jumpingalien.part1.facade;

import jumpingalien.model.Mazub;
import jumpingalien.util.Sprite;

public class Facade implements IFacade {

	@Override
	public Mazub createMazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
		Mazub alien = new Mazub(pixelLeftX, pixelBottomY, sprites);
		return alien;
	}

	@Override
	public int[] getLocation(Mazub alien) {
		int location[] = new int[2];
		location[0] = (int) alien.getLocationX();
		location[1] = (int) alien.getLocationY();
		return location;
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
	public int[] getSize(Mazub alien) {
		int[] size = new int[2];
		Sprite sprite = alien.getCurrentSprite();
		size[0] = sprite.getHeight();
		size[1] = sprite.getWidth();
		return size;
	}

	@Override
	public Sprite getCurrentSprite(Mazub alien) {
		// TODO Auto-generated method stub
		return alien.getCurrentSprite();
	}

	
	
	@Override
	public void startJump(Mazub alien) {
		alien.startMove('u');
		
	}

	@Override
	public void endJump(Mazub alien) {
		alien.endJump();
		
	}

	@Override
	public void startMoveLeft(Mazub alien) {
		alien.startMove('l');
	}

	@Override
	public void endMoveLeft(Mazub alien) {
		alien.stopMoveX();
	}

	@Override
	public void startMoveRight(Mazub alien) {
		alien.startMove('r');
	}

	@Override
	public void endMoveRight(Mazub alien) {
		alien.stopMoveX();
		
	}

	@Override
	public void startDuck(Mazub alien) {
		alien.setDucking(true);
	}

	@Override
	public void endDuck(Mazub alien) {
		alien.setDucking(false);		
	}

	@Override
	public void advanceTime(Mazub alien, double dt) {
		alien.advanceTime(dt);
		
	}

}
