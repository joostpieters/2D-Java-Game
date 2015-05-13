package jumpingalien.part3.programs.statements;

import java.util.Map;

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
	public void run(Map<String, Expression> variables) {
		System.out.println(value.getValue());
	}
}
