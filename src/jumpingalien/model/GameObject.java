package jumpingalien.model;

import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
import be.kuleuven.cs.som.annotate.Basic;

public abstract class GameObject extends jumpingalien.part3.programs.types.GameItem implements CollisionDetect  {
	
	/**
	 * 
	 * @param 	x
	 * 			the x coordinate for this game object
	 * @param 	y
	 * 			the y coordinate for this game object
	 * @param 	sprites
	 * 			the sprites for this game object
	 * @throws 	IllegalArgumentException
	 * 			if one of the used methodes to initialize this Game object throw an acception
	 *			| x < 0 || y < 0 || sprites.length < getRequiredLengthSpriteArray() || sprites.contains(null)
	 * @effect 	The x location of this object will be set to the given x location
	 * 			| setLocationX(x)
	 * @effect 	The y location of this object will be set to the given y location
	 * 			| setLocationY(y) 
	 * @effect 	The sprite array of this game object will be set to the given sprite array
	 * 			|setSprites(sprites)
	 * @post	The program of this gameObject will equal null
	 * 			| new.getProgram() == null
	 */
	protected GameObject(int x, int y, Sprite[] sprites)throws IllegalArgumentException {
		setLocationX(x);
		setLocationY(y);
		setSprites(sprites);
		program = null;
	}
	
	/**
	 * 
	 * @param 	x
	 * 			the x coordinate for this game object
	 * @param 	y
	 * 			the y coordinate for this game object
	 * @param 	sprites
	 * 			the sprites for this game object
	 * @throws 	IllegalArgumentException
	 * 			if the given program equals null or 
	 * 				if one of the methodes used to initialize this Game object throws an acception
	 *			| program == null || x < 0 || y < 0 || sprites.length < getRequiredLengthSpriteArray() || sprites.contains(null)
	 * @effect 	The x location of this object will be set to the given x location
	 * 			| setLocationX(x)
	 * @effect 	The y location of this object will be set to the given y location
	 * 			| setLocationY(y) 
	 * @effect 	The sprite array of this game object will be set to the given sprite array
	 * 			|setSprites(sprites)
	 * @post	The program of this gameObject will equal the given program
	 * 			| new.getProgram() == program
	 * @effect	The program will be linked to this Game Object
	 * 			| new.program.getObject() == this
	 */
	protected GameObject(int x, int y, Sprite[] sprites, Program program) 
			throws IllegalArgumentException {
		if(program == null){
			throw new IllegalArgumentException();
		}
		setLocationX(x);
		setLocationY(y);
		setSprites(sprites);
		this.program = program;
		program.setObject(this);
	}
	
	/**
	 * @return will return terminated
	 * 			|result == this.terminated
	 */
	public boolean isTerminated() {
		return this.terminated;
	}
	
	/**
	 * 
	 * @param value
	 * @post	terminated will equal the given value
	 * 			|new.isTerminated() = value
	 */
	protected void setTerminated(boolean value) {
		this.terminated = value;
	}
	
	/**
	 * This variable contains whether the given Game Object is terminated or not
	 */
	private boolean terminated;
	
	/**
	 * Returns the x location of this Game Object
	 */
	@Basic
	protected double getLocationX() {
		return this.locationX;
	}

	/**
	 * Sets the x location of this Game Object to the given x
	 * 
	 * @param 	x
	 *          the new x location for this Game Object
	 * @throws	IllegalArgumentException
	 * 			if the given location is less than zero
	 * 			| x < 0
	 * @throws	IllegalArgumentException
	 * 			if the given location is greater than the width of the world if the world is set
	 * 			| getWorld() != null && x > this.getWorld().getWorldSizeInPixels()[0]
	 * @post 	the x location of this Game Object is equal to the given x 
	 * 			|new.getLocationX() == x
	 */
	protected void setLocationX(double x) throws IllegalArgumentException {
		if(x < 0){
			throw new IllegalArgumentException();
		}
		if(getWorld() != null && x > this.getWorld().getWorldSizeInPixels()[0]){
			throw new IllegalArgumentException();
		}
			
		this.locationX = x;
	}

	/**
	 * This function terminates the Game Object
	 */
	abstract void terminate();

	/**
	 * This variable contains the x location of this Game Object.
	 */
	private double locationX;

	/**
	 * Returns the y location of this Game Object
	 */
	@Basic
	protected double getLocationY() {
		return this.locationY;
	}

	/**
	 * Sets the y location of this Game Object to the given y
	 * 
	 * @param 	y
	 *          the new y location for this Game Object
	 * @throws	IllegalArgumentException
	 * 			If the given location is less than 0 or greater than the height of the game world
	 * 			|if(y < 0) then
	 * 			|	throw new IllegalArgumentException();
	 * 			|if(hasAWorld() && getWorld().getWorldSizeInPixels()[1] > y) then
	 *			|	throw new IllegalArgumentException()
	 * @post 	the y location of this Game Object is equal to the given y
	 *       	|new.getLocationY() = y
	 */
	protected void setLocationY(double y) {
		if(y < 0){
			throw new IllegalArgumentException();
		} else if(hasAWorld() && getWorld().getWorldSizeInPixels()[1] < y){
			throw new IllegalArgumentException();
		} else { 
			this.locationY = y;
		}
	}

	/**
	 * This variable contains the y location of this Game Object.
	 */
	private double locationY;

	/**
	 * Returns the location of this Game Object
	 * 
	 * @return returns an array of integers, the first element is the x
	 *         location, the second element is the y location 
	 *         | result == {(int) getLocationX(), (int) getLocationY()}
	 */
	@Override
	public int[] getLocation() {
		int[] location = new int[2];
		location[0] = (int) getLocationX();
		location[1] = (int) getLocationY();
		return location;
	}
	
	/**
	 * Returns the required length of the sprite array for this Game Object
	 */
	abstract int getRequiredLengthSpriteArray();
	
	/**
	 * Checks if the given sprites array is valid or not
	 * @param sprites
	 * 			the sprite array to check
	 * @return	false if the length of the sprite array not is equal to the required length
	 * 			| if (sprites.length != getRequiredLengthSpriteArray()) then
	 * 			|	result == false
	 * @return	if the length of the sprite array is equal to the required length and 
	 * 				if the sprite array contains a null pointer, return false, otherwise return true	 
	 * 			| if (sprites.length == getRequiredLengthSpriteArray()) then
	 * 			|	foreach (sprite in sprites) 
	 * 			|		if (sprite == null) then
	 * 			|			result == false
	 * 			|	result == true
	 */
	protected boolean isValidSpriteArray(Sprite[] sprites) {
		if (sprites.length != getRequiredLengthSpriteArray()) {
			return false;
		} else {
			for (Sprite sprite : sprites) {
				if (sprite == null) {
					return false;
				}
			}
			return true;
		}
	}
	
	/**
	 * Return the sprites of this Game Object
	 */
	@Basic
	protected Sprite[] getSprites() {
		return this.sprites;
	}

	/**
	 * Set the sprites of this Game Object to the given sprites
	 * 
	 * @param 	sprites
	 *          the new sprites for this Game Object
	 * @throws 	IllegalArgumentException
	 * 			if the given sprite array is not valid, an exception will be thrown
	 * 			| !isValidSpriteArray(sprites)
	 * @post 	the sprites of this Game Object is equal to the given sprites
	 *       	|new.getSprites() == sprites
	 */
	protected void setSprites(Sprite[] sprites) throws IllegalArgumentException {
		if (!isValidSpriteArray(sprites)) {
			throw new IllegalArgumentException();
		}
		this.sprites = sprites;
	}

	/**
	 * This array holds the sprites of this Game Object
	 */
	private Sprite[] sprites;
	
	/**
	 * Returns the hitPoints of this game object
	 */
	@Basic
	public int getHitPoints() {
		return hitPoints;
	}

	/**
	 * 
	 * @param 	hitPoints
	 * @post 	the amount of hitPoints of this Game Object will equal the given amount of hitPoints
	 * 			|new.getHitPoints() == hitPoints
	 */
	protected void setHitPoints(int hitPoints) {
 		if(hitPoints <= 0){
			this.hitPoints = 0;
 			setDead(true);
 		} else if(hitPoints > getMaxHitPoints()) {
			this.hitPoints = getMaxHitPoints();
 		} else {
			this.hitPoints = hitPoints;
 		}
 	}
	
	/**
	 * 
	 * @param 	amount
	 * @post 	the amount of hitPoints will be reduced with the given amount
	 * 			|new.getHitPoints() == getHitPoints()-amount
	 */
	protected void lowerHitPoints(int amount) {
 		setHitPoints(getHitPoints()-amount);
 	}
	
	/**
	 * This variable contains the amount of hitpoints for this Game Object
	 */
	private int hitPoints;
	
	/**
	 * This variable returns whether this Game Object is dead or not
	 */
	@Basic
	public boolean isDead() {
		return isDead;
	}

	/**
	 * 
	 * @param 	isDead
	 * 			the new dead state for this Game Object
	 * @post 	the new dead state for this object will equal the given boolean
	 * 			|new.isDead() == isDead
	 */
	void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	
	/**
	 * This variable indicates whether this Game Object is dead or not
	 */
	private boolean isDead;

	/**
	 * Returns how long this Game Object is already dead
	 */
	protected double getTimeDead() {
		return timeDead;
	}

	/**
	 * 
	 * @param 	timeDead
	 * 			the time this Object is already dead
	 * @post 	the new timeDead of this Game Object will equal the given timeDead
	 * 			|new.getTimeDead() == timeDead
	 */
	protected void setTimeDead(double timeDead) {
		this.timeDead = timeDead;
	}
	/**
	 * This variable containts the time that this Game Object is dead
	 */
	private double timeDead;
	
	/**
	 * Returns how long this Game Object is already in the water
	 */
	protected double getInWaterTimer() {
		return inWaterTimer;
	}


	/**
	 * @param 	inWaterTime 
	 * 			the time how long this game object is under water
	 * @post 	the inWaterTimer will equal the given inWaterTime
	 * 			|new.getInWaterTimer() == inWaterTime 
	 */
	protected void setInWaterTimer(double inWaterTime) {
		this.inWaterTimer = inWaterTime;
	}
	
	/**
	 * This variable contains how long this game Object is already under water
	 */
	private double inWaterTimer;

	abstract int getMaxHitPoints();
	
	/**
	 * Returns how long this Game Object is already under water
	 */
	protected double getInMagmaTimer() {
		return inMagmaTimer;
	}


	/**
	 * @param 	inMagmaTime
	 * 			the time how long this game Object is already in magma
	 * @post	the inMagmaTimer will equal the given inMagmaTime
	 * 			|new.getInMagmaTimer() == inMagmaTime
	 */
	protected void setInMagmaTimer(double magmaTimer) {
		this.inMagmaTimer = magmaTimer;
	}
	
	/**
	 * This variable contains the value how long this Game Object is in Magma
	 */
	private double inMagmaTimer;

	/**
	 * Returns the current world where this game object is in
	 */
	@Override
	public World getWorld() {
		return world;
	}

	/**
	 * Sets a new world for this Game Object
	 * @param 	world
	 * 			the new world for this Game Object
	 * @post 	the the world of this game object will equal the given world if it is valid
	 * 			|if(isValidWorld(world)) then
	 *			|	new.getWorld() == world;
	 * @effect 	checks if the current position of this game object is valid in this world
	 * 			| detectLocationValidInWorld()
	 */
	protected void setWorld(World world) {
		if(isValidWorld(world))
			this.world = world;
		terminateIfUnValidLocationInWorld();
	}
	
	/**
	 * @effect Terminates the object if it is out of the bounderies of this world
	 * 			| if(isValidLocation((int)getLocationX(),(int)getLocationY()) ) then
	 *			|	setDead(true);
	 *			|		terminate();
	 */
	private void terminateIfUnValidLocationInWorld() {
		if(!isValidLocation((int)getLocationX(),(int)getLocationY()) ){
			setDead(true);
			terminate();
		}
	}
	
	/**
	 * 
	 * @param locationX
	 * 			the X coordinate that needs to be checked
	 * @param locationY
	 * 			the Y coordinate that needs to be checked
	 * @return	returns true if the given position is in the bounderies of this world, 
	 * 				else returns false
	 * 			|result ==  (locationX < 0 || locationX > getWorld().getWorldSizeInPixels()[0]-1 
	 *			|	|| locationY < 0 || locationY > getWorld().getWorldSizeInPixels()[1]-1)
	 * @return	if this Game Object has no world than false will always be returned
	 * 			|result == false
	 */
	protected boolean isValidLocation(int locationX, int locationY){
		if(hasAWorld()){
			return !(locationX < 0 || locationX > getWorld().getWorldSizeInPixels()[0]-1 
					|| locationY < 0 || locationY > getWorld().getWorldSizeInPixels()[1]-1);
		} else {
			return false;
		}
	}

	/**
	 * Checks wheter the given world if valid for this Game Object or not
	 * @param 	world
	 * 			the world that has to be checked
	 * @return	true if the world is valid else false
	 */
	protected abstract boolean isValidWorld(World world);

	/**
	 * The world of this Game Object will be removed
	 * @post	the world of this Game Object will equal null
	 * 			|new.getWorld() == null
	 */
	protected void removeWorld() {
		this.world = null;
	}
	
	/**
	 * Returns whether this Game Object is already in a world or not
	 * @return	returns true if the Game Object has a world, else false will be returned
	 * 			| result == (getWorld() != null);
	 */
	protected boolean hasAWorld(){
		return getWorld() != null;
	}
	
	/**
	 * This variable contains the world for this Game Object
	 */
	private World world;
	
	/**
	 * @return 	if the velocity needs to be set to zero
	 *			|result == onGameObject
	 */
	protected boolean isOnGameObject() {
		return onGameObject;
	}

	/**
	 * 
	 * @param value
	 * 			this boolean indicades if the velocityY needs to be set to zero because Game Object is on a game object or not
	 * @post 	onGameObject of this Game Object will equal the given value
	 * 			|new.isOnGameObject() == value
	 */
	protected void setIsOnGameObject(boolean value) {
		this.onGameObject = value;
	}
	
	/**
	 * This boolean indicades if this GameObject is on another Game Object
	 */
	private boolean onGameObject;	
	
	/**
	 * 
	 * @param dt
	 * @return	...
	 * 			| result == {getLocationX() + (getVelocityX()*dt + getActualAccelerationX()*dt*dt/2)*100, getLocationY() + (getVelocityY()*dt + getAccelerationY()*dt*dt/2)*100}
	 */
	protected double[] calculateLocation(double dt){
		double[] location = new double[2];
		location[0] = getLocationX() + (getVelocityX()*dt + getAccelerationX()*dt*dt/2)*100;
		location[1] = getLocationY() + (getVelocityY()*dt + getAccelerationY()*dt*dt/2)*100;
		return location;
	}
	
	/**
	 * 
	 * @param dt
	 * @param location
	 * @effect	...
	 * 			| if (hasCollisionX((int) location[0], (int) location[1]) then
	 * 			|	location[0] = getLocationX()
	 * @effect	...
	 * 			| if (hasCollisionY((int)location[0], (int)location[1])) then
	 * 			|	location[1] = getLocationY()
	 * 			|	location[0] = getLocationX() + (getVelocityX()*dt + getActualAccelerationX()*dt*dt/2)*100
	 * 			|	if (hasCollisionX((int)location[0], (int)location[1])) then
	 * 			|		location[0] = getLocationX()
	 */
	protected void calculateLocationCollisionTerrain(double dt,double[] location) {
		if(hasCollisionX((int)location[0],(int) location[1])){
			location[0] = getLocationX();
		}
		if(hasCollisionY((int)location[0],(int) location[1])){
			location[1] = getLocationY();
			location[0] = getLocationX() + (getVelocityX()*dt + getAccelerationX()*dt*dt/2)*100;
			if(hasCollisionX((int)location[0],(int) location[1])){
				location[0] = getLocationX();
			}
		}
	}
	
	/**
	 * 
	 * @param locationX
	 * @param locationY
	 * @return	...
	 * 			| result == (isValidLocation((int)locationX, (int)locationY)) && (isValidLocation((int)(getCurrentSprite().getWidth()-1+locationX), (int)(getCurrentSprite().getHeight()-1+locationY)))
	 */
	protected boolean locationIsValidInWorld(int locationX, int locationY){
		return ((isValidLocation((int)locationX, (int)locationY)) && (isValidLocation((int)(getCurrentSprite().getWidth()-1+locationX), (int)(getCurrentSprite().getHeight()-1+locationY))));
	}
	
	/**
	 * 
	 * @param location
	 * @post	...
	 * 			| new.getLocation()[0] == location[0] && new.getLocaion()[1] == location[1]
	 */
	protected void setLocation(double[] location){
		setLocationX(location[0]);
		setLocationY(location[1]);
	}

	/**
	 * 
	 * @param location
	 * @effect	...
	 * 			| if (getWorld().collisionSharks((int)location[0], (int)location[1], (int) location[0] + this.getCurrentSprite().getWidth(), (int) location[1] + this.getCurrentSprite().getHeight()).size() > 0) then
	 * 			|	if (!(getWorld().collisionSharks((int)getLocationX(), (int)location[1], (int) getLocationX() + this.getCurrentSprite().getWidth(), (int) location[1] + this.getCurrentSprite().getHeight()).size() > 0)) then
	 * 			|		location[0] = getLocationX()
	 * 			|	else then
	 * 			|		if (!(getWorld().collisionSharks((int)location[0], (int)getLocationY(), (int) location[0] + this.getCurrentSprite().getWidth(), (int) getLocationY() + this.getCurrentSprite().getHeight()).size() > 0)) then
	 * 			|			location[1] = getLocationY()
	 * 			|			setIsOnGameObject(true)
	 * 			|		else then
	 * 			|			location[0] = getLocationX()
	 * 			|			location[1] = getLocationY()
	 * 			|			setIsOnGameObject(true)
	 */
	private void calculateLocationCollisionShark(double[] location) {
		boolean hasCollisionShark = getWorld().collisionSharks((int)location[0], (int)location[1], (int) location[0] + this.getCurrentSprite().getWidth(), (int) location[1] + this.getCurrentSprite().getHeight()).size() > 0;
		if(hasCollisionShark){
			hasCollisionShark = getWorld().collisionSharks((int)getLocationX(), (int)location[1], (int) getLocationX() + this.getCurrentSprite().getWidth(), (int) location[1] + this.getCurrentSprite().getHeight()).size() > 0;
			if(!hasCollisionShark){
				location[0] = getLocationX();				
			} else {
				hasCollisionShark = getWorld().collisionSharks((int)location[0], (int)getLocationY(), (int) location[0] + this.getCurrentSprite().getWidth(), (int) getLocationY() + this.getCurrentSprite().getHeight()).size() > 0;
				if(!hasCollisionShark){
					location[1] = getLocationY();	
					setIsOnGameObject(true);
				} else {
					location[0] = getLocationX();
					location[1] = getLocationY();
					setIsOnGameObject(true);
				}
			}
		}
	}
	
	/**
	 * 
	 * @param location
	 * @effect	...
	 * 			| if (getWorld().collisionMazub((int)location[0], (int)location[1], (int) location[0] + this.getCurrentSprite().getWidth(), (int) location[1] + this.getCurrentSprite().getHeight())) then
	 * 			|	if (!(getWorld().collisionMazub((int)getLocationX(), (int)location[1], (int) getLocationX() + this.getCurrentSprite().getWidth(), (int) location[1] + this.getCurrentSprite().getHeight()))) then
	 * 			| 		location[0] = getLocationX()
	 * 			|	else then
	 * 			|		if (!(getWorld().collisionMazub((int)location[0], (int)getLocationY(), (int) location[0] + this.getCurrentSprite().getWidth(), (int) getLocationY() + this.getCurrentSprite().getHeight()))) then
	 * 			|			location[1] = getLocatinoY()
	 * 			|			setIsOnGameObject(true)
	 * 			|		else then
	 * 			|			location[0] = getLocationX()
	 * 			|			location[1] = getLocationY()
	 * 			|			setIsOnGameObject(true)
	 */
	private void calculateLocationCollisionMazub(double[] location) {
		boolean hasCollisionMazub = getWorld().collisionMazub((int)location[0], (int)location[1], (int) location[0] + this.getCurrentSprite().getWidth(), (int) location[1] + this.getCurrentSprite().getHeight());
		if(hasCollisionMazub){
			hasCollisionMazub = getWorld().collisionMazub((int)getLocationX(), (int)location[1], (int) getLocationX() + this.getCurrentSprite().getWidth(), (int) location[1] + this.getCurrentSprite().getHeight());
			if(!hasCollisionMazub){
				location[0] = getLocationX();				
			} else {
				hasCollisionMazub = getWorld().collisionMazub((int)location[0], (int)getLocationY(), (int) location[0] + this.getCurrentSprite().getWidth(), (int) getLocationY() + this.getCurrentSprite().getHeight());
				if(!hasCollisionMazub){
					location[1] = getLocationY();	
					setIsOnGameObject(true);
				} else {
					location[0] = getLocationX();
					location[1] = getLocationY();
					setIsOnGameObject(true);
				}
			}
		}
	}
	
	/**
	 * 
	 * @param location
	 * @effect	...
	 * 			| if (getWorld().collisionBuzam((int)location[0], (int)location[1], (int) location[0] + this.getCurrentSprite().getWidth(), (int) location[1] + this.getCurrentSprite().getHeight())) then
	 * 			|	if (!(getWorld().collisionBuzam((int)getLocationX(), (int)location[1], (int) getLocationX() + this.getCurrentSprite().getWidth(), (int) location[1] + this.getCurrentSprite().getHeight()))) then
	 * 			| 		location[0] = getLocationX()
	 * 			|	else then
	 * 			|		if (!(getWorld().collisionBuzam((int)location[0], (int)getLocationY(), (int) location[0] + this.getCurrentSprite().getWidth(), (int) getLocationY() + this.getCurrentSprite().getHeight()))) then
	 * 			|			location[1] = getLocatinoY()
	 * 			|			setIsOnGameObject(true)
	 * 			|		else then
	 * 			|			location[0] = getLocationX()
	 * 			|			location[1] = getLocationY()
	 * 			|			setIsOnGameObject(true)
	 */
	private void calculateLocationCollisionBuzam(double[] location) {
		boolean hasCollisionBuzam = getWorld().collisionBuzam((int)location[0], (int)location[1], (int) location[0] + this.getCurrentSprite().getWidth(), (int) location[1] + this.getCurrentSprite().getHeight());
		if(hasCollisionBuzam){
			hasCollisionBuzam = getWorld().collisionBuzam((int)getLocationX(), (int)location[1], (int) getLocationX() + this.getCurrentSprite().getWidth(), (int) location[1] + this.getCurrentSprite().getHeight());
			if(!hasCollisionBuzam){
				location[0] = getLocationX();				
			} else {
				hasCollisionBuzam = getWorld().collisionBuzam((int)location[0], (int)getLocationY(), (int) location[0] + this.getCurrentSprite().getWidth(), (int) getLocationY() + this.getCurrentSprite().getHeight());
				if(!hasCollisionBuzam){
					location[1] = getLocationY();	
					setIsOnGameObject(true);
				} else {
					location[0] = getLocationX();
					location[1] = getLocationY();
					setIsOnGameObject(true);
				}
			}
		}
	}	
	
	/**
	 * 
	 * @param location
	 * @effect	...
	 * 			| if (getWorld().collisionSlimes((int)location[0], (int)location[1], (int) location[0] + this.getCurrentSprite().getWidth(), (int) location[1] + this.getCurrentSprite().getHeight()).size() > 0) then
	 * 			|	if (!(getWorld().collisionSlimes((int)getLocationX(), (int)location[1], (int) getLocationX() + this.getCurrentSprite().getWidth(), (int) location[1] + this.getCurrentSprite().getHeight()).size() > 0)) then
	 * 			|		location[0] = getLocationX()
	 * 			|	else then
	 * 			|		if (!(getWorld().collisionSlimes((int)location[0], (int)getLocationY(), (int) location[0] + this.getCurrentSprite().getWidth(), (int) getLocationY() + this.getCurrentSprite().getHeight()).size() > 0)) then
	 * 			|			location[1] = getLocationY()
	 * 			|			setIsOnGameObject(true)
	 * 			|		else then
	 * 			|			location[0] = getLocationX()
	 * 			|			location[1] = getLocationY()
	 * 			|			setIsOnGameObject(true)
	 */
	private void calculateLocationCollisionSlime(double[] location) {
		boolean hasCollisionSlime = getWorld().collisionSlimes((int)location[0], (int)location[1], (int) location[0] + this.getCurrentSprite().getWidth(), (int) location[1] + this.getCurrentSprite().getHeight()).size() > 0;
		if(hasCollisionSlime){
			hasCollisionSlime = getWorld().collisionSlimes((int)getLocationX(), (int)location[1], (int) getLocationX() + this.getCurrentSprite().getWidth(), (int) location[1] + this.getCurrentSprite().getHeight()).size() > 0;
			if(!hasCollisionSlime){
				location[0] = getLocationX();				
			} else {
				hasCollisionSlime = getWorld().collisionSlimes((int)location[0], (int)getLocationY(), (int) location[0] + this.getCurrentSprite().getWidth(), (int) getLocationY() + this.getCurrentSprite().getHeight()).size() > 0;
				if(!hasCollisionSlime){
					location[1] = getLocationY();
					setIsOnGameObject(true);
				} else {
					location[0] = getLocationX();
					location[1] = getLocationY();
					setIsOnGameObject(true);
				}
			}
		}
	}
	
	/**
	 * 
	 * @param location
	 * @effect	...
	 * 			| if (!(this instanceof Slime)) then
	 * 			|	calculateLocationCollisionSlime(location)
	 * @effect	...
	 * 			| if (!(this instanceof Mazub)) then
	 * 			|	calculateLocationCollisionMazub(location)
	 * @effect	...
	 * 			| if (!(this instanceof Shark)) then
	 * 			|	calculateLocationCollisionShark(location)
	 */
	protected void calculateLocationCollisionObjects(double[] location){
		if (!(this instanceof Slime))
			calculateLocationCollisionSlime(location);
		if (this.getClass() != Mazub.class)
			calculateLocationCollisionMazub(location);
		if (!(this instanceof Buzam))
			calculateLocationCollisionBuzam(location);
		if (!(this instanceof Shark))
			calculateLocationCollisionShark(location);
	}
	
	/**
	 * 
	 * @param seconds
	 * @return		Returns the time needed for a one pixel movement
	 * 					returns seconds if a one pixel moment takes longer than the calculated
	 * 						time
	 * 				|if((getVelocityX() != 0) || (getAccelerationX() != 0)) then
	 * 				|	if((getVelocityY() != 0) || (getAccelerationY() != 0)) then
	 * 				|		if((0.01)/(Math.abs(getVelocityX())+Math.abs(getAccelerationX())*seconds) >= seconds && (0.01)/(Math.abs(getVelocityY())+Math.abs(getAccelerationY())*seconds) >= seconds) then
	 * 				|			result == seconds
	 * 				|		else if((0.01)/(Math.abs(getVelocityX())+Math.abs(getAccelerationX())*seconds) < (0.01)/(Math.abs(getVelocityY())+Math.abs(getAccelerationY())*seconds)) then
	 * 				|			result == Math.abs(getVelocityX())+Math.abs(getAccelerationX())*seconds));
	 *				|		else if((0.01)/(Math.abs(getVelocityX())+Math.abs(getAccelerationX())*seconds) > (0.01)/(Math.abs(getVelocityY())+Math.abs(getAccelerationY())*seconds)) then
	 *				|			result == (0.01)/(Math.abs(getVelocityY())+Math.abs(getAccelerationY())*seconds)
	 *				|	else if((0.01)/(Math.abs(getVelocityX())+Math.abs(getAccelerationX())*seconds) >= seconds) then
	 *				|		 result == seconds
	 *				|	else then
	 *				|		result == (0.01)/(Math.abs(getVelocityX())+Math.abs(getAccelerationX())*seconds));
	 *				|else if((getVelocityY() != 0) || (getAccelerationY() != 0)) then
	 *				|	if ((0.01)/(Math.abs(getVelocityY())+Math.abs(getAccelerationY())*seconds) > seconds) then
	 *				|		result == seconds
	 *				|	else then
	 *				|		result == (0.01)/(Math.abs(getVelocityY())+Math.abs(getAccelerationY())*seconds));
	 */
	public double timeOnePixelMovement(double seconds) {
		double dt1 = 0.2;
		double dt2 = 0.2;
		if((getVelocityX() != 0) || (getAccelerationX() != 0)){
			dt1 = (0.01)/(Math.abs(getVelocityX())+Math.abs(getAccelerationX())*seconds);
		}
		if((getVelocityY() != 0) || (getAccelerationY() != 0)){
			dt2 = (0.01)/(Math.abs(getVelocityY())+Math.abs(getAccelerationY())*seconds);
		}
		if((dt1 != 0.2) || (dt2 != 0.2)){
			if(Util.fuzzyGreaterThanOrEqualTo(dt1, seconds)&& Util.fuzzyGreaterThanOrEqualTo(dt2, seconds)){
				return seconds;
			} else if(dt1 < dt2){
				return dt1;
			} else if(dt2 <= dt1){
				return dt2;
			}
		}
		return seconds;
	}
	
	/**
	 * 
	 * @param seconds
	 * @effect	...
	 * 			| while (seconds > 0 && !isTerminated()) 
	 * 			|		advanceTimeCollisionDetect(timeOnePixelMovement(seconds))
	 * 			|		seconds -= advanceTime
	 */
	protected void updateLocationAndVelocity(double seconds) {
		while(seconds > 0 && !isTerminated()){
			double advanceTime = timeOnePixelMovement(seconds);
			advanceTimeCollisionDetect(advanceTime);
			seconds -= advanceTime;				
		}
	}
	
	/**
	 * @param dt
	 * @effect	...
	 * 			| if (isInMagma()) then
	 * 			|	if (Util.fuzzyGreaterThanOrEqualTo(getInMagmaTimer(), 0.2)) then
	 * 			|		lowerHitPoints(50)
	 * 			|		setInMagmaTimer(0)
	 * @effect	...
	 * 			| if (isInMagma()) then
	 * 			|	if (!Util.fuzzyGreaterThanOrEqualTo(getInMagmaTimer(), 0.2)) then
	 * 			|		setInMagmaTimer(getInMagmaTimer() + dt)
	 * @effect	...
	 * 			| if (!isInMagma()) then
	 * 			| 	setInMagmaTimer(0.2)
	 */
	protected void handleMagma(double dt) {
		if(isInMagma()){
			if(Util.fuzzyGreaterThanOrEqualTo(getInMagmaTimer(), 0.2)){
				lowerHitPoints(50);
				setInMagmaTimer(0);
			} else {
				setInMagmaTimer(getInMagmaTimer() + dt);
			}
		} else {
			setInMagmaTimer(0.2);
		}
	}
	
	/**
	 * 
	 * @param dt
	 * @effect	If the Game Object is in water and the water timer is set to -1, set the water timer to 0
	 * 			| if (isInWater()) then
	 * 			|	if(Util.fuzzyEquals(getInWaterTimer(), -1)) then
	 *			| 		setInWaterTimer(0)
	 * @effect 	If the Game Object is in water but the water timer is not set to -1, add dt to the water timer and if the water timer is greater to or equals 0.2, lower the water timer with 0.2 and lower the hitpoints with 2
	 * 			| if (isInWater()) then
	 * 			|	if( ! Util.fuzzyEquals(getInWaterTimer(), -1)) then
	 * 			|		setInWaterTimer(getInWaterTimer()+dt)
	 * 			|		if (Util.fuzzyGreaterThanOrEqualTo(getInWaterTimer(), 0.2)) then
	 * 			|			setInWaterTimer(getInWaterTimer() - 0.2)
	 * 			|			setHitPoints(getHitPoints() - 2)
	 * @effect	If the Game Object is not in water, set the water timer to -1
	 * 			| if (!isInWater()) then
	 * 			|	setInWaterTimer(-1)
	 */
	protected void handleWater(double dt){
		if(isInWater()){
			if(Util.fuzzyEquals(getInWaterTimer(), -1)){
				setInWaterTimer(0);
			} else {
				setInWaterTimer(getInWaterTimer()+dt);
				if(Util.fuzzyGreaterThanOrEqualTo(getInWaterTimer(), 0.2)){
					setInWaterTimer(getInWaterTimer()-0.2);
					setHitPoints(getHitPoints()-2);
				}
			}
		} else {
			setInWaterTimer(-1);
		}
	}
	
	/**
	 * Returns the program of this Game Object
	 */
	@Basic
	protected Program getProgram() {
		return this.program;
	}
	
	/**
	 * Returns whether this Game Object has a program or not
	 */
	protected boolean hasAProgram() {
		return (getProgram() != null);
	}
	
	/**
	 * 
	 * @param 	direction
	 * 			the direction in which this Mazub needs to start moving
	 * @pre		The given direction needs to be RIGHT or LEFT
	 * 			|direction == Motion.RIGHT || direction == Motion.LEFT
	 * @pre		If the direction is RIGHT, the rightkey can not be pressed already
	 * 			|if (direction == Motion.RIGHT) then
	 * 			|	!isRightKeyPressed()
	 * @pre		If the direction is LEFT, the leftkey can not be pressed already
	 * 			|if (direction == Motion.LEFT) then
	 * 			|	!isLeftKeyPressed() 
	 * @effect 	if the given direction is RIGHT, the horizontal velocity will be set to the 
	 * 				initial horizontal velocity
	 * 				and the acceleration will be equal to the initial horizontal acceleration
	 * 			|if (direction == Motion.RIGHT) then
	 * 			|	setVelocityX(getInitialHorizontalVelocity())
				|	setAccelerationX(getInitialHorizontalAcceleration())
	 * @effect 	if the given direction is LEFT, the horizontal velocity will be set to 
	 * 				the negative of the initial horizontal velocity
	 * 				and the acceleration will be set to the initial horizontal acceleration
	 * 			|if (direction == Motion.LEFT) then
	 * 			|	setVelocityX(getInitialHorizontalVelocity()*(-1))
	 * 			|	setAccelerationX(getInitialHorizontalAcceleration())				
	 */
	public void startMove(Motion direction) {
		assert(direction == Motion.RIGHT || direction == Motion.LEFT);
		if (direction == Motion.RIGHT) {
			assert(!isRightKeyPressed());
			setRightKeyPressed(true);
			setVelocityX(getInitialHorizontalVelocity());
			setAccelerationX(getInitialHorizontalAcceleration());
		} else if (direction == Motion.LEFT){
			assert(!isLeftKeyPressed());
			setLeftKeyPressed(true);
			setVelocityX(getInitialHorizontalVelocity()*-1);
			setAccelerationX(getInitialHorizontalAcceleration()*-1);
		}
		setMovement(direction);
	}
	
	/**
	 * Stops this Game Object's horizontal movement and stores the direction of its last movement
	 * @param 	direction
	 * 			the direction in which mazub needs to be stop moving
	 * @pre		if the given direction is needs to be Motion.LEFT or Motion.RIGHT
	 * 			|((direction == Motion.RIGHT) || (direction == Motion.LEFT))
	 * @pre		if the stop direction equals RIGHT, the right key needs to be pressed
	 * 			|if ( direction == Motion.RIGHT ) then
	 * 			|	isRightKeyPressed()
	 * @pre		if the stop direction equals LEFT, the left key needs to be pressed
	 * 			|if ( direction == Motion.LEFT ) then
	 * 			|	isLeftKeyPressed() 
	 * @effect	if the last movement was to the right and this Mazub is not ducking, 
	 * 				then the lastMoveDirection is set to Motion.RIGHT
	 * 			| if isMovingRight() then
	 * 			|	if not isDucking() then
	 * 			|		setLastMoveDirection(Motion.RIGHT)
	 * @effect	if the last movement was to the right, and this Mazub is ducking, 
	 * 				then the lastMoveDirection is set to Motion.RIGHT_AND_DUCKING
	 * 			| if isMovingRight() then
	 * 			|	if isDucking() then
	 * 			|		setLastMoveDirection(Motion.RIGHT_AND_DUCKING)
	 * @effect	if the last movement was to the left, and this Mazub is not ducking, 
	 * 				then the lastMoveDirection equals Motion.LEFT
	 * 			| if isMovingLeft() then
	 * 			|	if not isDucking() then
	 * 			|		setLastMoveDirection(Motion.LEFT)
	 * @effect	if the last movement was to the left, and this Mazub is ducking, 
	 * 				then the lastMoveDirection is set to Motion.LEFT_AND_DUCKING
	 * 			| if isMovingLeft() then
	 * 			|	if isDucking() then
	 * 			|		setLastMoveDirection(Motion.LEFT_AND_DUCKING)
	 * @post 	if Mazub stops moving left and is moving to the left at this moment, 
	 * 				the velocityX equals zero, accelerationX equals zero 
	 * 				and the lastMoveTimer equals to the current value of timer
	 * 			| new.getLastMoveTimer() == getTimer() && new.getVelocityX() == 0 &&
	 * 			| 	new.getAccelerationX() == 0
	 * @post 	if Mazub stops moving right and is moving to the right at this moment, 
	 * 				the velocityX equals zero, accelerationX equals zero 
	 * 				and the lastMoveTimer equals to the current value of timer
	 * 			| new.getLastMoveTimer() == getTimer() && new.getVelocityX() == 0 &&
	 * 			| 	new.getAccelerationX() == 0
	 */
	public void endMove(Motion direction){
		assert ((direction == Motion.RIGHT) || (direction == Motion.LEFT));
		if ( direction == Motion.RIGHT ){
			assert(isRightKeyPressed());
			setRightKeyPressed(false);
			if (isMovingRight()){
				if (isDucking())
					this.setLastMoveDirection(Motion.RIGHT_AND_DUCKING);
				else
					this.setLastMoveDirection(Motion.RIGHT);	
				setVelocityX(0);
				setAccelerationX(0);
				this.setLastMoveTimer(getSpriteTimer());
				setMovement(null);
				if(isLeftKeyPressed()){
					setVelocityX(getInitialHorizontalVelocity()*-1);
					setAccelerationX(getInitialHorizontalAcceleration()*-1);
					setMovement(Motion.LEFT);
				}
			}
		}
		if ( direction == Motion.LEFT ){
			assert(isLeftKeyPressed());
			setLeftKeyPressed(false);
			if (isMovingLeft()){
				if (isDucking())
					this.setLastMoveDirection(Motion.LEFT_AND_DUCKING);
				else
					this.setLastMoveDirection(Motion.LEFT);
				setVelocityX(0);
				setAccelerationX(0);
				this.setLastMoveTimer(getSpriteTimer());
				setMovement(null);
				if(isRightKeyPressed()){
					setVelocityX(getInitialHorizontalVelocity());
					setAccelerationX(getInitialHorizontalAcceleration());
					setMovement(Motion.RIGHT);
				}
			}
		}
	}
	
	/**
	 * Return lastMoveDirection of this Game Object
	 * 		lastMoveDirection indicates the direction of the last move of this Mazub
	 */
	@Basic
	protected Motion getLastMoveDirection() {
		return lastMoveDirection;
	}
	
	/**
	 * Return the value of timer
	 */
	@Basic
	protected double getLastMoveTimer() {
		return lastMoveTimer;
	}

	/**
	 * 
	 * @param 	time
	 * 			the new time for this Game Object's lastMoveTimer
	 * @pre		time is greater or equal to zero
	 * @post	lastMoveTimer of this Game Object will be equal to the given time
	 * 			| new.getLastMoveTimer() = time
	 * 			
	 */
	private void setLastMoveTimer(double time) {
		assert(time >= 0);
		this.lastMoveTimer = time;
	}
	
	/**
	 * This variable contains the time since when this Game Object did his last move
	 */
	private double lastMoveTimer;

	/**
	 * @param 	lastMoveDirection
	 * 			the new direction in which this Mazub has last moved
	 * @pre		lastMoveDirection equals Motion.RIGHT or Motion.LEFT or
	 * 				Motion.RIGHT_AND_DUCKING or Motion.LEFT_AND_DUCKING
	 * 			| lastMoveDirection == Motion.RIGHT || lastMoveDirection == Motion.LEFT ||
	 * 			| 	lastMoveDirection == Motion.RIGHT_AND_DUCKING || lastMoveDirection == Motion.LEFT_AND_DUCKING
	 * @post 	the new lastMoveDirection of this Mazub will equal to lastMoveDirection
	 * 			| new.getLastMoveDirection() = lastMoveDirection
	 */
	private void setLastMoveDirection(Motion lastMoveDirection) {
		assert((lastMoveDirection == Motion.RIGHT) || (lastMoveDirection == Motion.LEFT) 
				|| (lastMoveDirection == Motion.RIGHT_AND_DUCKING) 
				|| (lastMoveDirection == Motion.LEFT_AND_DUCKING) );
		this.lastMoveDirection = lastMoveDirection;
	}
	
	/**
	 * This variable contains the last direction in which this Mazub has moved
	 */
	private Motion lastMoveDirection;
	
	/**
	 * Returns the current value of the timer.
	 */
	@Basic
	protected double getSpriteTimer() {
		return spriteTimer;
	}

	/**
	 * Sets the timer at the given value.
	 * @param 	time
	 * 		  	The new time for this timer.
	 * @post	The timer of this Mazub is equal to the given time.
	 * 			| new.getTimer() == time
	 */
	protected void setSpriteTimer(double time) {
		this.spriteTimer = time;
	}
	
	/**
	 * Adds the timer with a given value.
	 * @param 	time
	 * 		  	the time that needs to be added to the timer
	 * @effect	if the given time equals Double.NEGATIVE_INFINITY or 
	 * 				Double.POSITIVE_INFINITY or Double.NaN
	 * 				nothing will happen
	 * 			| setSpriteTimer(getSpriteTimer())
	 * @effect	if the current value of the timer plus time is greater than Double.MAX_VALUE
	 * 				and the given time is greater than zero
	 * 				and the given time does not equal Double.NEGATIVE_INFINITY or 
	 * 				Double.POSITIVE_INFINITY or Double.NaN
	 * 				than the new timer will be set to Double.MAX_VALUE
	 * 			| setSpriteTimer(Double.MAX_VALUE)
	 * @effect	if the current value of the timer plus time is less than Double.MAX_VALUE
	 * 				and the given time is positive 
	 * 				and the given time does not equal Double.NEGATIVE_INFINITY or 
	 * 				Double.POSITIVE_INFINITY or Double.NaN
	 * 				than the new timer will be set to 
	 * 				the current value of the timer plus the given time
	 * 			| setSpriteTimer(getSpriteTimer()+time)
	 * @effect	if the given time is negative and the sum of Double.MAX_VALUE
	 * 				and the given time is less than the current timer value
	 * 				and the given time does not equal Double.NEGATIVE_INFINITY or 
	 * 				Double.POSITIVE_INFINITY or Double.NaN
	 *				than the new timer will be set to Double.MAX_VALUE
	 * 			| setSpriteTimer(Double.MAX_VALUE)
	 * @effect	if the given time is positive and the sum of Double.MAX_VALUE
	 * 				and the given time is greater than the current timer value
	 * 				and the given time does not equal Double.NEGATIVE_INFINITY or 
	 * 				Double.POSITIVE_INFINITY or Double.NaN
	 *				than the new timer will be set to current timer value
	 *				minus the given time
	 * 			| setSpriteTimer(getSpriteTimer()-time)
	 */
	protected void addToTimer(double time) {
		if(time == Double.NEGATIVE_INFINITY || time == Double.POSITIVE_INFINITY || time == Double.NaN){
			return;
		}else if(time > 0 && getSpriteTimer() > (Double.MAX_VALUE - time)){
			setSpriteTimer(Double.MAX_VALUE);
		} else if(time < 0 && getSpriteTimer() > (Double.MAX_VALUE + time)){
			setSpriteTimer(Double.MAX_VALUE);
		} else if (time < 0){
			setSpriteTimer(getSpriteTimer()-time);
		} else {
			setSpriteTimer(getSpriteTimer()+time);
		}
	}
	
	/**
	 * This variable contains the current time for this Mazub.
	 */
	private double spriteTimer;
	
	/**
	 * Returns whether this game Object is ducking or not
	 */
	protected abstract boolean isDucking();

	/**
	 * Returns whether this Game Object is moving right or not.
	 * @return 	true when the horizontal velocity is greater than zero, false otherwise.
	 * 			| if getVelocityX() > 0 then
	 * 			|	return true
	 * 			| else then
	 * 			| 	return false
	 */
	protected boolean isMovingRight() {
		if (this.getVelocityX() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns whether this Game Object is moving left or not.
	 * @return 	true when the horizontal velocity is less than zero, false otherwise.
	 * 			| if getVelocityX() < 0 then
	 * 			|	return true
	 * 			| else then
	 * 			| 	return false
	 */
	protected boolean isMovingLeft() {
		if (this.getVelocityX() < 0) {
			return true;
		} else {
			return false;
		}
	}	
	
	/**
	 * Return accelerationX of this Game Object
	 * 	accelerationX expresses the current horizontal acceleration of this Mazub
	 */
	@Basic
	public double getAccelerationX() {
		return accelerationX;
	}

	/**
	 * 
	 * @param 	accelerationX
	 * 			the new accelerationX for this Game Object
	 * @post 	if the given accelerationX does not equal Double.NaN
	 * 				the accelerationX of this Game Object will equal the given accelerationX
	 * 			|if (accelerationX >= 0)
	 * 			|	new.getAcceleration() == accelerationX
	 * @post 	if the given accelerationX equals Double.NaN than
	 * 				the accelerationX of this Game Object will equal zero
	 * 			|if (accelerationX == Double.NaN) then
	 *			|		accelerationX = 0
	 */
	protected void setAccelerationX(double accelerationX) {
		if (Double.isNaN(accelerationX))
				accelerationX = 0;
		this.accelerationX = accelerationX;
	}
	
	/**
	 * this variable contains the current horizontal acceleration for this Game Object
	 */	
	private double accelerationX;

	/**
	 * The initial horizontal acceleration for this object
	 */
	protected abstract double getInitialHorizontalAcceleration();

	/**
	 * The initial horizontal velocity for this object
	 */
	protected abstract double getInitialHorizontalVelocity();

	/**
	 * Returns whether the right key is pressed or not
	 */
	protected boolean isRightKeyPressed() {
		return isRightKeyPressed;
	}

	/**
	 * @param isRightKeyPressed
	 * 			the current pressed status of the right key
	 * @post 	isRightKeyPressed will equal the given value
	 * 			|new.isRightKeyPressed() == isRightKeyPressed
	 */
	protected void setRightKeyPressed(boolean isRightKeyPressed) {
		this.isRightKeyPressed = isRightKeyPressed;
	}

	/**
	 * This variable contains the current pressed status of the right key
	 */
	private boolean isRightKeyPressed;

	/**
	 * Returns whether the left key is pressed or not
	 */
	protected boolean isLeftKeyPressed() {
		return isLeftKeyPressed;
	}

	/**
	 * @param isLeftKeyPressed
	 * 			the current pressed status of the left key
	 * @post 	isLeftKeyPressed will equal the given value
	 * 			|new.isLeftKeyPressed() == isLeftKeyPressed
	 */
	protected void setLeftKeyPressed(boolean isLeftKeyPressed) {
		this.isLeftKeyPressed = isLeftKeyPressed;
	}

	/**
	 * This variable contains the current pressed status of the left key
	 */
	private boolean isLeftKeyPressed;
	
	/**
	 * Return velocityX of this Mazub
	 * 	velocityX expresses the current horizontal velocity of this Mazub
	 */
	@Basic 
	public double getVelocityX() {
		return this.velocityX;
	}
	
	/**
	 * Set velocityX of this Game Object to a given value
	 * 
	 * @param 	velocityX
	 * 			The new velocityX for this Mazub
	 * @pre		the given velocity needs to be valid
	 * 			| isValidVelocityX(velocityX)
	 * @post 	The velocityX of this Mazub is equal to the given velocityX
	 * 			| new.getvelocityX() == velocityX
	 */
	protected void setVelocityX(double velocityX) {
		assert(isValidVelocityX(velocityX));
		this.velocityX = velocityX;
	}
	
	/**
	 * 
	 * @param velocityX
	 * @return	the given velocity needs te be between the maximum horizontal velocity and the initial horizontal velocity, 
	 * 				or needs to be zero,
	 * 				or needs to be between minus the maximum horizontal velocity and minus the initial horizontal velocity
	 * 			| result == ((velocityX <= getMaximumHorizontalVelocity()) && (velocityX >= getInitialHorizontalVelocity())) 
	 *			|		|| (velocityX == 0) || ((velocityX >= -getMaximumHorizontalVelocity()) 
	 *			|		&& (velocityX <= -getInitialHorizontalVelocity()))
	 */
	protected boolean isValidVelocityX(double velocityX) {
		return (velocityX != Double.NaN && (velocityX <= getMaximumHorizontalVelocity()) && (velocityX >= getInitialHorizontalVelocity())) 
		|| (velocityX == 0) || ((velocityX >= -getMaximumHorizontalVelocity()) 
				&& (velocityX <= -getInitialHorizontalVelocity()));
	}
	
	/**
	 * The maximum horizontal velocity for this object
	 */
	protected abstract double getMaximumHorizontalVelocity();

	/**
	 * This variable contains the current horizontal velocity of this Mazub
	 */
	private double velocityX;
	
	/**
	 * 
	 * @return 	...
	 * 			|result == this.movement
	 */
	protected Motion getMovement() {
		return movement;
	}

	/**
	 * 
	 * @param 	movement
	 * 			...
	 * @post 	...
	 * 			| new.getMovement() = movement
	 */
	@Basic
	protected void setMovement(Motion movement) {
		this.movement = movement;
	} 
	
	/**
	 * This variable contains the current movement for this gameObject
	 */
	private Motion movement;
	
	/**
	 * Returns if this Game Object is on the right side of another Game Object
	 * @param 	object
	 * 			the other game Object
	 * @return	returns true is the horizontal location of this Game object is smaller 
	 * 				than the horizontal Location plus the width of the other game Object
	 * 			| result == this.getLocationX() < (object.getLocationX() + object.getCurrentSprite().getWidth())
	 */
	public boolean isLeftOff(GameObject object){
		return this.getLocationX() < (object.getLocationX() + object.getCurrentSprite().getWidth());
	}
	
	/**
	 * Returns if this Game Object is on the right side of another Game Object
	 * @param 	object
	 * 			the other game Object
	 * @return	returns whether the other object is on the leftside of this object or not
	 * 			| result == object.isLeftOff(this)
	 */
	public boolean isRightOff(GameObject object){
		return object.isLeftOff(this);
	}
	
	
	/**
	 * This variable holds the program of this Game Object
	 */
	private final Program program;
	

	protected abstract void advanceTimeCollisionDetect(double advanceTime);

	/**
	 * @return The vertical acceleration for this game object
	 */
	protected abstract double getAccelerationY();

	/**
	 * @return The vertical velocity for this game object
	 */
	public abstract double getVelocityY();

	public boolean isAbove(GameObject otherObject) {
		return (int)this.getLocationY() > (int)otherObject.getLocationY();
	}

	public boolean isUnder(GameObject otherObject) {
		return (int)this.getLocationY() < (int)otherObject.getLocationY();
	}
}
