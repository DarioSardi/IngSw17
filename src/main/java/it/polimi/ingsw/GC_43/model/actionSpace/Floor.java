package it.polimi.ingsw.GC_43.model.actionSpace;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.Bonus;
import it.polimi.ingsw.GC_43.model.Card;
import it.polimi.ingsw.GC_43.model.FamilyMember;

public class Floor extends ActionSpace{
private boolean floorOccupied;
private Card card;
private Tower tower;
//DARIO metodo addCard() / removeCard()

public Floor(Bonus bonus,Tower tower, Integer minDiceValue) {
	super();
	this.setMinDiceValue(minDiceValue);
	this.setBonus(bonus);
	this.tower = tower;
	this.setFamiliarIn(new ArrayList<FamilyMember>());
}
public boolean isFloorOccupied() {
	return floorOccupied;
}

public void setFloorOccupied(boolean floorOccupied) {
	this.floorOccupied = floorOccupied;
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
	return f.getDiceValue()>=this.getMinDiceValue(); //DARIO necessaria logica buycard
}

public boolean execute(FamilyMember f) {
	if(this.check(f)){
		return true;			//DARIO necessaria logica buycard
	}
	else {return false;} 
}
/*
 * print floor info
 *@return print floor cost,floor bonus,floor card
 */
public String toString(){
	return "foor cost: "+ String.valueOf(this.getMinDiceValue())+ "floor bonus: "+this.getBonus().toString()+" floor Card: "+ this.card.toString();
}
@Override
public boolean familiarValueCheck(FamilyMember f) {
	return f.getDiceValue()+this.tower.getBonusOfArea(f.getPlayer())>=this.getMinDiceValue();
}
@Override
public boolean checkColor(FamilyMember f) {
	return true;
}

}
