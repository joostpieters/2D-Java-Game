package jumpingalien.part3.programs.statements;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.ReturnTypeDetection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.Statement;
import jumpingalien.part3.programs.Type;
import jumpingalien.part3.programs.exceptions.TypeError;

public class Assignment extends Statement {

	public Assignment(String variableName, Type variableType, Expression value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.variableName = variableName;
		if((variableType instanceof jumpingalien.part3.programs.types.Boolean && !ReturnTypeDetection.returnsBoolean(value)) 
				||(variableType instanceof jumpingalien.part3.programs.types.GameItem && !ReturnTypeDetection.returnsObject(value)) 
				||(variableType instanceof jumpingalien.part3.programs.types.Double && !ReturnTypeDetection.returnsDouble(value))
				||(variableType instanceof jumpingalien.part3.programs.types.Direction && !ReturnTypeDetection.returnsDirection(value))){
			throw new TypeError(sourceLocation);
		}
		this.variableType = variableType;
		this.value = value;
	}
	
	private String getVariableName() {
		return variableName;
	}
	
	private final String variableName;

	private Type getVariableType() {
		return variableType;
	}
	
	private final Type variableType;

	private Expression getValue() {
		return value;
	}
	private final Expression value;

	@Override
	public void runStatement(Program program) {
		if(program.getSourceLocation() == null || program.getSourceLocation() == this.getSourceLocation()){
			if(program.getSourceLocation() == this.getSourceLocation()){
				program.setSourceLocation(null);
			}
			program.lowerLinesToRun();
			if(program.getGlobalVariables().containsKey(getVariableName()) && program.getGlobalVariables().containsValue(getVariableType())){
				program.getDeclarationVariables().put(getVariableName(), getValue().getValue(program));
			} else {
				program.stopBecauseError();
			}
		}
	}
}
