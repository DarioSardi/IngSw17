package it.polimi.ingsw.GC_43.model.initialization;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.cards.BuildingCard;
import it.polimi.ingsw.GC_43.model.cards.Card;
import it.polimi.ingsw.GC_43.model.cards.CharacterCard;
import it.polimi.ingsw.GC_43.model.cards.TerritoryCard;
import it.polimi.ingsw.GC_43.model.cards.VentureCard;
import it.polimi.ingsw.GC_43.model.effects.AdditionalDiceValueToTower;
import it.polimi.ingsw.GC_43.model.effects.CostEffect;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class InitActionSpaces {
	
	public void readJson() {
        JSONParser parser = new JSONParser();
 
        try {
       	 Object obj;
      
	   	 obj = parser.parse(new FileReader("src/main/java/it/polimi/ingsw/GC_43/model/initialization/ActionSpaces.jar"));
 	
         JSONObject jsonObject = (JSONObject) obj;
         
         JSONArray actionSpaces = (JSONArray) jsonObject.get("ActionSpaces");
         Iterator<?> actionSpaceIterator = actionSpaces.iterator();

         while (actionSpaceIterator.hasNext()) {
        	 JSONObject slides = (JSONObject) actionSpaceIterator.next();
        	 

             // Now we try to take the data from "presentationSlides" array
             JSONArray faithArea = (JSONArray) slides.get("FaithPoints");
             Iterator faithAreaIterator = faithArea.iterator();
                 	             
             Integer[] faithPoints = new Integer[GlobalVariables.maxFaithPoints+1];
             int position;
             int victoryPoints;
             
             while (faithAreaIterator.hasNext()) {
     			JSONObject slide = (JSONObject) faithAreaIterator.next();
     			position = Integer.valueOf((String)slide.get("position"));
     			victoryPoints = Integer.valueOf((String)slide.get("victoryPoint"));
     			faithPoints[position] = victoryPoints;
     		 }
         }
         
    	 System.out.println("inizializzati tutti gli spazi azione");

           	 	
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }	
}