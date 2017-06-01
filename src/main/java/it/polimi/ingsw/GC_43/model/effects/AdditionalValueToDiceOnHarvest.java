package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.FamilyMember;

public class AdditionalValueToDiceOnHarvest extends Effect{
	private int valueToAddOnDice;
	
	public AdditionalValueToDiceOnHarvest(int valueToAddOnDice){
		this.valueToAddOnDice= valueToAddOnDice;
	}
	
	public String toString(){
		
		String toString="Player will have an additional value on Dice on Harvest of: "+this.valueToAddOnDice;
		return toString;
	}
	
	public void executeEffect(FamilyMember familyMember){
		familyMember.getPlayer().getPlayerBounusMalus().setBonusHarvestArea(familyMember.getPlayer().getPlayerBounusMalus().getBonusHarvestArea()+this.valueToAddOnDice);
		
	}

}
