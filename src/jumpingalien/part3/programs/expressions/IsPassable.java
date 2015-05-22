package jumpingalien.part3.programs.expressions;

import jumpingalien.model.Program;
import jumpingalien.model.Tile;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;

public class IsPassable extends Expression {

	public IsPassable(Expression expression, SourceLocation sourceLocation) {
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
			int geologicalFeature = ((Tile) getExpression().getValue(program)).getGeologicalFeature();
			return geologicalFeature == 0 || geologicalFeature == 2 || geologicalFeature == 3;
		} else {
			program.setLinesToRun(0);
			program.setHasAnError(true);
			return false;
		}
	}

}
