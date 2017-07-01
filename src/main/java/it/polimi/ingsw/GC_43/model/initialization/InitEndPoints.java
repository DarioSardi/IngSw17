package it.polimi.ingsw.GC_43.model.initialization;

import java.io.FileReader;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import it.polimi.ingsw.GC_43.model.GlobalVariables;

public class InitEndPoints {
	Integer[] endCharacterPoints;
	Integer[] endTerritoryPoints;
	
	InitEndPoints(){
		this.endCharacterPoints = new Integer[GlobalVariables.maxFaithPoints+1];
        this.endTerritoryPoints = new Integer[GlobalVariables.maxNumberPlayerCards+1];
	}
	
	/**
	 * Initialize victory points at the end of the game 
	 */
	public void readJson() {
        JSONParser parser = new JSONParser();
 
        try {
       	 Object obj = parser.parse(new FileReader("src/main/java/it/polimi/ingsw/GC_43/model/initialization/EndPoints.jar"));
 	
         JSONObject jsonObject = (JSONObject) obj;
         
         JSONArray endPointsArray = (JSONArray) jsonObject.get("EndPoints");
         Iterator<?> endPointsIterator = endPointsArray.iterator();

         while (endPointsIterator.hasNext()) {
        	 JSONObject slide = (JSONObject) endPointsIterator.next();
        	 
             JSONArray endPointsCharacterArray = (JSONArray) slide.get("EndPointsCharacterCards");
             Iterator<?> endPointsCharacterIterator = endPointsCharacterArray.iterator();
 
             while (endPointsCharacterIterator.hasNext()) {
     			JSONObject slide1 = (JSONObject) endPointsCharacterIterator.next();
     			int numCharacaterCards = Integer.parseInt((String)slide1.get("numCharacterCards"));
     			int victoryPoints = Integer.parseInt((String)slide1.get("victoryPoints"));
     			
     			if (numCharacaterCards >= 0 && numCharacaterCards <= GlobalVariables.maxNumberPlayerCards){
     				this.endCharacterPoints[numCharacaterCards] = victoryPoints;
     			}	     			
     		 }             
             
             JSONArray endPointsTerritoryArray = (JSONArray) slide.get("EndPointsTerritoryCards");
             Iterator<?> endPointsTerritoryIterator = endPointsTerritoryArray.iterator();
   
             while (endPointsTerritoryIterator.hasNext()) {
     			JSONObject slide1 = (JSONObject) endPointsTerritoryIterator.next();
     			int numTerritoryCards = Integer.parseInt((String)slide1.get("numTerritoryCards"));
     			int victoryPoints = Integer.parseInt((String)slide1.get("victoryPoints"));
     			
     			if (numTerritoryCards >= 0 && numTerritoryCards <= GlobalVariables.maxNumberPlayerCards){
     				this.endTerritoryPoints[numTerritoryCards] = victoryPoints;
     			}	     			
     		 }
          }
           	 	
        } catch (Exception e) {
    		System.out.println("Exception on read end points");
            e.printStackTrace();
        }   
    }

	public Integer[] getEndCharacterPoints() {
		return this.endCharacterPoints;
	}

	public Integer[] getEndTerritoryPoints() {
		return this.endTerritoryPoints;
	}
	
	
}