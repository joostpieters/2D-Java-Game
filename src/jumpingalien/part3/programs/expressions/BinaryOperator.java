package jumpingalien.part3.programs.expressions;

import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;

public abstract class BinaryOperator extends Operator {
	
	protected BinaryOperator(Expression leftOperand, Expression rightOperand, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}
	
	private final Expression leftOperand;
	
	public Expression getLeftOperand() {
		return leftOperand;
	}

	
	private final Expression rightOperand;
	
	public Expression getRightOperand() {
		return rightOperand;
	}
}
