package jumpingalien.part3.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;

public class Object extends Literal {
	public Object(Object object, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.object = object;
	}
	
	private Object object;

	@Override
	public java.lang.Object getValue() {
		// TODO Auto-generated method stub
		return null;
	}
}
