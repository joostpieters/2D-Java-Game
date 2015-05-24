package jumpingalien.model;

import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.*;
import jumpingalien.part2.facade.Facade;
import jumpingalien.part2.facade.IFacadePart2;
import jumpingalien.util.Sprite;

import org.junit.Test;

public class ModelTest {

	public static final int FEATURE_AIR = 0;
	public static final int FEATURE_SOLID = 1;
	public static final int FEATURE_WATER = 2;
	public static final int FEATURE_MAGMA = 3;
	
	private Sprite[] creatSpriteArrayOfLength(int size){
		assert(size > 0);
		Sprite[] sprite = new Sprite[size];
		Sprite example = spriteArrayForSize(2, 2)[0];
		for(int i = 0; i < size; i++){
			sprite[i] = example;
		}
		return sprite;
	}

	@Test (expected = IllegalArgumentException.class)
	public void testContructorIllegalPositionX() {
		new Mazub(-1, 0, spriteArrayForSize(2, 2));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testContructorIllegalPositionY() {
		new Mazub(0, -1, spriteArrayForSize(2, 2));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testContructorIllegalPositionXAndY() {
		new Mazub(-1, -1, spriteArrayForSize(2, 2));
	}
	
	@Test
	public void testContructorMazubOk() {
		new Mazub(0, 0, spriteArrayForSize(2, 2));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testContructorMazubSpriteArrayUneven() {
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
	public void testContructorMazubSpriteArrayNullPointer() {
		Sprite[] sprites = new Sprite[31];
		int i = 0;
		for(Sprite sprite : spriteArrayForSize(2, 2)){
			sprites[i] = sprite;
			i++;
		}
		new Mazub(0, 0, sprites);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testContructorMazubNotEnoughSprites() {
		new Mazub(0, 0, creatSpriteArrayOfLength(9));
	}
	
	@Test
	public void testContructorSharkOk() {
		new Shark(0, 0, creatSpriteArrayOfLength(2));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testContructorSharkSpriteArrayNullPointer() {
		Sprite[] sprites = new Sprite[2];
		int i = 0;
		for(Sprite sprite : creatSpriteArrayOfLength(1)){
			sprites[i] = sprite;
			i++;
		}
		new Shark(0, 0, sprites);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testContructorSharkNotEnoughSprites() {
		new Shark(0, 0, creatSpriteArrayOfLength(1));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testContructorSharkToMuchSprites() {
		new Shark(0, 0, creatSpriteArrayOfLength(3));
	}
	
	@Test
	public void testGameObjectIllegalHitPoints() {
		GameObject mazub = new Mazub(0, 0, spriteArrayForSize(2, 2));
		mazub.setHitPoints(-1);
		assertEquals(0, mazub.getHitPoints());
	}
	
	@Test
	public void testGameObjectIllegalHitPointsMazub() {
		GameObject mazub = new Mazub(0, 0, spriteArrayForSize(2, 2));
		mazub.setHitPoints(1000);
		assertEquals(500, mazub.getHitPoints());
	}
	
	@Test
	public void testGameObjectLocationX() {
		GameObject mazub = new Mazub(56, 0, spriteArrayForSize(2, 2));
		assertEquals(true, mazub.getLocationX() == 56);
	}
	
	@Test
	public void testGameObjectLocationY() {
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
	public void testGameObjectMovementTest() {
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
	public void testMazubEndJump() {
		Mazub mazub = creatAlien();
		mazub.endJump();		
	}
	
	@Test
	public void testMazubTerminateBeceausHitPoints() {
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
	public void testMazubTerminateBeceausHitPointsNegative() {
		Mazub mazub = creatAlien();
		mazub.setHitPoints(-1);
		mazub.advanceTime(0.1);
		mazub.advanceTime(0.1);
		mazub.advanceTime(0.1);
		mazub.advanceTime(0.1);
		mazub.advanceTime(0.1);
		mazub.advanceTime(0.11);
		assertEquals(true, mazub.isTerminated());
	}
	
	@Test
	public void testMazubTerminateBeceausLocation() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 1, 2, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		Mazub mazub =  facade.createMazub(0, 4990, spriteArrayForSize(3, 3));
		world.setMazub(mazub);
		mazub.advanceTime(0.01);
		mazub.isTerminated();
	}
	
	@Test
	public void testGameObjectAccelerationX() {
		Mazub mazub =  creatAlien();
		mazub.setAccelerationX(Double.NaN);
		assertEquals(true, mazub.getAccelerationX() == 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testWorldAdvanceTimeIllegalArgument1(){
		World world = new World(500, 1, 2, 1, 1, 1, 1);
		world.advanceTime(-1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testWorldAdvanceTimeIllegalArgument2(){
		World world = new World(500, 1, 2, 1, 1, 1, 1);
		world.advanceTime(0.2);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testWorldAdvanceTimeIllegalArgument3(){
		World world = new World(500, 1, 2, 1, 1, 1, 1);
		world.advanceTime(Double.NaN);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testWorldAddInvalidMazub1() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 1, 2, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		Mazub mazub =  facade.createMazub(0, 4990, spriteArrayForSize(3, 3));
		world.setMazub(mazub);
		mazub.advanceTime(0.01);
		mazub.isTerminated();
		world.setMazub(mazub);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testWorldAddInvalidMazub2() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 1, 2, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		Mazub mazub =  facade.createMazub(0, 4990, spriteArrayForSize(3, 3));
		world.setMazub(mazub);
		mazub.advanceTime(0.01);
		mazub.isTerminated();
		world.setMazub(mazub);
	}
	
	@Test(expected = IllegalStateException.class)
	public void testEndJumping() {
		creatAlien().endJump();
	}
	
	@Test(expected = IllegalStateException.class)
	public void testEndDucking() {
		creatAlien().endDucking();
	}
	
	@Test
	public void testIsAbove() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 1, 2, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		Mazub mazub =  facade.createMazub(0, 0, spriteArrayForSize(3, 3));
		Shark shark = facade.createShark(0, 300, creatSpriteArrayOfLength(2));
		world.setMazub(mazub);
		world.addShark(shark);
		assertEquals(true, shark.isAbove(mazub));
		assertEquals(false, shark.isUnder(mazub));
		assertEquals(false, mazub.isAbove(shark));
		assertEquals(true, mazub.isUnder(shark));
	}
	
	@Test
	public void testIsUnder() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 1, 2, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		Mazub mazub =  facade.createMazub(0, 300, spriteArrayForSize(3, 3));
		Shark shark = facade.createShark(0, 0, creatSpriteArrayOfLength(2));
		world.setMazub(mazub);
		world.addShark(shark);
		assertEquals(false, shark.isAbove(mazub));
		assertEquals(true, shark.isUnder(mazub));
		assertEquals(true, mazub.isAbove(shark));
		assertEquals(false, mazub.isUnder(shark));
	}
	
	@Test
	public void testIsLeft() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 1, 2, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		Mazub mazub =  facade.createMazub(300, 0, spriteArrayForSize(3, 3));
		Shark shark = facade.createShark(0, 0, creatSpriteArrayOfLength(2));
		world.setMazub(mazub);
		world.addShark(shark);
		assertEquals(true, shark.isLeftOff(mazub));
		assertEquals(false, shark.isRightOff(mazub));
		assertEquals(false, mazub.isLeftOff(shark));
		assertEquals(true, mazub.isRightOff(shark));
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void testslimeInvalidSchool1() {
		new Slime(0, 0, creatSpriteArrayOfLength(2), null);
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void testslimeInvalidSchool2() {
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 1, 2, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		Mazub mazub =  facade.createMazub(300, 0, spriteArrayForSize(3, 3));
		world.setMazub(mazub);
		School school = new School();
		Slime slime1 = new Slime(0, 0, creatSpriteArrayOfLength(2), school);
		world.addSlime(slime1);
		facade.startGame(world);
		slime1.setHitPoints(0);
		world.advanceTime(0.19);
		world.advanceTime(0.19);
		world.advanceTime(0.19);
		world.advanceTime(0.05);
		new Slime(0, 0, creatSpriteArrayOfLength(2), school);
	}
	

}
