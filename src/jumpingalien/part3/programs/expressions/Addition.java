package jumpingalien.part3.programs.expressions;

import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;

public class Addition extends BinaryOperator {
	
	public Addition(Expression leftOperand, Expression rightOperand, SourceLocation sourceLocation) {
		super(leftOperand, rightOperand, sourceLocation);
	}
	
	public java.lang.Double getValue() {
		double left = (double) getLeftOperand().getValue();
		double right = (double) getRightOperand().getValue();
		return new java.lang.Double(left+right);
	}
}
