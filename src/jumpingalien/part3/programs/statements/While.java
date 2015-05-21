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
	public void runStatement(Program program) {
		if(program.getSourceLocation() == null || program.getSourceLocation() == this.getSourceLocation()){
			if(program.getSourceLocation() == this.getSourceLocation()){
				program.setSourceLocation(null);
			}
			program.lowerLinesToRun();
			while((boolean) condition.getValue(program) && !program.isBreakActivated()){
				body.run(program);
				if(program.getLinesToRun() > 0){
					program.lowerLinesToRun();
				} else if(program.getSourceLocation() == null){
					program.setSourceLocation(this.getSourceLocation());
					break;
				} else {
					break;
				}
			}
			if(program.isBreakActivated()){
				program.setBreakActivated(false);
			}
		} else {
			program.setInWhile(program.getInWhile()+1);
			body.run(program);
			program.setInWhile(program.getInWhile()-1);
			if(program.getSourceLocation() == null && program.getLinesToRun() > 0){
				this.runStatement(program);
			} else if (program.getLinesToRun() == 0 && program.getSourceLocation() == null) {
				program.setSourceLocation(this.getSourceLocation());
			}
		}
	}


}
