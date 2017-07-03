package it.polimi.ingsw.GC_43.model.cards;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.effects.Effect;

public class TerritoryCard extends Card {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4943852848013446288L;
	public static final String TYPE = "TerritoryCard";
	private int harvestDice;

	public TerritoryCard(String cardName, int cardEra, ArrayList<Effect> instantBonus, ArrayList<Effect> permaBonus,
			int productionDice,String cardIcon) {
		super(cardName, cardEra, null, instantBonus, permaBonus,cardIcon);
		this.harvestDice = productionDice;
	}

	public int getProductionDice() {
		return harvestDice;
	}

	@Override
	public String getType() {
		return TYPE;
	}
	@Override
	public String toString() {
		
		return super.toString()+"dice value for the permanent bonus: "+this.harvestDice+"\n";
		
	}

}