package jumpingalien.part3.programs.expressions;

import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;

public abstract class Literal extends Expression {

	public Literal(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
}
