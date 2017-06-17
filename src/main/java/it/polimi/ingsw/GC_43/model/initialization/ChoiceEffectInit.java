package it.polimi.ingsw.GC_43.model.initialization;

import it.polimi.ingsw.GC_43.model.effects.*;
import it.polimi.ingsw.GC_43.model.resources.*;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
 
 
public class ChoiceEffectInit {	
	MultipleChoiceEffect multChoices;
	
	public ChoiceEffectInit (){
		this.multChoices = new MultipleChoiceEffect(new ArrayList<ChoiceEffect>());
	}
		
	
	public void multipleChoiceInit(JSONObject slide){	
		JSONArray choices = (JSONArray) slide.get("Choices");
        Iterator<?> iter = choices.iterator();
        while (iter.hasNext()) {
        	JSONObject slides = (JSONObject) iter.next();
        	JSONArray costChoice = (JSONArray) slides.get("CostChoice");
            Iterator<?> costChoiceIt = costChoice.iterator();
            
            ArrayList<Resource> costResourcesChoices = new ArrayList<>();
            
                while (costChoiceIt.hasNext()) {
                	JSONObject slides2 = (JSONObject) costChoiceIt.next();
                	String costCh = (String) slides2.get("costCh");
                	int valueCh = Integer.parseInt((String) slides2.get("valueCh"));
                	
                	new AddGainAndCostResources().addGainAndCostResources(costResourcesChoices, costCh, valueCh);
                }
                
            JSONArray gainChoice = (JSONArray) slides.get("Gain");
            Iterator<?> gainChoiceIt = gainChoice.iterator();

            ArrayList<Resource> gainResourcesChoices = new ArrayList<>();
                while (gainChoiceIt.hasNext()) {
                	JSONObject slides2 = (JSONObject) gainChoiceIt.next();
                	String gainType = (String) slides2.get("gainType");
                	int valueGain = Integer.parseInt((String)slides2.get("valueGain"));
                	
                	new AddGainAndCostResources().addGainAndCostResources(gainResourcesChoices, gainType, valueGain);
                }
                
            ChoiceEffect choice = new ChoiceEffect(costResourcesChoices, gainResourcesChoices);
            this.multChoices.getChoices().add(choice);
        }	
	}
		
	public MultipleChoiceEffect getMultChoice(){
		return this.multChoices;
	}
}