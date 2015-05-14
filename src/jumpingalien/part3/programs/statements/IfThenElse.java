package jumpingalien.part3.programs.statements;

import java.util.Map;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.Statement;
import jumpingalien.part3.programs.Type;

public class IfThenElse extends Statement {

	public IfThenElse(Expression condition, Statement ifBody, Statement elseBody, SourceLocation sourceLocation) {
		super(sourceLocation);
		setCondition(condition);
		setIfBody(ifBody);
		setElseBody(elseBody);
		
	}
	
	@Override
	public void run(Program program) {
		program.lowerLinesToRun();
		if((boolean) getCondition().getValue(program)){
			getIfBody().run(program);
		} else if(getElseBody() != null) {
			getElseBody().run(program);
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
