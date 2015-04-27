package jumpingalien.model;

import jumpingalien.util.Sprite;

 interface CollisionDetect {
	 /**
	  * 
	  * @param x
	  * @param y
	  * @return	...
	  * 		| result == getWorld().detectGeologicalFeature(x+2, y + getCurrentSprite().getHeight()-2, x + getCurrentSprite().getWidth()-3, y + getCurrentSprite().getHeight()-2, 1) 
	  */
	default boolean hasCollisionTop(int x, int y){
		int endX = x + getCurrentSprite().getWidth();
		int endY = y + getCurrentSprite().getHeight();
		return getWorld().detectGeologicalFeature(x+2, endY-2, endX-3, endY-2, 1);
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return	...
	 * 			| result == getWorld().detectGeologicalFeature(x+2, y+1, x + getCurrentSprite().getWidth() - 3, y+1, 1)
	 */
	default boolean hasCollisionBottom(int x, int y){
		int endX = x + getCurrentSprite().getWidth();
		return getWorld().detectGeologicalFeature(x+2, y+1, endX-3, y+1, 1);
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return	...
	 * 			| result == getWorld().detectGeologicalFeature(x + getCurrentSprite().getWidth()-2, y+2, x + getCurrentSprite().getWidth()-2, y + getCurrentSprite().getHeight()-3, 1)
	 */
	 default boolean hasCollisionRight(int x, int y){
		int endX = x + getCurrentSprite().getWidth();
		int endY = y + getCurrentSprite().getHeight();
		return getWorld().detectGeologicalFeature(endX-2, y+2, endX-2, endY-3, 1);
	}
	
	 /**
	  * 
	  * @param x
	  * @param y
	  * @return	...
	  * 		| getWorld().detectGeologicalFeature(x+1, y+2, x+1, y + getCurrentSprite().getHeight()-3, 1)
	  */
	 default boolean hasCollisionLeft(int x, int y){
		int endY = y + getCurrentSprite().getHeight();
		return getWorld().detectGeologicalFeature(x+1, y+2, x+1, endY-3, 1);
	}
	
	 /**
	  * 
	  * @param x
	  * @param y
	  * @return	...
	  * 		| result == (hasCollisionLeft(x, y) || hasCollisionRight(x, y))
	  */
	default boolean hasCollisionX(int x, int y){
		return hasCollisionLeft(x, y) || hasCollisionRight(x, y);
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return	...
	 * 			| result == (hasCollisionTop(x, y) || hasCollisionBottom(x, y))
	 */
	default boolean hasCollisionY(int x, int y){
		return hasCollisionTop(x, y) || hasCollisionBottom(x, y);
	}
	
	/**
	 * 
	 * @return	...
	 * 			| result == getWorld().detectGeologicalFeature(getLocation()[0]+1, getLocation()[1] + getCurrentSprite().getHeight()-1, getLocation()[0]+getCurrentSprite().getWidth()-2, getLocation()[1] + getCurrentSprite().getHeight()-1)
	 */
	default boolean isTopPerimeterInWater(){
		int[] position = getLocation();
		int x = position[0];
		int y = position[1];
		int endX = x + getCurrentSprite().getWidth();
		int endY = y + getCurrentSprite().getHeight();
		return getWorld().detectGeologicalFeature(x+1, endY-1, endX-2, endY-1, 2);
	}

	/**
	 * 
	 * @return	...
	 * 			| result == getWorld().detectGeologicalFeature(getLocation()[0]+1, getLocation()[1], getLocation()[0] + getCurrentSprite().getWidth()-2, getLocation()[1], 2)
	 */
	default boolean isBottomPerimeterInWater(){
		int[] position = getLocation();
		int x = position[0];
		int y = position[1];
		int endX = x + getCurrentSprite().getWidth();
		return getWorld().detectGeologicalFeature(x+1, y, endX-2, y, 2);
	}
	
	/**
	 * 
	 * @return	...
	 * 			| result == getWorld().detectGeologicalFeature(getLocation()[0] + getCurrentSprite().getWidth()-1, getLocation()[1]+2, getLocation()[0] + getCurrentSprite().getWidth()-1, getLocation()[1] + getCurrentSprite().getHeight()-3, 2)
	 */
	default boolean isRightPerimeterInWater(){
		int[] position = getLocation();
		int x = position[0];
		int y = position[1];
		int endX = x + getCurrentSprite().getWidth();
		int endY = y + getCurrentSprite().getHeight();
		return getWorld().detectGeologicalFeature(endX-1, y+2, endX-1, endY-3, 2);
	}
	
	/**
	 * 
	 * @return	...
	 * 			| result == getWorld().detectGeologicalFeature(getLocation()[0], getLocation()[1]+2, getLocation()[0], getLocation()[1] + getCurrentSprite().getHeight()-3, 2)
	 */
	default boolean isLeftPerimeterInWater(){
		int[] position = getLocation();
		int x = position[0];
		int y = position[1];
		int endY = y + getCurrentSprite().getHeight();
		return getWorld().detectGeologicalFeature(x, y+2, x, endY-3, 2);
	}
	
	/**
	 * 
	 * @return 	...
	 * 			| result == getWorld().detectGeologicalFeature(getLocation()[0], getLocation()[1], getLocation()[0] + getCurrentSprite().getWidth()-1, getLocation()[1] + getCurrentSprite().getHeight()-1, 2)
	 */
	default boolean isInWater() {
		int[] position = getLocation();
		int x = position[0];
		int y = position[1];
		int endX = x + getCurrentSprite().getWidth();
		int endY = y + getCurrentSprite().getHeight();
		return getWorld().detectGeologicalFeature(x, y, endX-1, endY-1, 2);
	}
	
	/**
	 * 
	 * @return	...
	 * 			| result == getWorld().detectGeologicalFeature(getLocation()[0], getLocation()[1], getLocation()[0] + getCurrentSprite().getWidth()-1, getLocation()[1] + getCurrentSprite().getHeight()-1, 3)
	 */
	default boolean isInMagma() {
		int[] position = getLocation();
		int x = position[0];
		int y = position[1];
		int endX = x + getCurrentSprite().getWidth();
		int endY = y + getCurrentSprite().getHeight();
		return getWorld().detectGeologicalFeature(x, y, endX-1, endY-1, 3);
	}

	/**
	 * @return an array containing the location, with first the x value than the y value
	 */
	abstract int[] getLocation();
	
	/**
	 * @return the current world of this object
	 */
	abstract World getWorld();

	/**
	 * @return the current sprite of this object
	 */
	abstract Sprite getCurrentSprite();
}
