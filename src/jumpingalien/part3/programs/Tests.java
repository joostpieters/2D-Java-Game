package jumpingalien.part3.programs;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;
import jumpingalien.model.Program;
import jumpingalien.part3.facade.Facade;
import jumpingalien.part3.facade.IFacadePart3;
import jumpingalien.util.ModelException;

import org.junit.Test;

public class Tests {

	@Test
	public void testBreakNotWellformed() {
		IFacadePart3 facade = new Facade();
		ParseOutcome<?> outcome = facade.parse("double d := 1.0; break;");
		assumeTrue(outcome.isSuccess());
		assertFalse(facade.isWellFormed((Program) outcome.getResult()));
	}
	
	@Test
	public void testWellformed() {
		IFacadePart3 facade = new Facade();
		ParseOutcome<?> outcome = facade.parse("double d := 1.0; while d < 3 do if random 2 <= 1 then break; fi done");
		assumeTrue(outcome.isSuccess());
		assertTrue(facade.isWellFormed((Program) outcome.getResult()));
	}
	
	@Test
	public void testWellFormedActionStatementInForEach() {
		IFacadePart3 facade = new Facade();
		ParseOutcome<?> outcome = facade.parse("double x; object o; while true do foreach(any, o) where (! isplant o) sort (getx o) descending do x := getx o; start_run left; print x; done done");
		assumeTrue(outcome.isSuccess());
		assertFalse(facade.isWellFormed((Program) outcome.getResult()));
	}
	
	@Test(expected = ModelException.class)
	public void testMatchingTypes1() {
		IFacadePart3 facade = new Facade();
		facade.parse("double a := 10; bool b; a := a + b;");
	}
	
	@Test(expected = ModelException.class)
	public void testMatchingTypes2() {
		IFacadePart3 facade = new Facade();
		facade.parse("double a := 10; bool b; b := a==b;");
	}
	
	@Test(expected = ModelException.class)
	public void testMatchingTypes3() {
		IFacadePart3 facade = new Facade();
		facade.parse("double a := 10; bool b; b := isslime(a);");
	}
	
	@Test(expected = ModelException.class)
	public void testMatchingTypes4() {
		IFacadePart3 facade = new Facade();
		facade.parse("bool a := 10; bool b; b := a;");
	}
	
	@Test
	public void testMatchingTypes5() {
		IFacadePart3 facade = new Facade();
		facade.parse("bool a; bool b; b := a;");
	}
	
	@Test(expected = ModelException.class)
	public void testMatchingTypes6() {
		IFacadePart3 facade = new Facade();
		facade.parse("bool b; object c; b := isslime(gettile(1,1));");
	}

}
