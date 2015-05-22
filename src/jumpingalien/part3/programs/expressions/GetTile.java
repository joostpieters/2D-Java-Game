package jumpingalien.part3.programs.expressions;

import jumpingalien.model.GameObject;
import jumpingalien.model.Program;
import jumpingalien.model.Tile;
import jumpingalien.model.World;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;

public class GetTile extends Expression {

	public GetTile(Expression x, Expression y, SourceLocation sourceLocation) {
		super(sourceLocation);
		setX(x);
		setY(y);
	}

	private Expression getX() {
		return x;
	}

	private void setX(Expression expression) {
		this.x = expression;
	}
	
	private Expression x;
	
	private Expression getY() {
		return y;
	}

	private void setY(Expression expression) {
		this.y = expression;
	}
	
	private Expression y;
	
	@Override
	public Tile getValue(Program program) {
		World world = ((GameObject) program.getObject()).getWorld();
		int x = (int) (double) getX().getValue(program);
		int y = (int) (double) getY().getValue(program);
		int geologicalFeature = world.getGeologicalFeatureByPixel(x, y);
		try {
			return new Tile(x, y, geologicalFeature);
		} catch (IllegalArgumentException e){
			program.setLinesToRun(0);
			program.setHasAnError(true);
			return null;
		}
	}

}
