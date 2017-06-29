package it.polimi.ingsw.GC_43.model;


import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.cards.BuildingCard;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class PlayerPersonalBonus implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2815971959175413082L;
	private ArrayList<BuildingCard> buildingCards;
	
	public PlayerPersonalBonus (ArrayList<Effect> productionBonus, ArrayList<Effect> advancedBonus){
		this.buildingCards = new ArrayList<>();
	}
	
	public BuildingCard getBuildingCard(int numCarta){
		return this.buildingCards.get(numCarta);	
	}
	
	public ArrayList<BuildingCard> getArrayBuildingCards(){
		return this.buildingCards;	
	}
		
	public void addBuildingCard(BuildingCard card){
		this.buildingCards.add(card);	
	}

	
	public boolean canIAdd(int actualNumCards){
		return (actualNumCards <= GlobalVariables.maxNumberPlayerCards);
	}

	public String toString(){
		String s="";	
		return s;
	}
}