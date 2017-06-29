package it.polimi.ingsw.GC_43.model.leaderCards;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class DoubleCardInstantGain extends Effect {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1181805687746347920L;

	public DoubleCardInstantGain(){
		
	}
	
	public String toString() {
		String toString = "Player will receive a double amount of card instant bonus resources";
		return toString;
	}

	
	public void executeEffect(FamilyMember fam){
		System.out.println("Executing effect DoubleCardInstantGain");

		fam.getPlayer().getPlayerBounusMalus().setDoubleGainFromCardInstantBonus(true);
	}
	

}
