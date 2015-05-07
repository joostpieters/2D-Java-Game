package jumpingalien.part3.programs;

public abstract class Expression extends ProgramCode {
	public Expression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	
	public boolean hasAsSubExpression(Expression expression) {
		return this == expression;
	}

	public abstract Object getValue();
	
	private SourceLocation sourceLocation;
}
