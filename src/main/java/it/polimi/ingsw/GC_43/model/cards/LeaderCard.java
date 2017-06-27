package it.polimi.ingsw.GC_43.model.cards;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.effects.Effect;

public class LeaderCard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5014800585568321459L;
	private String cardName;
	private ArrayList<Effect> requirements;
	private ArrayList<Effect> ability;
	private boolean alreadyUsed;
	private boolean permanentAbility;

	public LeaderCard(String cardName, ArrayList<Effect> requirements, ArrayList<Effect> ability,
			boolean permanentAbility) {
		this.cardName = cardName;
		this.requirements = requirements;
		this.ability = ability;
		// Remember to reset for each roun alreadyUsed to false
		this.alreadyUsed = false;
		this.permanentAbility = permanentAbility;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public ArrayList<Effect> getRequirements() {
		return requirements;
	}

	public void setRequirements(ArrayList<Effect> requirements) {
		this.requirements = requirements;
	}

	public ArrayList<Effect> getAbility() {
		return ability;
	}

	public void setAbility(ArrayList<Effect> ability) {
		this.ability = ability;
	}

	public boolean isAlreadyUsed() {
		return alreadyUsed;
	}

	public void setAlreadyUsed(boolean alreadyUsed) {
		this.alreadyUsed = alreadyUsed;
	}

	public boolean isPermanentAbility() {
		return permanentAbility;
	}

	public void setPermanentAbility(boolean permanentAbility) {
		this.permanentAbility = permanentAbility;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n......................\n");
		sb.append("Card name: " + this.getCardName() + "\n");
		sb.append("Card has permanent ability: " + this.isPermanentAbility() + "\n");

		// cost

		// requirements bonus
		if (this.getRequirements().size() > 0) {
			sb.append("\n");
			sb.append("Card requirements are:\n");
			this.getRequirements().stream().forEach(e -> sb.append(e.toString()));
			sb.append("\n");
		}
		// Permanent bonus
		if (this.getAbility().size() > 0) {
			sb.append("\n");
			sb.append("Card ability:\n");
			this.getAbility().stream().forEach(e -> sb.append(e.toString()));
			sb.append("\n");
		}
		sb.append("......................\n");
		return sb.toString();
	}
}
