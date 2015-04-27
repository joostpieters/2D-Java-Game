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
	
	default boolean hasCollision(int x, int y){
		return hasCollisionX(x,y) || hasCollisionY(x,y);
	}
	
	default boolean isTopPerimeterInWater(){
		int[] position = getLocation();
		int x = position[0];
		int y = position[1];
		int endX = x + getCurrentSprite().getWidth();
		int endY = y + getCurrentSprite().getHeight();
		return getWorld().detectGeologicalFeature(x+1, endY-1, endX-2, endY-1, 2);
	}

	default boolean isBottomPerimeterInWater(){
		int[] position = getLocation();
		int x = position[0];
		int y = position[1];
		int endX = x + getCurrentSprite().getWidth();
		return getWorld().detectGeologicalFeature(x+1, y, endX-2, y, 2);
	}
	
	default boolean isRightPerimeterInWater(){
		int[] position = getLocation();
		int x = position[0];
		int y = position[1];
		int endX = x + getCurrentSprite().getWidth();
		int endY = y + getCurrentSprite().getHeight();
		return getWorld().detectGeologicalFeature(endX-1, y+2, endX-1, endY-3, 2);
	}
	
	default boolean isLeftPerimeterInWater(){
		int[] position = getLocation();
		int x = position[0];
		int y = position[1];
		int endY = y + getCurrentSprite().getHeight();
		return getWorld().detectGeologicalFeature(x, y+2, x, endY-3, 2);
	}
	
	default boolean isInWater() {
		int[] position = getLocation();
		int x = position[0];
		int y = position[1];
		int endX = x + getCurrentSprite().getWidth();
		int endY = y + getCurrentSprite().getHeight();
		return getWorld().detectGeologicalFeature(x, y, endX-1, endY-1, 2);
	}
	
	default boolean isInMagma() {
		int[] position = getLocation();
		int x = position[0];
		int y = position[1];
		int endX = x + getCurrentSprite().getWidth();
		int endY = y + getCurrentSprite().getHeight();
		return getWorld().detectGeologicalFeature(x, y, endX-1, endY-1, 3);
	}

	abstract int[] getLocation();
	
	abstract World getWorld();

	abstract Sprite getCurrentSprite();
}
