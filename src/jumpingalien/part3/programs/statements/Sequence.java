package jumpingalien.part3.programs.statements;

import java.util.List;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.Statement;

public class Sequence extends Statement {

	public Sequence(List<Statement> statements, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.statements = statements;
	}
	
	private List<Statement> statements;

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
