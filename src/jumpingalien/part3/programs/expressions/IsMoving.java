package jumpingalien.part3.programs.expressions;

import jumpingalien.model.GameObject;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.ReturnTypeDetection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.exceptions.TypeError;

public class IsMoving extends Expression {

	public IsMoving(Expression expr, Expression direction, SourceLocation sourceLocation) {
		super(sourceLocation);
		if(!(ReturnTypeDetection.returnsObject(expr) && ReturnTypeDetection.returnsDirection(direction))){
			throw new TypeError(sourceLocation);
		}
		this.expression = expr;
		this.direction = direction;
	}

	private Expression getExpression() {
		return this.expression;
	}
	
	private final Expression expression;
	
	private Expression getDirection() {
		return this.direction;
	}
	
	private final Expression direction;
	
	@Override
	public java.lang.Boolean getValue(Program program) {
		if (getExpression().getValue(program) instanceof GameObject && direction.getValue(program) instanceof jumpingalien.part3.programs.IProgramFactory.Direction) {
			if (direction.getValue(program) == jumpingalien.part3.programs.IProgramFactory.Direction.UP) {
				return ((GameObject) getExpression().getValue(program)).getVelocityY() > 0;
			} else if (direction.getValue(program) == jumpingalien.part3.programs.IProgramFactory.Direction.DOWN) {
				return ((GameObject) getExpression().getValue(program)).getVelocityY() < 0;
			} else if (direction.getValue(program) == jumpingalien.part3.programs.IProgramFactory.Direction.LEFT) {
				return ((GameObject) getExpression().getValue(program)).getVelocityX() < 0;
			} else if (direction.getValue(program) == jumpingalien.part3.programs.IProgramFactory.Direction.RIGHT) {
				return ((GameObject) getExpression().getValue(program)).getVelocityX() > 0;
			}
		}
		program.stopBecauseError();
		return false;
	}

}
