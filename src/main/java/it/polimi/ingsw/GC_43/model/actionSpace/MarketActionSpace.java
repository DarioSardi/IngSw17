package it.polimi.ingsw.GC_43.model.actionSpace;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class MarketActionSpace extends ActionSpace {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5559774555424341006L;

	public MarketActionSpace(ArrayList<Effect> e, int minDiceValue) {
		this.setBonus(e);
		this.setMinDiceValue(minDiceValue);
	}

	@Override
	public boolean check(FamilyMember f) {
		return familiarValueCheck(f)&&!this.isOccupied()&&f.getPlayer().getPlayerBounusMalus().isNoMarketActionSpaceBonus(); //TODO isNoMarketActionSpaceBonus return true if i can go in the market?!
	}

	@Override
	public boolean familiarValueCheck(FamilyMember f) {
		return f.getDiceValue()>=this.getMinDiceValue();
	}

	@Override
	public boolean checkColor(FamilyMember f) {
		return true;
	}
	
	@Override
	public String toString(){
		String toString= "in this market you will get : ";
		for(Effect effect : this.getBonus())
			toString=toString+effect.toString();
		return toString;	
	}

}
