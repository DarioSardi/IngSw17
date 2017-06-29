package it.polimi.ingsw.GC_43.model.leaderCards;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class LeaderOkPlaceOccupied extends Effect {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3279613405614137034L;
	public boolean oncePerRound;
	
	public LeaderOkPlaceOccupied(){
		this.oncePerRound=false;
	}
	
	public String toString() {
		String toString = "Player will have access to occupied action spaces too";
		return toString;
	}
	
	public void executeEffect(FamilyMember familyMember){
		System.out.println("Executing effect LeaderOkPlaceOccupied");

		familyMember.getPlayer().getPlayerBounusMalus().setOkPlaceOccupied(true);
	}

}
