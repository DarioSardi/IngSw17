package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.FamilyMember;

public class PickExtraCardFromTower extends Effect{ 
	private int dieValue;
	private String towerType;
	
	public PickExtraCardFromTower(int dieValue, String towerType){
		this.dieValue=dieValue;
		this.towerType= towerType;
	}
	
	public void executeEffect(FamilyMember familyMember){
		
	}
}