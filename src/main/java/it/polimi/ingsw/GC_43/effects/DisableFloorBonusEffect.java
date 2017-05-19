package it.polimi.ingsw.GC_43.effects;

import it.polimi.ingsw.GC_43.model.FamilyMember;

public class DisableFloorBonusEffect extends Effect {
	
	
	public void executeEffect(FamilyMember familyMember){
		familyMember.getPlayer().getPlayerBounusMalus().setNoFloorBonus(true);
	}

}
