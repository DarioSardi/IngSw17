package it.polimi.ingsw.GC_43.model.initialization;

import it.polimi.ingsw.GC_43.model.effects.*;
import it.polimi.ingsw.GC_43.model.resources.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
 
 
public class CouncilPrivilegeInit {	
	MultipleChoiceEffect multChoices;
	
	public CouncilPrivilegeInit (){
		this.multChoices = new MultipleChoiceEffect(new ArrayList<ChoiceEffect>());
	}
		
	
	public void multipleChoiceInit(JSONObject slide){	
		JSONArray councilPrivilege = (JSONArray) slide.get("CouncilPrivileges");
        Iterator<?> iter = councilPrivilege.iterator();
        while (iter.hasNext()) {
        	JSONObject slides = (JSONObject) iter.next();           
                
            JSONArray bonus = (JSONArray) slides.get("Bonus");
            Iterator<?> bonusIt = bonus.iterator();

            ArrayList<Resource> resources = new ArrayList<>();
                while (bonusIt.hasNext()) {
                	JSONObject slides2 = (JSONObject) bonusIt.next();
                	String type = (String) slides2.get("type");
                	int value = Integer.valueOf((String)slides2.get("value"));
                	
                	addGainAndCostResources(resources, type, value);
                }
                
            ChoiceEffect choice = new ChoiceEffect(null, resources);
            this.multChoices.getChoices().add(choice);
        }	
	}
	
	private void addGainAndCostResources(ArrayList<Resource> resources, String type, int value){
    	if (type.equals("coin")) resources.add(new Coin(value));
     	else if (type.equals("servant")) resources.add(new Servant(value));
     	else if (type.equals("stone")) resources.add(new Stone(value));
     	else if (type.equals("wood")) resources.add(new Wood(value));
     	else if (type.equals("faithPoint")) resources.add(new FaithPoint(value));
     	else if (type.equals("militaryPoint")) resources.add(new MilitaryPoint(value));
     	else if (type.equals("victoryPoint")) resources.add(new VictoryPoint(value));
	}
	
	public MultipleChoiceEffect getMultChoice(){
		return this.multChoices;
	}
}