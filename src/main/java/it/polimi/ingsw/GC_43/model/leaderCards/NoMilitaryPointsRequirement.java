package it.polimi.ingsw.GC_43.model.leaderCards;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class NoMilitaryPointsRequirement extends Effect{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1977586738317530768L;

	public NoMilitaryPointsRequirement(){
		
	}
	
	public String toString() {
		String toString = "Player will not have to satisfy military point requirements to buy territory cards anymore";
		return toString;
	}
	
	public void executeEffect(FamilyMember fam){
		System.out.println("Executing effect NoMilitaryPointsRequirement");

		fam.getPlayer().getPlayerBounusMalus().setNoMilitaryPointsRequirement(true);
	}

}
