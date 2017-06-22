package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.FamilyMember;

public class MalusOnGetResources extends Effect{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2396328772180992446L;
	
	private String resourceType;
	private int value;
	
	
	public MalusOnGetResources(String resourceType, int value){
		this.resourceType= resourceType;
		this.value=value;
	}
	
	public boolean check(FamilyMember familyMember){
		return true;
	}
	
	public String toString(){
		String toString="Player will receive a malus on get resources on "+this.resourceType+" of a value amounting to "+this.value;
		return toString;
	}
	
	public void executeEffect(FamilyMember familyMember){
		System.out.println("Executing MalusOnGetResources Effect");

		familyMember.getPlayer().getPlayerBounusMalus().getMalusOnAcquiringResources().put(this.resourceType, this.value);
		
	}
}
