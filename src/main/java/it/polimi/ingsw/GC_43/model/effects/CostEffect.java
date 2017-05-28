package it.polimi.ingsw.GC_43.model.effects;
import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.Resource;

public class CostEffect extends Effect {
	private ArrayList<Resource> Costs;
	
	public CostEffect(ArrayList<Resource> Costs){
		this.Costs= Costs;
	}
	
	public boolean check(Player player, boolean towerTax){
		if(towerTax){
			if
			player.subResource("coin", 3);
		}
		return false;
	}
	public void executeEffect(Player player, boolean towerTax){
		//test
	}
	
}
