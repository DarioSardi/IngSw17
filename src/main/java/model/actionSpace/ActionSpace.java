package model.actionSpace;

import model.FamilyMember;

public abstract class ActionSpace {
	private int minDiceValue;

	public boolean check() {
		return false; //default se non passo il familiare
	}

	public boolean execute() {
		return false; // default se non passo il familiare
	}
	
	public boolean familiarValueCheck(FamilyMember f){
		return f.getDiceValue()/*+bonus dice*/>=minDiceValue;
	}

	public int getMinDiceValue() {
		return minDiceValue;
	}

	public void setMinDiceValue(int minDiceValue) {
		this.minDiceValue = minDiceValue;
	}
}
