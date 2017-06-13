package it.polimi.ingsw.GC_43.model.initialization;

import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.parser.JSONParser;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.cards.Card;
 
public class InitCards {
	private ArrayList<Card> buildingCards;
	private ArrayList<Card> characterCards;
	private ArrayList<Card> territoryCards;
	private ArrayList<Card> ventureCards;
	
	public InitCards(){
		this.buildingCards = new ArrayList<>();
		this.characterCards = new ArrayList<>();
		this.territoryCards = new ArrayList<>();
		this.ventureCards = new ArrayList<>();
	}
    
	public void readJson(Board board) {
        JSONParser parser = new JSONParser();
 
        try {
       	 Object obj;
      
	   		obj = parser.parse(new FileReader("src/main/java/it/polimi/ingsw/GC_43/model/initialization/BuildingCards.jar"));
       	 	this.buildingCards = new CreateCards().readCards(obj);
       	 	board.setBuildingCardPool(this.buildingCards);
       	 	
       		obj = parser.parse(new FileReader("src/main/java/it/polimi/ingsw/GC_43/model/initialization/CharacterCards.jar"));
	   	 	this.characterCards = new CreateCards().readCards(obj);
	   	 	board.setCharacterCardPool(this.characterCards);
	   	 	
       	 	obj = parser.parse(new FileReader("src/main/java/it/polimi/ingsw/GC_43/model/initialization/TerritoryCards.jar"));
       	 	this.territoryCards = new CreateCards().readCards(obj);
       	 	board.setTerritoryCardPool(this.territoryCards);
       	 	
       	 	obj = parser.parse(new FileReader("src/main/java/it/polimi/ingsw/GC_43/model/initialization/VentureCards.jar"));
       	 	this.ventureCards = new CreateCards().readCards(obj);
    	 	board.setVentureCardPool(this.ventureCards);
    	 	
    	 	System.out.println("inizializzate tutte le carte");
           	 	
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