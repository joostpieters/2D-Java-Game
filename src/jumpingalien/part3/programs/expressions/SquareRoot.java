package jumpingalien.part3.programs.expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.ReturnTypeDetection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.exceptions.TypeError;

public class SquareRoot extends UnaryOperator {

	public SquareRoot(Expression operand, SourceLocation sourceLocation) {
		super(operand, sourceLocation);
		if(!ReturnTypeDetection.returnsDouble(operand)){
			throw new TypeError(sourceLocation);
		}
	}

	@Override
	public java.lang.Double getValue(Program program) {
		if(getOperand().getValue(program) instanceof java.lang.Double){
			double operand = (double) getOperand().getValue(program);
			return new java.lang.Double(Math.sqrt(operand));
		} else {
			program.stopBecauseError();
			return null;
		}
	}

}
