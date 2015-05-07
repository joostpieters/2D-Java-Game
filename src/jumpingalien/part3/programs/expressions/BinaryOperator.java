package jumpingalien.part3.programs.expressions;

import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;

public abstract class BinaryOperator extends Operator {
	
	protected BinaryOperator(Expression leftOperand, Expression rightOperand, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}
	
	@Override
	public final int getNbOperands() {
		return 2;
	}
	
	@Override
	public Expression getOperandAt(int index) throws IndexOutOfBoundsException {
		if (index == 0) 
			return this.getLeftOperand();
		else if (index == 1)
			return this.getRightOperand();
		else
			throw new IndexOutOfBoundsException();
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
