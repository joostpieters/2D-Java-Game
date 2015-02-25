package jumpingalien.part1.facade;

import jumpingalien.model.Mazub;
import jumpingalien.util.Sprite;

public class Facade implements IFacade {

	@Override
	public Mazub createMazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
		Mazub alien = new Mazub(pixelLeftX, pixelBottomY, Sprite[], sprites);
		return alien;
	}

	@Override
	public int[] getLocation(Mazub alien) {
		return alien.getLocation();
	}

	@Override
	public double[] getVelocity(Mazub alien) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] getAcceleration(Mazub alien) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getSize(Mazub alien) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sprite getCurrentSprite(Mazub alien) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void startJump(Mazub alien) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endJump(Mazub alien) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startMoveLeft(Mazub alien) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endMoveLeft(Mazub alien) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startMoveRight(Mazub alien) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endMoveRight(Mazub alien) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startDuck(Mazub alien) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endDuck(Mazub alien) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void advanceTime(Mazub alien, double dt) {
		// TODO Auto-generated method stub
		
	}

}
