package jumpingalien.part3.programs;

public abstract class ProgramCode {
	ProgramCode(SourceLocation sourceLocation) {
		this.setSourceLocation(sourceLocation);
	}
	
	protected SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	protected void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	private SourceLocation sourceLocation;
}
