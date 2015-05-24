package jumpingalien.tests;

import static org.junit.Assert.*;
import jumpingalien.model.GameObject;
import jumpingalien.model.Mazub;
import jumpingalien.util.Sprite;

import org.junit.Test;

public class GameObjectTests {

	@Test
	public void contructorTestIllegalPosition() {
		Sprite[] sprites = null;
		GameObject object = new Mazub(-1, 0, sprites);
	}

}
