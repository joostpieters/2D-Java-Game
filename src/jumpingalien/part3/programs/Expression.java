package jumpingalien.part3.programs;

import jumpingalien.model.Program;

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

	public abstract Object getValue(Program program);
	
	public static int doubleToInteger(Double value) {
		double tempValue = value;
		if (tempValue > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		} else if (tempValue < Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		} else {
			return (int) tempValue;
		}
	}
}
