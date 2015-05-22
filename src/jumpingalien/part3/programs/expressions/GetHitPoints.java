package jumpingalien.part3.programs.expressions;

import jumpingalien.model.GameObject;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.ReturnTypeDetection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.exceptions.TypeError;

public class GetHitPoints extends Expression {

	public GetHitPoints(Expression expression, SourceLocation sourceLocation) {
		super(sourceLocation);
		if(!ReturnTypeDetection.returnsObject(expression)){
			throw new TypeError(sourceLocation);
		}
		setExpression(expression);

	}

	private Expression getExpression() {
		return expression;
	}

	private void setExpression(Expression expression) {
		this.expression = expression;
	}
	
	private Expression expression;
	
	@Override
	public java.lang.Double getValue(Program program) {
		if( getExpression().getValue(program) instanceof GameObject){
			GameObject object = (GameObject) getExpression().getValue(program);
			return (double) object.getHitPoints();
		}
		program.setLinesToRun(0);
		program.setHasAnError(true);
		return null;
	}

}
