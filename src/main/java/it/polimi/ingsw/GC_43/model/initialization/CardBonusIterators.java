package it.polimi.ingsw.GC_43.model.initialization;

import it.polimi.ingsw.GC_43.model.effects.*;
import it.polimi.ingsw.GC_43.model.resources.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
 
 
public class CardBonusIterators {	
	
	/**
	 * @param bonus
	 * @param iter
	 */
	public void iterator(ArrayList<Effect> bonus, Iterator<?> iter){
		while (iter.hasNext()) {
			JSONObject slide = (JSONObject) iter.next(); 
			effectIterator(bonus, slide);
		}
	}
	
	/**
	 * Cards effects
	 * @param bonus 
	 * @param slide
	 */
	private void effectIterator(ArrayList<Effect> bonus, JSONObject slide){
		String effect = (String)slide.get("effect");
		float valueFloat = Float.parseFloat((String)slide.get("valueEffect"));
		int valueEffect = (int) valueFloat;
		
		if(valueEffect>=0)
		
		switch (effect) {
		 
		    case "addDiceValueBuildingTower": 
		    	int discountBValue = Integer.parseInt((String)slide.get("discountValue"));
		    	String discountBType = (String)slide.get("discountType");
		    	bonus.add(new AdditionalDieValueAndResourceDiscountToTower("BuildingCard", valueEffect, discountBValue)); 
		    	if ("special".equals(discountBType))
			    	bonus.add(new DoubleChoiceOnDiscountBuildingCard()); 
		    	break;	     
		    case "addDiceValueCharacterTower":
		    	int discountCValue = Integer.parseInt((String)slide.get("discountValue"));
		    	String discountCType = (String)slide.get("discountType");
		    	bonus.add(new AdditionalDieValueAndResourceDiscountToTower("CharacterCard", valueEffect, discountCValue));
		    	if ("special".equals(discountCType))
			    	bonus.add(new DoubleChoiceOnDiscountBuildingCard()); 
		    	break;	 
		    case "addDiceValueTerritoryTower": 
		    	int discountTValue = Integer.parseInt((String)slide.get("discountValue"));
		    	String discountTType = (String)slide.get("discountType");
		    	bonus.add(new AdditionalDieValueAndResourceDiscountToTower("TerritoryCard", valueEffect, discountTValue)); 
		    	if ("special".equals(discountTType))
			    	bonus.add(new DoubleChoiceOnDiscountBuildingCard()); 
		    	break;	 
		    case "addDiceValueVentureTower": 
		    	int discountVValue = Integer.parseInt((String)slide.get("discountValue"));
		    	String discountVType = (String)slide.get("discountType");
		    	bonus.add(new AdditionalDieValueAndResourceDiscountToTower("VentureCard", valueEffect, discountVValue)); 
		    	if ("special".equals(discountVType))
			    	bonus.add(new DoubleChoiceOnDiscountBuildingCard());
		    	break;	 
		    case "addProductionDiceValue":  bonus.add(new AdditionalValueToDiceOnProduction(valueEffect)); break;
		    case "addHarvestDiceValue":  bonus.add(new AdditionalValueToDiceOnHarvest(valueEffect)); break;
		    
		    case "disableFloorBonusEffect":  bonus.add(new DisableFloorBonusEffect()); break;
		    case "disableMarketEffect":  bonus.add(new DisableMarketActionSpacesEffect()); break;
		    
		    case "extraProduction":  bonus.add(new ExtraProductionAction(valueEffect)); break;
		    case "extraHarvest":  bonus.add(new ExtraHarvestAction(valueEffect)); break;
		    
		    case "malusColouredFamilyMemberValue": bonus.add(new MalusOnColouredFamilyMemberDiceValue(valueEffect)); break;
		    case "malusServant": bonus.add(new TwoServantsCountAsOne()); break;
		    
		    case "multCouncilPrivileges": bonus.add(new MultipleCouncilPrivileges(valueEffect)); break;
		    case "multVictoryPointsBuilding": bonus.add(new VictoryPointsMultiplierEffect(valueFloat, "buildingCards")); break;
		    case "multVictoryPointsCharacter": bonus.add(new VictoryPointsMultiplierEffect(valueFloat, "characterCards")); break;
		    case "multVictoryPointsTerritory": bonus.add(new VictoryPointsMultiplierEffect(valueFloat, "territoryCards")); break;
		    case "multVictoryPointsVenture": bonus.add(new VictoryPointsMultiplierEffect(valueFloat, "ventureCards")); break;
		    case "multVictoryPointsMilitaryPoint": bonus.add(new VictoryPointsMultiplierEffect(valueFloat, "militaryPoint")); break;
		    case "multCoinsBuilding": bonus.add(new CoinsMultiplierEffect(valueEffect, "buildingCards")); break;
		    case "multCoinsTerritory": bonus.add(new CoinsMultiplierEffect(valueEffect, "territoryCards")); break;
		    case "multExchange": 
		    	ChoiceEffectInit cei = new ChoiceEffectInit();
		    	cei.multipleChoiceInit(slide);
		    	bonus.add(cei.getMultChoice());  
		    	break;
		    case "extraCard": 
				PickExtraCardFromTower newExtraCard = new PickExtraCardFromTower(valueEffect);
				pickExtraCardDiscount(slide, newExtraCard);
				bonus.add(newExtraCard);
		    	break;	     
		    case "extraBuildingCard": 
				PickExtraCardFromTower newExtraBuildingCard = new PickExtraCardFromTower(valueEffect, "buildingCards");
				pickExtraCardDiscount(slide, newExtraBuildingCard);
				bonus.add(newExtraBuildingCard);
		    	break;	 
		    case "extraCharacterCard": 
		    	PickExtraCardFromTower newExtraCharacterCard = new PickExtraCardFromTower(valueEffect, "characterCards");
		    	pickExtraCardDiscount(slide, newExtraCharacterCard);
		    	bonus.add(newExtraCharacterCard);
		    	break;	 
		    case "extraTerritoryCard": 
				PickExtraCardFromTower newExtraTerritoryCard = new PickExtraCardFromTower(valueEffect, "territoryCards");
				pickExtraCardDiscount(slide, newExtraTerritoryCard);
				bonus.add(newExtraTerritoryCard);
		    	break;	 
		    case "extraVentureCard": 
		    	PickExtraCardFromTower newExtraVentureCard = new PickExtraCardFromTower(valueEffect, "ventureCards");
		    	pickExtraCardDiscount(slide, newExtraVentureCard);
		    	bonus.add(newExtraVentureCard);
		    	break;	 
		    	
		    case "coin": bonus.add(new ResourceEffect(new Coin(valueEffect))); break;	 
		    case "servant": bonus.add(new ResourceEffect(new Servant(valueEffect))); break;	 
		    case "stone": bonus.add(new ResourceEffect(new Stone(valueEffect))); break;	 
		    case "wood": bonus.add(new ResourceEffect(new Wood(valueEffect))); break;	 
		    case "faithPoint": bonus.add(new ResourceEffect(new FaithPoint(valueEffect))); break;	 
		    case "militaryPoint": bonus.add(new ResourceEffect(new MilitaryPoint(valueEffect))); break;	 
		    case "victoryPoint": bonus.add(new ResourceEffect(new VictoryPoint(valueEffect))); break;	 
		    case "councilPrivilege": {
		    	bonus.add(new ResourceEffect(new CouncilPrivilege(valueEffect))); break;	 
		    }
		    default: System.out.println("Effect: " + effect + " not found");
		}
		else System.out.println("Negative value to effect " + effect + " not accepted");
	}
	
	/**
	 * Import discount to PickExtraCardFromTower effect
	 * @param slide Discount's JSONOBject
	 * @param newExtraCard Effect
	 */
	private void pickExtraCardDiscount(JSONObject slide, PickExtraCardFromTower newExtraCard){
		JSONArray discountArray = (JSONArray) slide.get("Discount");
        Iterator<?> discountIterator = discountArray.iterator();
            	                   
        while (discountIterator.hasNext()) {
			JSONObject slide2 = (JSONObject) discountIterator.next();
			String discountType = (String)slide2.get("discountType");
			int discountValue = Integer.parseInt((String)slide2.get("discountValue"));
			if(discountValue > 0)
			newExtraCard.setDiscount(discountType, discountValue);
			else System.out.println("Negative discount value not possible");
		 }
	}
}