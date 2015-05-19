package jumpingalien.part3.programs.statements;

import java.util.Map;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.Statement;
import jumpingalien.part3.programs.Type;

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
			program.addBreak();
			//TODO moeten er hier ook een lijn worden verwijderd
		}
	}

}
