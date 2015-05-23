package jumpingalien.part3.facade;

import java.util.Collection;
import java.util.Optional;

import jumpingalien.model.Buzam;
import jumpingalien.model.Mazub;
import jumpingalien.model.Motion;
import jumpingalien.model.Plant;
import jumpingalien.model.Program;
import jumpingalien.model.School;
import jumpingalien.model.Shark;
import jumpingalien.model.Slime;
import jumpingalien.model.World;
import jumpingalien.part3.programs.IProgramFactory;
import jumpingalien.part3.programs.ParseOutcome;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.ProgramParser;
import jumpingalien.part3.programs.Type;
import jumpingalien.part3.programs.Statement;
import jumpingalien.part3.programs.ProgramFactory;
import jumpingalien.part3.programs.exceptions.TypeError;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;

public class Facade implements IFacadePart3 {

	@Override
	public int getNbHitPoints(Mazub alien) {
		return alien.getHitpoints();
	}

	@Override
	public World createWorld(int tileSize, int nbTilesX, int nbTilesY,
			int visibleWindowWidth, int visibleWindowHeight, int targetTileX,
			int targetTileY) {
		try{
			World world = new World(tileSize, nbTilesX, nbTilesY,
					visibleWindowWidth, visibleWindowHeight, targetTileX,
					 targetTileY);
			return world;
		} catch (IllegalArgumentException e){
			throw new ModelException("Illegal Argument for Game World");
		}
	}

	@Override
	public int[] getWorldSizeInPixels(World world) {
		return world.getWorldSizeInPixels();
	}

	@Override
	public int getTileLength(World world) {
		return world.getTileSize();
	}

	@Override
	public void startGame(World world) {
		try {
			world.setGameStarted(true);	
		} catch (IllegalArgumentException e){
			throw new ModelException("You can not stop the game by yourself");
		}
	}

	@Override
	public boolean isGameOver(World world) {
		return world.isGameOver();
	}

	@Override
	public boolean didPlayerWin(World world) {
		return world.hasReachedEnd();
	}

	@Override
	public void advanceTime(World world, double dt) throws ModelException {
		try{
			world.advanceTime(dt);
		}
		catch (IllegalArgumentException e) {
			throw new ModelException("The given time needs to be between 0 and 0.2");
		}	
	}

	@Override
	public int[] getVisibleWindow(World world) {
		return world.getVisibleWindow();
	}

	@Override
	public int[] getBottomLeftPixelOfTile(World world, int tileX, int tileY) {
		return world.getBottomLeftPixelOfTile(tileX, tileY);
	}

	@Override
	public int[][] getTilePositionsIn(World world, int pixelLeft,
			int pixelBottom, int pixelRight, int pixelTop) {
		return world.getTilePositionsIn(pixelLeft, pixelBottom, pixelRight, pixelTop);	
	}

	@Override
	public int getGeologicalFeature(World world, int pixelX, int pixelY)
			throws ModelException {
		try{
			return world.getGeologicalFeatureByBottomLeftPixel(pixelX, pixelY);
		}
		catch(IllegalArgumentException e){
			throw new ModelException("The given pixels are no left bottom pixels off a tile");
		}
	}

	@Override
	public void setGeologicalFeature(World world, int tileX, int tileY,
			int tileType) {
		world.setGeologicalFeatureOfTile(tileX, tileY, tileType);
	}

	@Override
	public void setMazub(World world, Mazub alien) throws ModelException {
		try{
			world.setMazub(alien);	
		} catch(IllegalArgumentException e){
			throw new ModelException("Invalid Mazub");
		}
	}

	@Override
	public boolean isImmune(Mazub alien) {
		return alien.isImmune();
	}

	@Override
	public Plant createPlant(int x, int y, Sprite[] sprites) throws ModelException {
		try {
			return new Plant(x, y, sprites);
		} catch (IllegalArgumentException e) {
			throw new ModelException("Invalid Argument");
		}
	}

	@Override
	public void addPlant(World world, Plant plant) throws ModelException {
		try{
			world.addPlant(plant);
		} catch (IllegalArgumentException e) {
			throw new ModelException("The given plant is not valid");
		} catch (IllegalStateException e){
			throw new ModelException("The game is already started");
		}
	}

	@Override
	public Collection<Plant> getPlants(World world) {
		return world.getPlants();
	}

	@Override
	public int[] getLocation(Plant plant) {
		return plant.getLocation();
	}

	@Override
	public Sprite getCurrentSprite(Plant plant) {
		return plant.getCurrentSprite();
	}

	@Override
	public Shark createShark(int x, int y, Sprite[] sprites) throws ModelException {
		try {
			return new Shark(x, y, sprites);
		} catch (IllegalArgumentException e) {
			throw new ModelException("Illegal Argument");
		}
	}

	@Override
	public void addShark(World world, Shark shark) {
		try{
			world.addShark(shark);
		} catch (IllegalArgumentException e) {
			throw new ModelException("The given shark is not valid");
		} catch (IllegalStateException e){
			throw new ModelException("The game is already started");
		}		
	}

	@Override
	public Collection<Shark> getSharks(World world) {
		return world.getSharks();
	}

	@Override
	public int[] getLocation(Shark shark) {
		return shark.getLocation();
	}

	@Override
	public Sprite getCurrentSprite(Shark shark) {
		return shark.getCurrentSprite();
	}

	@Override
	public School createSchool() {
		return new School();
	}

	@Override
	public Slime createSlime(int x, int y, Sprite[] sprites, School school) throws ModelException {
		try{
			return new Slime(x, y, sprites, school);
		} catch (IllegalArgumentException e){
			throw new ModelException("Invalid argument for createSlime");
		}
	}

	@Override
	public void addSlime(World world, Slime slime) throws ModelException{
		try{
			world.addSlime(slime);
		} catch (IllegalArgumentException e){
			throw new ModelException("Invalid argument for addSlime()");
		} catch (IllegalStateException e){
			throw new ModelException("The game is already started");
		}
		
	}

	@Override
	public Collection<Slime> getSlimes(World world) {
		return world.getSlimes();
	}

	@Override
	public int[] getLocation(Slime slime) {
		return slime.getLocation();
	}

	@Override
	public Sprite getCurrentSprite(Slime slime) {
		return slime.getCurrentSprite();
	}

	@Override
	public School getSchool(Slime slime) {
		return slime.getSchool();
	}

	@Override
	public Mazub createMazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites) throws ModelException {
		try{
			Mazub alien = new Mazub(pixelLeftX, pixelBottomY, sprites);
			return alien;
		} catch (IllegalArgumentException e){
			throw new ModelException("Illegal Sprite argment");
		}
	}

	@Override
	public int[] getLocation(Mazub alien) {
		return alien.getLocation();
	}

	@Override
	public double[] getVelocity(Mazub alien) {
		double[] velocity = new double[2];
		velocity[0] = alien.getVelocityX();
		velocity[1] = alien.getVelocityY();
		return velocity;
	}

	@Override
	public double[] getAcceleration(Mazub alien) {
		double[] acceleration = new double[2];
		acceleration[0] = alien.getAccelerationX();
		acceleration[1] = alien.getAccelerationY();
		return acceleration;
	}

	@Override
	public int[] getSize(Mazub alien) {
		try{
			return alien.getSize();
		} catch (ModelException e){
			throw new ModelException("Unvalid current sprite");
		}
	}

	@Override
	public Sprite getCurrentSprite(Mazub alien) {
		return alien.getCurrentSprite();
	}

	@Override
	public void startJump(Mazub alien) {
		try {
			alien.startJump();
		} catch(IllegalStateException e){
			throw new ModelException("Up key was already pressed");
		}
	}

	@Override
	public void endJump(Mazub alien)throws ModelException {
		try {
			alien.endJump();
		} catch (IllegalStateException e){
			throw new ModelException("The Up key was not pressed");
		}
	}

	@Override
	public void startMoveLeft(Mazub alien) {
		alien.startMove(Motion.LEFT);
	}

	@Override
	public void endMoveLeft(Mazub alien) {
		alien.endMove(Motion.LEFT);
	}

	@Override
	public void startMoveRight(Mazub alien) {
		alien.startMove(Motion.RIGHT);
	}

	@Override
	public void endMoveRight(Mazub alien) {
		alien.endMove(Motion.RIGHT);
	}

	@Override
	public void startDuck(Mazub alien) {
		try{
			alien.startDucking();
		} catch (IllegalStateException e){
			throw new ModelException("The down key is already pressed");
		}
	}

	@Override
	public void endDuck(Mazub alien) {
		try{
			alien.endDucking();
		} catch (IllegalStateException e){
			throw new ModelException("The Down key was not pressed");
		}	
	}

	@Override
	public void advanceTime(Mazub alien, double dt) {
		// Nothing needs to happen		
	}

	@Override
	public Buzam createBuzam(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
		try{
			Buzam alien = new Buzam(pixelLeftX, pixelBottomY, sprites);
			return alien;
		} catch (IllegalArgumentException e){
			throw new ModelException("Illegal Sprite argment");
		}
	}

	@Override
	public Buzam createBuzamWithProgram(int pixelLeftX, int pixelBottomY, Sprite[] sprites, Program program) {
		try {
			return new Buzam(pixelLeftX, pixelBottomY, sprites, program);
		} catch (IllegalArgumentException e){
			throw new ModelException("Illegal Sprite argment");
		}
	}

	@Override
	public Plant createPlantWithProgram(int x, int y, Sprite[] sprites, Program program) {
		try {
			return new Plant(x, y, sprites, program);
		} catch (IllegalArgumentException e) {
			throw new ModelException("Invalid Argument");
		}
	}

	@Override
	public Shark createSharkWithProgram(int x, int y, Sprite[] sprites, Program program) {
		try {
			return new Shark(x, y, sprites, program);
		} catch (IllegalArgumentException e) {
			throw new ModelException("Illegal Argument for createSharkWithProgram");
		}
	}

	@Override
	public Slime createSlimeWithProgram(int x, int y, Sprite[] sprites, School school, Program program) {
		try {
			return new Slime(x, y, sprites, school, program);
		} catch (IllegalArgumentException e){
			throw new ModelException("Invalid argument for createSlimeWithProgram");
		}
	}

	@Override
	public ParseOutcome<?> parse (String text) throws ModelException {
		text = "object a; object b; object c; object d; a := searchobj left; if (ismazub a) then print 1; fi b := searchobj up; if (ismazub b) then print 2; fi d := searchobj down; if (ismazub d) then print 4; fi";
		IProgramFactory<Expression, Statement, Type, Program> factory = new ProgramFactory();
		ProgramParser<Expression, Statement, Type, Program> parser = new ProgramParser<>(factory);
		Optional<Program> parseResult;
		try{
			parseResult = parser.parseString(text);
		} catch(TypeError e){
			throw new ModelException("Type Problem in Program at line " + e.getSourceLocation().getLine() + " and column " + e.getSourceLocation().getColumn());
		}
		if(parseResult.isPresent()){
			return ParseOutcome.success(parseResult.get());
		} else {
			return ParseOutcome.failure(parser.getErrors());
		}
	}

	@Override
	public boolean isWellFormed(Program program) {
		if (program == null) {
			throw new ModelException("The program cannot be null");
		}
		return program.isWellFormed();
	}

	@Override
	public void addBuzam(World world, Buzam buzam) throws ModelException {
		try{
			world.setBuzam(buzam);	
		} catch(IllegalArgumentException e){
			throw new ModelException("Invalid Buzam");
		}
	}

	@Override
	public int[] getLocation(Buzam alien) {
		if(!alien.isTerminated())
			return alien.getLocation();
		return null;
	}

	@Override
	public double[] getVelocity(Buzam alien) {
		double[] velocity = new double[2];
		velocity[0] = alien.getVelocityX();
		velocity[1] = alien.getVelocityY();
		return velocity;
	}

	@Override
	public double[] getAcceleration(Buzam alien) {
		double[] acceleration = new double[2];
		acceleration[0] = alien.getAccelerationX();
		acceleration[1] = alien.getAccelerationY();
		return acceleration;
	}

	@Override
	public int[] getSize(Buzam alien) {
		try{
			return alien.getSize();
		} catch (ModelException e){
			throw new ModelException("Unvalid current sprite");
		}
	}

	@Override
	public Sprite getCurrentSprite(Buzam alien) {
		return alien.getCurrentSprite();
	}

	@Override
	public int getNbHitPoints(Buzam alien) {
		return alien.getHitpoints();
	}

}