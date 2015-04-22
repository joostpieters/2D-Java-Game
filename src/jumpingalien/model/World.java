package jumpingalien.model;

import java.util.ArrayList;
import java.util.Collection;

import be.kuleuven.cs.som.annotate.Basic;

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
	//TODO effect!
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
		this.geologicalFeatureOfTiles = new int[nbTilesX][nbTilesY];
		this.visibleWindowHeight = visibleWindowHeight;
		this.visibleWindowWidth = visibleWindowWidth;
		this.targetTileX = targetTileX;
		this.targetTileY = targetTileY;
		this.setVisibleWindow(0, 0, visibleWindowWidth, visibleWindowHeight);
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
	@Basic
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
	@Basic
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
	@Basic
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
	@Basic
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
	@Basic
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
		window[0] = windowLeft;
		window[1] = windowBottom;
		window[2] = windowRight;
		window[3] = windowTop;
		return window;
	}
	
	/**
	 * The boundaries for the window.
	 */
	private int windowLeft, windowBottom, windowRight, windowTop;
	
	/**
	 * 
	 * @param left
	 * @param bottom
	 * @param right
	 * @param top
	 * @post	...
	 * 			| new.getVisibleWindow() = {left, bottom, right, top}
	 */
	private void setVisibleWindow(int left, int bottom, int right, int top) {
		this.windowLeft = left;
		this.windowBottom = bottom;
		this.windowRight = right;
		this.windowTop = top;
	}
	
	// TODO documentatie
	private void updateWindow() {
		int x = (int) getMazub().getLocationX();
		int y = (int) getMazub().getLocationY();
		int newLeft = getVisibleWindow()[0];
		int newBottom = getVisibleWindow()[1];
		int newRight = getVisibleWindow()[2];
		int newTop = getVisibleWindow()[3];
		
		if ((x > getVisibleWindow()[2] - 200) && (x + 200 < getWorldSizeInPixels()[0])) {
			newRight = x + 200;
			newLeft = newRight - getVisibleWindowWidth();
		} else if ((x < getVisibleWindow()[0] + 200) && (x - 200 >= 0)) {
			newLeft = x - 200;
			newRight = newLeft + getVisibleWindowWidth();
		} else if (x <= 200) {
			newLeft = 0;
			newRight = newLeft + getVisibleWindowWidth();
		} else if (x >= getWorldSizeInPixels()[0] - 200) {
			newRight = getWorldSizeInPixels()[0]-1;
			newLeft = newRight - getVisibleWindowWidth();
		}
		
		if ((y > getVisibleWindow()[3] - 200) && (y + 200 < getWorldSizeInPixels()[1])) {
			newTop = y + 200;
			newBottom = newTop - getVisibleWindowHeight();
		} else if ((y < getVisibleWindow()[1] + 200) && (y - 200 >= 0)) {
			newBottom = y - 200;
			newTop = newBottom + getVisibleWindowHeight();
		} else if (y <= 200) {
			newBottom = 0;
			newTop = newBottom + getVisibleWindowHeight();
		} else if (y >= getWorldSizeInPixels()[1] - 200) {
			newTop = getWorldSizeInPixels()[1] - 1;
			newBottom = newTop - getVisibleWindowHeight();
		}
		
		setVisibleWindow(newLeft, newBottom, newRight, newTop);
	}
	
	/**
	 * Returns the tile x-coordinate of the target tile of this world
	 */
	@Basic
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
	@Basic
	private int getTargetTileY() {
		return targetTileY;
	}
	
	/**
	 * this variable contains the tile y-coordinate of the target tile of this world
	 */
	private final int targetTileY;
	
	/**
	 * Returns the bottom left pixel coordinate of the tile at the given tile position.
	 * @param tileX
	 * 			The x-position x_T of the tile
	 * @param tileY
	 * 			The y-position y_T of the tile
	 * @return An array which contains the x-coordinate and y-coordinate of the
	 *         bottom left pixel of the given tile, in that order.
	 *         | {tileX * getTileSize(), tileY * getTileSize()}
	 */
	public int[] getBottomLeftPixelOfTile(int tileX, int tileY) {
		int[] bottomLeftPixel = new int[2];
		bottomLeftPixel[0] = tileX * getTileSize();
		bottomLeftPixel[1] = tileY * getTileSize();
		return bottomLeftPixel;
	}
	
	/**
	 * Returns the mazub of this world
	 */
	@Basic 
	Mazub getMazub() {
		return this.alien;
	}
	
	/**
	 * Sets the given alien as the player's character in this world.
	 * @param alien
	 * 			The alien to be set as the player's character.
	 * @throws	IllegalArgumentException
	 * 			...
	 * 			|!isValidMazub(alien)
	 */
	public void setMazub(Mazub alien) throws IllegalArgumentException {
		if(!isValidMazub(alien)){
			throw new IllegalArgumentException();
		}
		this.alien = alien;
		alien.setWorld(this);
	}
	/**
	 * 
	 * @param alien
	 * @return	...
	 * 			| return ((alien != null) && (!alien.hasAWorld()));
	 */
	private boolean isValidMazub(Mazub alien){
		return ((alien != null) && (!alien.hasAWorld()));
	}
	
	/**
	 * This variable contains the player's character in this world.
	 */
	private Mazub alien;
	
	/**
	 * Returns whether the game is over.
	 * @return	...
	 * 			| result == getMazub().isDead()
	 */
	public boolean isGameOver() {
		return (getMazub().isDead() || hasReachedEnd());
	}
	
	@Basic
	public boolean hasReachedEnd() {
		return this.reachedEnd;
	}
	
	/**
	 * 
	 * @param value
	 * @post	...
	 * 			| new.hasReachedEnd() == value
	 */
	private void setReachedEnd(boolean value) {
		this.reachedEnd = value;
	}
	
	private boolean reachedEnd;
	
	private boolean alienIsAtTargetTile() {
		Mazub alien = getMazub();
		int x = (int) alien.getLocationX();
		int y = (int) alien.getLocationY();
		int endX = (int) (alien.getLocationX() + alien.getCurrentSprite().getWidth() - 1);
		int endY = (int) (alien.getLocationY() + alien.getCurrentSprite().getHeight() - 1);
		
		int[][] tiles = getTilePositionsIn(x, y, endX, endY);
		
		for (int[] tile : tiles) {
			if (tile[0] == getTargetTileX() && tile[1] == getTargetTileY()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns the feature of the given tile
	 * @param 	tileX
	 *            The x-position x_T of the tile for which the feature needs to be returned
	 * @param 	tileY
	 *            The y-position y_T of the tile for which the feature needs to be returned  
	 * @pre		the given tile coordinate x_T and y_T needs to be valid
	 * 			|isValidTileCoordinate(tileX, tileY)
	 * @return 	The type of the tile with the given x-position x_T and y-position y_T ,
	 *         		where
	 *         		the value 0 is provided for an air tile
	 *         		the value 1 is provided for a solid ground tile
	 *            	the value 2 is provided for a water tile
	 *           	the value 3 is provided for a magma tile
	 */
	@Basic
	public int getGeologicalFeatureOfTile(int tileX, int tileY){
		assert(isValidTileCoordinate(tileX, tileY));
		return geologicalFeatureOfTiles[tileX][tileY];
	}
	
	/**
	 * 
	 * @param 	tileX
	 *            The x-position x_T of the tile for which the type needs to be
	 *            modified
	 * @param 	tileY
	 *            The y-position y_T of the tile for which the type needs to be
	 *            modified
	 * @param 	tileType
	 *            The new type for the given tile, where
	 *            the value 0 is provided for an air tile
	 *            the value 1 is provided for a solid ground tile
	 *            the value 2 is provided for a water tile
	 *            the value 3 is provided for a magma tile
	 * @post	the new geological feature of this tile equals the given tileType
	 * 			|new.getGeologicalFeatureOfTile(tileX, tileY) = tileType;
	 * @throws	IllegalArgumentException
	 * 			when the given tileType is less than 
	 */
	public void setGeologicalFeatureOfTile(int tileX, int tileY, int tileType) 
			throws IllegalArgumentException{
		if (! isValidTileType(tileType) || (! isValidTileCoordinate(tileX, tileY)))
			throw new IllegalArgumentException();
		this.geologicalFeatureOfTiles[tileX][tileY] = tileType;
	}
	
	/**
	 * Checks if a given tile type is valid
	 * @param 	tileType
	 * 			the tile type that needs to be checked 
	 * @return	true is the given tileType is between 0 and 3 (inclusive 0 and 3)
	 * 				otherwise returns false
	 * 			|((tileType < 0)||(tileType > 3))
	 */
	private boolean isValidTileType(int tileType){
		return ((tileType >= 0)||(tileType <= 3));
	}
	
	/**
	 * 
	 * @param 	pixelX
	 * 			The X coordinate of the pixel
	 * @param 	pixelY
	 * 			The Y coordinate of the pixel
	 * @return	...
	 * 			|getGeologicalFeatureOfTile(pixelInWhichTile(pixelX, pixelY)[0], pixelInWhichTile(pixelX, pixelY)[1])
	 * @throws 	IllegalArgumentException
	 * 			...
	 * 			|(!isValidBottomLeftTilePixel(pixelX, pixelY))
	 */
	public int getGeologicalFeatureByPixel(int pixelX, int pixelY) throws IllegalArgumentException{
		if(!isValidBottomLeftTilePixel(pixelX, pixelY)){
			throw new IllegalArgumentException();
		}
		int[] tile = pixelInWhichTile(pixelX, pixelY);
		return getGeologicalFeatureOfTile(tile[0], tile[1]);	
		
	}
	
	/**
	 * 
	 * @param 	pixelLeft
	 * @param 	pixelBottom
	 * @param 	pixelRight
	 * @param 	pixelTop
	 * @return 	An array of tile positions, where each position (x_T, y_T) is
	 *         	represented as an array of 2 elements, containing the horizontal
	 *         	(x_T) and vertical (y_T) coordinate of a tile in that order.
	 *         	The returned array is ordered from left to right,
	 *         	bottom to top: all positions of the bottom row (ordered from
	 *         	small to large x_T) precede the positions of the row above that.
	 * 			|
	 */
	
	//TODO commentaar
	public int[][] getTilePositionsIn(int pixelLeft, int pixelBottom, int pixelRight, int pixelTop){
		int[] rightTop = pixelInWhichTile(pixelRight, pixelTop);
		int[] leftBottom = pixelInWhichTile(pixelLeft, pixelBottom);
		int amountTiles = (rightTop[0]-leftBottom[0]+1) * (rightTop[1]-leftBottom[1]+1);
		int[][] tiles = new int[amountTiles][2];
		int left = leftBottom[0];
		int right = rightTop[0];
		int bottom = leftBottom[1];
		for(int i=0; i < amountTiles; i++){
			tiles[i][0] = left++;
			tiles[i][1] = bottom;
			if (left > right){
				left = leftBottom[0];
				bottom++;
			}
		}
		return tiles;
	}
	
	/**
	 * 
	 * @param 	dt
	 * 			the elapsed time in seconds
	 * @throws 	IllegalArgumentException
	 * 			if dt is less than zero or if seconds is equal or bigger than 0.2
	 * 			| (dt < 0 || dt >= 0.2)
	 * @effect	...
	 * 			|getMazub().advanceTime(dt)
	 * @effect	...
	 * 			| updateWindow()
	 */
	public void advanceTime(double dt) throws IllegalArgumentException{
		if (dt < 0 || dt >= 0.2) 
			throw new IllegalArgumentException();
		getMazub().advanceTime(dt);
		for(Plant plant : plants){
			plant.advanceTime(dt);
		}
		for (Slime slime : slimes) {
			slime.advanceTime(dt);
		}
		for (Shark shark : sharks) {
			shark.advanceTime(dt);
		}
		
		if (alienIsAtTargetTile()) {
			setReachedEnd(true);
		}
		
		updateWindow();
	}
	/**
	 * 
	 * @param 	pixelX
	 * 			The X coordinate of the pixel
	 * @param 	pixelY
	 * 			The Y coordinate of the pixel
	 * @return	...
	 * 			|return((pixelX % getTileSize() == 0)&&(pixelY % getTileSize() == 0))
	 */
	private boolean isValidBottomLeftTilePixel(int pixelX, int pixelY){
		return((pixelX % getTileSize() == 0)&&(pixelY % getTileSize() == 0));	
	}
	
	/**
	 * Returns the tile in which the pixel is located
	 * 
	 * @param 	pixelX
	 * 			The X coordinate of the pixel
	 * @param 	pixelY
	 * 			The Y coordinate of the pixel
	 * @pre		the pixel needs to be in the game World
	 * 			|((pixelX <= getWorldSizeInPixels()[0]) && (pixelY <= getWorldSizeInPixels()[1]))
	 * @return	{pixelX / getTileSize(), pixelY / getTileSize()}
	 */
	private int[] pixelInWhichTile(int pixelX, int pixelY){
		assert((pixelX <= getWorldSizeInPixels()[0]) && (pixelY <= getWorldSizeInPixels()[1]));
		int[] result = new int[2];
		result[0] = pixelX / getTileSize();
		result[1] = pixelY / getTileSize();
		return result;
	}
	
	/**
	 * Returs whereas the given X_T and Y_T coordinate is valid
	 * @param 	xT
	 * 			the X_T coordinate of the tile
	 * @param 	yT
	 * 			the Y_T coordinate of the tile
	 * @return	true when xT and yT are greater or equal to zero and 
	 * 				xT is less than the amount of horzontal tiles and 
	 * 				yT is less than the amount of vertical tilles
	 * 				otherwise returns false
	 * 			|((xT >= 0) && (xT < getNbTilesX()) && (yT >= 0) && (yT < getNbTilesY()))
	 */
	public boolean isValidTileCoordinate(int xT, int yT){
		return ((xT >= 0) && (xT < getNbTilesX()) && (yT >= 0) && (yT < getNbTilesY()));
	}
	
	/**
	 * This table contains the geological feature of all tiles in this world
	 */
	private int[][] geologicalFeatureOfTiles;
	
	/**
	 * Returns a collection containing the Plants of this World
	 */
	public Collection<Plant> getPlants(){
		return new ArrayList<Plant>(plants);
	}
	
	/**
	 * Add the given plant to the plants of this world
	 * @param plant
	 * 			the plant to add
	 * @post	the plant is added to the list of plants
	 * 			| new.plants.contains(plant)
	 * @effect 	...
	 * 			| plant.setWorld(this);
	 * @throws 	IllegalArgumentException
	 * 			| ! canHaveAsPlant(plant)
	 */
	public void addPlant(Plant plant) throws IllegalArgumentException{
		if (!canHaveAsPlant(plant)){
			throw new IllegalArgumentException();
		}
		this.plants.add(plant);
		plant.setWorld(this);
	}
	
	/**
	 * Checks whether the given plant can be add to a world
	 * @param plant
	 * @return	...
	 * 			|return ((plant != null) && (!plant.hasAWorld()))
	 */
	private boolean canHaveAsPlant(Plant plant){
		return ((plant != null) && (!plant.hasAWorld()));
	}
	
	/**
	 * Returns whether the given plant belongs to this world
	 * @param 	plant
	 * @return 	...
	 * 			|return getPlants().contains(plant);
	 */
	boolean hasAsPlant(Plant plant){
		return plants.contains(plant);
	}
	
	/**
	 * This List contains the Plants of this World
	 */
	private ArrayList<Plant> plants = new ArrayList<Plant>();
	
	/**
	 * Add the given plant to the plants of this world
	 * @param plant
	 * 			the plant to add
	 * @post	the plant is added to the list of plants
	 * 			| new.plants.contains(plant)
	 * @effect 	...
	 * 			| plant.setWorld(this);
	 * @throw 	IllegalArgumentException
	 * 			...
	 * 			|!canHaveAsShark(shark)
	 */
	public void addShark(Shark shark){
		if (!canHaveAsShark(shark)){
			throw new IllegalArgumentException();
		}
		this.sharks.add(shark);
		shark.setWorld(this);
	}
	
	/**
	 * Checks whether the given shark can be add to a world
	 * @param 	shark
	 * @return	...
	 * 			|return ((shark != null) && (!shark.hasAWorld()))
	 */
	private boolean canHaveAsShark(Shark shark){
		return ((shark != null) && (!shark.hasAWorld()));
	}
	
	/**
	 * Returns whether the given shark belongs to this world
	 * @param 	shark
	 * @return 	...
	 * 			|return getSharks().contains(shark);
	 */
	boolean hasAsShark(Shark shark){
		return sharks.contains(shark);
	}
	
	/**
	 * Returns a collection containing the sharks of this World
	 */
	public Collection<Shark> getSharks(){
		return new ArrayList<Shark>(sharks);
	}
	
	private ArrayList<Shark> sharks = new ArrayList<Shark>();
	
	/**
	 * Add the given plant to the plants of this world
	 * @param plant
	 * 			the plant to add
	 * @post	the plant is added to the list of plants
	 * 			| new.plants.contains(plant)
	 * @effect 	...
	 * 			| plant.setWorld(this);
	 * @throw 	IllegalArgumentException
	 * 			...
	 * 			|!canHaveAsSlime(slime)
	 */
	public void addSlime(Slime slime){
		if (!canHaveAsSlime(slime)){
			throw new IllegalArgumentException();
		}
		this.slimes.add(slime);
		slime.setWorld(this);
	}
	
	/**
	 * Checks whether the given slime can be add to a world
	 * @param 	slime
	 * @return	...
	 * 			|return ((slime != null) && (!slime.hasAWorld()))
	 */
	private boolean canHaveAsSlime(Slime slime){
		return ((slime != null) && (!slime.hasAWorld()));
	}
	
	/**
	 * Returns whether the given slime belongs to this world
	 * @param 	slime
	 * @return 	...
	 * 			|return getSlimes().contains(slime);
	 */
	boolean hasAsSlime(Slime slime){
		return slimes.contains(slime);
	}
	
	/**
	 * Returns a collection containing the slimes of this World
	 */
	public Collection<Slime> getSlimes(){
		return new ArrayList<Slime>(slimes);
	}
	
	private ArrayList<Slime> slimes = new ArrayList<Slime>();
	public void deleteShark(Shark shark){
		sharks.remove(shark);
	}
	
	private boolean hasCollision(int startX1, int startY1, int endX1, int endY1, int startX2, int startY2, int endX2, int endY2){
		startX1 += 1;
		startX2 += 1;
		startY1 += 1;
		startY2 += 1;
		endX1 -= 2;
		endX2 -= 2;
		endY1 -= 2;
		endY2 -= 2;
		if ((startX2 < endX1 && startX2 > startX1) || (startX1 < endX2 && startX1 > startX2)) {
			if ((startY2 < endY1 && startY2 > startY1) || (startY1 < endY2 && startY1 > startY2)) {
				return true;
			}
		}
		return false;
	}
	
	Collection<Plant> collisionPlants(int startX, int startY, int endX, int endY) {
		ArrayList<Plant> list = new ArrayList<Plant>();
		int plantStartX;
		int plantEndX;
		int plantStartY;
		int plantEndY;
		for (Plant plant :  getPlants()) {
			plantStartX = plant.getLocation()[0];
			plantStartY = plant.getLocation()[1];
			plantEndX =	plantStartX + plant.getCurrentSprite().getWidth();
			plantEndY = plantStartY + plant.getCurrentSprite().getHeight();
			if (hasCollision(startX, startY, endX, endY, plantStartX, plantStartY, plantEndX, plantEndY)) {
				list.add(plant);
			}
		}
		return list;
	}
	
	void removePlant(Plant plant) {
		assert (plant != null);
		plants.remove(plant);
	}
}

