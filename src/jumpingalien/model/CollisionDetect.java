package jumpingalien.model;

import jumpingalien.util.Sprite;

 interface CollisionDetect {
	default boolean hasCollisionTop(int x, int y){
		int endX = x + getCurrentSprite().getWidth();
		int endY = y + getCurrentSprite().getHeight();
		return getWorld().detectGeologicalFeature(x+1, endY-2, endX-2, endY-2, 1);
	}

	default boolean hasCollisionBottom(int x, int y){
		int endX = x + getCurrentSprite().getWidth();
		return getWorld().detectGeologicalFeature(x+1, y+1, endX-2, y+1, 1);
	}
	
	 default boolean hasCollisionRight(int x, int y){
		int endX = x + getCurrentSprite().getWidth();
		int endY = y + getCurrentSprite().getHeight();
		return getWorld().detectGeologicalFeature(endX-2, y+2, endX-2, endY-3, 1);
	}
	
	 default boolean hasCollisionLeft(int x, int y){
		int endY = y + getCurrentSprite().getHeight();
		return getWorld().detectGeologicalFeature(x+1, y+2, x+1, endY-3, 1);
	}
	
	default boolean hasCollisionX(int x, int y){
		return hasCollisionLeft(x, y) || hasCollisionRight(x, y);
	}
	
	default boolean hasCollisionY(int x, int y){
		return hasCollisionTop(x, y) || hasCollisionBottom(x, y);
	}

	World getWorld();

	Sprite getCurrentSprite();
}
