package jumpingalien.part3.programs.expressions;

import jumpingalien.model.GameObject;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.ReturnTypeDetection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.exceptions.TypeError;

public class IsDead extends UnaryOperator {

	public IsDead(Expression expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
		if(!ReturnTypeDetection.returnsObject(expression) || ReturnTypeDetection.returnsTile(expression)){
			throw new TypeError(sourceLocation);
		}
	}

	@Override
	public java.lang.Boolean getValue(Program program) {
		if(getOperand().getValue(program) instanceof GameObject){
			return ((GameObject) getOperand().getValue(program)).isDead() || ((GameObject) getOperand().getValue(program)).isTerminated();
		}
		program.stopBecauseError();
		return null;
	}

}
