package jumpingalien.part3.programs.statements;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.Statement;

public class StartRun extends Statement {

	public StartRun(Expression direction, SourceLocation sourceLocation) {
		super(sourceLocation);
		setDirection(direction);
	}
	
	private Expression getDirection() {
		return direction;
	}

	private void setDirection(Expression direction) {
		this.direction = direction;
	}
	
	private Expression direction;

	@Override
	public void run(Program program) {
		// TODO Auto-generated method stub
		
	}

}
