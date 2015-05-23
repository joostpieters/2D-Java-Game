package jumpingalien.part3.programs.expressions;

import jumpingalien.model.GameObject;
import jumpingalien.model.Program;
import jumpingalien.model.Tile;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.ReturnTypeDetection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.exceptions.TypeError;

public class GetX extends Expression {

	public GetX(Expression expression, SourceLocation sourceLocation) {
		super(sourceLocation);
		if(!ReturnTypeDetection.returnsObject(expression)){
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
		if( getExpression().getValue(program) instanceof GameObject){
			GameObject object = (GameObject) getExpression().getValue(program);
			return (double) object.getLocation()[0];
		} else if (getExpression().getValue(program) instanceof Tile) {
			return (double) ((Tile) getExpression().getValue(program)).getX();
		}
		program.stopBecauseError();
		return null;
	}

}
