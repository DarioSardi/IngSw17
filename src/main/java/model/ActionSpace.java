package model;

public abstract class ActionSpace {
	private int minDiceValue;

	public boolean check() {
		return true; //DARIO compilare codice
	}

	public boolean execute() {
		return true; //DARIO compilare codice
	}

	public int getMinDiceValue() {
		return minDiceValue;
	}

	public void setMinDiceValue(int minDiceValue) {
		this.minDiceValue = minDiceValue;
	}
}
