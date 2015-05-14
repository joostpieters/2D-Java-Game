package jumpingalien.part3.programs.statements;

import java.util.Map;

import jumpingalien.model.Program;
import jumpingalien.part3.programs.Expression;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.Statement;
import jumpingalien.part3.programs.Type;

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
		// TODO
	}

}
