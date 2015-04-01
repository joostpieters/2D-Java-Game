package jumpingalien.model;

import java.util.ArrayList;

public class School {
	/**
	 * @effect 	...
	 * 			|setAmountSlimes(0)
	 */
	public School(){
		setAmountSlimes(0);
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
		setAmountSlimes(getAmountSlimes() + 1);
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
	private int getAmountSlimes(){
		return this.amountSlimes; 
	}
	
	/**
	 * 
	 * @param 	amount
	 * @post 	...
	 * 			|new.getAmountSlimes() = amount
	 */
	private void setAmountSlimes(int amount){
		this.amountSlimes = amount; 
	}
	
	private ArrayList<Slime> slimes = new ArrayList<Slime>();
	/**
	 * This variable contains the amout of slimes in this school
	 */
	private int amountSlimes;

}
