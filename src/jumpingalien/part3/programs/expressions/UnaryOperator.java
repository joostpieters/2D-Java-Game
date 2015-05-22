package jumpingalien.part3.programs.expressions;

import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;

abstract class UnaryOperator extends Operator {

	protected UnaryOperator(Expression operand, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.operand = operand;
	}

	private final Expression operand;
	
	public Expression getOperand() {
		return operand;
	}
}
