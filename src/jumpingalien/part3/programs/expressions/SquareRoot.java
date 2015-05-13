package jumpingalien.part3.programs.expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;

public class SquareRoot extends UnaryOperator {

	public SquareRoot(Expression operand, SourceLocation sourceLocation) {
		super(operand, sourceLocation);
	}

	@Override
	public java.lang.Double getValue(Program program) {
		double operand = (double) getOperand().getValue(program);
		return new java.lang.Double(Math.sqrt(operand));
	}

}
