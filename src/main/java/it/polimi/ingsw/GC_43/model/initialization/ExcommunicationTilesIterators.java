package it.polimi.ingsw.GC_43.model.initialization;

import it.polimi.ingsw.GC_43.model.*;
import it.polimi.ingsw.GC_43.model.actionSpace.TowerColors;
import it.polimi.ingsw.GC_43.model.cards.Card;
import it.polimi.ingsw.GC_43.model.effects.*;
import it.polimi.ingsw.GC_43.model.resources.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.json.simple.JSONObject;
 
 
public class ExcommunicationTilesIterators {	
	
	public void iterator(ArrayList<Effect> malus, Iterator<?> iter){
		while (iter.hasNext()) {
			JSONObject slide = (JSONObject) iter.next(); 
			String type = (String)slide.get("type");
			if (type.equals("malusOnFinalVictoryPoints")){
				String malusOn = (String)slide.get("malusOn");
				malus.add(new MalusOnFinalVictoryPoints(malusOn));
			}
			else{
				int valueEffect = Integer.valueOf((String)slide.get("value"));
				if (type.equals("malusOnGetMilitaryPoints")) malus.add(new MalusOnGetResources("militaryPoint", valueEffect));
				else if (type.equals("malusOnGetCoins")) malus.add(new MalusOnGetResources("coin", valueEffect));
				else if (type.equals("malusOnGetWood")) malus.add(new MalusOnGetResources("wood", valueEffect));
				else if (type.equals("malusOnGetStone")) malus.add(new MalusOnGetResources("stone", valueEffect));
				else if (type.equals("malusOnGetServant")) malus.add(new MalusOnGetResources("servant", valueEffect));
				else if (type.equals("malusOnFamilyMemberDiceValue")) malus.add(new MalusOnColouredFamilyMemberDiceValue(valueEffect));
				else if (type.equals("malusOnBuildingTower")) malus.add(new AdditionalDiceValueToTower("buildingTower", -valueEffect));
				else if (type.equals("malusOnCharacterTower")) malus.add(new AdditionalDiceValueToTower("characterTower", -valueEffect));
				else if (type.equals("malusOnTerritoryTower")) malus.add(new AdditionalDiceValueToTower("territoryTower", -valueEffect));
				else if (type.equals("malusOnVentureTower")) malus.add(new AdditionalDiceValueToTower("ventureTower", -valueEffect));
				else if (type.equals("malusOnProduction")) malus.add(new AdditionalValueToDiceOnProduction(-valueEffect));
				else if (type.equals("malusOnHarvest")) malus.add(new AdditionalValueToDiceOnHarvest(-valueEffect));
				else if (type.equals("twoServantsCountAsOne")) malus.add(new TwoServantsCountAsOne());
				else if (type.equals("malusOnFirstTurn")) malus.add(new GetBackMoveAtTheEnd());
				else if (type.equals("malusOnMarket")) malus.add(new DisableMarketActionSpacesEffect());	
				else System.out.println("Effetto non trovato");
			}		
	    }
		
	}
}