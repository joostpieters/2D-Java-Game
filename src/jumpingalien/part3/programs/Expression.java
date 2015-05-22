package jumpingalien.part3.programs;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.expressions.*;
import jumpingalien.part3.programs.types.Double;

public abstract class Expression {
	public Expression(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	protected SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	private final SourceLocation sourceLocation;
	
	public boolean hasAsSubExpression(Expression expression) {
		return this == expression;
	}

	public abstract java.lang.Object getValue(Program program);
	
	protected static int doubleToInteger(java.lang.Double value) {
		double tempValue = value;
		if (tempValue > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		} else if (tempValue < Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		} else {
			return (int) tempValue;
		}
	}
	
	static boolean returnsDouble(Expression ... expressions){
		boolean isValid = true;
		for(Expression expression : expressions){
			if(!(expression instanceof Addition || expression instanceof Division || 
					expression instanceof jumpingalien.part3.programs.expressions.Double || 
					expression instanceof GetHeight || expression instanceof GetHitPoints || 
					expression instanceof GetWidth || expression instanceof GetX || 
					expression instanceof GetY || expression instanceof Multiplication || 
					expression instanceof RandomDouble || expression instanceof SquareRoot || 
					expression instanceof Substraction || (expression instanceof Variable 
							&& ((Variable)expression).getType() instanceof Double))){
				isValid = false;
				break;
			}
		}
		return isValid;
	}
}
