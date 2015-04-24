package jumpingalien.model;

import java.util.ArrayList;

public class School {
	/**
	 * @effect 	...
	 * 			|setAmountSlimes(0)
	 */
	public School(){
		
	}

	/**
	 * 
	 * @param slime
	 * @throws IllegalArgumentException
	 * 			...
	 * 			| !canHaveAsSlime(slime)
	 * @post 	...
	 * 			|new.slimes.contains(slime)
	 * @effect 	...
	 * 			| setAmountSlimes(getAmountSlimes() + 1)
	 */
	void addSlime(Slime slime) throws IllegalArgumentException{
		if(!canHaveAsSlime(slime)){
			throw new IllegalArgumentException();
		}
		slimes.add(slime);
	}
	
	// TODO documentation
	void removeSlime(Slime slime) {
		assert (slime != null);
		slimes.remove(slime);
		for (Slime otherSlime : slimes) {
			if (!slime.isDead()) {
				slime.setHitPoints(slime.getHitPoints() - 1);
				otherSlime.setHitPoints(otherSlime.getHitPoints() + 1);
			} else {
				break;
			}
		}
	}
	
	//TODO
	void addNewSchoolMember(Slime slime) {
		assert (canHaveAsSlime(slime));
		for (Slime otherSlime : slimes) {
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
	 * 			| return ((slime != null) && (slime.hasAsSchool(this)));
	 */
	private boolean canHaveAsSlime(Slime slime){
		return ((slime != null) && (slime.hasAsSchool(this)));
	}
	
	/**
	 * Returns the amount of slimes in this school
	 */
	int getAmountSlimes(){
		return slimes.size(); 
	}
	
	private ArrayList<Slime> slimes = new ArrayList<Slime>();
	
	
	void reducePoint(Slime slime) {
		for (Slime otherSlime : slimes) {
			if (otherSlime != slime) {
				otherSlime.setHitPoints(otherSlime.getHitPoints() - 1);
			}
		}
	}	
}
