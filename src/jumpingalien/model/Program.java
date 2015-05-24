package jumpingalien.model;

import java.util.HashMap;
import java.util.Map;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.Statement;
import jumpingalien.part3.programs.Type;

/**
 * 
 * @author Pieter-Jan Coenen (1ste Bacherlor Informatica) en Stijn Caerts (1ste Bacherlor Informatica)
 * 
 * @invar	A program always contains a (non null) mainstatement
 * 			| getMainStatemen() != nul
 * @invar	A program always has a (non null) globalVariables map
 * 			| getGlobalVariables() != null
 * @invar	A program always has a (non null) declarationVariables map
 * 			| getDeclarationVariables() != null 
 * @invar	The length of the globalVariables map equals the length of the variableDeclaration map
 * 			| getDeclarationVariables().size() == getGlobalVariables().size()
 * @invar	The globalVariables map contains the same keys as the variableDeclaration map
 * 			| For each key in getDeclarationVariables()
 * 			|		getGlobalVariables().containsKey(key) == true
 * @invar	The amount of lines that the program can run is always greater or equal to zero
 * 			| getAmountLinesToRun() > 0
 *
 */

public class Program {

	public Program(Statement mainStatement, Map<String, Type> globalVariables) {
		if(mainStatement == null || globalVariables == null){
			throw new IllegalArgumentException();
		}
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
		if(!hasAnError() && isWellFormed()){
			setLinesToRun(i);
			getMainStatement().run(this);
			if(getLinesToRun() > 0){
				initialiseVariables();
				run(getLinesToRun());
			}
		}
	}

	@Basic
	public GameObject getObject() {
		return object;
	}

	@Raw
	void setObject(GameObject object) {
		this.object = object;
	}
	
	private GameObject object;

	@Basic
	public int getLinesToRun() {
		return linesToRun;
	}

	public void setLinesToRun(int linesToRun) {
		if(linesToRun < 0){
			this.linesToRun = 0;
		} else {
			this.linesToRun = linesToRun;
		}
	}
	
	public void lowerLinesToRun(){
		setLinesToRun(getLinesToRun()-1);
	}
	
	private int linesToRun;	

	@Basic
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

	@Basic
	public Map<String, Type> getGlobalVariables() {
		return new HashMap<String, Type>(globalVariables);
	}
	

	private final Map<String, Type> globalVariables;
	
	@Basic
	public Map<String, Object> getDeclarationVariables() {
		return declarationVariables;
	}
	
	private final Map<String, Object> declarationVariables = new HashMap<>();
	
	@Basic
	public SourceLocation getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	private SourceLocation sourceLocation;

	@Basic
	public boolean isBreakActivated() {
		return breakActivated;
	}

	public void setBreakActivated(boolean breakActivated) {
		this.breakActivated = breakActivated;
	}
	
	private boolean breakActivated;
	
	@Basic
	public int getInForEach() {
		return inForEach;
	}

	public void setInForEach(int inForEach) {
		this.inForEach = inForEach;
	}

	private int inForEach;
	
	@Basic
	public int getInWhile() {
		return inWhile;
	}

	public void setInWhile(int inWhile) {
		this.inWhile = inWhile;
	}

	private int inWhile;
	
	@Basic
	private boolean getWellFormed(){
		return wellFormed;
	}
		
	public void setWellFormed(boolean wellFormed) {
		this.wellFormed = wellFormed;
	}

	private boolean wellFormed;
	
	@Basic
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
