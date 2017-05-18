package model.actionSpace;

import model.Bonus;
import model.FamilyMember;

public class CouncilPalaceSpace extends Space {
	public CouncilPalaceSpace(int minDiceValue, ActionArea actionArea,Bonus bonus) {
		super(true, minDiceValue, actionArea,bonus);
	}
	
	
	@Override
	public boolean familiarValueCheck(FamilyMember f) {
		return f.getDiceValue()>=this.getMinDiceValue();
	}
	
	

}
