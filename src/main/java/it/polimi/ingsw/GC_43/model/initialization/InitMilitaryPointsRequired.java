package it.polimi.ingsw.GC_43.model.initialization;

import it.polimi.ingsw.GC_43.model.GlobalVariables;

import java.io.FileReader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
 
 
public class InitMilitaryPointsRequired {	
	private Integer[] militaryPointsRequired;
	
	InitMilitaryPointsRequired(){
		this.militaryPointsRequired = new Integer[GlobalVariables.maxNumberPlayerCards+1];
	}

	public void readMilitaryPointsRequired() {
        JSONParser parser = new JSONParser();
 
        try {
       	 Object obj = parser.parse(new FileReader("src/main/java/it/polimi/ingsw/GC_43/model/initialization/MilitaryPointsRequired.jar"));
       	JSONObject jsonObject = (JSONObject) obj;
        
		JSONArray pointsRequiredArray = (JSONArray) jsonObject.get("MilitaryPointsRequired");
        Iterator<?> pointsRequiredIterator = pointsRequiredArray.iterator();
        while (pointsRequiredIterator.hasNext()) {
        	JSONObject slides = (JSONObject) pointsRequiredIterator.next();       
        	
        	int numTerritoryCards = Integer.valueOf((String)slides.get("numTerritoryCards"));
        	int requires = Integer.valueOf((String)slides.get("militaryPointsRequired"));
        	
        	if (numTerritoryCards >= 0 && numTerritoryCards <= GlobalVariables.maxNumberPlayerCards)
        		this.militaryPointsRequired[numTerritoryCards] = requires;
        }
		    			
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
		
	public Integer[] getMilitaryPointsRequired(){
		return this.militaryPointsRequired;
	}
}