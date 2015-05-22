package jumpingalien.model;

public class Tile extends jumpingalien.part3.programs.types.GameObject{

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
