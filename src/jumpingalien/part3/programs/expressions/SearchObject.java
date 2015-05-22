package jumpingalien.part3.programs.expressions;

import jumpingalien.model.GameObject;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.ReturnTypeDetection;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.exceptions.TypeError;
import jumpingalien.part3.programs.SourceLocation;

public class SearchObject extends Expression {

	public SearchObject(Expression direction, SourceLocation sourceLocation) {
		super(sourceLocation);
		if(!ReturnTypeDetection.returnsDirection(direction)){
			throw new TypeError(sourceLocation);
		}
		this.direction = direction;
	}
	
	public Expression getDirection() {
		return direction;
	}
	
	private final Expression direction;

	@Override
	public GameObject getValue(Program program) {
		if(getDirection().getValue(program) instanceof Direction){
			return program.getObject().getWorld().search(program.getObject(), (Direction) getDirection().getValue(program));
		}
		return null;
		//TODO wat gebeurd er indien nu wordt gereturnd, veroorzaakt dit geen null pointers ?
	}

}
