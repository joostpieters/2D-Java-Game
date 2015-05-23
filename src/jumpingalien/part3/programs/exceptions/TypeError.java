package jumpingalien.part3.programs.exceptions;

import jumpingalien.part3.programs.SourceLocation;

public class TypeError extends Error{

	/**
	 * 
	 * @param 	sourceLocation
	 * 			the location where the error occured
	 * @post	The source location will equal the given source location
	 * 			| new.getSourceLocation() == sourceLocation
	 */
	public TypeError(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	
	/**
	 * This methode will return the sourceLocation where the error occured
	 */
	public SourceLocation getSourceLocation() {
		return this.sourceLocation;
	}
	
	/**
	 * The sourceLocation where the error occured
	 */
	private final SourceLocation sourceLocation;
	
	private static final long serialVersionUID = 9071992556709951481L;
}
