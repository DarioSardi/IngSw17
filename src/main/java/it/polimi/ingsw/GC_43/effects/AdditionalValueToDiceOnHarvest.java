package it.polimi.ingsw.GC_43.effects;

import it.polimi.ingsw.GC_43.model.FamilyMember;

public class AdditionalValueToDiceOnHarvest extends Effect{
	private int valueToAddOnDice;
	
	public AdditionalValueToDiceOnHarvest(int valueToAddOnDice){
		this.valueToAddOnDice= valueToAddOnDice;
	}
	
	public void executeEffect(FamilyMember familyMember){
		familyMember.getPlayer().getPlayerBounusMalus().setBonusHarvestArea(familyMember.getPlayer().getPlayerBounusMalus().getBonusHarvestArea()+this.valueToAddOnDice);
		
	}

}
