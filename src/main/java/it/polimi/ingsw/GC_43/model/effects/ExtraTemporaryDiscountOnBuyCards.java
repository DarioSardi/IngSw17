package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.FamilyMember;

public class ExtraTemporaryDiscountOnBuyCards extends Effect{ 
	private int quantity;
	private String resourceType;
	
	public ExtraTemporaryDiscountOnBuyCards(int quantity, String resourceType){
		this.quantity=quantity;
		this.resourceType= resourceType;
	}
	
	public void executeEffect(FamilyMember familyMember){
		
	}
}