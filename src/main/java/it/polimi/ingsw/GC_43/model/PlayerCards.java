package it.polimi.ingsw.GC_43.model;


import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.cards.BuildingCard;
import it.polimi.ingsw.GC_43.model.cards.Card;
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
	public PlayerCards (){
		this.buildingCards = new ArrayList<>();
    	this.ventureCards = new ArrayList<>();
    	this.territoryCards= new ArrayList<>();
    	this.characterCards = new ArrayList<>();
	}
	
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
		return (actualNumCards <= GlobalVariables.maxNumberPlayerCards);
	}
	
	
	public String toStringForType(ArrayList<? extends Card> cardsList){
		String s ="";	
		for (int i=0; i < cardsList.size(); i++)
			s = s + cardsList.get(i).toString() + '\n';
		return s;
	}
	public String toString(){
		String s="";
		s = s + TerritoryCard.TYPE +'\n';
		s = s + toStringForType(this.territoryCards);
		s = s + CharacterCard.TYPE +'\n';
		s = s + toStringForType(this.characterCards);			
		s = s + BuildingCard.TYPE +'\n';
		s = s + toStringForType(this.buildingCards);			
		s = s + VentureCard.TYPE +'\n';
		s = s + toStringForType(this.ventureCards);			
		return s;
	}
}