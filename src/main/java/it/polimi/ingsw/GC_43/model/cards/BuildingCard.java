package it.polimi.ingsw.GC_43.model.cards;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.effects.CostEffect;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class BuildingCard extends Card {
	public static final String TYPE = "BuildingCard";
	private int productionDice;

	public BuildingCard(String cardName, int cardEra, CostEffect cost, ArrayList<Effect> instantBonus,
			ArrayList<Effect> permaBonus, int productionDice) {
		super(cardName, cardEra, cost, instantBonus, permaBonus);
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