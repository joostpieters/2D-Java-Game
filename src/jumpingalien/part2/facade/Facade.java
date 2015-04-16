package jumpingalien.part2.facade;

import java.util.Collection;

import jumpingalien.model.Direction;
import jumpingalien.model.Mazub;
import jumpingalien.model.Plant;
import jumpingalien.model.School;
import jumpingalien.model.Shark;
import jumpingalien.model.Slime;
import jumpingalien.model.World;
import jumpingalien.util.ModelException;
import jumpingalien.util.Sprite;

public class Facade implements IFacadePart2 {

	@Override
	public Mazub createMazub(int pixelLeftX, int pixelBottomY, Sprite[] sprites) {
		try{
			Mazub alien = new Mazub(pixelLeftX, pixelBottomY, sprites);
			return alien;
		} catch (IllegalArgumentException e){
			throw new ModelException("Illegal Sprite argment");
		}
	}

	@Override
	public int[] getLocation(Mazub alien) {
		int location[] = new int[2];
		location[0] = (int) alien.getLocationX();
		location[1] = (int) alien.getLocationY();
		return location;
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
		alien.startJump();
	}

	@Override
	public void endJump(Mazub alien) {
		alien.endJump();
	}

	@Override
	public void startMoveLeft(Mazub alien) {
		alien.startMove(Direction.LEFT);
	}

	@Override
	public void endMoveLeft(Mazub alien) {
		alien.endMove(Direction.LEFT);
	}

	@Override
	public void startMoveRight(Mazub alien) {
		alien.startMove(Direction.RIGHT);
	}

	@Override
	public void endMoveRight(Mazub alien) {
		alien.endMove(Direction.RIGHT);
	}

	@Override
	public void startDuck(Mazub alien) {
		try{
			alien.startDucking();
		} catch (IllegalStateException e){
			throw new ModelException("Mazub is already ducking");
		}
	}

	@Override
	public void endDuck(Mazub alien) {
		try{
			alien.endDucking();
		} catch (IllegalStateException e){
			throw new ModelException("Mazub is not ducking");
		}	
	}

	@Override
	public void advanceTime(Mazub alien, double dt) {
		try {
			alien.advanceTime(dt);
		} catch (IllegalArgumentException e) {
			throw new ModelException("The elapsed time can never be negative.");
		}
	}

	@Override
	public int getNbHitPoints(Mazub alien) {
		return alien.getHitPoints();
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
		// TODO Auto-generated method stub
		
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
			return world.getGeologicalFeatureByPixel(pixelX, pixelY);
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
	public Plant createPlant(int x, int y, Sprite[] sprites) {
		return new Plant(x, y, sprites);
	}

	@Override
	public void addPlant(World world, Plant plant) throws ModelException {
		try{
			world.addPlant(plant);
		} catch (IllegalArgumentException e) {
			throw new ModelException("The given plant is not valid");
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
	public Shark createShark(int x, int y, Sprite[] sprites) {
		return new Shark(x, y, sprites);
	}

	@Override
	public void addShark(World world, Shark shark) {
		world.addShark(shark);
		
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

}
