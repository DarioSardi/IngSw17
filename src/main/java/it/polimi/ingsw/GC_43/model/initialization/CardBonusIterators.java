package it.polimi.ingsw.GC_43.model.initialization;

import it.polimi.ingsw.GC_43.model.*;
import it.polimi.ingsw.GC_43.model.actionSpace.TowerColors;
import it.polimi.ingsw.GC_43.model.effects.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONObject;
 
 
public class CardBonusIterators {	
	
	public void iterator(ArrayList<Effect> bonus, Iterator<?> iter){
		while (iter.hasNext()) {
			JSONObject slide = (JSONObject) iter.next(); 
			effectIterator(bonus, slide);
		}
	}
		
	private void effectIterator(ArrayList<Effect> bonus, JSONObject slide){
		String effect = (String)slide.get("effect");
		int valueEffect = Integer.valueOf((String)slide.get("valueEffect"));
		if (effect.equals("addDiceValueBuildingTower")) bonus.add(new AdditionalDiceValueToTower("buildingTower", valueEffect));
		else if (effect.equals("addDiceValueCharacterTower")) bonus.add(new AdditionalDiceValueToTower("characterTower", valueEffect));
		else if (effect.equals("addDiceValueTerritoryTower")) bonus.add(new AdditionalDiceValueToTower("territoryTower", valueEffect));
		else if (effect.equals("addDiceValueVentureTower")) bonus.add(new AdditionalDiceValueToTower("ventureTower", valueEffect));	
		else if (effect.equals("addProductionDiceValue")) bonus.add(new AdditionalValueToDiceOnProduction(valueEffect));
		else if (effect.equals("addHarvestDiceValue")) bonus.add(new AdditionalValueToDiceOnHarvest(valueEffect));
		
		else if (effect.equals("coinsMultiplierBuilding")) bonus.add(new CoinsMultiplierEffect(valueEffect, "buildingCards"));
     	else if (effect.equals("coinsMultiplierTerritory")) bonus.add(new CoinsMultiplierEffect(valueEffect, "territoryCards"));
     
     	else if (effect.equals("disableFloorBonusEffect")) bonus.add(new DisableFloorBonusEffect());
     	else if (effect.equals("disableMarketEffect")) bonus.add(new DisableMarketActionSpacesEffect());		
		
		else if (effect.equals("extraProduction")) bonus.add(new ExtraProductionAction(valueEffect));
		else if (effect.equals("extraHarvest")) bonus.add(new ExtraHarvestAction(valueEffect));

		else if (effect.equals("malusColouredFamilyMemberValue")) bonus.add(new MalusOnColouredFamilyMemberDiceValue(valueEffect));
	//	else if (effect.equals("malusFinalVictoryPoints")) bonus.add(new MalusOnFinalVictoryPoints(""));
		else if (effect.equals("malusServant")) bonus.add(new TwoServantsCountAsOne());

		else if (effect.equals("multCouncilPrivileges")) bonus.add(new MultipleCouncilPrivileges(valueEffect));
		else if (effect.equals("multVictoryPointsBuilding")) bonus.add(new VictoryPointsMultiplierEffect(valueEffect, "buildingCards"));
		else if (effect.equals("multVictoryPointsCharacter")) bonus.add(new VictoryPointsMultiplierEffect(valueEffect, "characterCards"));
		else if (effect.equals("multVictoryPointsTerritory")) bonus.add(new VictoryPointsMultiplierEffect(valueEffect, "territoryCards"));
		else if (effect.equals("multVictoryPointsVenture")) bonus.add(new VictoryPointsMultiplierEffect(valueEffect, "ventureCards"));
		else if (effect.equals("multVictoryPointsMilitaryPoint")) bonus.add(new VictoryPointsMultiplierEffect(valueEffect, "militaryPoint"));
		else if (effect.equals("multCoinsBuilding")) bonus.add(new CoinsMultiplierEffect(valueEffect, "buildingCards"));
		else if (effect.equals("multCoinsTerritory")) bonus.add(new CoinsMultiplierEffect(valueEffect, "territoryCards"));
		else if (effect.equals("multExchange")) {			
			ChoiceEffectInit cEI = new ChoiceEffectInit();
			cEI.multipleChoiceInit(bonus, slide);		
		}
		else if (effect.equals("extraCard")) bonus.add(new PickExtraCardFromTowers(valueEffect));
		else if (effect.equals("extraBuildingCard")) bonus.add(new PickExtraCardFromTower(valueEffect, "buildingCards"));
		else if (effect.equals("extraCharacterCard")) bonus.add(new PickExtraCardFromTower(valueEffect, "characterCards"));
		else if (effect.equals("extraTerritoryCard")) bonus.add(new PickExtraCardFromTower(valueEffect, "territoryCards"));
		else if (effect.equals("extraVentureCard")) bonus.add(new PickExtraCardFromTower(valueEffect, "ventureCards"));
	 
		else if (effect.equals("coin")) bonus.add(new ResourceEffect(new Coin(valueEffect)));
     	else if (effect.equals("servant")) bonus.add(new ResourceEffect(new Servant(valueEffect)));
     	else if (effect.equals("stone")) bonus.add(new ResourceEffect(new Stone(valueEffect)));
     	else if (effect.equals("wood")) bonus.add(new ResourceEffect(new Wood(valueEffect)));
     	else if (effect.equals("faithPoint")) bonus.add(new ResourceEffect(new FaithPoint(valueEffect)));
     	else if (effect.equals("militaryPoint")) bonus.add(new ResourceEffect(new MilitaryPoint(valueEffect)));
     	else if (effect.equals("victoryPoint")) bonus.add(new ResourceEffect(new VictoryPoint(valueEffect)));
     	else if (effect.equals("councilPrivilege")) bonus.add(new ResourceEffect(new CouncilPrivilege(valueEffect)));
	}
}