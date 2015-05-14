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
			while((boolean) condition.getValue(program) && program.getLinesToRun() > 0){
				body.run(program);
				program.lowerLinesToRun();
			}
		} else {
			body.run(program);
			if(program.getSourceLocation() == null){
				program.lowerLinesToRun();
				while((boolean) condition.getValue(program) && program.getLinesToRun() > 0){
					body.run(program);
					program.lowerLinesToRun();
				}
			}
		}
	}


}