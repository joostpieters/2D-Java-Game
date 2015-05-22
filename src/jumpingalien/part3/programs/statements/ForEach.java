package jumpingalien.part3.programs.statements;

import java.util.ArrayList;
import java.util.Iterator;

import jumpingalien.part3.programs.types.GameObject;
import jumpingalien.model.Program;
import jumpingalien.part2.internal.tmxfile.data.ImageTile.TileType;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.IProgramFactory.Kind;
import jumpingalien.part3.programs.IProgramFactory.SortDirection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.Statement;
import jumpingalien.util.Util;

public class ForEach extends Statement {

	public ForEach(String variableName,
			jumpingalien.part3.programs.IProgramFactory.Kind variableKind,
			Expression where,
			Expression sort,
			jumpingalien.part3.programs.IProgramFactory.SortDirection sortDirection,
			Statement body, SourceLocation sourceLocation) {
		super(sourceLocation);
		setVariableName(variableName);
		setVariableKind(variableKind);
		setWhere(where);
		setSort(sort);
		setSortDirection(sortDirection);
		setBody(body);
	}
	
	private String getVariableName() {
		return variableName;
	}


	private void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	
	private String variableName;



	private jumpingalien.part3.programs.IProgramFactory.Kind getVariableKind() {
		return variableKind;
	}


	private void setVariableKind(
			jumpingalien.part3.programs.IProgramFactory.Kind variableKind) {
		this.variableKind = variableKind;
	}
	
	private jumpingalien.part3.programs.IProgramFactory.Kind variableKind;


	private Expression getWhere() {
		return where;
	}


	private void setWhere(Expression where) {
		this.where = where;
	}
	
	private Expression where;


	private Expression getSort() {
		return sort;
	}


	private void setSort(Expression sort) {
		this.sort = sort;
	}

	private Expression sort;
	

	private jumpingalien.part3.programs.IProgramFactory.SortDirection getSortDirection() {
		return sortDirection;
	}


	private void setSortDirection(
			jumpingalien.part3.programs.IProgramFactory.SortDirection sortDirection) {
		this.sortDirection = sortDirection;
	}
	
	private jumpingalien.part3.programs.IProgramFactory.SortDirection sortDirection;


	private Statement getBody() {
		return body;
	}


	private void setBody(Statement body) {
		this.body = body;
	}

	private Statement body;
	

	@Override
	public void runStatement(Program program) {
		if(program.getSourceLocation() == null || program.getSourceLocation() == this.getSourceLocation()){
			if(program.getSourceLocation() == this.getSourceLocation()){
				program.setSourceLocation(null);
			}
			ArrayList<jumpingalien.part3.programs.types.GameObject> objects = new ArrayList<>();
			if(getVariableKind() == Kind.PLANT){
				objects.addAll(program.getObject().getWorld().getPlants());
			} else if(getVariableKind() == Kind.SLIME){
				objects.addAll(program.getObject().getWorld().getSlimes());
			} else if(getVariableKind() == Kind.SHARK){
				objects.addAll(program.getObject().getWorld().getSharks());
			} else if(getVariableKind() == Kind.MAZUB){
				objects.add(program.getObject().getWorld().getMazub());
			} else if(getVariableKind() == Kind.BUZAM){
				if(program.getObject().getWorld().getBuzam() != null){
					objects.add(program.getObject().getWorld().getBuzam());
				}
			} else if (getVariableKind() == Kind.TERRAIN) {
				objects.addAll(program.getObject().getWorld().getTiles());
			} else if(getVariableKind() == Kind.ANY){
				objects.addAll(program.getObject().getWorld().getPlants());
				objects.addAll(program.getObject().getWorld().getSlimes());
				objects.addAll(program.getObject().getWorld().getSharks());
				objects.add(program.getObject().getWorld().getMazub());
				if(program.getObject().getWorld().getBuzam() != null){
					objects.add(program.getObject().getWorld().getBuzam());
				}
				objects.addAll(program.getObject().getWorld().getTiles());
			}
			//Verwijderen van onnodige elementen
			if(getWhere() != null){
				ArrayList<GameObject> objectToRemove = new ArrayList<GameObject>();
				for(GameObject object : objects){
					program.getDeclarationVariables().put(getVariableName(), object);
					if(!((getWhere().getValue(program) instanceof Boolean ) 
							&& ((Boolean)getWhere().getValue(program)))){
						objectToRemove.add(object);
					}
				}
				objects.removeAll(objectToRemove);
			}
			
			//Sorteren van de lijst
			
			if (getSort() != null && getSortDirection() != null) {
				objects.sort((o1, o2) -> compare(o1, o2, program));
			}
			
			
			//Uitoeren
			if(getIterator() == null){
				setIterator(objects.iterator());
				program.lowerLinesToRun();
			}
			while(getIterator().hasNext() && !program.isBreakActivated()){
				GameObject object = getIterator().next();
				program.getDeclarationVariables().put(getVariableName(), object);
				getBody().run(program);
				if(program.getLinesToRun() == 00 && program.getSourceLocation() == null && getIterator().hasNext()){
					program.setSourceLocation(this.getSourceLocation());
					break;
				} else if(program.getLinesToRun() == 0) {
					break;
				}
			}
			if(!getIterator().hasNext() || program.isBreakActivated()){
				setIterator(null);
			}
			if(program.isBreakActivated()){
				program.setBreakActivated(false);
			}
		} else {
			program.setInForEach(program.getInForEach() + 1);
			getBody().run(program);
			program.setInForEach(program.getInForEach() - 1);
			if(program.getLinesToRun() > 0 && program.getSourceLocation() == null){
				this.runStatement(program);
			} else if(program.getLinesToRun() == 0 && program.getSourceLocation() == null){
				program.setSourceLocation(this.getSourceLocation());
			}
		}
	}
	
	private double getValue(GameObject obj, Program program){
		program.getDeclarationVariables().put(getVariableName(), obj);
		return (double) getSort().getValue(program);
	}
	
	private Iterator<GameObject> getIterator() {
		return iterator;
	}

	private void setIterator(Iterator<GameObject> iterator) {
		this.iterator = iterator;
	}

	private Iterator<GameObject> iterator;
	
	private int compare(GameObject o1, GameObject o2, Program program) { 
		double value1 = getValue(o1, program);
		double value2 = getValue(o2, program);
		
		if (Util.fuzzyEquals(value1, value2)) {
			return 0;
		}
		
		if (getSortDirection() == SortDirection.ASCENDING) {
			if (value1 > value2) {
				return 1;
			} else {
				return -1;
			}
		} else if (getSortDirection() == SortDirection.DESCENDING) {
			if (value1 > value2) {
				return -1;
			} else {
				return 1;
			}
		}
		throw new IllegalStateException();
	}

}
