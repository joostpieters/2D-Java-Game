package jumpingalien.model;

public class World  {
	
	public World (int tileSize, int nbTilesX, int nbTilesY,
			int visibleWindowWidth, int visibleWindowHeight, int targetTileX,
			int targetTileY){
		this.tileSize = tileSize;
		this.nbTilesX = nbTilesX;
		this.nbTilesY = nbTilesY;
		this.visibleWindowHeight = visibleWindowHeight;
		this.visibleWindowWidth = visibleWindowWidth;
		this.targetTileX = targetTileX;
		this.targetTileY = targetTileY;		
	}
	
	/**
	 * 
	 * @return tileSize
	 */
	private int getTileSize() {
		return tileSize;
	}
	
	/**
	 * This variable contains the length (in pixels) of a side of each square tile of this world
	 */
	private final int tileSize;
	
	/**
	 * 
	 * @return nbTilesX
	 */
	private int getNbTilesX() {
		return nbTilesX;
	}
	/**
	 * this variable contains the number of tiles in the horizontal direction of this world
	 */
	private final int nbTilesX;
	
	/**
	 * 
	 * @return nbTilesY
	 */
	private int getNbTilesY() {
		return nbTilesY;
	}
	
	/**
	 * This variable contains the number of tiles in the vertical direction of this world
	 */
	private final int nbTilesY;
	
	/**
	 * 
	 * @return visibleWindowWidth
	 */
	private int getVisibleWindowWidth() {
		return visibleWindowWidth;
	}
	
	/**
	 * This variable contains the width of the visible window, in pixels of this world
	 */
	private final int visibleWindowWidth;
	
	/**
	 * 
	 * @return visibleWindowHeight
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
	 * @return targetTileX
	 */
	private int getTargetTileX() {
		return targetTileX;
	}
	
	/**
	 * this variable contains the tile x-coordinate of the target tile of this world
	 */
	private final int targetTileX;
	
	/**
	 * 
	 * @return targetTileY
	 */
	private int getTargetTileY() {
		return targetTileY;
	}
	
	/**
	 * this variable contains the tile y-coordinate of the target tile of this world
	 */
	private final int targetTileY;
}
