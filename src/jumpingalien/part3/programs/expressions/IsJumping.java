package jumpingalien.part3.programs.expressions;

import jumpingalien.model.Mazub;
import jumpingalien.model.Program;
import jumpingalien.model.Shark;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;

public class IsJumping extends Expression {

	public IsJumping(Expression expression, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.expression = expression;
	}
	
	private Expression getExpression() {
		return this.expression;
	}
	
	private final Expression expression;

	@Override
	public java.lang.Boolean getValue(Program program) {
		if (getExpression().getValue(program) instanceof Mazub) {
			return ((Mazub) getExpression().getValue(program)).isUpKeyPressed();
		}
		if (getExpression().getValue(program) instanceof Shark) {
			return ((Shark) getExpression().getValue(program)).isUpKeyPressed();
		}
		return false;
	}

}
