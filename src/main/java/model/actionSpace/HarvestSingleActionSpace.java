package model.actionSpace;

import model.FamilyMember;

public class HarvestSingleActionSpace extends SingleActionSpace{
	private HarvestArea harvestArea;
	
	/**
	 * create a single harvest space with a standard harvest bonus;
	 * @param minDiceValue to define the min value of the familiar
	 * @param harvestArea to define the harvestArea
	 */
	public HarvestSingleActionSpace(int minDiceValue,HarvestArea harvestArea) {
		this.setBonus(null); //DARIO inserire bonus produzione
		this.setMinDiceValue(minDiceValue);
		this.setFamiliarIn(null);
		this.harvestArea=harvestArea;
	}
	
	/**
	 * check if the space is empty and if the familiar have enough points to enter the space
	 * @param f
	 * @return true if action can be performed
	 */
	public boolean check(FamilyMember f){
		return getFamiliarIn()==null && this.familiarValueCheck(f) && this.harvestArea.havePlayerInCheck(f);
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
	public boolean familiarValueCheck(FamilyMember f){
		return f.getDiceValue()+f.getPlayer().getPlayerBounusMalus().getBonusProductionArea()>=this.getMinDiceValue();
	}
	
	

}
