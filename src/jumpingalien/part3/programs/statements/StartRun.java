package jumpingalien.part3.programs.statements;

import jumpingalien.model.GameObject;
import jumpingalien.model.Motion;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.Statement;

public class StartRun extends Statement {

	public StartRun(Expression direction, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.direction = direction;
	}
		
	private Expression getDirection() {
		return direction;
	}
		
	private final Expression direction;

	@Override
	public void runStatement(Program program) {
		if(program.getSourceLocation() == null || program.getSourceLocation() == this.getSourceLocation()){
			if(program.getSourceLocation() == this.getSourceLocation()){
				program.setSourceLocation(null);
			}
			program.lowerLinesToRun();
			if(program.getObject() instanceof GameObject && (getDirection().getValue(program) == jumpingalien.part3.programs.IProgramFactory.Direction.LEFT 
					|| getDirection().getValue(program) == jumpingalien.part3.programs.IProgramFactory.Direction.RIGHT)){
				if (getDirection().getValue(program) == jumpingalien.part3.programs.IProgramFactory.Direction.LEFT) {
					 ((GameObject) program.getObject()).startMove(Motion.LEFT);
				} else if (getDirection().getValue(program) == jumpingalien.part3.programs.IProgramFactory.Direction.RIGHT) {
					((GameObject) program.getObject()).startMove(Motion.RIGHT);
				}
			} else {
				program.stopBecauseError();
			}
		} else {
			if(program.getInForEach() > 0){
				program.setWellFormed(false);
			}
		}
	}

}
