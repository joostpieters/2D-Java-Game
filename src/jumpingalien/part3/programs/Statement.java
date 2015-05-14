package jumpingalien.part3.programs;

import java.util.Map;

import jumpingalien.model.Program;

public abstract class Statement extends ProgramCode {
	public Statement(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	
	protected abstract void runStatement(Program program);
	
	public void run(Program program){
		if(program.getLinesToRun() > 0){
			if(this.getSourceLocation() == null || this.getSourceLocation() == program.getSourceLocation()){
				if(this.getSourceLocation() == program.getSourceLocation()){
					program.setSourceLocation(null);
				}
				runStatement(program);
			}
		} else if(program.getSourceLocation() == null) {
			program.setSourceLocation(getSourceLocation());
		}
	}
}
