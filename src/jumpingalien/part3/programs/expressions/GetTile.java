package jumpingalien.part3.programs.expressions;

import jumpingalien.model.GameObject;
import jumpingalien.model.Program;
import jumpingalien.model.Tile;
import jumpingalien.model.World;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.ReturnTypeDetection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.exceptions.TypeError;

import java.lang.Double;

public class GetTile extends Expression {

	public GetTile(Expression x, Expression y, SourceLocation sourceLocation) {
		super(sourceLocation);
		if(!ReturnTypeDetection.returnsDouble(x, y)){
			throw new TypeError(sourceLocation);
		}
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
		if (getX().getValue(program) instanceof java.lang.Double && getY().getValue(program) instanceof java.lang.Double) {
			int x = doubleToInteger((Double) getX().getValue(program));
			int y = doubleToInteger((Double) getY().getValue(program));
			try {
				int geologicalFeature = world.getGeologicalFeatureByPixel(x, y);
				return new Tile(x, y, geologicalFeature);
			} catch (IllegalArgumentException e){
				program.stopBecauseError();
				return null;
			}
		} else {
			program.stopBecauseError();
			return null;
		}
	}

}
