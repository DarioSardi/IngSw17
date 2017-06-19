package it.polimi.ingsw.GC_43.model.initialization;

import it.polimi.ingsw.GC_43.model.resources.*;
import it.polimi.ingsw.GC_43.model.effects.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONObject;
 
 
public class CardCostIterators {	
	private ArrayList<Resource> costs;
	public CardCostIterators(CostEffect costEffect, Iterator<?> iter){
		costs = new ArrayList<>();
		while (iter.hasNext()) {
			JSONObject slide = (JSONObject) iter.next(); 
			String typeCost = (String)slide.get("typeCost");
	       	 int valueCost = Integer.valueOf((String)slide.get("valueCost")); 
	       	 
	       	new AddGainAndCostResources().addGainAndCostResources(this.costs, typeCost, valueCost);
	    }
	}

	public ArrayList<Resource> getCosts() {
		return costs;
	}	

		
}