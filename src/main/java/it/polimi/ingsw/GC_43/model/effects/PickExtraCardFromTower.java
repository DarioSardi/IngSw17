package it.polimi.ingsw.GC_43.model.effects;

import java.util.HashMap;

import it.polimi.ingsw.GC_43.model.ExtraFamilyMember;
import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.actions.HarvestAction;
import it.polimi.ingsw.GC_43.model.actions.TowerAction;

public class PickExtraCardFromTower extends Effect{ 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int dieValue;
	private String towerType;
	private HashMap<String,Integer> resourceDiscounted; 
	
	
	
	public String toString(){
		String toString="Pick extra card from Tower "+this.towerType+" with a die value of "+this.dieValue;
		return toString;
	}
	public PickExtraCardFromTower(int dieValue, String towerType){
		this.dieValue=dieValue;
		this.towerType= towerType;
		this.resourceDiscounted= new HashMap<String,Integer>();
		this.resourceDiscounted.put("coin", 0);
		this.resourceDiscounted.put("servant", 0);
		this.resourceDiscounted.put("stone", 0);
		this.resourceDiscounted.put("wood", 0);
		this.resourceDiscounted.put("victoryPoint", 0);
		this.resourceDiscounted.put("militaryPoint", 0);
		this.resourceDiscounted.put("faithPoint",0);
	}
	public PickExtraCardFromTower(int dieValue){
		this.dieValue=dieValue;
		this.towerType= "whatever";
	}
	
	public void setDiscount(String resourceType, int value){
		this.resourceDiscounted.put(resourceType, value);
	}
	
	public void executeEffect(FamilyMember familyMember){
		TowerAction extraTowerAction= new TowerAction(familyMember.getPlayer().getPlayerName(), familyMember.getPlayer());
		extraTowerAction.setDefaultFamilyMember(true);
		ExtraFamilyMember extraFamilyMember= new ExtraFamilyMember(familyMember.getPlayer(),0);
		extraFamilyMember.setDieToFamilyMember(this.dieValue);
		extraTowerAction.setFamilyMember(extraFamilyMember);
		extraTowerAction.setFamilyMemberColor(extraFamilyMember.getColor());
		
		if(!this.towerType.equals("whatever")){
			if(this.towerType.equals("territoryTower")){
		//TODO TO BE CONTINUED, WITH THE OTHER EVEN EXTRA ACTIONS		
			}
		}
	}
}
