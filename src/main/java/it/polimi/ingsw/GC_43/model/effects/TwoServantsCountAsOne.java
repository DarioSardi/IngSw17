package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.FamilyMember;

public class TwoServantsCountAsOne extends Effect {
	
	public void executeEffect(FamilyMember familyMember){
		familyMember.getPlayer().getPlayerBounusMalus().setTwoServantsCountsAsOne(true);
	}
	
}