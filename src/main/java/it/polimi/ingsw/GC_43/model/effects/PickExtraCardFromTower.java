package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.FamilyMember;

public class PickExtraCardFromTower extends Effect{ 
	private int dieValue;
	private String towerType;
	
	
	
	public String toString(){
		String toString="Pick extra card from Tower "+this.towerType+" with a die value of "+this.dieValue;
		return toString;
	}
	public PickExtraCardFromTower(int dieValue, String towerType){
		this.dieValue=dieValue;
		this.towerType= towerType;
	}
	public PickExtraCardFromTower(int dieValue){
		this.dieValue=dieValue;
		this.towerType= "whatever";
	}
	
	public void executeEffect(FamilyMember familyMember){
		
	}
}
