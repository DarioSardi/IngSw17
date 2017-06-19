package it.polimi.ingsw.GC_43.model.initialization;

import it.polimi.ingsw.GC_43.model.effects.*;
import it.polimi.ingsw.GC_43.model.resources.*;
import java.util.ArrayList;
  
public class AddGainAndCostResources {	
	
	/**
	 * @param effectType
	 * @param valueEffect
	 * @return
	 */
	public Effect retResourceEffect(String effectType, int valueEffect){
		switch (effectType) {     	
		    case "coin": return (new ResourceEffect(new Coin(valueEffect)));	 
		    case "servant": return (new ResourceEffect(new Servant(valueEffect)));	 
		    case "stone": return (new ResourceEffect(new Stone(valueEffect)));	 
		    case "wood": return (new ResourceEffect(new Wood(valueEffect)));	 
		    case "faithPoint": return (new ResourceEffect(new FaithPoint(valueEffect)));	 
		    case "militaryPoint": return (new ResourceEffect(new MilitaryPoint(valueEffect)));	 
		    case "victoryPoint": return (new ResourceEffect(new VictoryPoint(valueEffect)));	 
		    case "councilPrivilege": return (new MultipleCouncilPrivileges(valueEffect));	 
		    default: System.out.println("Effect: " + effectType + "not found"); return null;
		}
	}
	
	/**
	 * @param resources 
	 * @param type
	 * @param value
	 */
	public void addGainAndCostResources(ArrayList<Resource> resources, String type, int value){
		switch (type) {     	
		    case "coin":  resources.add(new Coin(value)); break;	 
		    case "servant": resources.add(new Servant(value)); break; 
		    case "stone": resources.add(new Stone(value)); break; 
		    case "wood": resources.add(new Wood(value)); break; 
		    case "faithPoint": resources.add(new FaithPoint(value)); break;	 
		    case "militaryPoint": resources.add(new MilitaryPoint(value)); break; 
		    case "victoryPoint": resources.add(new VictoryPoint(value)); break; 
		    default: System.out.println("Resource: " + type + "not found"); break;
		}
	}
}