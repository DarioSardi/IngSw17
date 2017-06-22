package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.FamilyMember;

public class TwoServantsCountAsOne extends Effect {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8106938090065823704L;

	public String toString(){
		String toString="Malus on player: his servant will count as just a half";
		return toString;
	}
	public void executeEffect(FamilyMember familyMember){
		System.out.println("Executing TwoServantsCountAsOne Effect");

		familyMember.getPlayer().getPlayerBounusMalus().setTwoServantsCountAsOne(true);
	}
	
}
