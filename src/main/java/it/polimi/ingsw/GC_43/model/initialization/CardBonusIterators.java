package it.polimi.ingsw.GC_43.model.initialization;

import it.polimi.ingsw.GC_43.model.effects.*;
import it.polimi.ingsw.GC_43.model.resources.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
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
		float valueFloat = Float.parseFloat((String)slide.get("valueEffect"));
		int valueEffect = (int) valueFloat;
		if ("addDiceValueBuildingTower".equalsIgnoreCase(effect)) bonus.add(new AdditionalDiceValueToTower("buildingTower", valueEffect));
		else if ("addDiceValueCharacterTower".equalsIgnoreCase(effect)) bonus.add(new AdditionalDiceValueToTower("characterTower", valueEffect));
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
		else if (effect.equals("malusServant")) bonus.add(new TwoServantsCountAsOne());

		else if (effect.equals("multCouncilPrivileges")) bonus.add(new MultipleCouncilPrivileges(valueEffect));
		else if (effect.equals("multVictoryPointsBuilding")) bonus.add(new VictoryPointsMultiplierEffect(valueFloat, "buildingCards"));
		else if (effect.equals("multVictoryPointsCharacter")) bonus.add(new VictoryPointsMultiplierEffect(valueFloat, "characterCards"));
		else if (effect.equals("multVictoryPointsTerritory")) bonus.add(new VictoryPointsMultiplierEffect(valueFloat, "territoryCards"));
		else if (effect.equals("multVictoryPointsVenture")) bonus.add(new VictoryPointsMultiplierEffect(valueFloat, "ventureCards"));
		else if (effect.equals("multVictoryPointsMilitaryPoint")) bonus.add(new VictoryPointsMultiplierEffect(valueFloat, "militaryPoint"));
	
		else if (effect.equals("multCoinsBuilding")) bonus.add(new CoinsMultiplierEffect(valueEffect, "buildingCards"));
		else if (effect.equals("multCoinsTerritory")) bonus.add(new CoinsMultiplierEffect(valueEffect, "territoryCards"));
		else if (effect.equals("multExchange")) {			
			ChoiceEffectInit cei = new ChoiceEffectInit();
			cei.multipleChoiceInit(slide);
			bonus.add(cei.getMultChoice()); 
		}
		else if (effect.equals("extraCard")){
			PickExtraCardFromTower newExtraCard = new PickExtraCardFromTower(valueEffect);
			pickExtraCardDiscount(slide, newExtraCard);
			bonus.add(newExtraCard);
		}
		else if (effect.equals("extraBuildingCard")){
			PickExtraCardFromTower newExtraCard = new PickExtraCardFromTower(valueEffect, "buildingCards");
			pickExtraCardDiscount(slide, newExtraCard);
			bonus.add(newExtraCard);
		}
		else if (effect.equals("extraCharacterCard")){
			PickExtraCardFromTower newExtraCard = new PickExtraCardFromTower(valueEffect, "characterCards");
			pickExtraCardDiscount(slide, newExtraCard);
			bonus.add(newExtraCard);
		}
		else if (effect.equals("extraTerritoryCard")){
			PickExtraCardFromTower newExtraCard = new PickExtraCardFromTower(valueEffect, "territoryCards");
			pickExtraCardDiscount(slide, newExtraCard);
			bonus.add(newExtraCard);
		}
		else if (effect.equals("extraVentureCard")){
			PickExtraCardFromTower newExtraCard = new PickExtraCardFromTower(valueEffect, "ventureCards");
			pickExtraCardDiscount(slide, newExtraCard);
			bonus.add(newExtraCard);
		}
 
		else if (effect.equals("coin")) bonus.add(new ResourceEffect(new Coin(valueEffect)));
     	else if (effect.equals("servant")) bonus.add(new ResourceEffect(new Servant(valueEffect)));
     	else if (effect.equals("stone")) bonus.add(new ResourceEffect(new Stone(valueEffect)));
     	else if (effect.equals("wood")) bonus.add(new ResourceEffect(new Wood(valueEffect)));
     	else if (effect.equals("faithPoint")) bonus.add(new ResourceEffect(new FaithPoint(valueEffect)));
     	else if (effect.equals("militaryPoint")) bonus.add(new ResourceEffect(new MilitaryPoint(valueEffect)));
     	else if (effect.equals("victoryPoint")) bonus.add(new ResourceEffect(new VictoryPoint(valueEffect)));
     	else if (effect.equals("councilPrivilege")) bonus.add(new ResourceEffect(new CouncilPrivilege(valueEffect)));
	}
	
	private void pickExtraCardDiscount(JSONObject slide, PickExtraCardFromTower newExtraCard){
		JSONArray discountArray = (JSONArray) slide.get("Discount");
        Iterator<?> discountIterator = discountArray.iterator();
            	                   
        while (discountIterator.hasNext()) {
			JSONObject slide2 = (JSONObject) discountIterator.next();
			String discountType = (String)slide2.get("discountType");
			int discountValue = Integer.parseInt((String)slide2.get("discountValue"));
			newExtraCard.setDiscount(discountType, discountValue);
		 }
	}
}