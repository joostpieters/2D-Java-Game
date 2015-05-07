package jumpingalien.part3.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;

public class Bool extends Literal {
	public Bool(boolean value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.value = value;
	}
	
	private boolean value;

	@Override
	public java.lang.Object getValue() {
		// TODO Auto-generated method stub
		return null;
	}
}
