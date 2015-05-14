package jumpingalien.part3.programs.statements;

import java.util.Map;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.Statement;
import jumpingalien.part3.programs.Type;

public class Print extends Statement {

	public Print(Expression value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.value = value;
	}
	
	private Expression value;

	@Override
	public void run(Program program) {
		program.lowerLinesToRun();
		System.out.println(value.getValue(program));
	}

}
