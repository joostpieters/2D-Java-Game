package jumpingalien.part3.programs.expressions;

import jumpingalien.model.GameObject;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.ReturnTypeDetection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.exceptions.TypeError;

public class IsMoving extends BinaryOperator {

	public IsMoving(Expression expr, Expression direction, SourceLocation sourceLocation) {
		super(expr, direction, sourceLocation);
		if(!(ReturnTypeDetection.returnsObject(expr) && ReturnTypeDetection.returnsDirection(direction))){
			throw new TypeError(sourceLocation);
		}
	}
	
	@Override
	public java.lang.Boolean getValue(Program program) {
		Expression expression = getLeftOperand();
		Expression direction = getRightOperand();
		
		if (expression.getValue(program) instanceof GameObject && direction.getValue(program) instanceof jumpingalien.part3.programs.IProgramFactory.Direction) {
			if (direction.getValue(program) == jumpingalien.part3.programs.IProgramFactory.Direction.UP) {
				return ((GameObject) expression.getValue(program)).getVelocityY() > 0;
			} else if (direction.getValue(program) == jumpingalien.part3.programs.IProgramFactory.Direction.DOWN) {
				return ((GameObject) expression.getValue(program)).getVelocityY() < 0;
			} else if (direction.getValue(program) == jumpingalien.part3.programs.IProgramFactory.Direction.LEFT) {
				return ((GameObject) expression.getValue(program)).getVelocityX() < 0;
			} else if (direction.getValue(program) == jumpingalien.part3.programs.IProgramFactory.Direction.RIGHT) {
				return ((GameObject) expression.getValue(program)).getVelocityX() > 0;
			}
		}
		program.stopBecauseError();
		return false;
	}

}
