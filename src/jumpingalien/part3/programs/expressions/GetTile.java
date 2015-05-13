package jumpingalien.part3.programs.expressions;

import jumpingalien.model.GameObject;
import jumpingalien.model.Program;
import jumpingalien.model.World;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;

public class GetTile extends Expression {

	public GetTile(Expression x, Expression y, SourceLocation sourceLocation) {
		super(sourceLocation);
		setX(x);
		setY(y);
	}

	private Expression getX() {
		return x;
	}

	private void setX(Expression expression) {
		this.x = expression;
	}
	
	private Expression x;
	
	private Expression getY() {
		return y;
	}

	private void setY(Expression expression) {
		this.y = expression;
	}
	
	private Expression y;
	
	@Override
	public java.lang.Double getValue(Program program) {
		World world = ((GameObject) program.getObject()).getWorld();
		try {
			return (double) world.getGeologicalFeatureByPixel( (int) ((double) getX().getValue(program)), (int) ((double) getY().getValue(program)));
		} catch (IllegalArgumentException e){
			return null;
			//TODO miss nog eens overbezinnen ? 
		}
	}

}
