package jumpingalien.part3.programs.expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;

public class LessThan extends Comparison{

	public LessThan(Expression leftOperand, Expression rightOperand,
			SourceLocation sourceLocation) {
		super(leftOperand, rightOperand, sourceLocation);
	}

	@Override
	public java.lang.Object getValue(Program program) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
