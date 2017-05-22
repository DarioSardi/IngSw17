package it.polimi.ingsw.GC_43.model.effects;
import java.util.HashMap;

import it.polimi.ingsw.GC_43.model.Resource;

public class CostEffect extends Effect {
	private HashMap<String, Resource> Costs;
	
	public CostEffect(){
		this.Costs= new HashMap<String, Resource>();
	}
	
	public void executeEffect(){
		//test
	}
	
}
