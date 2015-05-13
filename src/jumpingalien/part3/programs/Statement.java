package jumpingalien.part3.programs;

public abstract class Statement extends ProgramCode {
	public Statement(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	
	public abstract void run();
}
