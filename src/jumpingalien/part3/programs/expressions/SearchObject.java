package jumpingalien.part3.programs.expressions;

import jumpingalien.model.GameObject;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.ReturnTypeDetection;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.exceptions.TypeError;
import jumpingalien.part3.programs.SourceLocation;

public class SearchObject extends UnaryOperator {

	public SearchObject(Expression direction, SourceLocation sourceLocation) {
		super(direction, sourceLocation);
		if(!ReturnTypeDetection.returnsDirection(direction)){
			throw new TypeError(sourceLocation);
		}
	}

	@Override
	public GameObject getValue(Program program) {
		Expression direction = getOperand();
		if(direction.getValue(program) instanceof Direction){
			return program.getObject().getWorld().search(program.getObject(), (Direction) direction.getValue(program));
		}
		program.stopBecauseError();
		return null;
	}

}
