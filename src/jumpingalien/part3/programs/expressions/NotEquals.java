package jumpingalien.part3.programs.expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;

public class NotEquals extends Comparison {

	public NotEquals(Expression leftOperand, Expression rightOperand,
			SourceLocation sourceLocation) {
		super(leftOperand, rightOperand, sourceLocation);
	}

	// TODO boolean ook?
	@Override
	public java.lang.Boolean getValue(Program program) {
		double left = (double) getLeftOperand().getValue(program);
		double right = (double) getRightOperand().getValue(program);
		return new java.lang.Boolean(left != right);
	}
}
