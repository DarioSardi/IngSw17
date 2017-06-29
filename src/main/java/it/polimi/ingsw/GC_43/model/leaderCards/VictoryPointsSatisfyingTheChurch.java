package it.polimi.ingsw.GC_43.model.leaderCards;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class VictoryPointsSatisfyingTheChurch extends Effect{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3109987119382889721L;
	private int extraVictoryPoints;
	
	public VictoryPointsSatisfyingTheChurch(int extraVictoryPoints){
		this.extraVictoryPoints=extraVictoryPoints;
	}
	
	
	public String toString() {
		String toString = "Player will gain additional "+this.extraVictoryPoints+" victory points each time he will satisfy the church";
		return toString;
	}
	
	public void executeEffect(FamilyMember fam){
		System.out.println("Executing effect VictoryPointsSatisfyingTheChurch");

		fam.getPlayer().getPlayerBounusMalus().setVictoryPointsAvoidingExcommunication(this.extraVictoryPoints);
		
	}

}
