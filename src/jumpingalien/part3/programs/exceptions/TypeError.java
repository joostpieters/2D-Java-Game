package jumpingalien.part3.programs.exceptions;

import jumpingalien.part3.programs.SourceLocation;

public class TypeError extends Error{

	public TypeError(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	public SourceLocation getSourceLocation() {
		return this.sourceLocation;
	}
	
	private final SourceLocation sourceLocation;
	
	private static final long serialVersionUID = 9071992556709951481L;
}
