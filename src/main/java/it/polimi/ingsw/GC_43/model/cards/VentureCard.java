package it.polimi.ingsw.GC_43.model.cards;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.effects.CostEffect;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class VentureCard extends Card{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2078693514720530416L;
	private int militaryCost, militaryMin;
	public static final String TYPE = "VentureCard";
	public VentureCard(String cardName, int cardEra, CostEffect cost, int militaryCost,int militaryMin, ArrayList<Effect> instantBonus,
			ArrayList<Effect> permaBonus,String cardIcon) {
		super(cardName, cardEra, cost, instantBonus, permaBonus,cardIcon);
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
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(super.toString());
		sb.append("alternative military point cost\n");
		sb.append("military point cost:"+this.militaryCost+"\n");
		sb.append("minimum military point to have:"+this.militaryMin+"\n");
		return sb.toString();
	}
}