package jumpingalien.part3.programs.expressions;
import java.util.Random;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.ReturnTypeDetection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.exceptions.TypeError;

public class RandomDouble extends Literal {

	public RandomDouble(Expression expression, SourceLocation sourceLocation) {
		super(sourceLocation);
		if(!ReturnTypeDetection.returnsDouble(expression)){
			throw new TypeError(sourceLocation);
		}
		this.expression = expression;		
	}

	private Expression getExpression() {
		return expression;
	}
	
	private final Expression expression;

	@Override
	public java.lang.Double getValue(Program program) {
		if(getExpression().getValue(program) instanceof Double){
			Random r = new Random();
			return new java.lang.Double((double)getExpression().getValue(program) * r.nextDouble());
		} else {
			program.stopBecauseError();
			return null;
		}
	}

	

}
