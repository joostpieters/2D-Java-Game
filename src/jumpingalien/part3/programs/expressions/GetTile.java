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

public class GetTile extends BinaryOperator {

	public GetTile(Expression x, Expression y, SourceLocation sourceLocation) {
		super(x, y, sourceLocation);
		if(!ReturnTypeDetection.returnsDouble(x, y)){
			throw new TypeError(sourceLocation);
		}
	}
	
	@Override
	public Tile getValue(Program program) {
		World world = ((GameObject) program.getObject()).getWorld();
		if (getLeftOperand().getValue(program) instanceof java.lang.Double && getRightOperand().getValue(program) instanceof java.lang.Double) {
			int x = doubleToInteger((Double) getLeftOperand().getValue(program));
			int y = doubleToInteger((Double) getRightOperand().getValue(program));
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
