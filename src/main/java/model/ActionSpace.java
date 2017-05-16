package model;

public abstract class ActionSpace {
	private int minDiceValue;

	public boolean check() {
		return false; //DARIO compilare codice
	}

	public boolean execute() {
		return false; //DARIO compilare codice
	}

	public int getMinDiceValue() {
		return minDiceValue;
	}

	public void setMinDiceValue(int minDiceValue) {
		this.minDiceValue = minDiceValue;
	}
}
