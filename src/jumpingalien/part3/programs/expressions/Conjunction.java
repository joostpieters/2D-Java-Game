package jumpingalien.part3.programs.expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;

public class Conjunction extends BinaryOperator {

	public Conjunction(Expression leftOperand, Expression rightOperand, SourceLocation sourceLocation) {
		super(leftOperand, rightOperand, sourceLocation);
	}

	@Override
	public java.lang.Boolean getValue(Program program) {
		boolean left = (boolean) getLeftOperand().getValue(program);
		boolean right = (boolean) getRightOperand().getValue(program);
		return new java.lang.Boolean(left && right);
	}

}
