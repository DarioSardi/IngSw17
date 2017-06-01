package it.polimi.ingsw.GC_43.model.actionSpace;

import it.polimi.ingsw.GC_43.model.FamilyMember;

public class MarketActionSpace extends ActionSpace {

	@Override
	public boolean check(FamilyMember f) {
		return familiarValueCheck(f)&&this.getFamiliarIn().isEmpty()&&f.getPlayer().getPlayerBounusMalus().isNoMarketActionSpaceBonus(); //TODO isNoMarketActionSpaceBonus return true if i can go in the market?!
	}

	@Override
	public boolean familiarValueCheck(FamilyMember f) {
		return f.getDiceValue()>=this.getMinDiceValue();
	}

	@Override
	public boolean checkColor(FamilyMember f) {
		return true;
	}
	
	@Override
	public String toString(){
		return "in this market you will get : "+ this.getBonus().toString();	
	}

}
