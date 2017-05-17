package model.actionSpace;

public abstract class ActionSpace {
	private int minDiceValue;

	public boolean check() {
		return false; //default se non passo il familiare
	}

	public boolean execute() {
		return false; // default se non passo il familiare
	}

	public int getMinDiceValue() {
		return minDiceValue;
	}

	public void setMinDiceValue(int minDiceValue) {
		this.minDiceValue = minDiceValue;
	}
}
