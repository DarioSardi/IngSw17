package it.polimi.ingsw.GC_43.model;


import java.lang.reflect.Array;

import java.util.ArrayList;
import java.util.List;

public class PlayerCards{
	private Array[] militaryPointsRequirements;
	private List<Card> buildingCards;
	private List<Card> ventureCards;
	private List<Card> characterCards;
	private List<Card> territoryCards;
	private ArrayList<Bonus> defaultBonusHarvest;
	private ArrayList<Bonus> defaultBonusBuildings;
	
	public PlayerCards (){
		this.buildingCards = new ArrayList<Card>();
    	this.ventureCards = new ArrayList<Card>();
    	this.territoryCards= new ArrayList<Card>();
    	this.characterCards = new ArrayList<Card>();
    	this.defaultBonusHarvest = new ArrayList<Bonus>();
    	this.defaultBonusBuildings = new ArrayList<Bonus>();
	}
	
	public void harvestProduction(){
		//SAMUEL Capire a cosa serva metodo harvestProduction
	}
	
	public void buildingProduction(){
		//SAMUEL Capire a cosa serva metodo buildingProduction
	}
	
	public void setMilitaryPointsRequirements(Array[] requirements){	//SAMUEL A cosa serve?
		this.militaryPointsRequirements = requirements;
	}
	
	public Array[] getMilitaryPointsRequirements(){		//SAMUEL A cosa serve?
		return this.militaryPointsRequirements;
	}
	
	public void addBuildingCard(Card card){
	this.buildingCards.add(card);	
	}
	public void addVentureCard(Card card){
		this.ventureCards.add(card);	
	}
	public void addCharacterCard(Card card){
		this.characterCards.add(card);
	}
	public void addTerritoryCard(Card card){
		this.territoryCards.add(card);	
	}
	
	public boolean canIAdd(int actualNumCards){
		if(actualNumCards <= GlobalVariables.maxNumberPlayerCards) return true;
		else return false;
	}
	
	public String toString(){ this.
		return ""; //SAMUEL toString
	}
}