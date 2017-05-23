package it.polimi.ingsw.GC_43.model.actionSpace;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class CouncilPalaceSpace extends Space {
	public CouncilPalaceSpace(int minDiceValue, ActionArea actionArea,Effect bonus) {
		super(true, minDiceValue, actionArea,bonus);
	}
	
	
	@Override
	public boolean familiarValueCheck(FamilyMember f) {
		return f.getDiceValue()>=this.getMinDiceValue();
	}
	
	

}
