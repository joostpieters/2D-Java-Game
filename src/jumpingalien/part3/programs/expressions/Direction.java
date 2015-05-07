package jumpingalien.part3.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;

public class Direction extends Literal {
	public Direction(jumpingalien.part3.programs.IProgramFactory.Direction value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.direction = value;
	}
	
	private jumpingalien.part3.programs.IProgramFactory.Direction direction;
}
