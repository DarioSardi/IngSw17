package it.polimi.ingsw.GC_43.model.initialization;

import java.io.FileReader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
 
public class GlobalVariablesInit {
	   public static int numberOfPlayers;
	   
	   public static int maxNumberOfPlayers;
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
	   public static int mindDiceFirstProductionArea;
	   public static int mindDiceSecondProductionArea;
	   public static int mindDiceValueCouncilPalace;
	   public static int towerTax;
	   public static int victoryPointsFirstMilitaryPoint;
	   public static int victoryPointsSecondMilitaryPoint;

	   public static void readGlobalVariables() {
        JSONParser parser = new JSONParser();
 
        try {
       	 Object obj = parser.parse(new FileReader("src/main/java/it/polimi/ingsw/GC_43/model/initialization/GlobalVariables.jar"));
       	JSONObject jsonObject = (JSONObject) obj;
        
        JSONArray cardContent = (JSONArray) jsonObject.get("Global Variables");
        Iterator<?> iterator1 = cardContent.iterator();

	        while (iterator1.hasNext()) {
		       	 JSONObject slides = (JSONObject) iterator1.next();
		        
		       	maxNumberOfPlayers = Integer.valueOf((String)slides.get("maxNumberOfPlayers"));
		       	numberOfTowers = Integer.valueOf((String)slides.get("numberOfTowers"));
		       	numberOfDice = Integer.valueOf((String)slides.get("numberOfDice"));
		       	excommunicationRound = Integer.valueOf((String)slides.get("excommunicationRound"));
		       	totalNumberOfCardsPerSet = Integer.valueOf((String)slides.get("totalNumberOfCardsPerSet"));
		       	towerCardsPerRound = Integer.valueOf((String)slides.get("towerCardsPerRound"));
		       	towerCardsPerPeriod = Integer.valueOf((String)slides.get("towerCardsPerPeriod"));
		       	floorsPerTower = Integer.valueOf((String)slides.get("floorsPerTower"));
		       	totalNumberOfPeriods = Integer.valueOf((String)slides.get("totalNumberOfPeriods"));
		       	maxNumberPlayerCards = Integer.valueOf((String)slides.get("maxNumberPlayerCards"));
		       	initialWoods = Integer.valueOf((String)slides.get("initialWoods"));
		       	initialStones = Integer.valueOf((String)slides.get("initialStones"));
		       	initialServants = Integer.valueOf((String)slides.get("initialServants"));
		       	initialFirstPlayerCoins = Integer.valueOf((String)slides.get("initialFirstPlayerCoins"));
		       	initialSecondPlayerCoins = Integer.valueOf((String)slides.get("initialSecondPlayerCoins"));
		       	initialThirdPlayerCoins = Integer.valueOf((String)slides.get("initialThirdPlayerCoins"));
		       	initialFourthPlayerCoins = Integer.valueOf((String)slides.get("initialFourthPlayerCoins"));
		       	// Add here for add coins to another player   	
		       	initialVictoryPoints = Integer.valueOf((String)slides.get("initialVictoryPoints"));
		       	initialMilitaryPoints = Integer.valueOf((String)slides.get("initialMilitaryPoints"));
		       	initialFaithPoints = Integer.valueOf((String)slides.get("initialFaithPoints"));
		       	minDiceFirstHarvestArea = Integer.valueOf((String)slides.get("minDiceFirstHarvestArea"));
		       	minDiceSecondHarvestArea = Integer.valueOf((String)slides.get("minDiceSecondHarvestArea"));
		       	mindDiceFirstProductionArea = Integer.valueOf((String)slides.get("mindDiceFirstProductionArea"));
		       	mindDiceSecondProductionArea = Integer.valueOf((String)slides.get("mindDiceSecondProductionArea"));
		       	mindDiceValueCouncilPalace = Integer.valueOf((String)slides.get("mindDiceValueCouncilPalace"));
		       	towerTax = Integer.valueOf((String)slides.get("towerTax"));
		       	victoryPointsFirstMilitaryPoint = Integer.valueOf((String)slides.get("victoryPointsFirstMilitaryPoint"));
		       	victoryPointsSecondMilitaryPoint = Integer.valueOf((String)slides.get("victoryPointsSecondMilitaryPoint"));
		       	System.out.println("inizializzate tutte le variabili globali");
		    }				
        } catch (Exception e) {
            e.printStackTrace();
        }
    }	
	   
	public void setNumberOfPlayers(int numberOfPlayers){
		GlobalVariablesInit.numberOfPlayers = numberOfPlayers;
	}
}