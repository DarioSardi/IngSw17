package it.polimi.ingsw.GC_43.model;


import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.cards.BuildingCard;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class PlayerPersonalBonus implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2815971959175413082L;
	private ArrayList<Effect> productionBonus;
	private ArrayList<Effect> harvestBonus;
	public PlayerPersonalBonus (ArrayList<Effect> productionBonus, ArrayList<Effect> harvestBonus){
		this.productionBonus = productionBonus;
		this.harvestBonus = harvestBonus;
	}
	
	public ArrayList<Effect> getProductionBonus(){
		return this.productionBonus;	
	}
	
	public ArrayList<Effect> getHarvestBonus(){
		return this.harvestBonus;	
	}

	public String toString(){
		String s="";
		s = s + "ProductionBonus: \n";
		for (int i=0; i < this.productionBonus.size(); i++)
			s = s + this.productionBonus.get(i).toString();
		s = s + "HarvestBonus: \n";
		for (int i=0; i < this.harvestBonus.size(); i++)
			s = s + this.harvestBonus.get(i).toString();
		return s;
	}
}