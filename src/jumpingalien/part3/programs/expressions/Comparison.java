package jumpingalien.part3.programs.expressions;

import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;

public abstract class Comparison extends BinaryOperator {

	protected Comparison(Expression leftOperand, Expression rightOperand, SourceLocation sourceLocation) {
		super(leftOperand, rightOperand, sourceLocation);
	}

}
