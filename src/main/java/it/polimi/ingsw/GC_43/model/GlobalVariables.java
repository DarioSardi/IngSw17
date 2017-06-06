package it.polimi.ingsw.GC_43.model;

import it.polimi.ingsw.GC_43.model.effects.ResourceEffect;

public class GlobalVariables {
	   public static int maxNumberOfPlayers = 4;
	   public static int numberOfFamilyMembers = 4;
	   public static int numberOfTowers= 4;
	   public static int numberOfDice=3;
	   public static int excommunicationRound=2;
	   public static int totalNumberOfCardsPerSet=24;
	   public static int towerCardsPerRound=4;
	   public static int towerCardsPerPeriod=4;
	   public static int floorsPerTower=4;
	   public static int totalNumberOfPeriods=3;		//TODO Per chi la usa cambi il nome, si chiamano periodi, non ere
	   public static int maxNumberPlayerCards = 6;
	   public static int initialWoods = 2;
	   public static int initialStones = 2;
	   public static int initialServants = 2;
	   public static int initialFirstPlayerCoins = 5;	//FRANCESCO-SAMUEL dobbiamo pensare a come gestire le monete
	   public static int initialSecondPlayerCoins = 6;	
	   public static int initialThirdPlayerCoins = 7;	
	   public static int initialFourthPlayerCoins = 8;	
	   public static int initialVictoryPoints = 0;
	   public static int initialMilitaryPoints = 0;
	   public static int initialFaithPoints = 0;
	   public static int minDiceValueHarvestArea = 1; //TODO cancellare le dipendenze. Dipendenze in ProductionArea?
	   public static int minDiceFirstHarvestArea = 1;
	   public static int minDiceSecondHarvestArea = 1;
	   public static int mindDiceValueProductionArea = 1; //TODO cancellare le dipendenze
	   public static int mindDiceFirstProductionArea = 1;
	   public static int mindDiceSecondProductionArea = 1;
	   public static int mindDiceValueCouncilPalace= 1;
	   public static int towerTax= 3;
	   public static int numberOfPlayers;
	   public static ResourceEffect councilPriviledgeEffect;
	   public static int victoryPointsFirstMilitaryPower= 5;	//TODO Point, non power
	   public static int victoryPointsSecondMilitaryPower= 2;	//TODO Point, non power
	   

	   

}
