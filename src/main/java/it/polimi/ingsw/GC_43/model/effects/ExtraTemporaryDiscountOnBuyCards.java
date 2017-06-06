package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.FamilyMember;

import it.polimi.ingsw.GC_43.model.resources.*;

public class ExtraTemporaryDiscountOnBuyCards extends Effect{ 
	private Resource resource;
	String towerType;
	
	public String toString(){
		String toString="Get an instant temporary discount on buy Cards in "+this.towerType+"of "+this.resource.getResourceType()+": "+this.resource.getValue();
		return toString;
	}
	
	public ExtraTemporaryDiscountOnBuyCards(Resource resource, String towerType){
		this.resource=resource;
		this.towerType=towerType;
	}
	
	public void executeEffect(FamilyMember familyMember){
		//TODO to implement
	}
}