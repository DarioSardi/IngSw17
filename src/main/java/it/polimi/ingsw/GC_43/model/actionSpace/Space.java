package it.polimi.ingsw.GC_43.model.actionSpace;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class Space extends ActionSpace{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1345677933307984329L;
	private boolean unlimited;
	private ActionArea actionArea;
	public Space(boolean unlimited, int minDiceValue,ActionArea actionArea){
		this.unlimited=unlimited;
		this.setMinDiceValue(minDiceValue);
		this.actionArea=actionArea;
		this.setBonus(null);
		}
	
	/**
	 * check if i can enter the space
	 * @return true if action can be performed
	 */
	@Override
	public boolean check(FamilyMember f) {
		if(unlimited){
			return this.familiarValueCheck(f)&&this.actionArea.check(f);
		}
		else{
			return this.familiarValueCheck(f)&&this.actionArea.check(f)&&this.getFamiliarIn().isEmpty();
		}
		
	}
	
	/**
	 * evaluate if the familiarValue+bonusArea>=min dice value requested
	 * @return true if is enough
	 */
	@Override
	public boolean familiarValueCheck(FamilyMember f) {
		return f.getDiceValue()+this.actionArea.getBonusOfArea(f.getPlayer())>=this.getMinDiceValue();
	}

	/**
	 * @return true if there are no other familiar with the same color inside
	 */
	@Override
	public boolean checkColor(FamilyMember f) {
		for(FamilyMember fM:this.getFamiliarIn()){
			if(fM.getColor()==f.getColor()){return false;}
		}
		return true;
	}

	public boolean isUnlimited() {
		return unlimited;
	}

	public ActionArea getActionArea() {
		return actionArea;
	}
	
	@Override
	public String toString() {
		return "lo spazio è vuoto? "+this.getFamiliarIn().isEmpty();
	}

	

}
