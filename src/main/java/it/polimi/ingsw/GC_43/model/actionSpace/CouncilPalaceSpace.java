package it.polimi.ingsw.GC_43.model.actionSpace;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class CouncilPalaceSpace extends Space {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4033708900462156747L;
	private ArrayList<Effect> bonus;

	public CouncilPalaceSpace(int minDiceValue, ActionArea actionArea,ArrayList<Effect> bonus) {
		super(true, minDiceValue, actionArea);
		this.bonus=bonus;
	}
	
	
	@Override
	public boolean familiarValueCheck(FamilyMember f) {
		return f.getDiceValue()>=this.getMinDiceValue();
	}
	
	@Override
	public boolean check(FamilyMember f) {
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
