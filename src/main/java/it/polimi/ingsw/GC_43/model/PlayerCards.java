package it.polimi.ingsw.GC_43.model;


import java.util.ArrayList;
import java.util.List;

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