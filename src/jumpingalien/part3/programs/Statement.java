package jumpingalien.part3.programs;

import java.util.Map;

public abstract class Statement extends ProgramCode {
	public Statement(SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	
	public abstract void run(Map<String, Expression> variables);
}
