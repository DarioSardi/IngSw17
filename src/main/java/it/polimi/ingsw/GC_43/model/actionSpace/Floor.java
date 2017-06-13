package it.polimi.ingsw.GC_43.model.actionSpace;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.cards.Card;
import it.polimi.ingsw.GC_43.model.cards.CardHandler;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class Floor extends ActionSpace {
	private boolean floorOccupied;
	private Card card;
	private Tower tower;

	public Floor(ArrayList<Effect> bonus, Tower tower, Integer minDiceValue) {
		super();
		this.setMinDiceValue(minDiceValue);
		this.setBonus(bonus);
		this.tower = tower;
		this.setFamiliarIn(new ArrayList<FamilyMember>());
		this.card = null;
		this.floorOccupied = false;
	}

	public boolean isFloorOccupied() {
		return floorOccupied;
	}

	public void setFloorOccupied(boolean floorOccupied) {
		this.floorOccupied = floorOccupied;
	}

	public void addFamiliarIn(FamilyMember f) {
		this.getFamiliarIn().add(f);
		this.setFloorOccupied(true);

	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public void removeCard() {
		card = null;
	}

	public Tower getTower() {
		return tower;
	}

	public void setTower(Tower tower) {
		this.tower = tower;
	}

	public boolean check(FamilyMember f) {
		return f.getDiceValue() >= this.getMinDiceValue()
				&& CardHandler.checkBuy(this, f.getPlayer(), this.getCard(), this.getTower().check(f))
				&& this.getTower().checkColor(f) && !this.floorOccupied;
	}
	// DARIO tower tax!

	/**
	 * buy the card,get the floor bonus,set the familiar,set the floor and the
	 * tower as occupied
	 */
	public boolean execute(FamilyMember f) {
		if (this.check(f)) {
			CardHandler.buyCard(this, f, this.card, this.floorOccupied);
			for (Effect effect : this.getBonus())
				effect.executeEffect(f);
			this.getFamiliarIn().add(f);
			f.setAlreadyPlaced(true);
			f.setFamilyMemberPosition(this);
			this.getTower().setTowerOccupied(true);
			this.setFloorOccupied(true);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean familiarValueCheck(FamilyMember f) {
		return f.getDiceValue() + this.tower.getBonusOfArea(f.getPlayer()) >= this.getMinDiceValue();
	}

	/**
	 * check if the color of "f" matches the color of the familiar already in
	 * the floor
	 */
	@Override
	public boolean checkColor(FamilyMember f) {
		if (!this.getFamiliarIn().isEmpty() && this.getFamiliarIn().get(0).getColor() == f.getColor()) {
			return true;
		} else
			return false;
	}

	public void resetSpace() {
		this.removeCard();
		this.floorOccupied = false;
		this.removeAllFamiliars();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("||||||||||||||||||||||||||||||||||||||||");
		sb.append("\nwe are in the " + this.getTower().getTowerColor().toString());
		sb.append("\nfloor cost: " + String.valueOf(this.getMinDiceValue()));
		if (this.getBonus() != null) {
			sb.append("\nfloor bonus: " + this.getBonus().toString());
		} else {
			sb.append("\nfloor bonus: no bonus for this floor");
		}
		if (this.getCard() != null) {
			sb.append("\nfloor Card: " + this.card.getCardName());
		} else {
			sb.append("\nfloor Card: no card in this floor");
		}
		return sb.toString();
	}
	
	/*
	 * print floor info
	 * 
	 * @return print floor cost,floor bonus,floor card
	 */
	public String toStringAll() {
		StringBuilder sb = new StringBuilder();
		sb.append("Floor dice requirement: " + String.valueOf(this.getMinDiceValue()));
		sb.append("\n");
		if (this.getBonus() != null) {
			sb.append("Floor bonus: \n ");
			for (Effect effect : this.getBonus())
				sb.append(effect.toString());
		} else {
			sb.append("Floor bonus: no bonus here");
		}
		sb.append("\n");
		if (this.getCard() != null) {
			sb.append("Floor Card: " + this.card.toString());
		} else {
			sb.append("no card");
		}
		sb.append("\n");
		return sb.toString();
	}

}
