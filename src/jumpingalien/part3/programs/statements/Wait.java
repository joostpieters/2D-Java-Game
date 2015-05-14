package jumpingalien.part3.programs.statements;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.Statement;

public class Wait extends Statement {

	public Wait(Expression duration, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.duration = duration;
		setTimeWait(0);
	}
	
	private Expression getDuration() {
		return duration;
	}
	
	private final Expression duration;
	
	private int getTimeWait() {
		return timeWait;
	}

	private void setTimeWait(int timeWait) {
		this.timeWait = timeWait;
	}	
	
	private int timeWait;

	@Override
	public void run(Program program) {
		if(getDuration().getValue(program) instanceof Double){
			if(getTimeWait() == 0){
				setTimeWait((int) getDuration().getValue(program));
			}
			while(getTimeWait() > 0 && program.getLinesToRun() > 0){
				program.lowerLinesToRun();
				setTimeWait(getTimeWait()-1);
			}			
		} else {
			assert(false);
			//TODO error ? expection ?
		}
	}

}
