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

	
	

}
