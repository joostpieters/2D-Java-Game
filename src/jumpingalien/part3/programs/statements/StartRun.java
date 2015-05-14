package jumpingalien.part3.programs.statements;

import jumpingalien.model.GameObject;
import jumpingalien.model.Motion;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.IProgramFactory.Direction;
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
		program.lowerLinesToRun();
		if(program.getObject() instanceof GameObject && (direction.getValue(program) == jumpingalien.part3.programs.IProgramFactory.Direction.LEFT || direction.getValue(program) == jumpingalien.part3.programs.IProgramFactory.Direction.RIGHT)){
			//TODO enkel links en rechts ok ?
			if (direction.getValue(program) == jumpingalien.part3.programs.IProgramFactory.Direction.LEFT) {
				 ((GameObject) program.getObject()).startMove(Motion.LEFT);
			} else if (direction.getValue(program) == jumpingalien.part3.programs.IProgramFactory.Direction.RIGHT) {
				((GameObject) program.getObject()).startMove(Motion.RIGHT);
			} else {
				// TODO miss beter acception
				assert(false);
			}
		} else {
			assert(false);
		}
	}

}
