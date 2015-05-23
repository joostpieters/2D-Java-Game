package jumpingalien.part3.programs.expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.ReturnTypeDetection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.exceptions.TypeError;
import jumpingalien.util.Util;

public class Multiplication extends BinaryOperator {

	public Multiplication(Expression leftOperand, Expression rightOperand, SourceLocation sourceLocation) {
		super(leftOperand, rightOperand, sourceLocation);
		if(!ReturnTypeDetection.returnsDouble(leftOperand, rightOperand)){
			throw new TypeError(sourceLocation);
		}
	}

	@Override
	public java.lang.Double getValue(Program program) {
		if(getLeftOperand().getValue(program) instanceof Double && getRightOperand().getValue(program) instanceof Double){
			double left = (double) getLeftOperand().getValue(program);
			double right = (double) getRightOperand().getValue(program);
			return new java.lang.Double(left*right);
		} else {
			program.stopBecauseError();
			return null;
		}
	}


}
