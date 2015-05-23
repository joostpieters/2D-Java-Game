package jumpingalien.part3.programs.statements;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.ReturnTypeDetection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.Statement;
import jumpingalien.part3.programs.exceptions.TypeError;

public class While extends Statement {

	public While(Expression condition, Statement body, SourceLocation sourceLocation) {
		super(sourceLocation);
		if(!ReturnTypeDetection.returnsBoolean(condition)){
			throw new TypeError(sourceLocation);
		}
		this.condition = condition;
		this.body = body;
	}

	private Expression getCondition() {
		return condition;
	}

	private final Expression condition;
	
	private Statement getBody() {
		return body;
	}

	private final Statement body;
	
	@Override
	public void runStatement(Program program) {
		if(program.getSourceLocation() == null || program.getSourceLocation() == this.getSourceLocation()){
			if(program.getSourceLocation() == this.getSourceLocation()){
				program.setSourceLocation(null);
			}
			program.lowerLinesToRun();
			while(getCondition().getValue(program) instanceof Boolean && (boolean) getCondition().getValue(program) && !program.isBreakActivated()){
				getBody().run(program);
				if(program.getLinesToRun() > 0){
					program.lowerLinesToRun();
				} else if(program.getSourceLocation() == null){
					program.setSourceLocation(this.getSourceLocation());
					break;
				} else {
					break;
				}
			}
			if(!(getCondition().getValue(program) instanceof Boolean)){
				program.stopBecauseError();
			}
			if(program.isBreakActivated()){
				program.setBreakActivated(false);
			}
		} else {
			program.setInWhile(program.getInWhile()+1);
			getBody().run(program);
			program.setInWhile(program.getInWhile()-1);
			if(program.getSourceLocation() == null && program.getLinesToRun() > 0){
				this.runStatement(program);
			} else if (program.getLinesToRun() == 0 && program.getSourceLocation() == null) {
				program.setSourceLocation(this.getSourceLocation());
			}
		}
	}


}
