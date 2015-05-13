package jumpingalien.part3.programs;

import java.util.Map;

import jumpingalien.model.Program;

public abstract class Statement extends ProgramCode {
	public Statement(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	
	public abstract void run(Program program);
}
