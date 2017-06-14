package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.ExtraFamilyMember;
import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.actions.HarvestAction;

public class ExtraHarvestAction extends Effect {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int dieValue;
	
	public ExtraHarvestAction(int dieValue){
		this.dieValue=dieValue;
		
	}
	
	public String toString(){
		String toString="Get an extra harvest action on territories of value "+this.dieValue;
		return toString;
	}
	
	public void executeEffect(FamilyMember familyMember){
		
		HarvestAction extraHarvestAction= new HarvestAction(familyMember.getPlayer().getPlayerName(), familyMember.getPlayer());
		extraHarvestAction.setDefaultFamilyMember(true);
		ExtraFamilyMember extraFamilyMember= new ExtraFamilyMember(familyMember.getPlayer(),0);
		extraFamilyMember.setDieToFamilyMember(this.dieValue);
		extraHarvestAction.setFamilyMember(extraFamilyMember);
		extraHarvestAction.setFamilyMemberColor(extraFamilyMember.getColor());
		//TODO ADD THIS ACTION TO A PLAYER ATTRIBUTE CLASS EXTRA ACTION FIELD
		//familyMember.getPlayer().getExtraActionField().getExtraActionList().add(extraHarvestAction);
	}
}
