package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.FamilyMember;

public class GetBackMoveAtTheEnd extends Effect{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6150336694981441601L;



	public String toString(){
		String toString="Malus on Player: he will have to skip his first turn and get it back at the end";
		return toString;
	}
	
	public void executeEffect(FamilyMember familyMember){
		familyMember.getPlayer().getPlayerBounusMalus().setSkipFirstFamiliarMoveAndGetItBackAtTheEnd(true);
		
	}
}
