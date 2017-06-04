package it.polimi.ingsw.GC_43.model.initialization;

import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.parser.JSONParser;

import it.polimi.ingsw.GC_43.model.cards.Card;
 
public class InitCards {
	CreateCards createCards;
	private ArrayList<Card> buildingCards;
	private ArrayList<Card> characterCards;
	private ArrayList<Card> territoryCards;
	private ArrayList<Card> ventureCards;
	
	public InitCards(){
		this.createCards = new CreateCards();
		this.buildingCards = new ArrayList<>();
		this.characterCards = new ArrayList<>();
		this.territoryCards = new ArrayList<>();
		this.ventureCards = new ArrayList<>();
	}
    
	public void readJson() {
        JSONParser parser = new JSONParser();
 
        try {
       	 Object obj;
      
	   		obj = parser.parse(new FileReader("src/main/java/it/polimi/ingsw/GC_43/model/initialization/BuildingCards.jar"));
       	 	this.createCards.readCards(obj, this.buildingCards);
     	
       		obj = parser.parse(new FileReader("src/main/java/it/polimi/ingsw/GC_43/model/initialization/CharacterCards.jar"));
	   	 	this.createCards.readCards(obj, this.characterCards);
	  	 
       	 	obj = parser.parse(new FileReader("src/main/java/it/polimi/ingsw/GC_43/model/initialization/TerritoryCards.jar"));
       	 	this.createCards.readCards(obj, this.territoryCards);
       	 	
       	 	obj = parser.parse(new FileReader("src/main/java/it/polimi/ingsw/GC_43/model/initialization/VentureCards.jar"));
    	 	this.createCards.readCards(obj, this.ventureCards);
           	 	
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
	
	public ArrayList<Card> getBuildingCards(){
		return this.buildingCards;
	}

	public ArrayList<Card> getCharacterCards() {
		return characterCards;
	}

	public ArrayList<Card> getTerritoryCards() {
		return territoryCards;
	}

	public ArrayList<Card> getVentureCards() {
		return ventureCards;
	}
	
	
}