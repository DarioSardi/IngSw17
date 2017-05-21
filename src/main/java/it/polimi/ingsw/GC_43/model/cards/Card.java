package it.polimi.ingsw.GC_43.model.cards;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.effects.Effect;
import it.polimi.ingsw.GC_43.model.FamilyMember;

public abstract class Card {
	private String cardName;
	private int cardEra;
	private ArrayList<Effect> instantBonus; 
	private CostEffect cost;
	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public int getCardEra() {
		return cardEra;
	}

	public void setCardEra(int cardEra) {
		this.cardEra = cardEra;
	}

	public CostEffect getCostEffect() {
		return cost;
	}

	public void setCostEffect(CostEffect costEffect) {
		this.cost = costEffect;
	}
	
	public void addEffect(Effect e){
		instantBonus.add(e);
	}
	

	public abstract boolean canIBuy(FamilyMember familiar, boolean towerTax);
	
	public abstract boolean Buy(FamilyMember familiar, boolean towerTax);
	
	
}