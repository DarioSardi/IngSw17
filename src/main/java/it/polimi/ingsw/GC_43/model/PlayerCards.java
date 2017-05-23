package it.polimi.ingsw.GC_43.model;


import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.GC_43.model.cards.BuildingCard;
import it.polimi.ingsw.GC_43.model.cards.CharacterCard;
import it.polimi.ingsw.GC_43.model.cards.TerritoryCard;
import it.polimi.ingsw.GC_43.model.cards.VentureCard;

public class PlayerCards{
	private List<BuildingCard> buildingCards;
	private List<VentureCard> ventureCards;
	private List<CharacterCard> characterCards;
	private List<TerritoryCard> territoryCards;
	private ArrayList<Bonus> defaultBonusHarvest;
	private ArrayList<Bonus> defaultBonusBuildings;
	
	public PlayerCards (){
		this.buildingCards = new ArrayList<BuildingCard>();
    	this.ventureCards = new ArrayList<VentureCard>();
    	this.territoryCards= new ArrayList<TerritoryCard>();
    	this.characterCards = new ArrayList<CharacterCard>();
    	this.defaultBonusHarvest = new ArrayList<Bonus>();
    	this.defaultBonusBuildings = new ArrayList<Bonus>();
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
	
	public void addCard(BuildingCard card){
		this.buildingCards.add(card);	
	}
	public void addCard(VentureCard card){
		this.ventureCards.add(card);	
	}
	public void addCard(CharacterCard card){
		this.characterCards.add(card);
	}
	public void addCard(TerritoryCard card){
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