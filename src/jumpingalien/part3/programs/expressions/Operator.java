package jumpingalien.part3.programs.expressions;

import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;

abstract class Operator extends Expression {

	public Operator(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
}
