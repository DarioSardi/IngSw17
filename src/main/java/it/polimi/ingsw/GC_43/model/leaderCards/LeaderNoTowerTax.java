package it.polimi.ingsw.GC_43.model.leaderCards;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class LeaderNoTowerTax extends Effect{

		
		/**
	 * 
	 */
	private static final long serialVersionUID = 5247728105940555071L;
		public boolean oncePerRound;
		
		public LeaderNoTowerTax(){
			this.oncePerRound=false;
		}
		
		public String toString() {
			String toString = "Player will not have to pay for tower tax anymore";
			return toString;
		}
		
		
		public void executeEffect(FamilyMember familyMember){
			familyMember.getPlayer().getPlayerBounusMalus().setNoTowerTax(true);;
		}

	}



