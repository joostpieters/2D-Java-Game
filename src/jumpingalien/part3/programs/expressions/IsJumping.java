package jumpingalien.part3.programs.expressions;

import jumpingalien.model.GameObject;
import jumpingalien.model.Mazub;
import jumpingalien.model.Program;
import jumpingalien.model.Shark;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.ReturnTypeDetection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.exceptions.TypeError;

public class IsJumping extends UnaryOperator {

	public IsJumping(Expression expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
		if(!ReturnTypeDetection.returnsObject(expression)){
			throw new TypeError(sourceLocation);
		}
	}

	@Override
	public java.lang.Boolean getValue(Program program) {
		if (getOperand().getValue(program) instanceof Mazub) {
			return ((Mazub) getOperand().getValue(program)).isUpKeyPressed();
		}
		if (getOperand().getValue(program) instanceof Shark) {
			return ((Shark) getOperand().getValue(program)).isUpKeyPressed();
		}
		if (getOperand().getValue(program) instanceof GameObject){
			return false;
		}
		program.stopBecauseError();
		return false;
	}

}
