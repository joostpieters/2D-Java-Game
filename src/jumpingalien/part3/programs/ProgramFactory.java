package jumpingalien.part3.programs;

import java.util.List;
import java.util.Map;
import java.util.Random;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.expressions.*;
import jumpingalien.part3.programs.expressions.Double;
import jumpingalien.part3.programs.expressions.Object;
import jumpingalien.part3.programs.statements.*;
import jumpingalien.part3.programs.types.GameObject;

public class ProgramFactory implements IProgramFactory<Expression, Statement, Type, Program> {

	@Override
	public Expression createReadVariable(String variableName,
			Type variableType, SourceLocation sourceLocation) {
		return new Variable(variableName, variableType, sourceLocation);
	}

	@Override
	public Expression createDoubleConstant(double value,
			SourceLocation sourceLocation) {
		return new Double(value, sourceLocation);
	}

	@Override
	public Expression createTrue(SourceLocation sourceLocation) {
		return new Bool(true, sourceLocation);
	}

	@Override
	public Expression createFalse(SourceLocation sourceLocation) {
		return new Bool(false, sourceLocation);
	}

	@Override
	public Expression createNull(SourceLocation sourceLocation) {
		return new Object(null, sourceLocation);
	}

	@Override
	public Expression createSelf(SourceLocation sourceLocation) {
		return new Object(null, true, sourceLocation);
	}

	@Override
	public Expression createDirectionConstant(
			jumpingalien.part3.programs.IProgramFactory.Direction value,
			SourceLocation sourceLocation) {
		return new jumpingalien.part3.programs.expressions.Direction(value, sourceLocation);
	}

	@Override
	public Expression createAddition(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Addition(left, right, sourceLocation);
	}

	@Override
	public Expression createSubtraction(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Substraction(left, right, sourceLocation);
	}

	@Override
	public Expression createMultiplication(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Multiplication(left, right, sourceLocation);
	}

	@Override
	public Expression createDivision(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Division(left, right, sourceLocation);
	}

	@Override
	public Expression createSqrt(Expression expr, SourceLocation sourceLocation) {
		return new SquareRoot(expr, sourceLocation);
	}

	@Override
	public Expression createRandom(Expression maxValue,
			SourceLocation sourceLocation) {
		return new RandomDouble(maxValue, sourceLocation);
	}

	@Override
	public Expression createAnd(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Conjunction(left, right, sourceLocation);
	}

	@Override
	public Expression createOr(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Disjunction(left, right, sourceLocation);
	}

	@Override
	public Expression createNot(Expression expr, SourceLocation sourceLocation) {
		return new Negation(expr, sourceLocation);
	}

	@Override
	public Expression createLessThan(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new LessThan(left, right, sourceLocation);
	}

	@Override
	public Expression createLessThanOrEqualTo(Expression left,
			Expression right, SourceLocation sourceLocation) {
		return new LessThanOrEqual(left, right, sourceLocation);
	}

	@Override
	public Expression createGreaterThan(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new GreaterThan(left, right, sourceLocation);
	}

	@Override
	public Expression createGreaterThanOrEqualTo(Expression left,
			Expression right, SourceLocation sourceLocation) {
		return new GreaterThanOrEqual(left, right, sourceLocation);
	}

	@Override
	public Expression createEquals(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new Equals(left, right, sourceLocation);
	}

	@Override
	public Expression createNotEquals(Expression left, Expression right,
			SourceLocation sourceLocation) {
		return new NotEquals(left, right, sourceLocation);
	}

	@Override
	public Expression createGetX(Expression expr, SourceLocation sourceLocation) {
		return new GetX(expr, sourceLocation);
	}

	@Override
	public Expression createGetY(Expression expr, SourceLocation sourceLocation) {
		return new GetY(expr, sourceLocation);
	}

	@Override
	public Expression createGetWidth(Expression expr, SourceLocation sourceLocation) {
		return new GetWidth(expr, sourceLocation);
	}

	@Override
	public Expression createGetHeight(Expression expr,
			SourceLocation sourceLocation) {
		return new GetHeight(expr, sourceLocation);
	}

	@Override
	public Expression createGetHitPoints(Expression expr,
			SourceLocation sourceLocation) {
		return new GetHitPoints(expr, sourceLocation);
	}

	@Override
	public Expression createGetTile(Expression x, Expression y,
			SourceLocation sourceLocation) {
		return new GetTile(x, y, sourceLocation);
	}

	@Override
	public Expression createSearchObject(Expression direction,
			SourceLocation sourceLocation) {
		return new SearchObject(direction, sourceLocation);
	}

	@Override
	public Expression createIsMazub(Expression expr, SourceLocation sourceLocation) {
		return new IsMazub(expr, sourceLocation);
	}

	@Override
	public Expression createIsShark(Expression expr,
			SourceLocation sourceLocation) {
		return new IsShark(expr, sourceLocation);
	}

	@Override
	public Expression createIsSlime(Expression expr,
			SourceLocation sourceLocation) {
		return new IsSlime(expr, sourceLocation);
	}

	@Override
	public Expression createIsPlant(Expression expr,
			SourceLocation sourceLocation) {
		return new IsPlant(expr, sourceLocation);
	}

	@Override
	public Expression createIsDead(Expression expr,
			SourceLocation sourceLocation) {
		return new IsDead(expr, sourceLocation);
	}

	@Override
	public Expression createIsTerrain(Expression expr,
			SourceLocation sourceLocation) {
		return new IsTerrain(expr, sourceLocation);
	}

	@Override
	public Expression createIsPassable(Expression expr,
			SourceLocation sourceLocation) {
		return new IsPassable(expr, sourceLocation);
	}

	@Override
	public Expression createIsWater(Expression expr,
			SourceLocation sourceLocation) {
		return new IsWater(expr, sourceLocation);
	}

	@Override
	public Expression createIsMagma(Expression expr,
			SourceLocation sourceLocation) {
		return new IsMagma(expr, sourceLocation);
	}

	@Override
	public Expression createIsAir(Expression expr, SourceLocation sourceLocation) {
		return new IsAir(expr, sourceLocation);
	}

	@Override
	public Expression createIsMoving(Expression expr, Expression direction,
			SourceLocation sourceLocation) {
		return new IsMoving(expr, direction, sourceLocation);
	}

	@Override
	public Expression createIsDucking(Expression expr,
			SourceLocation sourceLocation) {
		return new IsDucking(expr, sourceLocation);
	}

	@Override
	public Expression createIsJumping(Expression expr,
			SourceLocation sourceLocation) {
		return new IsJumping(expr, sourceLocation);
	}

	@Override
	public Statement createAssignment(String variableName, Type variableType,
			Expression value, SourceLocation sourceLocation) {
		return new Assignment(variableName, variableType, value, sourceLocation);
	}

	@Override
	public Statement createWhile(Expression condition, Statement body,
			SourceLocation sourceLocation) {
		return new While(condition, body, sourceLocation);
	}

	@Override
	public Statement createForEach(
			String variableName,
			jumpingalien.part3.programs.IProgramFactory.Kind variableKind,
			Expression where,
			Expression sort,
			jumpingalien.part3.programs.IProgramFactory.SortDirection sortDirection,
			Statement body, SourceLocation sourceLocation) {
		return new ForEach(variableName, variableKind, where, sort, sortDirection, body, sourceLocation);
	}

	@Override
	public Statement createBreak(SourceLocation sourceLocation) {
		return new Break(sourceLocation);
	}

	@Override
	public Statement createIf(Expression condition, Statement ifBody, Statement elseBody, SourceLocation sourceLocation) {
		return new IfThenElse(condition, ifBody, elseBody, sourceLocation);
	}

	@Override
	public Statement createPrint(Expression value, SourceLocation sourceLocation) {
		return new Print(value, sourceLocation);
	}

	@Override
	public Statement createStartRun(Expression direction, SourceLocation sourceLocation) {
		return new StartRun(direction, sourceLocation);
	}

	@Override
	public Statement createStopRun(Expression direction,
			SourceLocation sourceLocation) {
		return new StopRun(direction, sourceLocation);
	}

	@Override
	public Statement createStartJump(SourceLocation sourceLocation) {
		return new StartJump(sourceLocation);
	}

	@Override
	public Statement createStopJump(SourceLocation sourceLocation) {
		return new StopJump(sourceLocation);
	}

	@Override
	public Statement createStartDuck(SourceLocation sourceLocation) {
		return new StartDuck(sourceLocation);
	}

	@Override
	public Statement createStopDuck(SourceLocation sourceLocation) {
		return new StopDuck(sourceLocation);
	}

	@Override
	public Statement createWait(Expression duration,
			SourceLocation sourceLocation) {
		return new Wait(duration, sourceLocation);
	}

	@Override
	public Statement createSkip(SourceLocation sourceLocation) {
		return new Skip(sourceLocation);
	}

	@Override
	public Statement createSequence(List<Statement> statements,
			SourceLocation sourceLocation) {
		return new Sequence(statements, sourceLocation);
	}

	@Override
	public Type getDoubleType() {
		return new jumpingalien.part3.programs.types.Double();
	}

	@Override
	public Type getBoolType() {
		return new jumpingalien.part3.programs.types.Boolean();
	}

	@Override
	public Type getGameObjectType() {
		return new GameObject();
	}

	@Override
	public Type getDirectionType() {
		return new jumpingalien.part3.programs.types.Direction();
	}

	@Override
	public Program createProgram(Statement mainStatement,
			Map<String, Type> globalVariables) {

		return new Program(mainStatement, globalVariables);
	}

}
