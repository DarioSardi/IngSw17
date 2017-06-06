package it.polimi.ingsw.GC_43.model.initialization;

import java.io.FileReader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import it.polimi.ingsw.GC_43.model.GlobalVariables;
 
public class GlobalVariablesInit {
	   public static void readGlobalVariables() {
        JSONParser parser = new JSONParser();
 
        try {
       	 Object obj = parser.parse(new FileReader("src/main/java/it/polimi/ingsw/GC_43/model/initialization/GlobalVariables.jar"));
       	JSONObject jsonObject = (JSONObject) obj;
        
        JSONArray cardContent = (JSONArray) jsonObject.get("Global Variables");
        Iterator<?> iterator1 = cardContent.iterator();

	        while (iterator1.hasNext()) {
		       	 JSONObject slides = (JSONObject) iterator1.next();
		        
		       	GlobalVariables.maxNumberOfPlayers = Integer.valueOf((String)slides.get("maxNumberOfPlayers"));
		       	GlobalVariables.numberOfTowers = Integer.valueOf((String)slides.get("numberOfTowers"));
		       	GlobalVariables.numberOfDice = Integer.valueOf((String)slides.get("numberOfDice"));
		       	GlobalVariables.excommunicationRound = Integer.valueOf((String)slides.get("excommunicationRound"));
		       	GlobalVariables.totalNumberOfCardsPerSet = Integer.valueOf((String)slides.get("totalNumberOfCardsPerSet"));
		       	GlobalVariables.towerCardsPerRound = Integer.valueOf((String)slides.get("towerCardsPerRound"));
		       	GlobalVariables.towerCardsPerPeriod = Integer.valueOf((String)slides.get("towerCardsPerPeriod"));
		       	GlobalVariables.floorsPerTower = Integer.valueOf((String)slides.get("floorsPerTower"));
		       	GlobalVariables.totalNumberOfPeriods = Integer.valueOf((String)slides.get("totalNumberOfPeriods"));
		       	GlobalVariables.maxNumberPlayerCards = Integer.valueOf((String)slides.get("maxNumberPlayerCards"));
		       	GlobalVariables.initialWoods = Integer.valueOf((String)slides.get("initialWoods"));
		       	GlobalVariables.initialStones = Integer.valueOf((String)slides.get("initialStones"));
		       	GlobalVariables.initialServants = Integer.valueOf((String)slides.get("initialServants"));
		       	GlobalVariables.initialFirstPlayerCoins = Integer.valueOf((String)slides.get("initialFirstPlayerCoins"));
		       	GlobalVariables.initialSecondPlayerCoins = Integer.valueOf((String)slides.get("initialSecondPlayerCoins"));
		       	GlobalVariables.initialThirdPlayerCoins = Integer.valueOf((String)slides.get("initialThirdPlayerCoins"));
		       	GlobalVariables.initialFourthPlayerCoins = Integer.valueOf((String)slides.get("initialFourthPlayerCoins"));
		       	// Add here for add coins to another player   	
		       	GlobalVariables.initialVictoryPoints = Integer.valueOf((String)slides.get("initialVictoryPoints"));
		       	GlobalVariables.initialMilitaryPoints = Integer.valueOf((String)slides.get("initialMilitaryPoints"));
		       	GlobalVariables.initialFaithPoints = Integer.valueOf((String)slides.get("initialFaithPoints"));
		       	GlobalVariables.minDiceFirstHarvestArea = Integer.valueOf((String)slides.get("minDiceFirstHarvestArea"));
		       	GlobalVariables.minDiceSecondHarvestArea = Integer.valueOf((String)slides.get("minDiceSecondHarvestArea"));
		       	GlobalVariables.mindDiceFirstProductionArea = Integer.valueOf((String)slides.get("mindDiceFirstProductionArea"));
		       	GlobalVariables.mindDiceSecondProductionArea = Integer.valueOf((String)slides.get("mindDiceSecondProductionArea"));
		       	GlobalVariables.mindDiceValueCouncilPalace = Integer.valueOf((String)slides.get("mindDiceValueCouncilPalace"));
		       	GlobalVariables.towerTax = Integer.valueOf((String)slides.get("towerTax"));
		       	GlobalVariables.victoryPointsFirstMilitaryPoint = Integer.valueOf((String)slides.get("victoryPointsFirstMilitaryPoint"));
		       	GlobalVariables.victoryPointsSecondMilitaryPoint = Integer.valueOf((String)slides.get("victoryPointsSecondMilitaryPoint"));
		       	System.out.println("inizializzate tutte le variabili globali");
		    }				
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}