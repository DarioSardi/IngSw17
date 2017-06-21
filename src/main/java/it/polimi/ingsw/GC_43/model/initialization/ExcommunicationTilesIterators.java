package it.polimi.ingsw.GC_43.model.initialization;

import it.polimi.ingsw.GC_43.model.effects.*;
import it.polimi.ingsw.GC_43.model.resources.Coin;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONObject;
 
 
public class ExcommunicationTilesIterators {	
	private ArrayList<Effect> malus;
	
	ExcommunicationTilesIterators(){
		this.malus = new ArrayList<>();
	}
	
	/**
	 * Add malus to Excommunication Tiles
	 * 
	 * @param iter
	 */
	public void iterator(Iterator<?> iter){
		while (iter.hasNext()) {
			JSONObject slide = (JSONObject) iter.next(); 
			String type = (String)slide.get("type");
			if (type.equals("malusOnFinalVictoryPoints")){
				String malusOn = (String)slide.get("malusOn");
				this.malus.add(new MalusOnFinalVictoryPoints(malusOn));
			}
			else{
				int valueEffect = Integer.parseInt((String)slide.get("value"));
			
				switch (type) {     	
			    case "malusOnGetMilitaryPoints": this.malus.add(new MalusOnGetResources("militaryPoint", valueEffect)); break;
			    case "malusOnGetCoins": this.malus.add(new MalusOnGetResources("coin", valueEffect)); break;
			    case "malusOnGetWood": this.malus.add(new MalusOnGetResources("wood", valueEffect)); break;
			    case "malusOnGetStone": this.malus.add(new MalusOnGetResources("stone", valueEffect)); break;
			    case "malusOnGetServant": this.malus.add(new MalusOnGetResources("servant", valueEffect)); break;
			    case "malusOnFamilyMemberDiceValue": this.malus.add(new MalusOnColouredFamilyMemberDiceValue(valueEffect)); break;
			    case "malusOnBuildingTower": this.malus.add(new AdditionalDieValueAndResourceDiscountToTower("BuildingCard", -valueEffect, 0)); break;
			    case "malusOnCharacterTower": this.malus.add(new AdditionalDieValueAndResourceDiscountToTower("CharacterCard", -valueEffect, 0)); break;
			    case "malusOnTerritoryTower": this.malus.add(new AdditionalDieValueAndResourceDiscountToTower("TerritoryCard", -valueEffect, 0)); break;
			    case "malusOnVentureTower": this.malus.add(new AdditionalDieValueAndResourceDiscountToTower("VentureCard", -valueEffect, 0)); break;
			    case "malusOnProduction": this.malus.add(new AdditionalValueToDiceOnProduction(-valueEffect)); break;
			    case "malusOnHarvest": this.malus.add(new AdditionalValueToDiceOnHarvest(-valueEffect)); break;
			    case "twoServantsCountAsOne": this.malus.add(new TwoServantsCountAsOne()); break;
			    case "malusOnFirstTurn": this.malus.add(new GetBackMoveAtTheEnd()); break;
			    case "malusOnMarket": this.malus.add(new DisableMarketActionSpacesEffect()); break;
			    
				default: System.out.println("Effetto non trovato");
				}		
		    }
		}
	}
	
	public ArrayList<Effect> getMalus(){
		return this.malus;
	}
}