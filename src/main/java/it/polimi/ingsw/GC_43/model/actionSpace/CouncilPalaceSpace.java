package it.polimi.ingsw.GC_43.model.actionSpace;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class CouncilPalaceSpace extends Space {
	
	
	public CouncilPalaceSpace(int minDiceValue, ActionArea actionArea,ArrayList<Effect> bonus) {
		super(true, minDiceValue, actionArea,bonus);
	}
	
	
	@Override
	public boolean familiarValueCheck(FamilyMember f) {
		return f.getDiceValue()>=this.getMinDiceValue();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("council palace players:");
;		for(FamilyMember f:this.getFamiliarIn()){
				sb.append(f.getPlayer().getPlayerName());
				sb.append("\n");
		}
		return sb.toString();
	}

	
}
