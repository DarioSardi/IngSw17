package model;

public class PlayerBonusMalus {
	
	//BONUS ADDED EACH TIME NEEDED, THEY COULD BE NEGATIVE TOO
	//COMING FROM BOTH CHARACTER CARDS AND FROM EXCOMMUNICATION TILES
	private int bonusDiceCharachterTower;
	private int bonusDiceTerritoryTower;
	private int bonusDiceBuildingTower;
	private int bonusDiceVentureTower;
	private int bonusProductionArea;
	private int bonusHarvestArea;
	private int bonusCoinsOnBuyCharacterTower;
	private int bonusCoinsOnBuyVentureTower;
	private int bonusCoinsOnBuyBuildingTower;
	private int bonusCoinsOnBuyTerritoryTower;
	private int malusOnAcquiringMilitaryPoints;
	private int malusOnAcquiringMoney;
	private int malusOnAcquiringWoodOrStone;
	private int malusOnAcquiringServants;
	private int malusCoulouredFamiliarsDiceValue;
	
	//BOOLEAN, LOGIC OF THESE ARE INCORPORATED IN METHODS USING THOSE
	
	private boolean noFloorBonus;
	private boolean noMarketActionSpaceBonus;
	private boolean twoServantsCountsAsOne;
	
	//MALUS PO PIU SPECIALOTTI, DA TRATTARE CON UNA BUONA LOGICA
	private boolean skipFirstFamiliarMoveAndGetItBackAtTheEnd;
	//double choice on stone or wood, see the yellow card Costruttore
	private boolean doubleChoiceDiscountOnBuildingCard;
	
	
	//TO BE CHECKED IN THE END OF THE GAME
	//BOOLEAN, LOGIC OF THESE ARE INCORPORATED IN METHODS USING THOSE
	
	private boolean noVictoryPointsForVentureCards;
	private boolean noVictoryPointsForCharacterCards;
	private boolean noVictoryPointsForTerritoryCards;
	private boolean malusOnVictoryPoints;
	private boolean malusOnVictoryPointsPerMilitaryPoint;
	private boolean malusOnVictoryPointsForBuildingCardCosts;
	private boolean malusOnVictoryPointsPerResource;
	
	
	

	
	//GETTERS AND SETTERS

	
	
	public int getBonusDiceCharachterTower() {
		return bonusDiceCharachterTower;
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
	public int getBonusCoinsOnBuyCharacterTower() {
		return bonusCoinsOnBuyCharacterTower;
	}
	public void setBonusCoinsOnBuyCharacterTower(int bonusCoinsOnBuyCharacterTower) {
		this.bonusCoinsOnBuyCharacterTower = bonusCoinsOnBuyCharacterTower;
	}
	public int getBonusCoinsOnBuyVentureTower() {
		return bonusCoinsOnBuyVentureTower;
	}
	public void setBonusCoinsOnBuyVentureTower(int bonusCoinsOnBuyVentureTower) {
		this.bonusCoinsOnBuyVentureTower = bonusCoinsOnBuyVentureTower;
	}
	public int getBonusCoinsOnBuyBuildingTower() {
		return bonusCoinsOnBuyBuildingTower;
	}
	public void setBonusCoinsOnBuyBuildingTower(int bonusCoinsOnBuyBuildingTower) {
		this.bonusCoinsOnBuyBuildingTower = bonusCoinsOnBuyBuildingTower;
	}
	public int getBonusCoinsOnBuyTerritoryTower() {
		return bonusCoinsOnBuyTerritoryTower;
	}
	public void setBonusCoinsOnBuyTerritoryTower(int bonusCoinsOnBuyTerritoryTower) {
		this.bonusCoinsOnBuyTerritoryTower = bonusCoinsOnBuyTerritoryTower;
	}
	public int getMalusOnAcquiringMilitaryPoints() {
		return malusOnAcquiringMilitaryPoints;
	}
	public void setMalusOnAcquiringMilitaryPoints(int malusOnAcquiringMilitaryPoints) {
		this.malusOnAcquiringMilitaryPoints = malusOnAcquiringMilitaryPoints;
	}
	public int getMalusOnAcquiringMoney() {
		return malusOnAcquiringMoney;
	}
	public void setMalusOnAcquiringMoney(int malusOnAcquiringMoney) {
		this.malusOnAcquiringMoney = malusOnAcquiringMoney;
	}
	public int getMalusOnAcquiringWoodOrStone() {
		return malusOnAcquiringWoodOrStone;
	}
	public void setMalusOnAcquiringWoodOrStone(int malusOnAcquiringWoodOrStone) {
		this.malusOnAcquiringWoodOrStone = malusOnAcquiringWoodOrStone;
	}
	public int getMalusOnAcquiringServants() {
		return malusOnAcquiringServants;
	}
	public void setMalusOnAcquiringServants(int malusOnAcquiringServants) {
		this.malusOnAcquiringServants = malusOnAcquiringServants;
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
	public boolean isNoVictoryPointsForVentureCards() {
		return noVictoryPointsForVentureCards;
	}
	public void setNoVictoryPointsForVentureCards(boolean noVictoryPointsForVentureCards) {
		this.noVictoryPointsForVentureCards = noVictoryPointsForVentureCards;
	}
	public boolean isNoVictoryPointsForCharacterCards() {
		return noVictoryPointsForCharacterCards;
	}
	public void setNoVictoryPointsForCharacterCards(boolean noVictoryPointsForCharacterCards) {
		this.noVictoryPointsForCharacterCards = noVictoryPointsForCharacterCards;
	}
	public boolean isNoVictoryPointsForTerritoryCards() {
		return noVictoryPointsForTerritoryCards;
	}
	public void setNoVictoryPointsForTerritoryCards(boolean noVictoryPointsForTerritoryCards) {
		this.noVictoryPointsForTerritoryCards = noVictoryPointsForTerritoryCards;
	}
	public boolean isMalusOnVictoryPoints() {
		return malusOnVictoryPoints;
	}
	public void setMalusOnVictoryPoints(boolean malusOnVictoryPoints) {
		this.malusOnVictoryPoints = malusOnVictoryPoints;
	}
	public boolean isMalusOnVictoryPointsPerMilitaryPoint() {
		return malusOnVictoryPointsPerMilitaryPoint;
	}
	public void setMalusOnVictoryPointsPerMilitaryPoint(boolean malusOnVictoryPointsPerMilitaryPoint) {
		this.malusOnVictoryPointsPerMilitaryPoint = malusOnVictoryPointsPerMilitaryPoint;
	}
	public boolean isMalusOnVictoryPointsForBuildingCardCosts() {
		return malusOnVictoryPointsForBuildingCardCosts;
	}
	public void setMalusOnVictoryPointsForBuildingCardCosts(boolean malusOnVictoryPointsForBuildingCardCosts) {
		this.malusOnVictoryPointsForBuildingCardCosts = malusOnVictoryPointsForBuildingCardCosts;
	}
	public boolean isMalusOnVictoryPointsPerResource() {
		return malusOnVictoryPointsPerResource;
	}
	public void setMalusOnVictoryPointsPerResource(boolean malusOnVictoryPointsPerResource) {
		this.malusOnVictoryPointsPerResource = malusOnVictoryPointsPerResource;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
