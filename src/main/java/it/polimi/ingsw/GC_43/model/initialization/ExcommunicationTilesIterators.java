package it.polimi.ingsw.GC_43.model.initialization;

import it.polimi.ingsw.GC_43.model.effects.*;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONObject;
 
 
public class ExcommunicationTilesIterators {	
	private ArrayList<Effect> malus;
	
	ExcommunicationTilesIterators(){
		this.malus = new ArrayList<>();
	}
	
	public void iterator(Iterator<?> iter){
		while (iter.hasNext()) {
			JSONObject slide = (JSONObject) iter.next(); 
			String type = (String)slide.get("type");
			if (type.equals("malusOnFinalVictoryPoints")){
				String malusOn = (String)slide.get("malusOn");
				this.malus.add(new MalusOnFinalVictoryPoints(malusOn));
			}
			else{
				int valueEffect = Integer.valueOf((String)slide.get("value"));
				if (type.equals("malusOnGetMilitaryPoints")) this.malus.add(new MalusOnGetResources("militaryPoint", valueEffect));
				else if (type.equals("malusOnGetCoins")) this.malus.add(new MalusOnGetResources("coin", valueEffect));
				else if (type.equals("malusOnGetWood")) this.malus.add(new MalusOnGetResources("wood", valueEffect));
				else if (type.equals("malusOnGetStone")) this.malus.add(new MalusOnGetResources("stone", valueEffect));
				else if (type.equals("malusOnGetServant")) this.malus.add(new MalusOnGetResources("servant", valueEffect));
				else if (type.equals("malusOnFamilyMemberDiceValue")) this.malus.add(new MalusOnColouredFamilyMemberDiceValue(valueEffect));
				else if (type.equals("malusOnBuildingTower")) this.malus.add(new AdditionalDiceValueToTower("buildingTower", -valueEffect));
				else if (type.equals("malusOnCharacterTower")) this.malus.add(new AdditionalDiceValueToTower("characterTower", -valueEffect));
				else if (type.equals("malusOnTerritoryTower")) this.malus.add(new AdditionalDiceValueToTower("territoryTower", -valueEffect));
				else if (type.equals("malusOnVentureTower")) this.malus.add(new AdditionalDiceValueToTower("ventureTower", -valueEffect));
				else if (type.equals("malusOnProduction")) this.malus.add(new AdditionalValueToDiceOnProduction(-valueEffect));
				else if (type.equals("malusOnHarvest")) this.malus.add(new AdditionalValueToDiceOnHarvest(-valueEffect));
				else if (type.equals("twoServantsCountAsOne")) this.malus.add(new TwoServantsCountAsOne());
				else if (type.equals("malusOnFirstTurn")) this.malus.add(new GetBackMoveAtTheEnd());
				else if (type.equals("malusOnMarket")) this.malus.add(new DisableMarketActionSpacesEffect());	
				else System.out.println("Effetto non trovato");
			}		
	    }
	}
	
	public ArrayList<Effect> getMalus(){
		return this.malus;
	}
}