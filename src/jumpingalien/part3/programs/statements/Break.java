package jumpingalien.part3.programs.statements;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.Statement;

public class Break extends Statement {

	public Break(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public void runStatement(Program program) {
		if(program.getSourceLocation() == null || program.getSourceLocation() == this.getSourceLocation()){
			if(program.getSourceLocation() == this.getSourceLocation()){
				program.setSourceLocation(null);
			}
			program.lowerLinesToRun();
			program.setBreakActivated(true);
		} else {
			if(program.getInWhile() == 0  && program.getInForEach() == 0){
				program.setWellFormed(false);
			}
		}
	}

}
