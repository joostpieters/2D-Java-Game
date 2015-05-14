package jumpingalien.part3.programs.statements;

import java.util.List;
import java.util.Map;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.Statement;
import jumpingalien.part3.programs.Type;

public class Sequence extends Statement {

	public Sequence(List<Statement> statements, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.statements = statements;
	}
	
	private List<Statement> statements;

	@Override
	public void runStatement(Program program) {
		for(Statement statement : statements){
			if(program.getLinesToRun() > 0) {
				statement.run(program);
			}
		}
	}


}
