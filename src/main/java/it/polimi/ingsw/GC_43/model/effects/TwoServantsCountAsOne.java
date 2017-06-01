package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.FamilyMember;

public class TwoServantsCountAsOne extends Effect {
	
	public String toString(){
		String toString="Malus on player: his servant will count as just a half";
		return toString;
	}
	public void executeEffect(FamilyMember familyMember){
		familyMember.getPlayer().getPlayerBounusMalus().setTwoServantsCountsAsOne(true);
	}
	
}
