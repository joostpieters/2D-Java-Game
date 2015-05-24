package jumpingalien.model;

import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.*;
import jumpingalien.part2.facade.Facade;
import jumpingalien.part2.facade.IFacadePart2;
import jumpingalien.util.Sprite;

import org.junit.Test;

public class GameObjectTests {

	public static final int FEATURE_AIR = 0;
	public static final int FEATURE_SOLID = 1;
	public static final int FEATURE_WATER = 2;
	public static final int FEATURE_MAGMA = 3;

	@Test (expected = IllegalArgumentException.class)
	public void contructorTestIllegalPositionX() {
		new Mazub(-1, 0, spriteArrayForSize(2, 2));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void contructorTestIllegalPositionY() {
		new Mazub(0, -1, spriteArrayForSize(2, 2));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void contructorTestIllegalPositionXAndY() {
		new Mazub(-1, -1, spriteArrayForSize(2, 2));
	}
	
	@Test
	public void contructorTestMazubOk() {
		new Mazub(0, 0, spriteArrayForSize(2, 2));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void contructorTestMazubSpriteArrayUneven() {
		Sprite[] sprites = new Sprite[31];
		int i = 0;
		for(Sprite sprite : spriteArrayForSize(2, 2)){
			sprites[i] = sprite;
			i++;
		}
		sprites[30] = sprites[29];
		new Mazub(0, 0, sprites);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void contructorTestMazubSpriteArrayNullPointer() {
		Sprite[] sprites = new Sprite[31];
		int i = 0;
		for(Sprite sprite : spriteArrayForSize(2, 2)){
			sprites[i] = sprite;
			i++;
		}
		new Mazub(0, 0, sprites);
	}
	
	@Test
	public void gameObjectIllegalHitPoints() {
		GameObject mazub = new Mazub(0, 0, spriteArrayForSize(2, 2));
		mazub.setHitPoints(-1);
		assertEquals(0, mazub.getHitPoints());
	}
	
	@Test
	public void gameObjectIllegalHitPointsMazub() {
		GameObject mazub = new Mazub(0, 0, spriteArrayForSize(2, 2));
		mazub.setHitPoints(1000);
		assertEquals(500, mazub.getHitPoints());
	}
	
	@Test
	public void gameObjectLocationX() {
		GameObject mazub = new Mazub(56, 0, spriteArrayForSize(2, 2));
		assertEquals(true, mazub.getLocationX() == 56);
	}
	
	@Test
	public void gameObjectLocationY() {
		GameObject mazub = new Mazub(0, 56, spriteArrayForSize(2, 2));
		assertEquals(true, mazub.getLocationY() == 56);
	}
	
	private Mazub creatAlien(){
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 1, 2, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		Mazub mazub =  facade.createMazub(0, 499, spriteArrayForSize(3, 3));
		world.setMazub(mazub);
		return mazub;
	}
	
	@Test
	public void gameObjectMovementTest() {
		Mazub mazub = creatAlien();
		mazub.startMove(Motion.LEFT);
		mazub.advanceTime(0.01);
		assertEquals(true, mazub.getVelocityX() < 0);
		mazub.startMove(Motion.RIGHT);
		mazub.advanceTime(0.01);
		assertEquals(true, mazub.getVelocityX() > 0);
		mazub.endMove(Motion.RIGHT);
		mazub.advanceTime(0.01);
		assertEquals(true, mazub.getVelocityX() < 0);
		mazub.endMove(Motion.LEFT);
		mazub.advanceTime(0.01);
		assertEquals(true, mazub.getVelocityX() == 0);		
	}
	
	@Test (expected = IllegalStateException.class)
	public void mazubEndDucking() {
		Mazub mazub = creatAlien();
		mazub.endDucking();		
	}
	
	@Test (expected = IllegalStateException.class)
	public void mazubEndJump() {
		Mazub mazub = creatAlien();
		mazub.endJump();		
	}
	
	@Test
	public void mazubTerminateBeceausHitPoints() {
		Mazub mazub = creatAlien();
		mazub.setHitPoints(0);
		mazub.advanceTime(0.1);
		mazub.advanceTime(0.1);
		mazub.advanceTime(0.1);
		mazub.advanceTime(0.1);
		mazub.advanceTime(0.1);
		mazub.advanceTime(0.11);
		assertEquals(true, mazub.isTerminated());
	}
	
	@Test
	public void mazubTerminateBeceausLocation() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 1, 2, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		Mazub mazub =  facade.createMazub(0, 4990, spriteArrayForSize(3, 3));
		world.setMazub(mazub);
		mazub.advanceTime(0.01);
		mazub.isTerminated();
	}
	
	@Test
	public void gameObjectAccelerationX() {
		Mazub mazub =  creatAlien();
		mazub.setAccelerationX(Double.NaN);
		assertEquals(true, mazub.getAccelerationX() == 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void worldAdvanceTimeIllegalArgument1(){
		World world = new World(500, 1, 2, 1, 1, 1, 1);
		world.advanceTime(-1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void worldAdvanceTimeIllegalArgument2(){
		World world = new World(500, 1, 2, 1, 1, 1, 1);
		world.advanceTime(0.2);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void worldAdvanceTimeIllegalArgument3(){
		World world = new World(500, 1, 2, 1, 1, 1, 1);
		world.advanceTime(Double.NaN);
	}

}
