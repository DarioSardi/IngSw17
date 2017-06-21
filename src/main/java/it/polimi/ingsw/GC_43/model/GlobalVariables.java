package it.polimi.ingsw.GC_43.model;

import java.io.Serializable;

import it.polimi.ingsw.GC_43.model.effects.MultipleChoiceEffect;

public class GlobalVariables implements Serializable{
	   /**
	 * 
	 */
   private static final long serialVersionUID = 128365450850396921L;
   public static int maxNumberOfPlayers;
   public static int numberOfFamilyMembers;
   public static int numberOfTowers;
   public static int numberOfDice;
   public static int excommunicationRound;
   public static int totalNumberOfCardsPerSet;
   public static int towerCardsPerRound;
   public static int towerCardsPerPeriod;
   public static int floorsPerTower;
   public static int totalNumberOfPeriods;
   public static int maxNumberPlayerCards;
   public static int initialWoods;
   public static int initialStones;
   public static int initialServants;
   public static int initialFirstPlayerCoins;
   public static int initialSecondPlayerCoins;	
   public static int initialThirdPlayerCoins;	
   public static int initialFourthPlayerCoins;	
   public static int initialVictoryPoints;
   public static int initialMilitaryPoints;
   public static int initialFaithPoints;
   public static int minDiceFirstHarvestArea;
   public static int minDiceSecondHarvestArea;
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
   
   public static MultipleChoiceEffect councilPrivilegeEffect;
   public static int malusUnlimitedCells;
   public static Integer[] militaryPointsRequired;
   public static Integer[] faithPointExcomRequired;
   

   public static int endResourcesToVictoryPoint;
   public static Integer[] endCharacterVictoryPoints;
   public static Integer[] endTerritoryVictoryPoints;
   
   
   public void createCopyGlobalVariables(CopyOfGlobalVariables copyVariables){
	   
	   copyVariables.maxNumberOfPlayers = GlobalVariables.maxNumberPlayerCards;
	   copyVariables.numberOfFamilyMembers = GlobalVariables.numberOfFamilyMembers;
	   copyVariables.numberOfTowers = GlobalVariables.numberOfTowers;
	   copyVariables.numberOfDice = GlobalVariables.numberOfDice;
	   copyVariables.excommunicationRound = GlobalVariables.excommunicationRound;
	   copyVariables.totalNumberOfCardsPerSet = GlobalVariables.totalNumberOfCardsPerSet;
	   copyVariables.towerCardsPerRound = GlobalVariables.towerCardsPerRound;
	   copyVariables.towerCardsPerPeriod = GlobalVariables.towerCardsPerPeriod;
	   copyVariables.floorsPerTower = GlobalVariables.floorsPerTower;
	   copyVariables.totalNumberOfPeriods = GlobalVariables.totalNumberOfPeriods;
	   copyVariables.maxNumberPlayerCards = GlobalVariables.maxNumberPlayerCards;
	   copyVariables.initialWoods = GlobalVariables.initialWoods;
	   copyVariables.initialStones = GlobalVariables.initialStones;
	   copyVariables.initialServants = GlobalVariables.initialServants;
	   copyVariables.initialFirstPlayerCoins = GlobalVariables.initialFirstPlayerCoins;
	   copyVariables.initialSecondPlayerCoins = GlobalVariables.initialSecondPlayerCoins;	
	   copyVariables.initialThirdPlayerCoins = GlobalVariables.initialThirdPlayerCoins;	
	   copyVariables.initialFourthPlayerCoins = GlobalVariables.initialFourthPlayerCoins;	
	   copyVariables.initialVictoryPoints = GlobalVariables.initialVictoryPoints;
	   copyVariables.initialMilitaryPoints = GlobalVariables.initialMilitaryPoints;
	   copyVariables.initialFaithPoints = GlobalVariables.initialFaithPoints;
	   copyVariables.minDiceFirstHarvestArea = GlobalVariables.minDiceFirstHarvestArea;
	   copyVariables.minDiceSecondHarvestArea = GlobalVariables.minDiceSecondHarvestArea;
	   copyVariables.minDiceFirstProductionArea = GlobalVariables.minDiceFirstProductionArea;
	   copyVariables.minDiceSecondProductionArea = GlobalVariables.minDiceSecondProductionArea;
	   copyVariables.minDiceValueCouncilPalace = GlobalVariables.minDiceValueCouncilPalace;
	   copyVariables.towerTax = GlobalVariables.towerTax;
	   copyVariables.numberOfPlayers = GlobalVariables.numberOfPlayers;
	   copyVariables.victoryPointsFirstMilitaryPower = GlobalVariables.victoryPointsFirstMilitaryPower;
	   copyVariables.victoryPointsSecondMilitaryPower = GlobalVariables.victoryPointsSecondMilitaryPower;
	   copyVariables.maxVictoryPoints = GlobalVariables.maxVictoryPoints;
	   copyVariables.maxMilitaryPoints = GlobalVariables.maxMilitaryPoints;
	   copyVariables.maxFaithPoints = GlobalVariables.maxFaithPoints;
   }
}
