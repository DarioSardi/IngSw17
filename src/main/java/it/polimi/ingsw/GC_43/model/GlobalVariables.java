package it.polimi.ingsw.GC_43.model;

import it.polimi.ingsw.GC_43.model.effects.MultipleChoiceEffect;
import it.polimi.ingsw.GC_43.model.effects.ResourceEffect;

public class GlobalVariables {
	   public static int maxNumberOfPlayers;
	   public static int numberOfFamilyMembers;
	   public static int numberOfTowers;
	   public static int numberOfDice;
	   public static int excommunicationRound;
	   public static int totalNumberOfCardsPerSet;
	   public static int towerCardsPerRound;
	   public static int towerCardsPerPeriod;
	   public static int floorsPerTower;
	   public static int totalNumberOfPeriods;		//TODO Per chi la usa cambi il nome, si chiamano periodi, non ere
	   public static int maxNumberPlayerCards;
	   public static int initialWoods;
	   public static int initialStones;
	   public static int initialServants;
	   public static int initialFirstPlayerCoins;	//FRANCESCO-SAMUEL dobbiamo pensare a come gestire le monete
	   public static int initialSecondPlayerCoins;	
	   public static int initialThirdPlayerCoins;	
	   public static int initialFourthPlayerCoins;	
	   public static int initialVictoryPoints;
	   public static int initialMilitaryPoints;
	   public static int initialFaithPoints;
	   public static int minDiceValueHarvestArea = 1; //TODO cancellare le dipendenze. Dipendenze in ProductionArea?
	   public static int minDiceFirstHarvestArea;
	   public static int minDiceSecondHarvestArea;
	   public static int minDiceValueProductionArea = 1; //TODO cancellare le dipendenze
	   public static int minDiceFirstProductionArea;
	   public static int minDiceSecondProductionArea;
	   public static int minDiceValueCouncilPalace;
	   public static int towerTax;
	   public static int numberOfPlayers;
	   public static int victoryPointsFirstMilitaryPower;
	   public static int victoryPointsSecondMilitaryPower;
	   public static int maxVictoryPoints;
	   public static int maxMilitaryPoints;
	   public static int maxFaithPoints;
	   public static int endResourcesToVictoryPoint;
	   
	   public static MultipleChoiceEffect councilPrivilegeEffect;
	   public static int malusUnlimitedCells;
	   public static Integer[] militaryPointsRequired;
}
