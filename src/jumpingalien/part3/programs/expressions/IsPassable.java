package jumpingalien.part3.programs.expressions;

import jumpingalien.model.Program;
import jumpingalien.model.Tile;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.ReturnTypeDetection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.exceptions.TypeError;

public class IsPassable extends UnaryOperator {

	public IsPassable(Expression expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
		if(!ReturnTypeDetection.returnsObject(expression) || ReturnTypeDetection.returnsGameObject(expression) ){
			throw new TypeError(sourceLocation);
		}
	}

	@Override
	public java.lang.Boolean getValue(Program program) {
		if (getOperand().getValue(program) instanceof Tile) {
			int geologicalFeature = ((Tile) getOperand().getValue(program)).getGeologicalFeature();
			return geologicalFeature == 0 || geologicalFeature == 2 || geologicalFeature == 3;
		} else {
			program.stopBecauseError();
			return false;
		}
	}

}
