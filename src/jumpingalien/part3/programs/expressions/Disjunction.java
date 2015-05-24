package jumpingalien.part3.programs.expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.ReturnTypeDetection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.exceptions.TypeError;

public class Disjunction extends BinaryOperator {

	public Disjunction(Expression leftOperand, Expression rightOperand, SourceLocation sourceLocation) {
		super(leftOperand, rightOperand, sourceLocation);
		if(!ReturnTypeDetection.returnsBoolean(leftOperand, rightOperand)){
			throw new TypeError(sourceLocation);
		}
	}

	@Override
	public java.lang.Boolean getValue(Program program) {
		try{
			boolean left = (boolean) getLeftOperand().getValue(program);
			boolean right = (boolean) getRightOperand().getValue(program);
			return new java.lang.Boolean(left || right);
		} catch (ClassCastException e) {
			program.stopBecauseError();
			return null;
		}
	}


}
