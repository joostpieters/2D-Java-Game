package jumpingalien.part3.programs.expressions;

import jumpingalien.model.Plant;
import jumpingalien.model.Program;
import jumpingalien.model.Shark;
import jumpingalien.model.Slime;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;

public class IsPlant extends Expression {

	public IsPlant(Expression expression, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.expression = expression;
	}
	
	private final Expression getExpression() {
		return expression;
	}
	
	private final Expression expression;

	@Override
	public java.lang.Boolean getValue(Program program) {
		return getExpression().getValue(program) instanceof Plant;
	}

}
