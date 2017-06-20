package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.FamilyMember;

public class DisableFloorBonusEffect extends Effect {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5848060648308579367L;


	public void executeEffect(FamilyMember familyMember){
		familyMember.getPlayer().getPlayerBounusMalus().setNoFloorBonus(true);
	}
	
	public String toString(){
		String toString= "Player will NOT get the floor instant bonus effect permanently";
		return toString;
	}

}
