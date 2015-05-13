package jumpingalien.part3.programs.expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.util.Util;

public class LessThanOrEqual extends Comparison {

	public LessThanOrEqual(Expression leftOperand, Expression rightOperand,
			SourceLocation sourceLocation) {
		super(leftOperand, rightOperand, sourceLocation);
	}

	@Override
	public java.lang.Boolean getValue(Program program) {
		double left = (double) getLeftOperand().getValue(program);
		double right = (double) getRightOperand().getValue(program);
		return new java.lang.Boolean(Util.fuzzyLessThanOrEqualTo(left, right));
	}
}
