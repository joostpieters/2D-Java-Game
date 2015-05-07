package jumpingalien.part3.programs.expressions;

import jumpingalien.part3.programs.Expression;

public class Addition extends BinaryOperator {
	
	public Addition(Expression leftOperand, Expression rightOperand) {
		super(leftOperand, rightOperand);
	}
	
	public double getValue() {
		return getLeftOperand().getValue() + getRightOperand().getValue();
	}
}
