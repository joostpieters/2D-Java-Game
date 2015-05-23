package jumpingalien.part3.programs.expressions;

import jumpingalien.model.Program;
import jumpingalien.model.Shark;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.ReturnTypeDetection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.exceptions.TypeError;
import jumpingalien.part3.programs.types.GameItems;

public class IsShark extends UnaryOperator {

	public IsShark(Expression expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
		if(!ReturnTypeDetection.returnsObject(expression)){
			throw new TypeError(sourceLocation);
		}
	}

	@Override
	public java.lang.Boolean getValue(Program program) {
		if(program.getObject() instanceof GameItems){
			return getOperand().getValue(program) instanceof Shark;
		}
		program.stopBecauseError();
		return null;
	}

}
