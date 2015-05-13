package jumpingalien.part3.programs.expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.SourceLocation;

public class Bool extends Literal {
	public Bool(boolean value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.value = value;
	}
	
	private boolean value;
	
	private boolean getValue() {
		return value;
	}

	@Override
	public java.lang.Boolean getValue(Program program) {
		// TODO Auto-generated method stub
		return getValue();
	}
}
