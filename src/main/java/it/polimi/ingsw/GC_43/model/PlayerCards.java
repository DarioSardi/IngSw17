package it.polimi.ingsw.GC_43.model;


import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.cards.BuildingCard;
import it.polimi.ingsw.GC_43.model.cards.CharacterCard;
import it.polimi.ingsw.GC_43.model.cards.TerritoryCard;
import it.polimi.ingsw.GC_43.model.cards.VentureCard;

public class PlayerCards implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4103134888701674175L;
	private ArrayList<BuildingCard> buildingCards;
	private ArrayList<VentureCard> ventureCards;
	private ArrayList<CharacterCard> characterCards;
	private ArrayList<TerritoryCard> territoryCards;
	/*
	private ArrayList<Bonus> defaultBonusHarvest;
	private ArrayList<Bonus> defaultBonusBuildings;
	*/
	public PlayerCards (){
		this.buildingCards = new ArrayList<BuildingCard>();
    	this.ventureCards = new ArrayList<VentureCard>();
    	this.territoryCards= new ArrayList<TerritoryCard>();
    	this.characterCards = new ArrayList<CharacterCard>();
    /*
    	this.defaultBonusHarvest = new ArrayList<Bonus>();
    	this.defaultBonusBuildings = new ArrayList<Bonus>();
    */
	}
	
	//DARIO-SAMUEL implementare minMilitaryPoints per l'acquisto carta verde
	//TODO Aggiungere hashmap militaryPointRequirements per carte verdi
	public BuildingCard getBuildingCard(int numCarta){
		return buildingCards.get(numCarta);	
	}
	public VentureCard getVentureCard(int numCarta){
		return ventureCards.get(numCarta);	
	}
	public CharacterCard getCharacterCard(int numCarta){
		return characterCards.get(numCarta);
	}
	public TerritoryCard getTerritoryCard(int numCarta){
		return territoryCards.get(numCarta);	
	}
	
	public ArrayList<BuildingCard> getArrayBuildingCards(){
		return this.buildingCards;	
	}
	
	public ArrayList<VentureCard> getArrayVentureCards(){
		return this.ventureCards;	
	}
	
	public ArrayList<CharacterCard> getArrayCharacterCards(){
		return this.characterCards;	
	}
	
	public ArrayList<TerritoryCard> getArrayTerritoryCards(){
		return this.territoryCards;	
	}
	
	public void addBuildingCard(BuildingCard card){
		this.buildingCards.add(card);	
	}
	public void addVentureCard(VentureCard card){
		this.ventureCards.add(card);	
	}
	public void addCharacterCard(CharacterCard card){
		this.characterCards.add(card);
	}
	public void addTerritoryCard(TerritoryCard card){
		this.territoryCards.add(card);	
	}
	
	public boolean canIAdd(int actualNumCards){
		if(actualNumCards <= GlobalVariables.maxNumberPlayerCards) return true;
		else return false;
	}
	
	public String toString(){
		return "";
	}
}