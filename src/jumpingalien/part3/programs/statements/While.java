package jumpingalien.part3.programs.statements;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.Statement;

public class While extends Statement {

	public While(Expression condition, Statement body, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.condition = condition;
		this.body = body;
	}
	
	private Expression condition;
	private Statement body;
	@Override
	public void run(Program program) {
		// TODO Auto-generated method stub
		
	}


}
