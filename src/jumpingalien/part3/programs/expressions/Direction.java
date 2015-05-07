package jumpingalien.part3.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;

public class Direction extends Literal {
	public Direction(Direction value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.direction = value;
	}
	
	private Direction direction;
}
