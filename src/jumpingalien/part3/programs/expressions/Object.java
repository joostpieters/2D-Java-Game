package jumpingalien.part3.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;

public class Object extends Literal {
	public Object(Object object, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.object = object;
	}
	
	public Object(Object object, boolean self, SourceLocation sourceLocation) {
		this(object, sourceLocation);
		this.self = self;
	}
	
	private Object object;
	
	private boolean self;

	@Override
	public java.lang.Object getValue() {
		// TODO Auto-generated method stub
		return null;
	}
}
