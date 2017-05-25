package it.polimi.ingsw.GC_43.model.cards;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.effects.CostEffect;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public abstract class Card {
	private String cardName;
	private int cardPeriod;
	private CostEffect cost;
	private ArrayList<Effect> instantBonus;
	private ArrayList<Effect> permaBonus;

	public Card(String cardName, int cardEra, CostEffect cost, ArrayList<Effect> instantBonus,
			ArrayList<Effect> permaBonus) {
		super();
		this.cardName = cardName;
		this.cardPeriod = cardEra;
		this.cost = cost;
		this.instantBonus = instantBonus;
		this.permaBonus = permaBonus;
	}
	
	public abstract String getType();
	
	public String getCardName() {
		return cardName;
	}

	public int getCardEra() {
		return cardPeriod;
	}

	public CostEffect getCost() {
		return cost;
	}

	public ArrayList<Effect> getInstantBonus() {
		return instantBonus;
	}

	public ArrayList<Effect> getPermaBonus() {
		return permaBonus;
	}
	
	
}