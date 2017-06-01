package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.FamilyMember;

public class DisableMarketActionSpacesEffect extends Effect {
	
	public String toString(){
		String toString="Player will NOT have access to market action space anymore";
		return toString;
	}
	
	public void executeEffect(FamilyMember familyMember){
		familyMember.getPlayer().getPlayerBounusMalus().setNoMarketActionSpaceBonus(true);
	}


}
