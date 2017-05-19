package it.polimi.ingsw.GC_43.effects;

import it.polimi.ingsw.GC_43.model.FamilyMember;

public class MalusOnFinalVictoryPoints extends Effect {
	private String malusTypeOnVictoryPoint;
	
	public MalusOnFinalVictoryPoints(String malusType){
		this.malusTypeOnVictoryPoint=malusType;
		
	}
	
	public void executeEffect(FamilyMember familyMember){
		familyMember.getPlayer().getPlayerBounusMalus().getMalusOnFinalVictoryPoints().put(this.malusTypeOnVictoryPoint,true);
		
	}

}
