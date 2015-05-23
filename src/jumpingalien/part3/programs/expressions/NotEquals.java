package jumpingalien.part3.programs.expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.IProgramFactory;
import jumpingalien.part3.programs.ReturnTypeDetection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.exceptions.TypeError;

public class NotEquals extends Comparison {

	public NotEquals(Expression leftOperand, Expression rightOperand,
			SourceLocation sourceLocation) {
		super(leftOperand, rightOperand, sourceLocation);
		if(!(ReturnTypeDetection.returnsDouble(leftOperand, rightOperand) 
				|| ReturnTypeDetection.returnsBoolean(leftOperand, rightOperand)
				|| ReturnTypeDetection.returnsDirection(leftOperand, rightOperand)
				|| ReturnTypeDetection.returnsObject(leftOperand, rightOperand))){
			throw new TypeError(sourceLocation);
		}
	}

	@Override
	public java.lang.Boolean getValue(Program program) {
		java.lang.Object left = getLeftOperand().getValue(program);
		java.lang.Object right = getRightOperand().getValue(program);
		if(left instanceof java.lang.Double && right instanceof java.lang.Double){
			return new java.lang.Boolean((double)left != (double)right);			
		} if(left instanceof Boolean && right instanceof Boolean){
			return new java.lang.Boolean((boolean)left != (boolean)right);
		} if(left instanceof IProgramFactory.Direction && right instanceof IProgramFactory.Direction){
			return new java.lang.Boolean((IProgramFactory.Direction)left != (IProgramFactory.Direction)right);
		}
		return new java.lang.Boolean(left != right);
	}
}
