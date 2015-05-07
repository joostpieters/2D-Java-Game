package jumpingalien.part3.programs.expressions;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.expressions.Double;

public class RandomDouble extends Literal {

	public RandomDouble(Expression expression, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.expression = expression;
		
	}
	
	private Expression expression;

	@Override
	public java.lang.Object getValue() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
