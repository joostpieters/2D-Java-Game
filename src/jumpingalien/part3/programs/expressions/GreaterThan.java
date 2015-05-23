package jumpingalien.part3.programs.expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.ReturnTypeDetection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.exceptions.TypeError;
import jumpingalien.part3.programs.expressions.Comparison;

public class GreaterThan extends Comparison {

	public GreaterThan(Expression leftOperand, Expression rightOperand,
			SourceLocation sourceLocation) {
		super(leftOperand, rightOperand, sourceLocation);
		if(!ReturnTypeDetection.returnsDouble(leftOperand, rightOperand)){
			throw new TypeError(sourceLocation);
		}
	}

	@Override
	public java.lang.Boolean getValue(Program program) {
		if(getLeftOperand().getValue(program) instanceof java.lang.Double && getRightOperand().getValue(program) instanceof java.lang.Double){
			double left = (double) getLeftOperand().getValue(program);
			double right = (double) getRightOperand().getValue(program);
			return new java.lang.Boolean(left > right);
		}
		else{
			program.stopBecauseError();
			return null;
		}
	}

}
