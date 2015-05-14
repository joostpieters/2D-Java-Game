package jumpingalien.part3.programs.expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.SourceLocation;

public class Direction extends Literal {
	public Direction(jumpingalien.part3.programs.IProgramFactory.Direction value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.direction = value;
	}

	private jumpingalien.part3.programs.IProgramFactory.Direction getDirection() {
		return direction;
	}
	
	private final jumpingalien.part3.programs.IProgramFactory.Direction direction;


	@Override
	public jumpingalien.part3.programs.IProgramFactory.Direction getValue(Program program) {
		return getDirection();
	}

}
