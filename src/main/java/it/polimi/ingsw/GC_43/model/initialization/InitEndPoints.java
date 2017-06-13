package it.polimi.ingsw.GC_43.model.initialization;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.actionSpace.*;
import it.polimi.ingsw.GC_43.model.cards.BuildingCard;
import it.polimi.ingsw.GC_43.model.cards.Card;
import it.polimi.ingsw.GC_43.model.cards.CharacterCard;
import it.polimi.ingsw.GC_43.model.cards.TerritoryCard;
import it.polimi.ingsw.GC_43.model.cards.VentureCard;
import it.polimi.ingsw.GC_43.model.effects.AdditionalDiceValueToTower;
import it.polimi.ingsw.GC_43.model.effects.CostEffect;
import it.polimi.ingsw.GC_43.model.effects.Effect;
import it.polimi.ingsw.GC_43.model.effects.MultipleResourceEffect;
import it.polimi.ingsw.GC_43.model.effects.ResourceEffect;
import it.polimi.ingsw.GC_43.model.resources.*;

public class InitEndPoints {
	Integer[] endCharacterPoints;
	Integer[] endTerritoryPoints;
	
	InitEndPoints(){
		this.endCharacterPoints = new Integer[GlobalVariables.maxFaithPoints+1];
        this.endTerritoryPoints = new Integer[GlobalVariables.maxNumberPlayerCards+1];
	}
	
	public void readJson(Board board) {
        JSONParser parser = new JSONParser();
 
        try {
       	 Object obj;
      
	   	 obj = parser.parse(new FileReader("src/main/java/it/polimi/ingsw/GC_43/model/initialization/EndPoints.jar"));
 	
         JSONObject jsonObject = (JSONObject) obj;
         
         JSONArray endPointsArray = (JSONArray) jsonObject.get("EndPoints");
         Iterator<?> endPointsIterator = endPointsArray.iterator();

         while (endPointsIterator.hasNext()) {
        	 JSONObject slides = (JSONObject) endPointsIterator.next();
        	 
             JSONArray endPointsCharacterArray = (JSONArray) slides.get("EndPointsCharacterCards");
             Iterator endPointsCharacterIterator = endPointsCharacterArray.iterator();
 
             while (endPointsCharacterIterator.hasNext()) {
     			JSONObject slide = (JSONObject) endPointsCharacterIterator.next();
     			int numCharacaterCards = Integer.parseInt((String)slide.get("numCharacaterCards"));
     			int victoryPoints = Integer.parseInt((String)slide.get("victoryPoint"));
     			
     			if (numCharacaterCards >= 0 && numCharacaterCards <= GlobalVariables.maxNumberPlayerCards){
     				this.endCharacterPoints[numCharacaterCards] = victoryPoints;
     			}	     			
     		 }
             
             //SAMUEL inizializzare endPointsCharacter
             
             
             JSONArray endPointsTerritoryArray = (JSONArray) slides.get("EndPointsTerritoryCards");
             Iterator endPointsTerritoryIterator = endPointsTerritoryArray.iterator();
   
             while (endPointsTerritoryIterator.hasNext()) {
     			JSONObject slide = (JSONObject) endPointsTerritoryIterator.next();
     			int numTerritoryCards = Integer.parseInt((String)slide.get("numTerritoryCards"));
     			int victoryPoints = Integer.parseInt((String)slide.get("victoryPoint"));
     			
     			if (numTerritoryCards >= 0 && numTerritoryCards <= GlobalVariables.maxNumberPlayerCards){
     				this.endTerritoryPoints[numTerritoryCards] = victoryPoints;
     			}	     			
     		 }
             
             //SAMUEL inizializzare endPointsCharacter
             
    	 System.out.println("inizializzati tutti gli spazi azione");
         }
           	 	
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
}