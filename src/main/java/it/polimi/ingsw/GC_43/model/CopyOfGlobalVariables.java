package it.polimi.ingsw.GC_43.model;

import java.io.Serializable;

import it.polimi.ingsw.GC_43.model.effects.MultipleChoiceEffect;

public class CopyOfGlobalVariables implements Serializable{
	   /**
	 * 
	 */
	private static final long serialVersionUID = 128365450850396921L;
	
	   public int maxNumberOfPlayers;
	   public int numberOfFamilyMembers;
	   public int numberOfTowers;
	   public int numberOfDice;
	   public int excommunicationRound;
	   public int totalNumberOfCardsPerSet;
	   public int towerCardsPerRound;
	   public int towerCardsPerPeriod;
	   public int floorsPerTower;
	   public int totalNumberOfPeriods;
	   public int maxNumberPlayerCards;
	   public int initialWoods;
	   public int initialStones;
	   public int initialServants;
	   public int initialFirstPlayerCoins;
	   public int initialSecondPlayerCoins;	
	   public int initialThirdPlayerCoins;	
	   public int initialFourthPlayerCoins;	
	   public int initialFifthPlayerCoins;	
	   public int initialVictoryPoints;
	   public int initialMilitaryPoints;
	   public int initialFaithPoints;
	   public int minDiceFirstHarvestArea;
	   public int minDiceSecondHarvestArea;
	   public int minDiceFirstProductionArea;
	   public int minDiceSecondProductionArea;
	   public int minDiceValueCouncilPalace;
	   public int malusOnSecondHarvestArea;
	   public int malusOnSecondProductionArea;
	   public int towerTax;
	   public int numberOfPlayers;
	   public int victoryPointsFirstMilitaryPower;
	   public int victoryPointsSecondMilitaryPower;
	   public int maxVictoryPoints;
	   public int maxMilitaryPoints;
	   public int maxFaithPoints;
	   public int numOfLeaderCardsForPlayer;
	   public int totalNumOfLeaderCards;
	   
	   
	   public MultipleChoiceEffect councilPrivilegeEffect;
	   public int malusUnlimitedCells;
	   public Integer[] militaryPointsRequired;
	   public Integer[] faithPointExcomRequired;
  
	   public int endResourcesToVictoryPoint;
	   public Integer[] endCharacterVictoryPoints;
	   public Integer[] endTerritoryVictoryPoints;
	   
	   
}
