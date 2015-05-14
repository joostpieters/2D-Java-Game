package jumpingalien.part3.programs.statements;

import java.util.Map;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.Statement;
import jumpingalien.part3.programs.Type;

//TODO getters en setters !
public class Assignment extends Statement {

	public Assignment(String variableName, Type variableType, Expression value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.variableName = variableName;
		this.variableType = variableType;
		this.value = value;
	}
	
	private String variableName;
	private Type variableType;
	private Expression value;

	@Override
	public void run(Program program) {
		program.lowerLinesToRun();
		program.getDeclarationVariables().put(variableName, value.getValue(program));
	}
}
