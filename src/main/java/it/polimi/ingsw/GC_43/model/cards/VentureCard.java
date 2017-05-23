package it.polimi.ingsw.GC_43.model.cards;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.effects.CostEffect;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class VentureCard extends Card{
	private int militaryCost, militaryMin;
	public static final String TYPE = "VentureCard";
	public VentureCard(String cardName, int cardEra, CostEffect cost, int militaryCost,int militaryMin, ArrayList<Effect> instantBonus,
			ArrayList<Effect> permaBonus) {
		super(cardName, cardEra, cost, instantBonus, permaBonus);
		this.militaryCost=militaryCost;
		this.militaryMin=militaryMin;
	}

	public int getMilitaryCost() {
		return militaryCost;
	}

	public int getMilitaryMin() {
		return militaryMin;
	}

	@Override
	public String getType() {
		return TYPE;
	}
	
	
}