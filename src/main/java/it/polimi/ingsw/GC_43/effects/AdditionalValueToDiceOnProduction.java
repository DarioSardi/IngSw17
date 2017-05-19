package it.polimi.ingsw.GC_43.effects;

import it.polimi.ingsw.GC_43.model.FamilyMember;

public class AdditionalValueToDiceOnProduction extends Effect{
	private int valueToAddOnDice;
	
	public AdditionalValueToDiceOnProduction(int valueToAddOnDice){
		this.valueToAddOnDice= valueToAddOnDice;
	}
	
	public void executeEffect(FamilyMember familyMember){
		familyMember.getPlayer().getPlayerBounusMalus().setBonusProductionArea(familyMember.getPlayer().getPlayerBounusMalus().getBonusProductionArea()+this.valueToAddOnDice);
		
	}

}
