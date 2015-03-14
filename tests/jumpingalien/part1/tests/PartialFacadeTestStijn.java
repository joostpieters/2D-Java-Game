package jumpingalien.part1.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import jumpingalien.part1.facade.Facade;
import jumpingalien.part1.facade.IFacade;
import jumpingalien.model.Mazub;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static jumpingalien.tests.util.TestUtils.*;

public class PartialFacadeTestStijn {

	@Test
	public void startMoveRightCorrect() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		facade.startMoveRight(alien);
		facade.advanceTime(alien, 0.1);

		// x_new [m] = 0 + 1 [m/s] * 0.1 [s] + 1/2 0.9 [m/s^2] * (0.1 [s])^2 =
		// 0.1045 [m] = 10.45 [cm], which falls into pixel (10, 0)

		assertArrayEquals(intArray(10, 0), facade.getLocation(alien));
	}

	@Test
	public void startMoveRightMaxSpeedAtRightTime() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		facade.startMoveRight(alien);
		// maximum speed reached after 20/9 seconds
		for (int i = 0; i < 100; i++) {
			facade.advanceTime(alien, 0.2 / 9);
		}

		assertArrayEquals(doubleArray(3, 0), facade.getVelocity(alien),
				Util.DEFAULT_EPSILON);
	}

	@Test
	public void testAccellerationZeroWhenNotMoving() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		assertArrayEquals(doubleArray(0.0, 0.0), facade.getAcceleration(alien),
				Util.DEFAULT_EPSILON);
	}

	@Test
	public void testWalkAnimationLastFrame() {
		IFacade facade = new Facade();

		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(0, 0, sprites);

		facade.startMoveRight(alien);
		facade.advanceTime(alien, 0.005);
		for (int i = 0; i < m; i++) {
			facade.advanceTime(alien, 0.075);
		}
		assertEquals(sprites[8+m], facade.getCurrentSprite(alien));
	}
	
	@Test (expected = ModelException.class)
	public void testNegativeTimeAmount() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		
		facade.advanceTime(alien, -0.1);			
		
	}
	
	@Test(expected = ModelException.class)
	public void testToBigTimeAmount() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
			
		facade.advanceTime(alien, 0.3);			
		
	}
	
	@Test 
	public void testIllegalPosition() {
		IFacade facade = new Facade();
		Mazub alien1 = facade.createMazub(1920, 1080, spriteArrayForSize(2,2));
		
		assertEquals(alien1.getLocationX(), Mazub.getWindowWidth()-1, Util.DEFAULT_EPSILON);
		assertEquals(alien1.getLocationY(), Mazub.getWindowHeight()-1, Util.DEFAULT_EPSILON);
		
		Mazub alien2 = facade.createMazub(-51, -212, spriteArrayForSize(2,2));
		
		assertEquals(alien2.getLocationX(), 0, Util.DEFAULT_EPSILON);
		assertEquals(alien2.getLocationY(), 0, Util.DEFAULT_EPSILON);
	}

	
	@Test
	public void testDuckingSpeed() {
		IFacade facade = new Facade();
		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2,2));
		
		facade.startMoveRight(alien);
		for (int i = 0; i < 100; i++) {
			facade.advanceTime(alien, 0.2 / 9);
		}
		
		facade.startDuck(alien);
		facade.advanceTime(alien, 0.005);
		
		assertEquals(1, alien.getVelocityX(), Util.DEFAULT_EPSILON);
	}
	
	@Test
	public void testWalkAnimationLastFrameLeft() {
		IFacade facade = new Facade();

		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(0, 0, sprites);

		facade.startMoveLeft(alien);
		facade.advanceTime(alien, 0.005);
		for (int i = 0; i < m; i++) {
			facade.advanceTime(alien, 0.075);
		}
		assertEquals(sprites[9+ m*2], facade.getCurrentSprite(alien));
	}
	
	@Test
	public void testDuckAnimation() {
		IFacade facade = new Facade();

		int m = 10;
		Sprite[] sprites = spriteArrayForSize(2, 2, 10 + 2 * m);
		Mazub alien = facade.createMazub(0, 0, sprites);
		
		facade.startDuck(alien);
		facade.advanceTime(alien, 0.005);
		
		assertEquals(sprites[1], facade.getCurrentSprite(alien));
	}
	
	public void testStopMoveX() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		facade.startMoveRight(alien);
		facade.advanceTime(alien, 0.1);
		
		facade.endMoveRight(alien);
		
		assertEquals(0, alien.getVelocityX(), Util.DEFAULT_EPSILON);
	}
	
	public void testStopJump() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		facade.startJump(alien);
		facade.advanceTime(alien, 0.1);
		
		facade.endJump(alien);
		
		assertEquals(0, alien.getVelocityY(), Util.DEFAULT_EPSILON);
	}
	
	public void testStartJump() {
		IFacade facade = new Facade();

		Mazub alien = facade.createMazub(0, 0, spriteArrayForSize(2, 2));
		facade.startJump(alien);
		
		assertEquals(-10, alien.getAccelerationY(), Util.DEFAULT_EPSILON);
	}
}