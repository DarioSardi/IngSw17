package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.FamilyMember;

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
	//TODO to implement	
	}
}
