package jumpingalien.part3.programs.exceptions;

import jumpingalien.part3.programs.SourceLocation;

public class IllegalMatchingTypeException extends RuntimeException {


	public IllegalMatchingTypeException(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	public SourceLocation getSourceLocation() {
		return this.sourceLocation;
	}
	
	private final SourceLocation sourceLocation;
	
	private static final long serialVersionUID = 7707924077908740848L;

}
