package jumpingalien.part3.programs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import jumpingalien.part3.programs.expressions.*;
import jumpingalien.part3.programs.expressions.Object;
import jumpingalien.part3.programs.types.Double;

public interface  ReturnTypeDetection {
	
	static boolean returnsDouble(Expression ... expressions){
		List<Expression> lijst = new ArrayList<>(Arrays.asList(expressions));
		Stream<Expression> stream = lijst.parallelStream();
		if(stream.anyMatch((expression) -> !(expression instanceof Addition || expression instanceof Division || 
				expression instanceof jumpingalien.part3.programs.expressions.Double || 
				expression instanceof GetHeight || expression instanceof GetHitPoints || 
				expression instanceof GetWidth || expression instanceof GetX || 
				expression instanceof GetY || expression instanceof Multiplication || 
				expression instanceof RandomDouble || expression instanceof SquareRoot || 
				expression instanceof Substraction || (expression instanceof Variable 
						&& ((Variable)expression).getType() instanceof Double)))){
			return false;
		} else {
			return true;
		}
	}
	
	static boolean returnsBoolean(Expression ... expressions){
		List<Expression> lijst = new ArrayList<>(Arrays.asList(expressions));
		Stream<Expression> stream = lijst.parallelStream();
		if(stream.anyMatch((expression)->(!(expression instanceof Bool || expression instanceof Conjunction || 
				expression instanceof Disjunction || expression instanceof Equals 
				|| expression instanceof GreaterThan || 
				expression instanceof GreaterThanOrEqual|| expression instanceof IsAir || 
				expression instanceof IsDead || expression instanceof IsDucking || 
				expression instanceof IsJumping || expression instanceof IsMagma || 
				expression instanceof IsMoving || expression instanceof IsPassable || 
				expression instanceof IsPlant || expression instanceof IsShark || 
				expression instanceof IsSlime || expression instanceof IsTerrain || 
				expression instanceof IsWater || expression instanceof LessThan || 
				expression instanceof LessThanOrEqual || expression instanceof Negation ||
				expression instanceof NotEquals || expression instanceof IsMagma || 
				expression instanceof IsMazub || (expression instanceof Variable && 
				((Variable)expression).getType() instanceof jumpingalien.part3.programs.types.Boolean))))){
			return false;
		}
		return true;
	}
	
	static boolean returnsObject(Expression ... expressions){
		List<Expression> lijst = new ArrayList<>(Arrays.asList(expressions));
		Stream<Expression> stream = lijst.parallelStream();
		if(stream.anyMatch((expression)->(!(expression instanceof Object || expression instanceof GetTile || 
				expression instanceof SearchObject || (expression instanceof Variable 
						&& ((Variable)expression).getType() instanceof jumpingalien.part3.programs.types.GameItem))))){
			return false;
		}
		return true;
	}
	
	static boolean returnsDirection(Expression ... expressions){
		List<Expression> lijst = new ArrayList<>(Arrays.asList(expressions));
		Stream<Expression> stream = lijst.parallelStream();
		if(stream.anyMatch((expression)->(!(expression instanceof Direction || expression instanceof GetTile || 
					expression instanceof SearchObject || (expression instanceof Variable 
							&& ((Variable)expression).getType() instanceof jumpingalien.part3.programs.types.Direction))))){
			return false;
		}
		return true;
	}

}
