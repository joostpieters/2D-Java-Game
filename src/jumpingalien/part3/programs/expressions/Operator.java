package jumpingalien.part3.programs.expressions;

import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;

public abstract class Operator extends Expression {

	public Operator(SourceLocation sourceLocation) {
		super(sourceLocation);
		// TODO Auto-generated constructor stub
	}

	public abstract Expression getOperandAt(int index) throws IndexOutOfBoundsException;
	
	public abstract int getNbOperands();
	
	public boolean canHaveAsOperandAt(int index, Expression operand) {
		if ( (index < 0) || (index >= getNbOperands()) )
			return false;
		if (operand == null)
			return false;
		return ! operand.hasAsSubExpression(this);
	}
	
	@Override
	public boolean hasAsSubExpression(Expression expression) {
		if (super.hasAsSubExpression(expression))
			return true;
		for (int i = 0; i < this.getNbOperands(); i++) {
			if (this.getOperandAt(i).hasAsSubExpression(expression))
				return true;
		}
		return false;
	}
}
