package it.polimi.ingsw.GC_43.model.initialization;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import it.polimi.ingsw.GC_43.model.ExcommunicationTile;
import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.cards.BuildingCard;
import it.polimi.ingsw.GC_43.model.cards.Card;
import it.polimi.ingsw.GC_43.model.cards.CharacterCard;
import it.polimi.ingsw.GC_43.model.cards.TerritoryCard;
import it.polimi.ingsw.GC_43.model.cards.VentureCard;
import it.polimi.ingsw.GC_43.model.effects.AdditionalDiceValueToTower;
import it.polimi.ingsw.GC_43.model.effects.CostEffect;
import it.polimi.ingsw.GC_43.model.effects.Effect;
 
public class InitExcommunicationTiles {
    
	public static void readExcommunicationTiles() {
		int period;
		String type;
		int value;
		String malusOn;
		
		ArrayList<Effect> malusExcommunicationSelected = new ArrayList<>();
		ArrayList<Effect> malusExcommunicationTiles = new ArrayList<>();
		
        JSONParser parser = new JSONParser();
 
        try {
       	 Object obj;
  
   		obj = parser.parse(new FileReader("src/main/java/it/polimi/ingsw/GC_43/model/initialization/ExcommunicationTiles.jar"));

         JSONObject jsonObject = (JSONObject) obj;
         
         JSONArray excommunicationTiles = (JSONArray) jsonObject.get("ExcommunicationTiles");
         Iterator<?> excommunicationTilesIterator = excommunicationTiles.iterator();

         while (excommunicationTilesIterator.hasNext()) {
        	 JSONObject slides = (JSONObject) excommunicationTilesIterator.next();
        	 
  			period = Integer.valueOf((String)slides.get("period"));

             JSONArray malus = (JSONArray) slides.get("Malus");
             new ExcommunicationTilesIterators().iterator(malusExcommunicationTiles, malus.iterator());             
         }
         
    	 System.out.println("inizializzate tutte le carte scomunica");

           	 	
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
}