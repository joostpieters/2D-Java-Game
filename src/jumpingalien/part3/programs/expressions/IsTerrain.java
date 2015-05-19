package jumpingalien.part3.programs.expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;

public class IsTerrain extends Expression {

	public IsTerrain(Expression expression, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.expression = expression;
	}
	
	private final Expression getExpression() {
		return expression;
	}
	
	private final Expression expression;

	@Override
	public java.lang.Boolean getValue(Program program) {
		if((int)(double)getExpression().getValue(program) == 0 || (int)(double)getExpression().getValue(program) == 1 ||
				(int)(double)getExpression().getValue(program) == 2	|| (int)(double)getExpression().getValue(program) == 3){
			return true;
		}
		return false;
	}

}
