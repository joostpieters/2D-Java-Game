package jumpingalien.part3.programs;

public abstract class ProgramCode {
	ProgramCode(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	protected SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	private final SourceLocation sourceLocation;
}
