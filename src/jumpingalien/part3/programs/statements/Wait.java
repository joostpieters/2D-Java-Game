package jumpingalien.part3.programs.statements;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.ReturnTypeDetection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.Statement;
import jumpingalien.part3.programs.exceptions.TypeError;
import jumpingalien.util.Util;

public class Wait extends Statement {

	public Wait(Expression duration, SourceLocation sourceLocation) {
		super(sourceLocation);
		if(!ReturnTypeDetection.returnsDouble(duration)){
			throw new TypeError(sourceLocation);
		}
		this.duration = duration;
		setTimeWait(0);
	}
	
	private Expression getDuration() {
		return duration;
	}
	
	private final Expression duration;
	
	private double getTimeWait() {
		return timeWait;
	}

	private void setTimeWait(double d) {
		this.timeWait = d;
	}	
	
	private double timeWait;

	@Override
	public void runStatement(Program program) {
		if(program.getSourceLocation() == null || program.getSourceLocation() == this.getSourceLocation()){
			if(program.getSourceLocation() == this.getSourceLocation()){
				program.setSourceLocation(null);
			}
			if(getDuration().getValue(program) instanceof Double){
				if(getTimeWait() < 0.001){
					setTimeWait((double) getDuration().getValue(program));
				}
				while(Util.fuzzyGreaterThanOrEqualTo(getTimeWait(), 0.001) && program.getLinesToRun() > 0){
					program.lowerLinesToRun();
					setTimeWait(getTimeWait()-0.001);
				}
				if(program.getLinesToRun() == 0 && Util.fuzzyGreaterThanOrEqualTo(getTimeWait(), 0.001)){
					program.setSourceLocation(getSourceLocation());
				}
			} else {
				program.stopBecauseError();
			}
		}
	}

}
