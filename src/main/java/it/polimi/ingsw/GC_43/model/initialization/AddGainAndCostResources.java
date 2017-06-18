package it.polimi.ingsw.GC_43.model.initialization;

import it.polimi.ingsw.GC_43.model.effects.*;
import it.polimi.ingsw.GC_43.model.resources.*;
import java.util.ArrayList;
  
public class AddGainAndCostResources {	
	
	public Effect retResourceEffect(String effectType, int valueEffect){
		if ("coin".equals(effectType)) return (new ResourceEffect(new Coin(valueEffect)));
	 	else if ("servant".equals(effectType)) return (new ResourceEffect(new Servant(valueEffect)));
	 	else if ("stone".equals(effectType)) return (new ResourceEffect(new Stone(valueEffect)));
	 	else if ("wood".equals(effectType)) return (new ResourceEffect(new Wood(valueEffect)));
	 	else if ("faithPoint".equals(effectType)) return (new ResourceEffect(new FaithPoint(valueEffect)));
	 	else if ("militaryPoint".equals(effectType)) return (new ResourceEffect(new MilitaryPoint(valueEffect)));
	 	else if ("victoryPoint".equals(effectType)) return (new ResourceEffect(new VictoryPoint(valueEffect)));
	 	else if ("councilPrivilege".equals(effectType)) return (new MultipleCouncilPrivileges(valueEffect));
	 	else return null;
	}
	
	public void addGainAndCostResources(ArrayList<Resource> resources, String type, int value){
    	if ("coin".equals(type)) resources.add(new Coin(value));
     	else if ("servant".equals(type)) resources.add(new Servant(value));
     	else if ("stone".equals(type)) resources.add(new Stone(value));
     	else if ("wood".equals(type)) resources.add(new Wood(value));
     	else if ("faithPoint".equals(type)) resources.add(new FaithPoint(value));
     	else if ("militaryPoint".equals(type)) resources.add(new MilitaryPoint(value));
     	else if ("victoryPoint".equals(type)) resources.add(new VictoryPoint(value));
	}
}