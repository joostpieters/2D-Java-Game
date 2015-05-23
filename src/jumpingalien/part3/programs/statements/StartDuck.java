package jumpingalien.part3.programs.statements;

import jumpingalien.model.Mazub;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.Statement;

public class StartDuck extends Statement {

	public StartDuck(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	protected void runStatement(Program program) {
		if(program.getSourceLocation() == null || program.getSourceLocation() == this.getSourceLocation()){
			if(program.getSourceLocation() == this.getSourceLocation()){
				program.setSourceLocation(null);
			}
			program.lowerLinesToRun();
			if(program.getObject() instanceof Mazub){
				((Mazub) program.getObject()).startDucking();
			}
		} else {
			if(program.getInForEach() > 0){
				program.setWellFormed(false);
			}
		}
	}

}
