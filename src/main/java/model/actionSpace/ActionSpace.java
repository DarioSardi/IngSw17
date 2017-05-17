package model.actionSpace;

import model.FamilyMember;

public abstract class ActionSpace {
	private int minDiceValue;

	public boolean check(FamilyMember f) {
		return false; //default se non faccio override
	}

	public boolean execute(FamilyMember f) {
		return false; //default se non faccio override
	}
	
	public boolean familiarValueCheck(FamilyMember f){
		return false; //default se non faccio override
	}

	public int getMinDiceValue() {
		return minDiceValue;
	}

	public void setMinDiceValue(int minDiceValue) {
		this.minDiceValue = minDiceValue;
	}
}
