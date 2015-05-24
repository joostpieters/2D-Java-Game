package jumpingalien.part3.programs.expressions;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.Statement;
import jumpingalien.part3.programs.Type;
import jumpingalien.part3.programs.exceptions.TypeError;
import jumpingalien.part3.programs.statements.Skip;
import jumpingalien.util.Util;

import org.junit.Test;

public class ExpressionTest {
	
	private SourceLocation randomSourceLocation(){
		Random rand = new Random();
		return new SourceLocation(rand.nextInt(101), rand.nextInt(101));
	}
	
	private Program createRandomProgram(){
		Statement statement = new Skip(randomSourceLocation());
		Map<String, Type> map = new HashMap<>();
		return new Program(statement, map);
	}

	@Test
	public void testAddition() {
		Expression expr1 = new Double(4, randomSourceLocation());
		Expression expr2 = new Double(5, randomSourceLocation());
		Expression addition = new Addition(expr1, expr2, randomSourceLocation());
		assertEquals(true, (double)addition.getValue(createRandomProgram()) == 9);
	}
	
	@Test(expected = TypeError.class) 
	public void testAdditionIllegalArgument() {
		Expression expr1 = new Double(4, randomSourceLocation());
		Expression expr2 = new Bool(true, randomSourceLocation());
		new Addition(expr1, expr2, randomSourceLocation());
	}
	
	@Test
	public void testSubstraction() {
		Expression expr1 = new Double(5, randomSourceLocation());
		Expression expr2 = new Double(4, randomSourceLocation());
		Expression substraction = new Substraction(expr1, expr2, randomSourceLocation());
		assertEquals(true, (double)substraction.getValue(createRandomProgram()) == 1);
	}
	
	@Test(expected = TypeError.class) 
	public void testSubstractionIllegalArgument() {
		Expression expr1 = new Double(4, randomSourceLocation());
		Expression expr2 = new Bool(true, randomSourceLocation());
		new Substraction(expr1, expr2, randomSourceLocation());
	}
	
	@Test
	public void testDivision() {
		Expression expr1 = new Double(20, randomSourceLocation());
		Expression expr2 = new Double(4, randomSourceLocation());
		Expression expr = new Division(expr1, expr2, randomSourceLocation());
		assertEquals(true, (double)expr.getValue(createRandomProgram()) == 5);
	}
	
	@Test(expected = TypeError.class) 
	public void testDivisionIllegalArgument() {
		Expression expr1 = new Double(4, randomSourceLocation());
		Expression expr2 = new Bool(true, randomSourceLocation());
		new Division(expr1, expr2, randomSourceLocation());
	}
	
	@Test
	public void testMultiplication() {
		Expression expr1 = new Double(5, randomSourceLocation());
		Expression expr2 = new Double(4, randomSourceLocation());
		Expression expr = new Multiplication(expr1, expr2, randomSourceLocation());
		assertEquals(true, (double)expr.getValue(createRandomProgram()) == 20);
	}
	
	@Test(expected = TypeError.class) 
	public void testMultiplicationIllegalArgument() {
		Expression expr1 = new Double(4, randomSourceLocation());
		Expression expr2 = new Bool(true, randomSourceLocation());
		new Multiplication(expr1, expr2, randomSourceLocation());
	}
	
	@Test
	public void testEqualsDouble() {
		Expression expr1 = new Double(5, randomSourceLocation());
		Expression expr2 = new Double(5, randomSourceLocation());
		Expression expr = new Equals(expr1, expr2, randomSourceLocation());
		assertEquals(true, expr.getValue(createRandomProgram()));
	}
	
	@Test(expected = TypeError.class) 
	public void testEqualsIllegalArgument() {
		Expression expr1 = new Double(4, randomSourceLocation());
		Expression expr2 = new Bool(true, randomSourceLocation());
		new Equals(expr1, expr2, randomSourceLocation());
	}
	
	@Test
	public void testEqualsBooleanTrue() {
		Expression expr1 = new Bool(true, randomSourceLocation());
		Expression expr2 = new Bool(true, randomSourceLocation());
		Expression expr = new Equals(expr1, expr2, randomSourceLocation());
		assertEquals(true, expr.getValue(createRandomProgram()));
	}
	
	@Test
	public void testEqualsBooleanFalse() {
		Expression expr1 = new Bool(true, randomSourceLocation());
		Expression expr2 = new Bool(false, randomSourceLocation());
		Expression expr = new Equals(expr1, expr2, randomSourceLocation());
		assertEquals(false, expr.getValue(createRandomProgram()));
	}
	
	@Test
	public void testEqualObjectSimple() {
		Expression expr1 = new Object(null,true, randomSourceLocation());
		Expression expr = new Equals(expr1, expr1, randomSourceLocation());
		assertEquals(true, expr.getValue(createRandomProgram()));
	}
	
	@Test
	public void testSquareRoot() {
		Expression expr1 = new Double(25, randomSourceLocation());
		Expression expr = new SquareRoot(expr1,randomSourceLocation());
		assertEquals(true, (double)expr.getValue(createRandomProgram()) == 5);
	}
	
	@Test(expected = TypeError.class)
	public void testSquareRootIllegalArgument() {
		Expression expr1 = new Bool(true, randomSourceLocation());
		new SquareRoot(expr1,randomSourceLocation());
	}
	
	@Test
	public void testConjunction() {
		Expression expr1 = new Bool(true, randomSourceLocation());
		Expression expr2 = new Bool(false, randomSourceLocation());
		Expression expr = new Conjunction(expr1, expr2, randomSourceLocation());
		assertEquals(false, expr.getValue(createRandomProgram()));
	}
	
	@Test(expected = TypeError.class) 
	public void testConjunctionIllegalArgument() {
		Expression expr1 = new Bool(true, randomSourceLocation());
		Expression expr2 = new Double(5, randomSourceLocation());
		new Conjunction(expr1, expr2, randomSourceLocation());
	}	
	
	@Test
	public void testDisjunction() {
		Expression expr1 = new Bool(true, randomSourceLocation());
		Expression expr2 = new Bool(false, randomSourceLocation());
		Expression expr = new Disjunction(expr1, expr2, randomSourceLocation());
		assertEquals(true, expr.getValue(createRandomProgram()));
	}
	
	@Test(expected = TypeError.class) 
	public void testDisjunctionIllegalArgument() {
		Expression expr1 = new Bool(true, randomSourceLocation());
		Expression expr2 = new Double(5, randomSourceLocation());
		new Disjunction(expr1, expr2, randomSourceLocation());
	}
	
	@Test
	public void testRandomDouble() {
		Expression expr1 = new Double(25, randomSourceLocation());
		Expression expr = new RandomDouble(expr1,randomSourceLocation());
		double result = (double) expr.getValue(createRandomProgram());
		assertEquals(true, Util.fuzzyGreaterThanOrEqualTo(result, 0) && Util.fuzzyLessThanOrEqualTo(result, 25));
	}
	
	@Test(expected = TypeError.class)
	public void testRandomDoubleIllegalArgument() {
		Expression expr1 = new Bool(true, randomSourceLocation());
		new RandomDouble(expr1,randomSourceLocation());
	}

	@Test
	public void testNegation() {
		Expression expr1 = new Bool(true, randomSourceLocation());
		Expression expr = new Negation(expr1,randomSourceLocation());
		assertEquals(false, expr.getValue(createRandomProgram()));
	}
	
	@Test(expected = TypeError.class)
	public void testNegationIllegalArgument() {
		Expression expr1 = new Double(5, randomSourceLocation());
		new Negation(expr1,randomSourceLocation());
	}
	
}
