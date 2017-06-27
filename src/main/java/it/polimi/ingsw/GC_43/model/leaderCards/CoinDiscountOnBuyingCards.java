package it.polimi.ingsw.GC_43.model.leaderCards;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class CoinDiscountOnBuyingCards extends Effect {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4797656045725075010L;
	private int coinValue;
	
	public CoinDiscountOnBuyingCards(int coinValue){
		this.coinValue=coinValue;
	}
	
	public String toString() {
		String toString = "Player will receive a discount on coins amounting to "+this.coinValue+" on buying cards";
		return toString;
	}
	
	public void executeEffect(FamilyMember fam){
		fam.getPlayer().getPlayerBounusMalus().setCoinDiscountOnCards(coinValue);
	}

}
