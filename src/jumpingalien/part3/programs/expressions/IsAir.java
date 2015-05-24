package jumpingalien.part3.programs.expressions;

import jumpingalien.model.Program;
import jumpingalien.model.Tile;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.ReturnTypeDetection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.exceptions.TypeError;

public class IsAir extends UnaryOperator {

	public IsAir(Expression expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
		if(!ReturnTypeDetection.returnsObject(expression) || ReturnTypeDetection.returnsGameObject(expression)){
			throw new TypeError(sourceLocation);
		}
	}

	@Override
	public java.lang.Boolean getValue(Program program) {
		if (getOperand().getValue(program) instanceof Tile) {
			return ((Tile) getOperand().getValue(program)).getGeologicalFeature() == 0;
		} else {
			program.stopBecauseError();
			return false;
		}
	}

}
