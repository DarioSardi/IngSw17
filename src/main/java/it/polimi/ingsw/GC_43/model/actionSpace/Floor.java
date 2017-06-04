package it.polimi.ingsw.GC_43.model.actionSpace;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.cards.Card;
import it.polimi.ingsw.GC_43.model.cards.CardHandler;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class Floor extends ActionSpace{
private boolean floorOccupied;
private Card card;
private Tower tower;


public Floor(Effect bonus,Tower tower, Integer minDiceValue) {
	super();
	this.setMinDiceValue(minDiceValue);
	this.setBonus(bonus);
	this.tower = tower;
	this.setFamiliarIn(new ArrayList<FamilyMember>());
	this.card=null;
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

public void removeCard(){
	card=null;
}

public Tower getTower() {
	return tower;
}
public void setTower(Tower tower) {
	this.tower = tower;
}

public boolean check(FamilyMember f) {
	return f.getDiceValue()>=this.getMinDiceValue()&&CardHandler.checkBuy(this, f.getPlayer(), this.getCard(),this.getTower().check(f));
}
//DARIO tower tax!

/**
 * buy the card,get the floor bonus,set the familiar,set the floor and the tower as occupied
 */
public boolean execute(FamilyMember f) {
	if(this.check(f)){	
		CardHandler.buyCard(this, f,this.card, this.floorOccupied);
		this.getBonus().executeEffect(f);
		this.getFamiliarIn().add(f);
		f.setAlreadyPlaced(true);
		f.setFamilyMemberPosition(this);
		this.getTower().setTowerOccupied(true);
		this.setFloorOccupied(true);
		return true;
	}
	else {return false;} 
}
public String toString(){
	return "foor cost: "+ String.valueOf(this.getMinDiceValue())+ "\n"+"floor bonus: "+this.getBonus().toString()+"\n"+" floor Card: "+ this.card.getCardName();
}

/*
 * print floor info
 *@return print floor cost,floor bonus,floor card
 */
public String toStringAll(){
StringBuilder sb= new StringBuilder();
	sb.append("Floor dice requirement: "+ String.valueOf(this.getMinDiceValue()));
	sb.append("\n");
	if(this.getBonus()!=null){
		sb.append("Floor bonus: \n "+this.getBonus().toString());}
	else{sb.append("Floor bonus: no bonus here");}
	sb.append("\n");
	if(this.getCard()!=null){
		sb.append("Floor Card: "+ this.card.toString());}
	else{sb.append("no card");}
	sb.append("\n");
	return sb.toString();
}
@Override
public boolean familiarValueCheck(FamilyMember f) {
	return f.getDiceValue()+this.tower.getBonusOfArea(f.getPlayer())>=this.getMinDiceValue();
}
@Override
public boolean checkColor(FamilyMember f) {
	return true;
}

public void resetSpace(){
	this.removeCard();
	this.floorOccupied=false;
	this.removeAllFamiliars();
}

}
