package jumpingalien.part3.programs.expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.ReturnTypeDetection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.exceptions.TypeError;

public class Negation extends UnaryOperator {

	public Negation(Expression operand, SourceLocation sourceLocation) {
		super(operand, sourceLocation);
		if(!ReturnTypeDetection.returnsBoolean(operand)){
			throw new TypeError(sourceLocation);
		}
	}
	
	@Override
	public java.lang.Boolean getValue(Program program) {
		if(getOperand().getValue(program) instanceof Boolean){
			boolean operand = (boolean) getOperand().getValue(program);
			return new java.lang.Boolean(!operand);
		} else{
			program.stopBecauseError();
			return null;
		}
	}

}
