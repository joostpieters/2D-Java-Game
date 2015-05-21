package jumpingalien.part3.programs.statements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import jumpingalien.model.Buzam;
import jumpingalien.model.GameObject;
import jumpingalien.model.Mazub;
import jumpingalien.model.Plant;
import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.Statement;
import jumpingalien.part3.programs.Type;
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
			ArrayList<GameObject> objects = new ArrayList<GameObject>();
			if(getVariableKind() == jumpingalien.part3.programs.IProgramFactory.Kind.PLANT){
				objects.addAll(program.getObject().getWorld().getPlants());
			} else if(getVariableKind() == jumpingalien.part3.programs.IProgramFactory.Kind.SLIME){
				objects.addAll(program.getObject().getWorld().getSlimes());
			} else if(getVariableKind() == jumpingalien.part3.programs.IProgramFactory.Kind.SHARK){
				objects.addAll(program.getObject().getWorld().getSharks());
			} else if(getVariableKind() == jumpingalien.part3.programs.IProgramFactory.Kind.MAZUB){
				objects.add(program.getObject().getWorld().getMazub());
			} else if(getVariableKind() == jumpingalien.part3.programs.IProgramFactory.Kind.MAZUB){
				objects.add(program.getObject().getWorld().getBuzam());
			} else if(getVariableKind() == jumpingalien.part3.programs.IProgramFactory.Kind.MAZUB){
				objects.addAll(program.getObject().getWorld().getPlants());
				objects.addAll(program.getObject().getWorld().getSlimes());
				objects.addAll(program.getObject().getWorld().getSharks());
				objects.add(program.getObject().getWorld().getMazub());
				objects.add(program.getObject().getWorld().getBuzam());
			}
			//Verwijderen van onnodige elementen
			ArrayList<GameObject> objectToRemove = new ArrayList<GameObject>();
			for(GameObject object : objects){
				program.getDeclarationVariables().put(getVariableName(), object);
				if(!((getWhere().getValue(program) instanceof Boolean ) 
						&& ((Boolean)getWhere().getValue(program)))){
					objectToRemove.add(object);
				}
			}
			objects.removeAll(objectToRemove);
			
			//Sorteren van de lijst
			
			objects.sort((o1, o2) -> (int)(getValue(o1, program) - getValue(o2, program)));
			
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
			if(program.isBreakActivated()){
				program.setBreakActivated(false);
			}
			if(!getIterator().hasNext()){
				setIterator(null);
			}
		} else {
			getBody().run(program);
			if(program.getLinesToRun() > 0 && program.getSourceLocation() == null){
				this.runStatement(program);
			} else if(program.getLinesToRun() == 0 && program.getSourceLocation() == null){
				program.setSourceLocation(this.getSourceLocation());
			}
		}
	}
	
	private double getValue(GameObject o2, Program program){
		program.getDeclarationVariables().put(getVariableName(), o2);
		return (double) getSort().getValue(program);
	}
	
	private Iterator<GameObject> getIterator() {
		return iterator;
	}

	private void setIterator(Iterator<GameObject> iterator) {
		this.iterator = iterator;
	}

	private Iterator<GameObject> iterator;

}
