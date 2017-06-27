package it.polimi.ingsw.GC_43.model.leaderCards;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class PermanentDieToCoulouredFamiliars extends Effect {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7560411100124295708L;

	private int dieValue;

	public PermanentDieToCoulouredFamiliars(int dieValue) {
		this.dieValue = dieValue;
	}

	public String toString() {
		String toString = "Each couloured family Member of player will have a starting permanent die value, regardless dice values, of "
				+ this.dieValue;
		return toString;
	}

	public void executeEffect(FamilyMember fam) {

	}

}
