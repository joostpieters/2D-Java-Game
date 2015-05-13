package jumpingalien.part3.programs.expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.SourceLocation;

public class Double extends Literal {
	public Double(double value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.value = value;
	}
	
	private double value;

	@Override
	public java.lang.Object getValue(Program program) {
		// TODO Auto-generated method stub
		return null;
	}


}
