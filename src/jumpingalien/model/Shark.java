package jumpingalien.model;

import java.util.Collection;

import be.kuleuven.cs.som.annotate.*;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

/**
 * 
 * @invar	...
 * 			| getWorld() != null
 */
public class Shark extends GameObject {
	/**
	 * @param x
	 * @param y
	 * @param sprites
	 * @effect	call the constructor of the superclass
	 * 			| super(x, y, sprites)
	 * @effect	...
	 * 			| setHitPoints(100)
	 * @effect	...
	 * 			| setMagmaTimer(0.2)
	 * @effect	...
	 * 			| setMovementCounter(4)
	 */
	public Shark (int x, int y, Sprite[] sprites) throws IllegalArgumentException {
		super(x, y, sprites);
		setHitPoints(100);
		setMovementCounter(4);
		setInMagmaTimer(0.2);
	}
	
	/** 
	 * @param x
	 * @param y
	 * @param sprites
	 * @param program
	 * @effect	call the constructor of the superclass
	 * 			| super(x, y, sprites, program)
	 * @effect	...
	 * 			| setHitPoints(100)
	 * @effect	...
	 * 			| setMagmaTimer(0.2)
	 * @effect	...
	 * 			| setMovementCounter(4)
	 */
	public Shark (int x, int y, Sprite[] sprites, Program program) throws IllegalArgumentException {
		super(x, y, sprites, program);
		setHitPoints(100);
		if(!hasAProgram()){
			setMovementCounter(4);
		}
		setInMagmaTimer(0.2);
	}
	
	/**
	 * @return 	...
	 * 			|if(getVelocityX() > 0) then
	 *			|	result == getSprites()[1]
	 * @return 	...
	 * 			|if !(getVelocityX() > 0) then
	 * 			|	result == getSprites()[0]
	 */
	@Basic @Override
	public Sprite getCurrentSprite() {
		if(getVelocityX() > 0)
			return getSprites()[1];
		else
			return getSprites()[0];
	}
	
	/**
	 * @return 	...
	 * 			| result == 2
	 */
	@Override
	int getRequiredLengthSpriteArray() {
		return 2;
	}
	
	/**
	 * 
	 * @return	...
	 * 			| result == this.velocityX
	 */
	@Override
	public double getVelocityY() {
		return velocityY;
	}

	/**
	 * 
	 * @param velocityY
	 * @post 	...
	 * 			| new.getVelocityY() == velocityY
	 */
	private void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}
	
	/**
	 * This variable contains the vertical velocity for this shark
	 */
	private double velocityY;
	
	
	/**
	 * 
	 * @return	...
	 * 			| result == 1.5
	 */
	protected double getInitialHorizontalAcceleration(){
		return 1.5;
	}

	/**
	 * @return	...
	 * 			| result == this.accelerationY;
	 */
	@Override
	protected double getAccelerationY() {
		return accelerationY;
	}

	/**
	 * 
	 * @param accelerationY
	 * 
	 * @post	...
	 * 			|new.getAccelerationY == accelerationY
	 */
	private void setAccelerationY(double accelerationY) {
		this.accelerationY = accelerationY;
	}
	
	/**
	 * This variable contains the vertical acceleration of this shark
	 */
	private double accelerationY;
	
	/**
	 * 
	 * @param dt
	 * @throws IllegalArgumentException
	 * 			...
	 * 			|(dt < 0 || dt >= 0.2)
	 * @effect ...
	 * 			|if(!isDead() && !isInWater()) then
	 * 			|	setNotInWaterTimer(getNotInWaterTimer() + dt)
	 * @effect	...
	 * 			|if(!isDead() && isInWater()) then
	 * 			|	setNotInWaterTimer(0)
	 * @effect	...
	 * 			|if(!isDead()) then
	 * 			|	updateLocationAndVelocity(dt)
	 * @effect 	...
	 * 			|if(!isDead() && getNotInWaterTimer() >= 0.2) then
	 * 			|	setNotInWaterTimer(getNotInWaterTimer() - 0.2)
	 * 			|	setHitPoints(getHitPoints() - 1)
	 * @effect	...
	 * 			|if(isDead()) then
	 * 			|	setTimeDead(getTimeDead() + dt)
	 * @effect	...
	 * 			|if(isDead() && getTimeDead() >= 0.6) then
	 * 			|	terminate() 	
	 */
	void advanceTime(double dt) throws IllegalArgumentException {
		if (dt < 0 || Util.fuzzyGreaterThanOrEqualTo(dt, 0.2)){
			throw new IllegalArgumentException();
		}
		if(!isDead()){
			if(!isInWater()){
				setNotInWaterTimer(getNotInWaterTimer() + dt);
			} else {
				setNotInWaterTimer(0);
			}
			if(hasAProgram()){
				double amountStatements = dt/0.001;
				amountStatements = Math.ceil(amountStatements);
				getProgram().run((int)amountStatements);
			}
			updateLocationAndVelocity(dt);
			if(getNotInWaterTimer() >= 0.2){
				setNotInWaterTimer(getNotInWaterTimer() - 0.2);
				setHitPoints(getHitPoints() - 1);
			}
		} else {
			setTimeDead(getTimeDead() + dt);
			if(Util.fuzzyGreaterThanOrEqualTo(getTimeDead(), 0.6)){
				terminate();
			}
		}
	}

	/**
	 * @effect 	...
	 * 			|updateLocation(dt);
	 * @effect 	...
	 * 			|updateVelocity(dt);
	 * @effect 	...
	 * 			|handleCollisionMazub();
	 * @effect 	...
	 * 			|handleCollisionSlime();
	 * @effect	...
	 * 			|handleMagma(dt);
	 * @effect 	....
	 * 			|setMovementTime(getMovementTime()-dt);
	 * @effect 	...
	 * 			|if(!isInWater()) then
	 * 			| 	setAccelerationY(-10)
	 * @effect 	...
	 * 			|if(isBottomPerimeterInSolidGround() && !isInWater()) then
	 * 			|	setAccelerationY(0)
	 * 			|	setVelocityY(0)
	 * 			|	setVelocityX(0)
	 * 			|	setMovement(null)
	 * @effect	...
	 * 			|if(getMovementTime() <= 0) then
	 * 			|	newMovement()
	 * @effect 	....
	 * 			|if(!isInWater())then
	 * 			| 	setAccelerationY(-10)
	 * @effect 	...
	 * 			|if(isTopPerimeterInWater() && getAccelerationY()<-9) then
	 * 			| 	setAccelerationY(0);
	 * 			| 	setVelocityY(0)
	 */
	@Override
	protected void advanceTimeCollisionDetect(double dt){
		if(!isInWater()){
			setAccelerationY(-10);
		}
		if(!hasAProgram()){
			setMovementTime(getMovementTime()-dt);
		}
		if(isBottomPerimeterInSolidGround() && !isInWater()){
			setAccelerationY(0);
			setVelocityY(0);
			setVelocityX(0);
			setMovement(null);
		}
		if(!hasAProgram()){
			if(getMovementTime() <= 0){
				newMovement();
			}
		}
		if(!isInWater()){
			setAccelerationY(-10);
		}
		if(isTopPerimeterInWater() && getAccelerationY()<-9){
			setAccelerationY(0);
			setVelocityY(0);
		}
		updateLocation(dt);	
		updateVelocity(dt);
		handleCollisionMazub();
		handleCollisionSlime();
		handleMagma(dt);
	}
	
	/**
	 * 
	 * @param dt
	 * @effect 	 ...
	 * 			|setIsOnGameObject(false)
	 * @effect	...
	 * 			| double[] location = calculateLocation(seconds)
	 * 			| if (locationIsValidInWorld((int) location[0], (int) location[1]) then
	 * 			|	calculateLocationCollisionTerrain(seconds, location)
	 * 			|	calculateLocationCollisionObjects(location)
	 * 			| 	calculateLocationCollisionShark(location)
	 * 			|	setLocation(location)
	 */
	private void updateLocation(double dt){
		setIsOnGameObject(false);
		double[] location = calculateLocation(dt);
		if(locationIsValidInWorld((int)location[0], (int)location[1])){
			calculateLocationCollisionTerrain(dt, location);
			calculateLocationCollisionObjects(location);
			calculateLocationCollisionShark(location);
			setLocation(location);			
		}
	}

	/**
	 * @param location
	 * @effect	...
	 * 			| if(hasCollisionShark(location[0], location[1], location[0] + this.getCurrentSprite().getWidth(), location[1] + this.getCurrentSprite().getHeight())) then
	 *			|	if(!hasCollisionShark(getLocationX(), location[1], getLocationX() + this.getCurrentSprite().getWidth(), location[1] + this.getCurrentSprite().getHeight())) then
	 *			|		location[0] = getLocationX()
	 *			|	else then
	 *			|		if(!hasCollisionShark(location[0], getLocationY(), location[0] + this.getCurrentSprite().getWidth(), getLocationY() + this.getCurrentSprite().getHeight())) then
	 *			|			location[1] = getLocationY();	
	 *			|			setIsOnGameObject(true);
	 *			|		else then
	 *			|			location[0] = getLocationX();
	 *			|			location[1] = getLocationY();
	 *			|			setIsOnGameObject(true);
	 */
	protected void calculateLocationCollisionShark(double[] location) {
		if(hasCollisionShark((int)location[0], (int)location[1], (int) location[0] + this.getCurrentSprite().getWidth(),
				(int) location[1] + this.getCurrentSprite().getHeight())){
			if(!hasCollisionShark((int)getLocationX(), (int)location[1], (int) getLocationX() + this.getCurrentSprite().getWidth(),
					(int) location[1] + this.getCurrentSprite().getHeight())){
				location[0] = getLocationX();				
			} else {
				if(!hasCollisionShark((int)location[0], (int)getLocationY(), (int) location[0] + this.getCurrentSprite().getWidth(), 
						(int) getLocationY() + this.getCurrentSprite().getHeight())){
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
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 * @return 	...
	 * 			| result == getWorld().collisionSharks(startX, startY, endX, endY, this).size() > 0
	 */
	private boolean hasCollisionShark(int startX, int startY, int endX, int endY){
		return getWorld().collisionSharks(startX, startY, endX, endY, this).size() > 0;
	}

	
	/**
	 * 
	 * @param dt
	 * @effect 	...
	 * 			|setVelocityX(getVelocityX() + getActualAccelerationX()*dt);
	 * @effect 	...
	 * 			|setVelocityY(getVelocityY() + getAccelerationY()*dt)
	 * @effect 	...
	 * 			|if(isOnGameObject()) then
	 *				setVelocityY(0);
	 * 
	 */
	private void updateVelocity(double dt){
		if (getVelocityX() + getAccelerationX()*dt < -getMaximumHorizontalVelocity()) {
			setVelocityX(-getMaximumHorizontalVelocity());
		} else if (getVelocityX() + getAccelerationX()*dt > getMaximumHorizontalVelocity()) {
			setVelocityX(getMaximumHorizontalVelocity());
		} else {
			setVelocityX(getVelocityX() + getAccelerationX()*dt);
		}
		setVelocityY(getVelocityY() + getAccelerationY()*dt);
		if(isOnGameObject())
			setVelocityY(0);
	}
	
	
	/**
	 * @effect 	...
	 * 			|setVelocityX(0)
	 * @effect 	...
	 * 			|setMovement(null)
	 * @effect 	...
	 * 			|if(getAccelerationY() > 0) then
	 * 			| 	setAccelerationY(0)
	 * @effect 	...
	 * 			|if(isInWater()) then
	 * 			|	setVelocityY(0)
	 * @effect 	...
	 * 			|if((getMovementCounter() == 4)) then
	 * 			|	if(isBottomPerimeterInWater() || isBottomPerimeterInSolidGround()) then
	 * 			| 		if(2 == (int)(Math.random()*2)) then
	 * 			|			setVelocityY(2)
	 * 			|			setMovementCounter(-1)
	 * 			|			setJumping(true)
	 * @effect 	...
	 * 			|setJumping(false)
	 * @effect 	...
	 * 			|if(isInWater() || isJumping()) then
	 * 			|	switch((int)(Math.random()*2)){
	 * 			|		case 0: setMovement(Direction.RIGHT)
	 * 			|				break
	 * 			|		case 1: setMovement(Direction.LEFT)
	 * 			|				break
	 * 			|	}
	 * @effect 	...
	 * 			|if(!isJumping() && isInWater()) then
	 * 			|	switch((int)(Math.random()*2)){
	 * 			|		case 0: setAccelerationY(0)
	 * 			|				break
	 * 			|		case 1: setAccelerationY(0.1)
	 * 			|				break
	 * 			|		case 2: setAccelerationY(0.2)
	 * 			|				break
	 * 			|	}
	 * 			|	if(1 == (int)(Math.random()*1)) then
	 * 			|		setAccelerationY(getAccelerationY()*-1)	
	 * @effect 	...
	 * 			|setMovementTime(1 + (int)(Math.random()*4))
	 * @effect 	...
	 * 			|addToMovementCounter(1)
	 */
	private void newMovement(){
		setJumping(false);
		setVelocityX(0);
		setMovement(null);
		//Todo double vergelijking
		if(getAccelerationY() > 0)
			setAccelerationY(0);
		if(isInWater())
			setVelocityY(0);
		int random;
		if((getMovementCounter() == 4)){
			if(isBottomPerimeterInWater() || isBottomPerimeterInSolidGround()){
				random = (int)(Math.random()*2);
				if(random == 1){
					setVelocityY(2);
					setJumping(true);
					setMovementCounter(-1);
				}
			}
		}
		if(isInWater() || isJumping()){
			random = (int)(Math.random()*2);
			switch (random){
				case 0: setMovement(Motion.RIGHT);
						setAccelerationX(getInitialHorizontalAcceleration());
							break;
				case 1: setMovement(Motion.LEFT);
						setAccelerationX(getInitialHorizontalAcceleration()*-1);
							break;
			}
		}
		if(!isJumping() && isInWater()){
			random = (int)(Math.random()*3);
			switch (random){
				case 0: setAccelerationY(0);
							break;
				case 1: setAccelerationY(0.1);
							break;
				case 2: setAccelerationY(0.2);
							break;
			}
			if(random != 0){
				random = (int)(Math.random()*2);
				if(random == 1){
					setAccelerationY(getAccelerationY()*-1);
				}			
			}
		}
		
		double time = 1 + (int)(Math.random()*4);
		setMovementTime(time);
		addToMovementCounter(1);		
	}

	/**
	 * @return 	...
	 * 			|result == isJumping
	 */
	private boolean isJumping() {
		return isJumping;
	}

	/**
	 * @param 	isJumping
	 * @post 	...
	 * 			|new.isJumping() == isJumping
	 */
	private void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}
	
	/**
	 * This variable indicates if this shark is jumping or not
	 */
	private boolean isJumping;

	/**
	 * 
	 * @return	...
	 * 			| result == this.movementTimer
	 */
	private double getMovementTime() {
		return movementTime;
	}

	/**
	 * 
	 * @param time
	 * @post	...
	 * 			| new.getMovementTimer() == time
	 */
	private void setMovementTime(double time) {
		this.movementTime = time;
	}
	
	/**
	 * This variable contains the time that the current movement will last
	 */
	private double movementTime;
	
	/**
	 * 
	 * @param value
	 * @effect 	...
	 * 			if (getMovementCounter() + value >= 4) then
	 *				setMovementCounter(4);
	 * @effect 	...
	 * 			if (getMovementCounter() + value < 4) then
	 *				setMovementCounter(value);
	 */
	private void addToMovementCounter(int value){
		if(getMovementCounter() + value >= 4)
			setMovementCounter(4);
		else
			setMovementCounter(getMovementCounter() + value);
	}
	

	/**
	 * 
	 * @return 	...
	 * 			| result == this.movementCounter
	 */
	private int getMovementCounter() {
		return movementCounter;
	}

	/**
	 * 
	 * @param movementCounter
	 * @post 	...
	 * 			| new.getMovementCounter() = movementCounter;
	 */
	private void setMovementCounter(int movementCounter) {
		this.movementCounter = movementCounter;
	}
	
	/**
	 * This variable contains the amount of movements after a jump
	 */
	private int movementCounter;
	
	/**
	 * 
	 * @return	...
	 * 			|result == getWorld().detectGeologicalFeature((int) getLocationX()+1, 
	 * 			|			(int) getLocationY(), (int) getLocationX() + getCurrentSprite().getWidth()-2, 
	 * 			|				(int) getLocationY(), 1)
	 */
	private boolean isBottomPerimeterInSolidGround(){
		int x = (int) getLocationX();
		int y = (int) getLocationY();
		int endX = x + getCurrentSprite().getWidth();
		return getWorld().detectGeologicalFeature(x+1, y, endX-2, y, 1);		
	}

	/**
	 * 
	 * @return 	...
	 * 			| result == this.notInWaterTimer
	 */
	private double getNotInWaterTimer() {
		return notInWaterTimer;
	}

	/**
	 * 
	 * @param notInWaterTimer
	 * @post 	...
	 * 			|new.getNotInWaterTimer() == notInWaterTimer
	 */
	private void setNotInWaterTimer(double notInWaterTimer) {
		this.notInWaterTimer = notInWaterTimer;
	}
	
	/**
	 * This variable contains the time that the shark was not in the water
	 */
	private double notInWaterTimer;	
	
	/**
	 * 
	 * @return 	...
	 * 			| result == 100
	 */
	@Override
	int getMaxHitPoints(){
		return 100;
	}
	
	/**
	 * @effect 	...
	 * 			|removeWorld()
	 * @effect	...
	 * 			|setTerminated(true)
	 */
	@Override
	void terminate() {
		removeWorld();
		setTerminated(true);
	}
	
	/**
	 * @effect 	...
	 *			|if(!getWorld().getMazub().isImmune() && getWorld().collisionMazubInPerimeters(
	 *			|	(int)getLocationX(), (int)getLocationY(), 
	 *			|	(int)getLocationX()+getCurrentSprite().getWidth(), 
	 *			|	(int)getLocationY()+getCurrentSprite().getHeight())) then
	 *			|		getWorld().getMazub().hadCollissionShark()
	 *			|		hadCollisionMazub()
	 */
	private void handleCollisionMazub(){
		if(!getWorld().getMazub().isImmune() && getWorld().collisionMazubInPerimeters(
				(int)getLocationX(), (int)getLocationY(), 
				(int)getLocationX()+getCurrentSprite().getWidth(), 
				(int)getLocationY()+getCurrentSprite().getHeight())){
			getWorld().getMazub().hadCollissionShark();
			hadCollisionMazub();
		}
	}

	/**
	 * @effect ...
	 * 			| setHitPoints(getHitPoints()-50)
	 */
	void hadCollisionMazub() {
		setHitPoints(getHitPoints()-50);
	}
	
	/**
	 * @effect 	...
	 * 			|foreach(slime in getWorld().collisionSlimesInPerimeters((int) getLocationX(), (int) getLocationY(), (int) getLocationX()+getCurrentSprite().getWidth(), 
	 * 			|			(int) getLocationY()+getCurrentSprite().getHeight())
	 * 			|				hadCollisionSlime()
	 * 			|				slime.hadCollisionShark()
	 * 			|				if(slime.isTerminated()) then
	 * 			|					getWorld().removeSlime(slime)
	 */
	private void handleCollisionSlime() {
	Collection<Slime> collection = getWorld().collisionSlimesInPerimeters((int) getLocationX(), (int) getLocationY(), (int) getLocationX()+getCurrentSprite().getWidth(), (int) getLocationY()+getCurrentSprite().getHeight());
		for (Slime slime : collection) {
			hadCollisionSlime();				
			slime.hadCollisionShark();
			if(slime.isTerminated()){
				getWorld().removeSlime(slime);				
			}
		}
	}
	
	/**
	 * Let this Shark jump
	 * 	@throws IllegalStateException
	 * 			if the up key is already pressed
	 * 			|isUpKeyPressed()
	 *  @effect	the vertical acceleration will be set to 0.2
	 * 			|setAccelerationY(0.2);
	 */
	public void startJump() throws IllegalStateException {
		if (isUpKeyPressed()){
			throw new IllegalStateException();
		}
		if(isInWater()){
			setAccelerationY(0.2);
		}
		setUpKeyPressed(true);		
	}
	//TODO moet System.out.println blijven staan?
	/**
	 * Ends this Shark's jump
	 * @throws 	IllegalStateException
	 * 				if the up key was not pressed
	 * 			| !isUpKeyPressed()
	 * @effect	if accelerationY is greater than 0, accelerationY will be set to zero
	 * 			| if getAccelerationY() > 0 then
	 * 			|	setAccelerationY(0)
	 * @effect	set that the up key is no longer pressed
	 * 			| setUpKeyPressed(false)
	 */
	public void endJump() throws IllegalStateException {
		if(!isUpKeyPressed()) {
			throw new IllegalStateException();
		}
		if (this.getAccelerationY()> 0) {
			setAccelerationY(0);
			System.out.println("End Jump ontvangen en uitgevoerd");
		}
		setUpKeyPressed(false);
	}
	
	/**
	 * Returns whether the up key is pressed or not
	 */
	public boolean isUpKeyPressed() {
		return isUpKeyPressed;
	}

	/**
	 * @param isUpKeyPressed
	 * 			the current pressed status of the up key
	 * @post 	isUpKeyPressed will equal the given value
	 * 			|new.isUpKeyPressed() == isUpKeyPressed
	 */
	private void setUpKeyPressed(boolean isUpKeyPressed) {
		this.isUpKeyPressed = isUpKeyPressed;
	}

	/**
	 * This variable contains the current pressed status of the up key
	 */
	private boolean isUpKeyPressed;
	
	/**
	 * @effect 	...
	 * 			|setHitPoints(getHitPoints()-50)
	 */
	void hadCollisionSlime(){
		setHitPoints(getHitPoints()-50);		
	}

	/**
	 * @return 	...
	 * 			| result == (world != null && world.hasAsShark(this))
	 */
	@Override
	protected boolean isValidWorld(World world) {
		return world != null && world.hasAsShark(this);
	}

	@Override
	protected double getInitialHorizontalVelocity() {
		return 0;
	}

	@Override
	protected double getMaximumHorizontalVelocity() {
		return 4;
	}

	@Override
	protected boolean isDucking() {
		return false;
	}

	
	
	
}