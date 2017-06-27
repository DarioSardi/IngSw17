package it.polimi.ingsw.GC_43.model.leaderCards;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class AssignDieValueToSingleFamiliar extends Effect {

	/**
	 * 
	 */
	private static final long serialVersionUID = 583862635930759913L;
	private int dieValue;

	public AssignDieValueToSingleFamiliar(int dieValue) {
		this.dieValue = dieValue;
	}
	
	public String toString() {
		String toString = "A choosen family member will receive a die value of "+this.dieValue;
		return toString;
	}

	public void executeEffect(FamilyMember fam) {
		if (fam.getColor() != 0) 
			fam.setDieToFamilyMember(this.dieValue);
		
	}

}
