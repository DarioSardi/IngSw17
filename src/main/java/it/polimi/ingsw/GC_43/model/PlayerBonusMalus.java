package it.polimi.ingsw.GC_43.model;

import java.util.HashMap;

import it.polimi.ingsw.GC_43.model.actionSpace.TowerColors;


public class PlayerBonusMalus {
	
	//BONUS ADDED EACH TIME NEEDED, THEY COULD BE NEGATIVE TOO
	//COMING FROM BOTH CHARACTER CARDS AND FROM EXCOMMUNICATION TILES
	
	private int bonusDiceCharachterTower;
	private int bonusDiceTerritoryTower;
	private int bonusDiceBuildingTower;
	private int bonusDiceVentureTower;
	private int bonusProductionArea;
	private int bonusHarvestArea;
	

	private int malusCoulouredFamiliarsDiceValue;
	
	//BOOLEAN, LOGIC OF THESE ARE INCORPORATED IN METHODS USING THOSE
	
	private boolean noFloorBonus;
	private boolean noMarketActionSpaceBonus;
	private boolean twoServantsCountsAsOne;
	
	//MALUS PO PIU SPECIALOTTI, DA TRATTARE CON UNA BUONA LOGICA
	private boolean skipFirstFamiliarMoveAndGetItBackAtTheEnd;
	//double choice on stone or wood, see the yellow card Costruttore
	private boolean doubleChoiceDiscountOnBuildingCard;
	
	
	private HashMap<String,Integer> malusOnAcquiringResources;

	//TO BE CHECKED IN THE END OF THE GAME
	//BOOLEAN, LOGIC OF THESE ARE INCORPORATED IN METHODS USING THOSE
	
//Malus On Final Victory Points
	private HashMap<String,Boolean> malusOnFinalVictoryPoints;
	
//Bonus (coins discount) on acquiring cards in towers
	
	private HashMap<String,Integer> bonusCoinsOnBuyInTowers;

	
	
	public PlayerBonusMalus(){
		this.malusOnAcquiringResources= new HashMap<String,Integer>();
		this.malusOnAcquiringResources.put("wood", 0);
		this.malusOnAcquiringResources.put("coin", 0);
		this.malusOnAcquiringResources.put("stone", 0);
		this.malusOnAcquiringResources.put("militaryPoint", 0);
		this.malusOnAcquiringResources.put("faithPoint", 0);
		this.malusOnAcquiringResources.put("servant", 0);
		this.malusOnAcquiringResources.put("victoryPoint", 0);
		this.malusOnFinalVictoryPoints= new HashMap<String,Boolean>();
		this.malusOnFinalVictoryPoints.put("victoryPoint",false);
		this.malusOnFinalVictoryPoints.put("militaryPoint",false);
		this.malusOnFinalVictoryPoints.put("resource",false);
		this.malusOnFinalVictoryPoints.put("buildingCardCost",false);
		this.malusOnFinalVictoryPoints.put("ventureCard",false);
		this.malusOnFinalVictoryPoints.put("territoryCard",false);
		this.malusOnFinalVictoryPoints.put("characterCard",false);
		this.bonusCoinsOnBuyInTowers= new HashMap<String,Integer>();
		this.bonusCoinsOnBuyInTowers.put("territoryCard", 0);
		this.bonusCoinsOnBuyInTowers.put("characterCard", 0);
		this.bonusCoinsOnBuyInTowers.put("ventureCard", 0);
		this.bonusCoinsOnBuyInTowers.put("buildingCard", 0);

		
	}
	
	//GETTERS AND SETTERS

	
	
	public int getBonusDiceCharachterTower() {
		return bonusDiceCharachterTower;
	}
	public HashMap<String, Boolean> getMalusOnFinalVictoryPoints() {
		return malusOnFinalVictoryPoints;
	}

	public void setMalusOnFinalVictoryPoints(HashMap<String, Boolean> malusOnFinalVictoryPoints) {
		this.malusOnFinalVictoryPoints = malusOnFinalVictoryPoints;
	}

	public HashMap<String, Integer> getMalusOnAcquiringResources() {
		return malusOnAcquiringResources;
	}

	public void setMalusOnAcquiringResources(HashMap<String, Integer> malusOnAcquiringResources) {
		this.malusOnAcquiringResources = malusOnAcquiringResources;
	}

	public void setBonusDiceCharachterTower(int bonusDiceCharachterTower) {
		this.bonusDiceCharachterTower = bonusDiceCharachterTower;
	}
	public int getBonusDiceTerritoryTower() {
		return bonusDiceTerritoryTower;
	}
	public void setBonusDiceTerritoryTower(int bonusDiceTerritoryTower) {
		this.bonusDiceTerritoryTower = bonusDiceTerritoryTower;
	}
	public int getBonusDiceBuildingTower() {
		return bonusDiceBuildingTower;
	}
	public void setBonusDiceBuildingTower(int bonusDiceBuildingTower) {
		this.bonusDiceBuildingTower = bonusDiceBuildingTower;
	}
	public int getBonusDiceVentureTower() {
		return bonusDiceVentureTower;
	}
	public void setBonusDiceVentureTower(int bonusDiceVentureTower) {
		this.bonusDiceVentureTower = bonusDiceVentureTower;
	}
	public int getBonusProductionArea() {
		return bonusProductionArea;
	}
	public void setBonusProductionArea(int bonusProductionArea) {
		this.bonusProductionArea = bonusProductionArea;
	}
	public int getBonusHarvestArea() {
		return bonusHarvestArea;
	}
	public void setBonusHarvestArea(int bonusHarvestArea) {
		this.bonusHarvestArea = bonusHarvestArea;
	}
	public int getMalusCoulouredFamiliarsDiceValue() {
		return malusCoulouredFamiliarsDiceValue;
	}
	public void setMalusCoulouredFamiliarsDiceValue(int malusCoulouredFamiliarsDiceValue) {
		this.malusCoulouredFamiliarsDiceValue = malusCoulouredFamiliarsDiceValue;
	}
	public boolean isNoFloorBonus() {
		return noFloorBonus;
	}
	public void setNoFloorBonus(boolean noFloorBonus) {
		this.noFloorBonus = noFloorBonus;
	}
	public boolean isNoMarketActionSpaceBonus() {
		return noMarketActionSpaceBonus;
	}
	public void setNoMarketActionSpaceBonus(boolean noMarketActionSpaceBonus) {
		this.noMarketActionSpaceBonus = noMarketActionSpaceBonus;
	}
	public boolean isTwoServantsCountsAsOne() {
		return twoServantsCountsAsOne;
	}
	public void setTwoServantsCountsAsOne(boolean twoServantsCountsAsOne) {
		this.twoServantsCountsAsOne = twoServantsCountsAsOne;
	}
	public boolean isSkipFirstFamiliarMoveAndGetItBackAtTheEnd() {
		return skipFirstFamiliarMoveAndGetItBackAtTheEnd;
	}
	public void setSkipFirstFamiliarMoveAndGetItBackAtTheEnd(boolean skipFirstFamiliarMoveAndGetItBackAtTheEnd) {
		this.skipFirstFamiliarMoveAndGetItBackAtTheEnd = skipFirstFamiliarMoveAndGetItBackAtTheEnd;
	}
	public boolean isDoubleChoiceDiscountOnBuildingCard() {
		return doubleChoiceDiscountOnBuildingCard;
	}
	public void setDoubleChoiceDiscountOnBuildingCard(boolean doubleChoiceDiscountOnBuildingCard) {
		this.doubleChoiceDiscountOnBuildingCard = doubleChoiceDiscountOnBuildingCard;
	}
	public HashMap<String, Integer> getBonusCoinsOnBuyInTowers() {
		return bonusCoinsOnBuyInTowers;
	}

	

	

}
