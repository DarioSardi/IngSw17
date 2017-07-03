package it.polimi.ingsw.GC_43.model.cards;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.effects.CostEffect;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class CharacterCard extends Card{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3927577823990360173L;
	public static final String TYPE = "CharacterCard";
	public CharacterCard(String cardName, int cardEra, CostEffect cost, ArrayList<Effect> instantBonus,
			ArrayList<Effect> permaBonus,String cardIcon) {
		super(cardName, cardEra, cost, instantBonus, permaBonus,cardIcon);
	}
	@Override
	public String getType() {
		return TYPE;
	}
	
	
}