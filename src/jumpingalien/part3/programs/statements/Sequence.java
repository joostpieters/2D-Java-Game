package jumpingalien.part3.programs.statements;

import java.util.Iterator;
import java.util.List;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.Statement;

public class Sequence extends Statement {

	public Sequence(List<Statement> statements, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.statements = statements;
	}
	
	private List<Statement> getStatements() {
		return statements;
	}
	
	private final List<Statement> statements;

	@Override
	public void runStatement(Program program) {
		if(program.getSourceLocation() == this.getSourceLocation()){
			program.setSourceLocation(null);
		}
		Iterator<Statement> iterator = getStatements().iterator();
		while(iterator.hasNext() && (program.getLinesToRun() != 0 || program.getSourceLocation() == null)){
			iterator.next().run(program);
		}
	}
}
