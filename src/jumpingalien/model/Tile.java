package jumpingalien.model;

/**
 * 
 * @author Pieter-Jan Coenen (1ste Bacherlor Informatica) en Stijn Caerts (1ste Bacherlor Informatica)
 *
 * @invar	the x coordinate of the tile is always greater or equal to zero
 * 			|  getX() >= 0
 * 
 * @invar	the y coordinate of the tile is always greater or equal to zero
 * 			|  getY() >= 0
 * @invar	the y coordinate of the tile is always greater or equal to zero
 * 			|  getY() >= 0
 */

public class Tile extends jumpingalien.part3.programs.types.GameItem{

	/**
	 * 
	 * @param 	x
	 * 			the x coordinate of the tile
	 * @param 	y
	 * 			the y coordinate of the tile
	 * @param 	geologicalFeature
	 * 			the geological feature of the tile
	 * @post	the x coordinate of the tile will equal the given x coordinate
	 * 			|new.getX() = x
	 * @post	the y coordinate of the tile will equal the given y coordinate
	 * 			|new.getY() = y
	 * @post	the Geological Feature of the tile will equal the given geologicalFeature
	 * 			|new.getGeologicalFeature() = geologicalFeature
	 */
	public Tile(int x, int y, int geologicalFeature) {
		this.x = x;
		this.y = y;
		this.geologicalFeature = geologicalFeature;
	}

	
	public int getX() {
		return x;
	}

	private final int x;
	
	public int getY() {
		return y;
	}

	private final int y;
	
	public int getGeologicalFeature() {
		return geologicalFeature;
	}
	
	private final int geologicalFeature;
}
