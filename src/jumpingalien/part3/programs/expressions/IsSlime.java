package jumpingalien.part3.programs.expressions;

import jumpingalien.model.Plant;
import jumpingalien.model.Program;
import jumpingalien.model.Slime;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.ReturnTypeDetection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.exceptions.TypeError;
import jumpingalien.part3.programs.types.GameItems;

public class IsSlime extends Expression {

	public IsSlime(Expression expression, SourceLocation sourceLocation) {
		super(sourceLocation);
		if(!ReturnTypeDetection.returnsObject(expression)){
			throw new TypeError(sourceLocation);
		}
		this.expression = expression;
	}
	
	private final Expression getExpression() {
		return expression;
	}
	
	private final Expression expression;

	@Override
	public java.lang.Boolean getValue(Program program) {
		if(program.getObject() instanceof GameItems){
			return getExpression().getValue(program) instanceof Slime;
		}
		program.stopBecauseError();
		return null;
	}

}
