package jumpingalien.part3.programs.expressions;

import jumpingalien.model.GameObject;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;

public class GetHeight extends Expression {

	public GetHeight(Expression expression, SourceLocation sourceLocation) {
		super(sourceLocation);
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
			return (double) object.getCurrentSprite().getHeight();
		}
		return null;
	}

}
