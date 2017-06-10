package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.FamilyMember;

public class MalusOnGetResources extends Effect{
	private String resourceType;
	private int value;
	
	
	public MalusOnGetResources(String resourceType, int value){
		this.resourceType= resourceType;
		this.value=value;
	}
	
	public boolean check(FamilyMember familyMember){
		return true;
	}
	
	public void executeEffect(FamilyMember familyMember){
		familyMember.getPlayer().getPlayerBounusMalus().getMalusOnAcquiringResources().put(this.resourceType, this.value);
		
	}
}
