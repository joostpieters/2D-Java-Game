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
	public void runStatement(Program program) {
		if(program.getSourceLocation() == null || program.getSourceLocation() == this.getSourceLocation()){
			if(program.getSourceLocation() == this.getSourceLocation()){
				program.setSourceLocation(null);
			}
			if(getDuration().getValue(program) instanceof Double){
				if(getTimeWait() == 0){
					setTimeWait((int) getDuration().getValue(program));
				}
				while(getTimeWait() > 0 && program.getLinesToRun() > 0){
					program.lowerLinesToRun();
					setTimeWait(getTimeWait()-1);
				}
				if(program.getLinesToRun() == 0){
					program.setSourceLocation(getSourceLocation());
				}
			} else {
				assert(false);
				//TODO error ? expection ?
			}
		}
	}

}
