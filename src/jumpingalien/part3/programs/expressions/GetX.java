package jumpingalien.part3.programs.expressions;

import jumpingalien.model.GameObject;
import jumpingalien.model.Program;
import jumpingalien.model.Tile;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.ReturnTypeDetection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.exceptions.TypeError;

public class GetX extends UnaryOperator {

	public GetX(Expression expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
		if(!ReturnTypeDetection.returnsObject(expression)){
			throw new TypeError(sourceLocation);
		}
	}
	
	@Override
	public java.lang.Double getValue(Program program) {
		if(getOperand().getValue(program) instanceof GameObject){
			GameObject object = (GameObject) getOperand().getValue(program);
			return (double) object.getLocation()[0];
		} else if (getOperand().getValue(program) instanceof Tile) {
			return (double) ((Tile) getOperand().getValue(program)).getX();
		}
		program.stopBecauseError();
		return null;
	}

}
