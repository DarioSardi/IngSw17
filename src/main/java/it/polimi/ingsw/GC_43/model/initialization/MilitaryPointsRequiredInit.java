package it.polimi.ingsw.GC_43.model.initialization;

import it.polimi.ingsw.GC_43.model.GlobalVariables;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
 
 
public class MilitaryPointsRequiredInit {	
	Integer[] militaryPointsRequired;
	
	public MilitaryPointsRequiredInit (){
		this.militaryPointsRequired = new Integer[GlobalVariables.maxNumberPlayerCards+1];
	}
		
	
	public void militaryPointsRequiredInit(JSONObject slide){	
		JSONArray pointsRequiredArray = (JSONArray) slide.get("MilitaryPointsRequired");
        Iterator<?> pointsRequiredIterator = pointsRequiredArray.iterator();
        while (pointsRequiredIterator.hasNext()) {
        	JSONObject slides = (JSONObject) pointsRequiredIterator.next();       
        	
        	int numTerritoryCards = Integer.valueOf((String)slides.get("numTerritoryCards"));
        	int requires = Integer.valueOf((String)slides.get("militaryPointsRequired"));
        	
        	if (numTerritoryCards >= 0 && numTerritoryCards <= GlobalVariables.maxNumberPlayerCards)
        		this.militaryPointsRequired[numTerritoryCards] = requires;
        }
	}
	
	
	public Integer[] getMilitaryPointsRequired(){
		return this.militaryPointsRequired;
	}
}