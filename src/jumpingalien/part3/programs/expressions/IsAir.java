package jumpingalien.part3.programs.expressions;

import jumpingalien.model.Program;
import jumpingalien.model.Tile;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;

public class IsAir extends Expression {

	public IsAir(Expression expression, SourceLocation sourceLocation) {
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
			return ((Tile) getExpression().getValue(program)).getGeologicalFeature() == 0;
		} else {
			program.setLinesToRun(0);
			program.setHasAnError(true);
			return false;
		}
	}

}
