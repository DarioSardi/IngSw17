package it.polimi.ingsw.GC_43.model.initialization;

import it.polimi.ingsw.GC_43.model.GlobalVariables;

import java.io.FileReader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
 
 
public class InitFaithPointExcomRequired {	
	private Integer[] faithPointExcom;
	
	InitFaithPointExcomRequired(){
		this.faithPointExcom = new Integer[GlobalVariables.totalNumberOfPeriods];
	}

	/**
	 * Initializes an array that tell how many military points are required to buy a territory card
	 */
	public void readFaithPointExcomRequired() {
        JSONParser parser = new JSONParser();
 
        try {
       	 Object obj = parser.parse(new FileReader("src/main/java/it/polimi/ingsw/GC_43/model/initialization/FaithPointExcomRequired.jar"));
       	JSONObject jsonObject = (JSONObject) obj;
        
		JSONArray pointsRequiredArray = (JSONArray) jsonObject.get("FaithPointExcomRequired");
        Iterator<?> pointsRequiredIterator = pointsRequiredArray.iterator();
        while (pointsRequiredIterator.hasNext()) {
        	JSONObject slides = (JSONObject) pointsRequiredIterator.next();       
        	
        	int period = Integer.parseInt((String)slides.get("period"));
        	int requires = Integer.parseInt((String)slides.get("numFaithPoints"));
        	
        	if (period >= 0 && period < GlobalVariables.totalNumberOfPeriods)
        		this.faithPointExcom[period] = requires;
        }
		    			
        } catch (Exception e) {
    		System.out.println("Exception on read faith point excommunication required");
            e.printStackTrace();
        }
    }
		
	public Integer[] getFaithPointExcom(){
		return this.faithPointExcom;
	}
}