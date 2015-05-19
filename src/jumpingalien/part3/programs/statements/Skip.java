package jumpingalien.part3.programs.statements;

import jumpingalien.model.Mazub;
import jumpingalien.model.Program;
import jumpingalien.model.Shark;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.Statement;

public class Skip extends Statement {

	public Skip(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	protected void runStatement(Program program) {
		if(program.getSourceLocation() == null || program.getSourceLocation() == this.getSourceLocation()){
			if(program.getSourceLocation() == this.getSourceLocation()){
				program.setSourceLocation(null);
			}
			program.lowerLinesToRun();
		}
	}

}
