package jumpingalien.model;

import java.util.Map;

import jumpingalien.part3.programs.Statement;
import jumpingalien.part3.programs.Type;

public class Program {

	public Program(Statement mainStatement, Map<String, Type> globalVariables) {
		this.mainStatement = mainStatement;
		this.globalVariables = globalVariables;
	}
	
	private Statement mainStatement;
	private Map<String, Type> globalVariables;
	
	public boolean isWellFormed() {
		// TODO Auto-generated method stub
		return false;
	}

}
