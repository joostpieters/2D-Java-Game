package jumpingalien.part3.programs;

import jumpingalien.model.Program;

public abstract class Statement implements ReturnTypeDetection {
	public Statement(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	protected SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	private final SourceLocation sourceLocation;
	
	protected abstract void runStatement(Program program);
	
	public void run(Program program){
		if(program.getLinesToRun() > 0 && !program.isBreakActivated()){
			runStatement(program);
		} else if(program.getSourceLocation() == null && !program.isBreakActivated()) {
			program.setSourceLocation(getSourceLocation());
		}
	}
}
