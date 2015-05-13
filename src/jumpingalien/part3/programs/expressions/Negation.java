package jumpingalien.part3.programs.expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;

public class Negation extends UnaryOperator {

	public Negation(Expression operand, SourceLocation sourceLocation) {
		super(operand, sourceLocation);
	}
	
	@Override
	public java.lang.Boolean getValue(Program program) {
		boolean operand = (boolean) getOperand().getValue(program);
		return new java.lang.Boolean(!operand);
	}

}
