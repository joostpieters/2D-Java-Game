package jumpingalien.model;

import java.util.HashMap;
import java.util.Map;

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
				getDeclarationVariables().put(entry.getKey(), 0.00);	
			}		
		}
	}
	
	private Statement getMainStatement() {
		return mainStatement;
	}
	
	private final Statement mainStatement;
	
	public boolean isWellFormed() {
		SourceLocation oldSourceLocation = getSourceLocation();
		SourceLocation sourceLocation = new SourceLocation(-1, -1);
		this.setSourceLocation(sourceLocation);
		setLinesToRun(getLinesToRun()+1);
		setWellFormed(true);
		setInForEach(0);
		setInWhile(0);
		getMainStatement().run(this);
		setSourceLocation(oldSourceLocation);
		setLinesToRun(getLinesToRun()-1);
		return getWellFormed();
	}

	public void run(int i) {
		if(!hasAnError()){
			setLinesToRun(i);
			getMainStatement().run(this);
			if(getLinesToRun() > 0){
				initialiseVariables();
				run(getLinesToRun());
			}
		}
	}

	public GameObject getObject() {
		return object;
	}

	void setObject(GameObject object) {
		this.object = object;
	}
	
	private GameObject object;

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

	public boolean isBreakActivated() {
		return breakActivated;
	}

	public void setBreakActivated(boolean breakActivated) {
		this.breakActivated = breakActivated;
	}
	
	private boolean breakActivated;
	
	public int getInForEach() {
		return inForEach;
	}

	public void setInForEach(int inForEach) {
		this.inForEach = inForEach;
	}

	private int inForEach;
	
	public int getInWhile() {
		return inWhile;
	}

	public void setInWhile(int inWhile) {
		this.inWhile = inWhile;
	}

	private int inWhile;
	
	private boolean getWellFormed(){
		return wellFormed;
	}
		
	public void setWellFormed(boolean wellFormed) {
		this.wellFormed = wellFormed;
	}

	private boolean wellFormed;
	
	private boolean hasAnError() {
		return hasAnError;
	}
	
	private void setHasAnError(boolean value) {
		this.hasAnError = value;
	}
	
	private boolean hasAnError;
	
	public void stopBecauseError(){
		setLinesToRun(0);
		setHasAnError(true);
	}

}
