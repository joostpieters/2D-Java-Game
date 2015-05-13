package jumpingalien.part3.programs.expressions;

import jumpingalien.model.GameObject;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;

public class GetX extends Expression {

	public GetX(Expression expression, SourceLocation sourceLocation) {
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
		if( expression.getValue(program) instanceof GameObject){
			GameObject object = (GameObject) expression.getValue(program);
			return (double) object.getLocation()[0];
		}
		return null;
		//TODO expection gooien miss ?
	}

}
