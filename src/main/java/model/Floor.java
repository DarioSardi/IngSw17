package model;

public class Floor extends ActionSpace{
private boolean floorOccupied;
private FamilyMember familiarIn;
private Bonus bonus;
private Card card;
private Tower tower;
//TODO diamo un valore anche al piano? tipo primo,secondo ecc...?

public Floor(Bonus bonus, Card card,Tower tower, Integer minDiceValue) {
	super();
	this.setMinDiceValue(minDiceValue);
	this.bonus = bonus;
	this.card = card;
	this.tower = tower;
}
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

public Tower getTower() {
	return tower;
}
public void setTower(Tower tower) {
	this.tower = tower;
}
public boolean check(FamilyMember f) {
	return f.getDiceValue()>=this.getMinDiceValue(); //DARIO && aggiungere metodo di controllo acquisto carta
}

public boolean execute(FamilyMember f) {
	if(this.check(f)){
		return true;			//DARIO compilare codice
	}
	else {return false;} 
}

public String toString(){
	return "";		//DARIO compilare codice
}

}
