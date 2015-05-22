package jumpingalien.part3.programs.expressions;

import jumpingalien.model.Program;
import jumpingalien.model.Tile;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;

public class IsWater extends Expression {

	public IsWater(Expression expression, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.expression = expression;
	}
	
	private final Expression getExpression() {
		return expression;
	}
	
	private final Expression expression;

	@Override
	public java.lang.Boolean getValue(Program program) {
		if (getExpression().getValue(program) instanceof Tile) {
			return ((Tile) getExpression().getValue(program)).getGeologicalFeature() == 2;
		} else {
			program.setLinesToRun(0);
			program.setHasAnError(true);
			return false;
		}
	}

}
