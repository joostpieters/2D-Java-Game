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
		for(Map.Entry<String, Type> entry : globalVariables.entrySet()){
			if(entry.getValue() instanceof jumpingalien.part3.programs.types.Boolean){
				declarationVariables.put(entry.getKey(), false);	
			} else if(entry.getValue() instanceof jumpingalien.part3.programs.types.Double){
				declarationVariables.put(entry.getKey(), 0);	
			}		
		}
		this.currentline = 0;
	}
	
	private Statement mainStatement;
	private Map<String, Type> globalVariables;
	private Map<String, Object> declarationVariables = new HashMap<>();
	private int currentline;
	
	public boolean isWellFormed() {
		// TODO Auto-generated method stub
		return true;
	}

	public void run(int i) {
		mainStatement.run(this);
	}

	public Map<String, Object> getDeclarationVariables() {
		return declarationVariables;
	}

	protected void setDeclarationVariables(
			Map<String, Object> declarationVariables) {
		this.declarationVariables = declarationVariables;
	}

}
