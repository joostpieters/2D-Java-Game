package jumpingalien.part3.programs.statements;

import jumpingalien.model.Mazub;
import jumpingalien.model.Program;
import jumpingalien.model.Shark;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.Statement;

public class StartJump extends Statement {

	public StartJump(SourceLocation sourceLocation) {
		super(sourceLocation);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void runStatement(Program program) {
		if(program.getSourceLocation() == null || program.getSourceLocation() == this.getSourceLocation()){
			if(program.getSourceLocation() == this.getSourceLocation()){
				program.setSourceLocation(null);
			}
			program.lowerLinesToRun();
			if(program.getObject() instanceof Mazub){
				((Mazub) program.getObject()).startJump();
			} else if (program.getObject() instanceof Shark){
				((Shark) program.getObject()).startJump();
			}
		}
	}

}
