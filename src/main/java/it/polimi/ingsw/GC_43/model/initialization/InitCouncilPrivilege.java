package it.polimi.ingsw.GC_43.model.initialization;

import it.polimi.ingsw.GC_43.model.effects.ChoiceEffect;
import it.polimi.ingsw.GC_43.model.effects.MultipleChoiceEffect;
import it.polimi.ingsw.GC_43.model.resources.Resource;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
 
 
public class InitCouncilPrivilege {	
	MultipleChoiceEffect multChoices;
	
	InitCouncilPrivilege(){
		this.multChoices = new MultipleChoiceEffect(new ArrayList<ChoiceEffect>());
	}

	/**
	 * Add effects to Council Privilege
	 */
	public void readCouncilPrivilege() {
        JSONParser parser = new JSONParser();
 
        try {
       	 Object obj = parser.parse(new FileReader("src/main/java/it/polimi/ingsw/GC_43/model/initialization/CouncilPrivilege.jar"));
       	JSONObject jsonObject = (JSONObject) obj;
        
		JSONArray councilPrivilege = (JSONArray) jsonObject.get("CouncilPrivileges");
        Iterator<?> iter = councilPrivilege.iterator();
        while (iter.hasNext()) {
        	JSONObject slides = (JSONObject) iter.next();           
                
            JSONArray bonus = (JSONArray) slides.get("Bonus");
            Iterator<?> bonusIt = bonus.iterator();

            ArrayList<Resource> resources = new ArrayList<>();
                while (bonusIt.hasNext()) {
                	JSONObject slides2 = (JSONObject) bonusIt.next();
                	String type = (String) slides2.get("type");
                	int value = Integer.parseInt((String)slides2.get("value"));
                	
                	new AddGainAndCostResources().addGainAndCostResources(resources, type, value);
                }
                
            ChoiceEffect choice = new ChoiceEffect(null, resources);
            this.multChoices.getChoices().add(choice);
        }			
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
		
	public MultipleChoiceEffect getMultChoice(){
		return this.multChoices;
	}
}