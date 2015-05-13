package jumpingalien.model;

import java.util.HashMap;
import java.util.Map;

import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.Statement;
import jumpingalien.part3.programs.Type;

public class Program {

	public Program(Statement mainStatement, Map<String, Type> globalVariables) {
		this.mainStatement = mainStatement;
		this.globalVariables = globalVariables;
		this.currentline = 0;
	}
	
	private Statement mainStatement;
	private Map<String, Type> globalVariables;
	private Map<String, Expression> declarationVariables = new HashMap<>();
	private int currentline;
	
	public boolean isWellFormed() {
		// TODO Auto-generated method stub
		return true;
	}

	public void run(int i) {
		mainStatement.run(declarationVariables);
	}

}
