package model.actionSpace;

import model.FamilyMember;

public class HarvestSingleActionSpace extends SingleActionSpace{
	private FamilyMember familiarIn;
	
	/**
	 * create a single harvest space with a standard harvest bonus;
	 * @param minDiceValue to define the min value of the familiar
	 */
	public HarvestSingleActionSpace(int minDiceValue) {
		this.setBonus(null); //DARIO inserire bonus produzione
		this.setMinDiceValue(minDiceValue);
		this.setFamiliarIn(null);
	}
	
	/**
	 * check if the space is empty and if the familiar have enough points to enter the space
	 * @param f
	 * @return true if action can be performed
	 */
	public boolean check(FamilyMember f){
		return familiarIn==null && this.familiarValueCheck(f); //DARIO logica bonus produzione
	}
	
	/**
	 * check if the action is possible, if it is give the player the bonus 
	 * @param f
	 * @return
	 */
	public boolean execute(FamilyMember f){
		if(check(f)){
			this.getBonus().earnBonus(f);
			this.familiarIn=f;
			f.setAlreadyPlaced(true);
			f.setFamilyMemberPosition(this);
			return true;
		}
		else return false;
	}
	
	

}
