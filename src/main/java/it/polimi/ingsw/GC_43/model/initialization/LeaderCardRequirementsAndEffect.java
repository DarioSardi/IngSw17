package it.polimi.ingsw.GC_43.model.initialization;

import it.polimi.ingsw.GC_43.model.effects.*;
import it.polimi.ingsw.GC_43.model.leaderCards.*;
import it.polimi.ingsw.GC_43.model.resources.*;

import java.util.ArrayList; 
 
public class LeaderCardRequirementsAndEffect {	
	
	/**
	 * @param bonus
	 * @param iter
	 */
	public void iterator(ArrayList<Effect> bonus, String typeEffect, float valueFloat){

		int valueEffect = (int) valueFloat;
		
		if(valueEffect>=0)		
		switch (typeEffect) {		 
			case "extraProduction":  bonus.add(new ExtraProductionAction(valueEffect)); break;
		    case "extraHarvest":  bonus.add(new ExtraHarvestAction(valueEffect)); break;
		    case "placeInOccupiedSpace":  bonus.add(new LeaderOkPlaceOccupied()); break;
		    case "noTowerTax":  bonus.add(new LeaderNoTowerTax()); break;
		    case "bonusOnNeutralMember":  bonus.add(new  LeaderBonusDieFamiliars(false, valueEffect)); break;
		    case "newFamilyMembersValue":  bonus.add(new  PermanentDieToCoulouredFamiliars(valueEffect)); break;
		    case "bonusColouredFamilyMemberValue":  bonus.add(new  LeaderBonusDieFamiliars(true, valueEffect)); break;
		    case "newSingleColouredMemberValue":  bonus.add(new  AssignDieValueToSingleFamiliar(valueEffect)); break;
		    case "copyTheAbility":  bonus.add(new  LorenzoEffect()); break;
		    case "bonusVictoryPointsOnChurch":  bonus.add(new  VictoryPointsSatisfyingTheChurch(valueEffect)); break;
		    case "noMilitaryPointsRequirement":  bonus.add(new  NoMilitaryPointsRequirement()); break;
		    case "doubleGainFromCards":  bonus.add(new  DoubleCardInstantGain()); break;
		    case "coinsDiscountOnTakeCard":  bonus.add(new  CoinDiscountOnBuyingCards(valueEffect)); break;
		    			    
		    case "coin": bonus.add(new ResourceEffect(new Coin(valueEffect))); break;	 
		    case "servant": bonus.add(new ResourceEffect(new Servant(valueEffect))); break;	 
		    case "stone": bonus.add(new ResourceEffect(new Stone(valueEffect))); break;	 
		    case "wood": bonus.add(new ResourceEffect(new Wood(valueEffect))); break;	 
		    case "faithPoint": bonus.add(new ResourceEffect(new FaithPoint(valueEffect))); break;	 
		    case "militaryPoint": bonus.add(new ResourceEffect(new MilitaryPoint(valueEffect))); break;	 
		    case "victoryPoint": bonus.add(new ResourceEffect(new VictoryPoint(valueEffect))); break;	 
		    case "councilPrivilege": bonus.add(new MultipleCouncilPrivileges(valueEffect)); break;	
		    
		    case "coinReq": bonus.add(new ResourceRequirements("coin", valueEffect)); break;	 
		    case "servantReq": bonus.add(new ResourceRequirements("servant", valueEffect)); break;	 
		    case "stoneReq": bonus.add(new ResourceRequirements("stone", valueEffect)); break;	 
		    case "woodReq": bonus.add(new ResourceRequirements("wood", valueEffect)); break;	 
		    case "faithPointReq": bonus.add(new ResourceRequirements("faithPoint", valueEffect)); break;	 
		    case "militaryPointReq": bonus.add(new ResourceRequirements("militaryPoint", valueEffect)); break;	 
		    case "victoryPointReq": bonus.add(new ResourceRequirements("victoryPoint", valueEffect)); break;
		    
		    case "numVentureCards": bonus.add(new CardsRequirement("ventureCard", valueEffect)); break;	
		    case "numCharacterCards": bonus.add(new CardsRequirement("characterCard", valueEffect)); break;	
		    case "numBuildingCards": bonus.add(new CardsRequirement("buildingCard", valueEffect)); break;	
		    case "numTerritoryCards": bonus.add(new CardsRequirement("territoryCard", valueEffect)); break;	
		    case "numCardsSameType": bonus.add(new CardsRequirement("any", valueEffect)); break;	
		    
		    default: System.out.println("Effect: " + typeEffect + " not found");
		}
		else System.out.println("Negative value to effect " + typeEffect + " not accepted");
	}
}