package jumpingalien.model;

import java.util.HashMap;
import java.util.Map;

import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.Statement;
import jumpingalien.part3.programs.Type;

public class Program {

	public Program(Statement mainStatement, Map<String, Type> globalVariables) {
		this.mainStatement = mainStatement;
		this.globalVariables = globalVariables;
		initialiseVariables();	
	}

	private void initialiseVariables() {
		Map<String, Type> globalVariables = getGlobalVariables();
		for(Map.Entry<String, Type> entry : globalVariables.entrySet()){
			if(entry.getValue() instanceof jumpingalien.part3.programs.types.Boolean){
				getDeclarationVariables().put(entry.getKey(), false);	
			} else if(entry.getValue() instanceof jumpingalien.part3.programs.types.Double){
				getDeclarationVariables().put(entry.getKey(), 0);	
			}		
		}
	}
	
	private Object object;
	
	private Statement getMainStatement() {
		return mainStatement;
	}
	
	private final Statement mainStatement;
	
	public boolean isWellFormed() {
		// TODO Auto-generated method stub
		return true;
	}

	public void run(int i) {
		setLinesToRun(i);
		getMainStatement().run(this);
		if(getLinesToRun() > 0){
			initialiseVariables();
			run(getLinesToRun());
		}
	}

	public Object getObject() {
		return object;
	}

	void setObject(Object object) {
		this.object = object;
	}

	public int getLinesToRun() {
		return linesToRun;
	}

	public void setLinesToRun(int linesToRun) {
		this.linesToRun = linesToRun;
	}
	
	public void lowerLinesToRun(){
		setLinesToRun(getLinesToRun()-1);
	}
	
	private int linesToRun;	

	public int getLinesToSkip() {
		return linesToSkip;
	}

	private void setLinesToSkip(int linesToSkip) {
		this.linesToSkip = linesToSkip;
	}
	
	public void lowerLinesToSkip(){
		setLinesToSkip(getLinesToSkip()-1);
	}
	
	private int linesToSkip;

	private Map<String, Type> getGlobalVariables() {
		return globalVariables;
	}
	
	private final Map<String, Type> globalVariables;
	
	public Map<String, Object> getDeclarationVariables() {
		return declarationVariables;
	}
	
	private final Map<String, Object> declarationVariables = new HashMap<>();
	
	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	private SourceLocation sourceLocation;

	private int getAmountBreaksSet() {
		return amountBreaksSet;
	}

	private void setAmountBreaksSet(int amountBreaksSet) {
		this.amountBreaksSet = amountBreaksSet;
	}
	
	public void addBreak() {
		setAmountBreaksSet(getAmountBreaksSet() + 1);	
	}
	
	public boolean deletebreak(){
		if(getAmountBreaksSet() > 1){
			setAmountBreaksSet(getAmountBreaksSet()-1);
			return false;
		}
		return true;
	}
	
	private int amountBreaksSet;

}
