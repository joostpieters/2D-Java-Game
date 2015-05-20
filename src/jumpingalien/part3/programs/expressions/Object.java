package jumpingalien.part3.programs.expressions;

import jumpingalien.model.GameObject;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.SourceLocation;

public class Object extends Literal {
	public Object(GameObject gameObject, SourceLocation sourceLocation) {
		super(sourceLocation);
		object = gameObject;
	}
	
	public Object(GameObject gameObject, boolean self, SourceLocation sourceLocation) {
		this(gameObject, sourceLocation);
		this.self = self;
	}
	
	private GameObject getObject() {
		return object;
	}
	
	private final GameObject object;
	
	private boolean isSelf() {
		return self;
	}

	private void setSelf(boolean self) {
		this.self = self;
	}
	
	private boolean self;

	@Override
	public GameObject getValue(Program program) {
		if(isSelf()){
			return program.getObject();
		} else {
			return getObject();
		}
	}

}
