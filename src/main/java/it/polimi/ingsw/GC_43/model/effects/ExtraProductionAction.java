package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.FamilyMember;

public class ExtraProductionAction extends Effect {
	private int dieValue;
	
	
	
	
	public ExtraProductionAction(int dieValue){
		this.dieValue=dieValue;
		
	}
	
	public String toString(){
		String toString="Get an extra production on buildings of value "+this.dieValue;
		return toString;
	}
	
	public void executeEffect(FamilyMember familyMember){
	//TODO to implement	
	}
}
