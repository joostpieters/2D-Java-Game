package jumpingalien.part3.programs;

public abstract class ProgramCode {
	ProgramCode(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	private SourceLocation sourceLocation;
}
