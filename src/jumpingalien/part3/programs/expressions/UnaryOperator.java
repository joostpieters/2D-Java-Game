package jumpingalien.part3.programs.expressions;

import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;

public abstract class UnaryOperator extends Operator {

	protected UnaryOperator(Expression operand, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.operand = operand;
	}

	private Expression operand; 
}