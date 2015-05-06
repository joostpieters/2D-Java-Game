package jumpingalien.model;

import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import jumpingalien.model.Mazub;
import jumpingalien.model.World;
import jumpingalien.part2.facade.Facade;
import jumpingalien.part2.facade.IFacadePart2;
import org.junit.Test;

public class Tests {
	
	public static final int FEATURE_AIR = 0;
	public static final int FEATURE_SOLID = 1;
	public static final int FEATURE_WATER = 2;
	public static final int FEATURE_MAGMA = 3;
	
	private Mazub creatAlien(){
		IFacadePart2 facade = new Facade();
		World world = facade.createWorld(500, 1, 2, 1, 1, 1, 1);
		facade.setGeologicalFeature(world, 0, 0, FEATURE_SOLID);
		return facade.createMazub(0, 499, spriteArrayForSize(3, 3));
	}

	@Test(expected = IllegalStateException.class)
	public void testEndJumping() {
		creatAlien().endJump();;
	}
	
	@Test(expected = IllegalStateException.class)
	public void testEndDucking() {
		creatAlien().endDucking();;
	}	

}
