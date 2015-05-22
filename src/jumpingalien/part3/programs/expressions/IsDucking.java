package jumpingalien.part3.programs.expressions;

import jumpingalien.model.Mazub;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.ReturnTypeDetection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.exceptions.TypeError;

public class IsDucking extends Expression {

	public IsDucking(Expression expression, SourceLocation sourceLocation) {
		super(sourceLocation);
		if(!ReturnTypeDetection.returnsObject(expression)){
			throw new TypeError(sourceLocation);
		}
		this.expression = expression;
	}
	
	private Expression getExpression() {
		return this.expression;
	}
	
	private final Expression expression;

	@Override
	public java.lang.Boolean getValue(Program program) {
		if (getExpression().getValue(program) instanceof Mazub) {
			return ((Mazub) getExpression().getValue(program)).isDucking();
		}
		return false;
	}

}
