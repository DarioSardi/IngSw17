package it.polimi.ingsw.GC_43.model.cards;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.effects.Effect;

public class TerritoryCard extends Card {
	public static final String TYPE = "TerritoryCard";
	private int productionDice;

	public TerritoryCard(String cardName, int cardEra, ArrayList<Effect> instantBonus, ArrayList<Effect> permaBonus,
			int productionDice) {
		super(cardName, cardEra, null, instantBonus, permaBonus);
		this.productionDice = productionDice;
	}

	public int getProductionDice() {
		return productionDice;
	}

	@Override
	public String getType() {
		return TYPE;
	}

}