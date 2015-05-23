package jumpingalien.part3.programs.statements;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.ReturnTypeDetection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.Statement;
import jumpingalien.part3.programs.exceptions.TypeError;

public class IfThenElse extends Statement {

	public IfThenElse(Expression condition, Statement ifBody, Statement elseBody, SourceLocation sourceLocation) {
		super(sourceLocation);
		if(!ReturnTypeDetection.returnsBoolean(condition)){
			throw new TypeError(sourceLocation);
		}
		setCondition(condition);
		setIfBody(ifBody);
		setElseBody(elseBody);		
	}
	
	@Override
	public void runStatement(Program program) {
		if(program.getSourceLocation() == null || program.getSourceLocation() == this.getSourceLocation()){
			if(program.getSourceLocation() == this.getSourceLocation()){
				program.setSourceLocation(null);
			}
			program.lowerLinesToRun();
			if(getCondition().getValue(program) instanceof Boolean){
				if((boolean) getCondition().getValue(program)){
					getIfBody().run(program);
				} else if(getElseBody() != null) {
					getElseBody().run(program);
				}
			} else {
				program.stopBecauseError();
			}
		} else if(program.getLinesToRun() > 0){
			getIfBody().run(program);
			if(getElseBody() != null && program.getSourceLocation() != null && program.getLinesToRun() > 0) {
				getElseBody().run(program);
			}
		}
		
	}

	private Expression getCondition() {
		return condition;
	}

	private void setCondition(Expression condition) {
		this.condition = condition;
	}

	private Statement getIfBody() {
		return ifBody;
	}

	private void setIfBody(Statement ifBody) {
		this.ifBody = ifBody;
	}

	private Statement getElseBody() {
		return elseBody;
	}

	private void setElseBody(Statement elseBody) {
		this.elseBody = elseBody;
	}
	
	private Expression condition;
	private Statement ifBody;
	private Statement elseBody;

}
