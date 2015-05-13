package jumpingalien.part3.programs.statements;

import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.Statement;

public class Print extends Statement {

	public Print(Expression value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.value = value;
	}
	
	private Expression value;

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
