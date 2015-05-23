package jumpingalien.part3.programs.expressions;

import jumpingalien.model.GameObject;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.ReturnTypeDetection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.exceptions.TypeError;
import jumpingalien.part3.programs.types.GameItems;

public class IsMazub extends Expression {

	public IsMazub(Expression expression, SourceLocation sourceLocation) {
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
			return ((GameObject) program.getObject()).getWorld().getMazub() == getExpression().getValue(program);
		}
		program.stopBecauseError();
		return null;
	}

}
