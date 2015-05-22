package jumpingalien.part3.programs.expressions;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.Type;

public class Variable extends Literal {
	public Variable(String name, Type type, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.name = name;
		this.type = type;
	}
	
	private String getName() {
		return this.name;
	}
	
	private final String name;
	
	public Type getType() {
		return type;
	}
	
	private final Type type;
	
	@Override
	public java.lang.Object getValue(Program program) {
		return program.getDeclarationVariables().get(getName());
	}
	

}
