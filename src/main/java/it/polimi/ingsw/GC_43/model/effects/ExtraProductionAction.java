package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.ExtraFamilyMember;
import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.actions.HarvestAction;
import it.polimi.ingsw.GC_43.model.actions.ProductionAction;

public class ExtraProductionAction extends Effect {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int dieValue;
	
	
	
	
	public ExtraProductionAction(int dieValue){
		this.dieValue=dieValue;
		
	}
	
	public String toString(){
		String toString="Get an extra production on buildings of value "+this.dieValue;
		return toString;
	}
	
	public void executeEffect(FamilyMember familyMember){
		ProductionAction extraProductionAction= new ProductionAction(familyMember.getPlayer().getPlayerName(), familyMember.getPlayer());
		extraProductionAction.setDefaultFamilyMember(true);
		ExtraFamilyMember extraFamilyMember= new ExtraFamilyMember(familyMember.getPlayer(),0);
		extraFamilyMember.setDieToFamilyMember(this.dieValue);
		extraProductionAction.setFamilyMember(extraFamilyMember);
		extraProductionAction.setFamilyMemberColor(extraFamilyMember.getColor());
	}
}
