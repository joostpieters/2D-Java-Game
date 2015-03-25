package jumpingalien.model;

/**
 * 
 * @author Pieter-Jan Coenen (1ste Bacherlor Informatica) en Stijn Caerts (1ste Bacherlor Informatica)
 * 
 * @invar	the tileSize is greater than zero
 * 			| getTileSize > 0;
 * @invar	the nbTilesX is greater than zero
 * 			| getNbTilesX > 0;
 * @invar	the nbTilesY is greater than zero
 * 			| getNbTilesY > 0;
 */
public class World  {
	/**
	 * @param tileSize
	 *            Length (in pixels) of a side of each square tile in the world
	 * @param nbTilesX
	 *            Number of tiles in the horizontal direction
	 * @param nbTilesY
	 *            Number of tiles in the vertical direction
	 * @param visibleWindowWidth
	 *            Width of the visible window, in pixels
	 * @param visibleWindowHeight
	 *            Height of the visible window, in pixels
	 * @param targetTileX
	 *            Tile x-coordinate of the target tile of the new world
	 * @param targetTileY
	 *            Tile y-coordinate of the target tile of the new world
	 * @throws	IllegalArgumentException
	 * 			the given tileSize, nbTilesX or nbTilesY is smaller or equal to zero
	 * 			| (tileSize <= 0) || (nbTilesX <= 0) || (nbTilesY <= 0)
	 */
	public World (int tileSize, int nbTilesX, int nbTilesY,
			int visibleWindowWidth, int visibleWindowHeight, int targetTileX,
			int targetTileY) throws IllegalArgumentException {
		if ((tileSize <= 0) || (nbTilesX <= 0) || (nbTilesY <= 0))
			throw new IllegalArgumentException();
		
		this.tileSize = tileSize;
		this.nbTilesX = nbTilesX;
		this.nbTilesY = nbTilesY;
		this.visibleWindowHeight = visibleWindowHeight;
		this.visibleWindowWidth = visibleWindowWidth;
		this.targetTileX = targetTileX;
		this.targetTileY = targetTileY;		
	}
	
	/**
	 * Return the world size as an array in pixels
	 * @return 	The size of the game world, in pixels, as an array of two
	 *         		elements: width (X) and height (Y), in that order.
	 *         	| {getNbTilesX() * getTileSize(), getNbTilesY() * getTileSize()}
	 */
	public int[] getWorldSizeInPixels(){
		int worldSize[] = new int[2];
		worldSize[0] = getNbTilesX() * getTileSize();
		worldSize[1] = getNbTilesY() * getTileSize();
		return worldSize;
	}
	
	/**
	 * Returns the length (in pixels) of a side of each square tile of this world
	 */
	public int getTileSize() {
		return tileSize;
	}
	
	/**
	 * This variable contains the length (in pixels) of a side of each square tile of this world
	 */
	private final int tileSize;
	
	/**
	 * Returns the number of tiles in the horizontal direction of this world
	 */
	private int getNbTilesX() {
		return nbTilesX;
	}
	/**
	 * this variable contains the number of tiles in the horizontal direction of this world
	 */
	private final int nbTilesX;
	
	/**
	 * Returns the number of tiles in the vertical direction of this world
	 */
	private int getNbTilesY() {
		return nbTilesY;
	}
	
	/**
	 * This variable contains the number of tiles in the vertical direction of this world
	 */
	private final int nbTilesY;
	
	/**
	 * Returns the width of the visible window, in pixels of this world
	 */
	private int getVisibleWindowWidth() {
		return visibleWindowWidth;
	}
	
	/**
	 * This variable contains the width of the visible window, in pixels of this world
	 */
	private final int visibleWindowWidth;
	
	/**
	 * Returns the height of the visible window, in pixels of this world
	 */
	private int getVisibleWindowHeight() {
		return visibleWindowHeight;
	}
	
	/**
	 * This variable contains the height of the visible window, in pixels of this world
	 */
	private final int visibleWindowHeight;
	
	/**
	 * 
	 * @return 	The pixel coordinates of the visible window, in the order
	 *         		left, bottom, right, top
	 *         	|{0, 0, getVisibleWindowWidth(), getVisibleWindowHeight()}
	 */
	public int[] getVisibleWindow(){
		int[] window = new int[4];
		window[0] = 0;
		window[1] = 0;
		window[2] = getVisibleWindowWidth();
		window[3] = getVisibleWindowHeight();
		return window;
	}
	
	/**
	 * Returns the tile x-coordinate of the target tile of this world
	 */
	private int getTargetTileX() {
		return targetTileX;
	}
	
	/**
	 * this variable contains the tile x-coordinate of the target tile of this world
	 */
	private final int targetTileX;
	
	/**
	 * Returns the tile y-coordinate of the target tile of this world
	 */
	private int getTargetTileY() {
		return targetTileY;
	}
	
	/**
	 * this variable contains the tile y-coordinate of the target tile of this world
	 */
	private final int targetTileY;
}
