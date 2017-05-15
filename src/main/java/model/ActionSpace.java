package model;

public abstract class ActionSpace {
	private int minDiceValue;

	public boolean check() {
		return false; //DARIO corretto?
	}

	public boolean execute() {
		return false; //DARIO corretto?
	}

	public int getMinDiceValue() {
		return minDiceValue;
	}

	public void setMinDiceValue(int minDiceValue) {
		this.minDiceValue = minDiceValue;
	}
}
