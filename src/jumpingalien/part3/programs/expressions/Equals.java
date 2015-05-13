package jumpingalien.part3.programs.expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.expressions.Comparison;
import jumpingalien.util.Util;

public class Equals extends Comparison {

	public Equals(Expression leftOperand, Expression rightOperand,
			SourceLocation sourceLocation) {
		super(leftOperand, rightOperand, sourceLocation);
	}

	@Override
	public java.lang.Boolean getValue(Program program) {
		double left = (double) getLeftOperand().getValue(program);
		double right = (double) getRightOperand().getValue(program);
		return new java.lang.Boolean(Util.fuzzyEquals(left, right));
	}



}
