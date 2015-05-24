package jumpingalien.model;

import java.util.ArrayList;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * 
 * @author Pieter-Jan Coenen (1ste Bacherlor Informatica) en Stijn Caerts (1ste Bacherlor Informatica)
 * 
 * @invar	The amount of Slimes in a School is always greater or equal to zero.
 * 			| this.getAmountSlimes() >= 0
 * 
 * @invar	The array that contains the slimes does not contain the null value
 * 			| !getSlimes().contains(null)
 *
 */
public class School {

	/**
	 * 
	 * @param slime
	 * @throws IllegalArgumentException
	 * 			...
	 * 			| !canHaveAsSlime(slime)
	 * @post 	...
	 * 			|new.getSlimes()contains(slime)
	 */
	void addSlime(@Raw Slime slime) throws IllegalArgumentException{
		if(!canHaveAsSlime(slime)){
			throw new IllegalArgumentException();
		}
		getSlimes().add(slime);
	}
	
	/**
	 * @pre		...
	 * 			| slime != null
	 * @param slime
	 * 			the Slime to remove from this School
	 * @effect 	...
	 * 			| getSlimes().remove(slime)
	 * @effect	...
	 * 			| foreach (otherSlime in slimes)
	 * 			|	if not getSlime().isDead() then
	 * 			|		getSlime().setHitpoints(slime.getHitPoints() - 1)
	 * 			|		otherSlime.setHitPoints(otherSlime.getHitpoints() + 1)
	 * @effect	...
	 * 			| if (new.getAmountSlimes() == 0) then
	 * 			|	this.terminate()
	 */
	void removeSlime(@Raw Slime slime) {
		assert (slime != null);
		getSlimes().remove(slime);
		for (Slime otherSlime : getSlimes()) {
			if (!slime.isDead()) {
				slime.setHitPoints(slime.getHitPoints() - 1);
				otherSlime.setHitPoints(otherSlime.getHitPoints() + 1);
			} else {
				break;
			}
		}
		if (getAmountSlimes() == 0 && slime.getWorld().isGameStarted()) {
			terminate();
		}
	}
	
	/**
	 * @pre		...
	 * 			| canHaveAsSlime(slime)
	 * @param slime
	 * @effect	...
	 * 			| for each otherSlime in slimes
	 * 			| 	if (!otherSlime.isDead()) then
	 * 			|		otherSlime.setHitPoints(otherSlime.getHitPoints() - 1)
	 * 			|		slime.setHitPoints(slime.getHitPoints() + 1)
	 * @effect	...
	 * 			| addSlime(slime)
	 */
	void addNewSchoolMember(@Raw Slime slime) throws IllegalAccessException {
		assert (canHaveAsSlime(slime));
		for (Slime otherSlime : getSlimes()) {
			if (!otherSlime.isDead()) {
				otherSlime.setHitPoints(otherSlime.getHitPoints() - 1);
				slime.setHitPoints(slime.getHitPoints() + 1);
			}
		}
		addSlime(slime);
	}
	
	
	/**
	 * 
	 * @param 	slime
	 * @return 	...
	 * 			| return ((slime != null) && (slime.hasAsSchool(this)) && (not slime.isTerminated());
	 */
	private boolean canHaveAsSlime(Slime slime){
		return ((slime != null) && (slime.hasAsSchool(this)) && (!slime.isTerminated()));
	}
	
	/**
	 * Returns the amount of slimes in this school
	 */
	int getAmountSlimes(){
		return getSlimes().size(); 
	}
	
	/**
	 * Returns the current slimes of this school
	 */
	private ArrayList<Slime> getSlimes(){
		return slimes;
	}
	
	/**
	 * This list holds the Slimes of this School
	 */
	private ArrayList<Slime> slimes = new ArrayList<Slime>();
	
	/**
	 * 
	 * @param slime
	 * 			the Slime who has lost some points
	 * @effect	...
	 * 			| for each otherSlime in getSlimes())
	 * 			| 	if (otherSlime != slime) then
	 * 			|		otherSlime.setHitPoints(otherSlime.getHitPoints() - 1)
	 * 
	 */
	void reducePoint(Slime slime) {
		for (Slime otherSlime : getSlimes()) {
			if (otherSlime != slime) {
				otherSlime.setHitPoints(otherSlime.getHitPoints() - 1);
			}
		}
	}	
	
	/**
	 * @effect	...
	 * 			| setTerminated(true)
	 */
	private void terminate() {
		if(getAmountSlimes() == 0)
		setTerminated(true);
	}
	
	/**
	 * 
	 * @return 	...
	 * 			|result == terminated
	 */
	@Basic
	boolean isTerminated() {
		return this.terminated;
	}
	
	/**
	 * 
	 * @param value
	 * @post	...
	 * 			| new.isTerminated() == value
	 */
	private void setTerminated(boolean value) {
		this.terminated = value;
	}
	
	/**
	 * This variable contains if this school is terminated or not
	 */
	private boolean terminated;
}
