package it.polimi.ingsw.GC_43.model.actionSpace;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.Bonus;
import it.polimi.ingsw.GC_43.model.FamilyMember;

public abstract class ActionSpace {
	private int minDiceValue;
	private ArrayList<FamilyMember> familiarIn;
	private Bonus bonus;
	
	public abstract boolean check(FamilyMember f);

	public abstract boolean familiarValueCheck(FamilyMember f);
	
	public abstract boolean checkColor(FamilyMember f);

	public int getMinDiceValue() {
		return minDiceValue;
	}

	public void setMinDiceValue(int minDiceValue) {
		this.minDiceValue = minDiceValue;
	}

	public ArrayList<FamilyMember> getFamiliarIn() {
		return familiarIn;
	}

	public void setFamiliarIn(ArrayList<FamilyMember> familiarIn) {
		this.familiarIn = familiarIn;
	}

	public Bonus getBonus() {
		return bonus;
	}

	public void setBonus(Bonus bonus) {
		this.bonus = bonus;
	}
	
	/**
	 * check if the action is possible, if it is give the player the bonus 
	 * @param f
	 * @return
	 */
	public boolean execute(FamilyMember f){
		if(check(f)){
			this.getBonus().earnBonus(f);
			this.getFamiliarIn().add(f);
			f.setAlreadyPlaced(true);
			f.setFamilyMemberPosition(this);
			return true;
		}
		else return false;
	}
	
	
	
	

	
}
