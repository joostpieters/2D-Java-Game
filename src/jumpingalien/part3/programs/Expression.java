package jumpingalien.part3.programs;

import jumpingalien.model.Program;

public abstract class Expression extends ProgramCode {
	public Expression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	
	public boolean hasAsSubExpression(Expression expression) {
		return this == expression;
	}

	public abstract Object getValue(Program program);
	
	private SourceLocation sourceLocation;
	
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
