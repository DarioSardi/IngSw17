package model.actionSpace;

import model.FamilyMember;
import model.GlobalVariables;

public class HarvestArea{
	private SingleActionSpace baseZone;
	private MultipleActionSpace extraZone;
	
	public HarvestArea(boolean enoughPlayers) {
		this.baseZone = new HarvestSingleActionSpace(GlobalVariables.minDiceValueHarvestArea, this);
		this.extraZone = new HarvestMultipleActionSpace(GlobalVariables.minDiceValueHarvestArea, this);
	}
	

	/**
	 * check if the player is already in one of the area
	 * @param f family member that is trying  to enter
	 * @return true if there are no conflicts (no other familar of the same color)
	 */
	public boolean havePlayerInCheck(FamilyMember f){
		return false; //DARIO compilare codice tenendo conto del familiare neutro
	}



 
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   