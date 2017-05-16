package model;

public class FamilyMember {
//TODO generata per risolvere le dipendenze
	private int color;
	private boolean alreadyPlaced;
	private ActionSpace familyMemberPosition;
	private Player player;
	int diceValue;
	
	
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public boolean isAlreadyPlaced() {
		return alreadyPlaced;
	}
	public void setAlreadyPlaced(boolean alreadyPlaced) {
		this.alreadyPlaced = alreadyPlaced;
	}
	public int getDiceValue() {
		return diceValue;
	}
	public void setDiceValue(int diceValue) {
		this.diceValue = diceValue;
	}
	
	private void printFamilyMember(){
		//TODO to implement ( da passare alla view, ritornerà una stringa in realtà)
	}
	public ActionSpace getFamilyMemberPosition() {
		return familyMemberPosition;
	}
	public void setFamilyMemberPosition(ActionSpace familyMemberPosition) {
		this.familyMemberPosition = familyMemberPosition;
	}
	
}
