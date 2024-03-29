package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.FamilyMember;

public class DisableMarketActionSpacesEffect extends Effect {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7919714609006212294L;

	

	public String toString(){
		String toString="Player will NOT have access to market action space anymore";
		return toString;
	}
	
	public void executeEffect(FamilyMember familyMember){
		System.out.println("Executing DisableMarketActionSpacesEffect Effect");

		familyMember.getPlayer().getPlayerBounusMalus().setNoMarketActionSpaceBonus(true);
	}


}
