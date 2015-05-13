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
	
	private String name;
	private Type type;
	
	@Override
	public java.lang.Object getValue(Program program) {
		Expression value = program.getDeclarationVariables().get(name);
		return value.getValue(program);
	}
	

}
