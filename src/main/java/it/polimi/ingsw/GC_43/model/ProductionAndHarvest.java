package it.polimi.ingsw.GC_43.model;

import java.util.concurrent.ExecutionException;

import it.polimi.ingsw.GC_43.model.cards.BuildingCard;
import it.polimi.ingsw.GC_43.model.cards.TerritoryCard;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class ProductionAndHarvest {
	
	public static boolean harvestAction(FamilyMember f, int dicevalue) throws ExecutionException{
		for(TerritoryCard c: f.getPlayer().getPlayerCards().getArrayTerritoryCards())
		{
			for(Effect e:c.getPermaBonus()){
				e.executeEffect(f);
			}
		}
		return true;
	}
	
	public static boolean ProductionAction(FamilyMember f, int dicevalue) throws ExecutionException{
		for(BuildingCard c: f.getPlayer().getPlayerCards().getArrayBuildingCards())
		{
			for(Effect e:c.getPermaBonus()){
				e.executeEffect(f);
			}
		}
		return true;
	}
}
