package jumpingalien.part3.programs.expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;

public class Addition extends BinaryOperator {
	
	public Addition(Expression leftOperand, Expression rightOperand, SourceLocation sourceLocation) {
		super(leftOperand, rightOperand, sourceLocation);
	}
	
	public java.lang.Double getValue(Program program) {
		double left = (double) getLeftOperand().getValue(program);
		double right = (double) getRightOperand().getValue(program);
		return new java.lang.Double(left+right);
	}
}
