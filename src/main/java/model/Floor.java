package model;

public class Floor extends ActionSpace{
private boolean floorOccupied;
private FamilyMember familiarIn;
private Bonus bonus;
private Card card;
//DARIO generare costruttore

public boolean isFloorOccupied() {
	return floorOccupied;
}

public void setFloorOccupied(boolean floorOccupied) {
	this.floorOccupied = floorOccupied;
}

public FamilyMember getFamiliarIn() {
	return familiarIn;
}

public void setFamiliarIn(FamilyMember familiarIn) {
	this.familiarIn = familiarIn;
}

public Bonus getBonus() {
	return bonus;
}

public void setBonus(Bonus bonus) {
	this.bonus = bonus;
}

public Card getCard() {
	return card;
}

public void setCard(Card card) {
	this.card = card;
}

public boolean check() {
	return true; //DARIO compilare codice
}

public boolean execute() {
	return true; //DARIO compilare codice
}

public String toString(){
	return "";		//DARIO compilare codice
}

}
