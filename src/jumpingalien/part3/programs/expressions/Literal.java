package jumpingalien.part3.programs.expressions;

import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;

abstract class Literal extends Expression {

	public Literal(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
}
