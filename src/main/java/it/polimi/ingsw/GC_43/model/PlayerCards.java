package it.polimi.ingsw.GC_43.model;


import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.cards.BuildingCard;
import it.polimi.ingsw.GC_43.model.cards.Card;
import it.polimi.ingsw.GC_43.model.cards.CharacterCard;
import it.polimi.ingsw.GC_43.model.cards.LeaderCard;
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
	private ArrayList<LeaderCard> leaderCards;
	public PlayerCards (){
		this.buildingCards = new ArrayList<>();
    	this.ventureCards = new ArrayList<>();
    	this.territoryCards= new ArrayList<>();
    	this.characterCards = new ArrayList<>();
    	this.leaderCards = new ArrayList<>();
	}
	
	public BuildingCard getBuildingCard(int numCarta){
		return this.buildingCards.get(numCarta);	
	}
	public VentureCard getVentureCard(int numCarta){
		return this.ventureCards.get(numCarta);	
	}
	public CharacterCard getCharacterCard(int numCarta){
		return this.characterCards.get(numCarta);
	}
	public TerritoryCard getTerritoryCard(int numCarta){
		return this.territoryCards.get(numCarta);	
	}	
	public LeaderCard getLeaderCard(int numCarta){
		return this.leaderCards.get(numCarta);	
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
	
	public ArrayList<LeaderCard> getArrayLeaderCards(){
		return this.leaderCards;	
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
	public void addLeaderCard(LeaderCard card){
		this.leaderCards.add(card);	
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
	
	public String toStringLeaderCards(ArrayList<LeaderCard> cardsList){
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
		s = s + "LeaderCard" +'\n';
		s = s + toStringLeaderCards(this.leaderCards);		
		return s;
	}
}