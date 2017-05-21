package it.polimi.ingsw.GC_43.model;

import it.polimi.ingsw.GC_43.model.cards.Card;

public class VentureCard extends Card{
	private int endVictoryPoints;
	private boolean doubleCost;
	
	public VentureCard(String cardName, int cardEra) {
		super(cardName, cardEra);
	}
}