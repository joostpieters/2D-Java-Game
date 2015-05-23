package jumpingalien.part3.programs.statements;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.ReturnTypeDetection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.Statement;
import jumpingalien.part3.programs.exceptions.TypeError;

public class Print extends Statement {

	public Print(Expression value, SourceLocation sourceLocation) {
		super(sourceLocation);
		if(!(ReturnTypeDetection.returnsDouble(value) 
				|| ReturnTypeDetection.returnsBoolean(value)
				|| ReturnTypeDetection.returnsDirection(value)
				|| ReturnTypeDetection.returnsObject(value))){
			throw new TypeError(sourceLocation);
		}
		this.value = value;
	}
	
	private Expression value;

	@Override
	public void runStatement(Program program) {
		if(program.getSourceLocation() == null || program.getSourceLocation() == this.getSourceLocation()){
			if(program.getSourceLocation() == this.getSourceLocation()){
				program.setSourceLocation(null);
			}
			program.lowerLinesToRun();
			System.out.println(value.getValue(program));
		}
	}

}
