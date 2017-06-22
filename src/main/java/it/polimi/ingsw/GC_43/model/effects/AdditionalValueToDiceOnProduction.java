package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.FamilyMember;

public class AdditionalValueToDiceOnProduction extends Effect{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1291812244387527174L;


	private int valueToAddOnDice;
	
	public AdditionalValueToDiceOnProduction(int valueToAddOnDice){
		this.valueToAddOnDice= valueToAddOnDice;
	}
	
	public String toString(){
		
		String toString="Player will have an additional value on Dice on Production of: "+this.valueToAddOnDice;
		return toString;
	}
	
	
	public void executeEffect(FamilyMember familyMember){
		System.out.println("Executing AdditionalValueToDiceOnProduction Effect");

		familyMember.getPlayer().getPlayerBounusMalus().setBonusProductionArea(familyMember.getPlayer().getPlayerBounusMalus().getBonusProductionArea()+this.valueToAddOnDice);
		
	}

}
