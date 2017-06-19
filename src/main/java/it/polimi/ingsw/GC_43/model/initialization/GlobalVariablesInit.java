package it.polimi.ingsw.GC_43.model.initialization;

import java.io.FileReader;
import java.io.Serializable;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import it.polimi.ingsw.GC_43.model.GlobalVariables;
 
public class GlobalVariablesInit implements Serializable{
	   /**
	 * 
	 */
	private static final long serialVersionUID = -2631448818732722455L;

	public static void readGlobalVariables(GlobalVariables globalVar) {
        JSONParser parser = new JSONParser();
 
        try {
       	 Object obj = parser.parse(new FileReader("src/main/java/it/polimi/ingsw/GC_43/model/initialization/GlobalVariables.jar"));
       	JSONObject jsonObject = (JSONObject) obj;
        
        JSONArray cardContent = (JSONArray) jsonObject.get("Global Variables");
        Iterator<?> iterator1 = cardContent.iterator();

	        while (iterator1.hasNext()) {
		       	JSONObject slides = (JSONObject) iterator1.next();
		        
		       	globalVar.maxNumberOfPlayers = Integer.valueOf((String)slides.get("maxNumberOfPlayers"));
		       	globalVar.numberOfFamilyMembers = Integer.valueOf((String)slides.get("numberOfFamilyMembers"));
		       	globalVar.numberOfTowers = Integer.valueOf((String)slides.get("numberOfTowers"));
		       	globalVar.numberOfDice = Integer.valueOf((String)slides.get("numberOfDice"));
		       	globalVar.excommunicationRound = Integer.valueOf((String)slides.get("excommunicationRound"));
		       	globalVar.totalNumberOfCardsPerSet = Integer.valueOf((String)slides.get("totalNumberOfCardsPerSet"));
		       	globalVar.towerCardsPerRound = Integer.valueOf((String)slides.get("towerCardsPerRound"));
		       	globalVar.towerCardsPerPeriod = Integer.valueOf((String)slides.get("towerCardsPerPeriod"));
		       	globalVar.floorsPerTower = Integer.valueOf((String)slides.get("floorsPerTower"));
		       	globalVar.totalNumberOfPeriods = Integer.valueOf((String)slides.get("totalNumberOfPeriods"));
		       	globalVar.maxNumberPlayerCards = Integer.valueOf((String)slides.get("maxNumberPlayerCards"));
		       	globalVar.initialWoods = Integer.valueOf((String)slides.get("initialWoods"));
		       	globalVar.initialStones = Integer.valueOf((String)slides.get("initialStones"));
		       	globalVar.initialServants = Integer.valueOf((String)slides.get("initialServants"));
		       	globalVar.initialFirstPlayerCoins = Integer.valueOf((String)slides.get("initialFirstPlayerCoins"));
		       	globalVar.initialSecondPlayerCoins = Integer.valueOf((String)slides.get("initialSecondPlayerCoins"));
		       	globalVar.initialThirdPlayerCoins = Integer.valueOf((String)slides.get("initialThirdPlayerCoins"));
		       	globalVar.initialFourthPlayerCoins = Integer.valueOf((String)slides.get("initialFourthPlayerCoins"));
		       	// Add here for add coins to another player   	
		       	globalVar.initialVictoryPoints = Integer.valueOf((String)slides.get("initialVictoryPoints"));
		       	globalVar.initialMilitaryPoints = Integer.valueOf((String)slides.get("initialMilitaryPoints"));
		       	globalVar.initialFaithPoints = Integer.valueOf((String)slides.get("initialFaithPoints"));
		       	globalVar.minDiceFirstHarvestArea = Integer.valueOf((String)slides.get("minDiceFirstHarvestArea"));
		       	globalVar.minDiceSecondHarvestArea = Integer.valueOf((String)slides.get("minDiceSecondHarvestArea"));
		       	globalVar.minDiceFirstProductionArea = Integer.valueOf((String)slides.get("minDiceFirstProductionArea"));
		       	globalVar.minDiceSecondProductionArea = Integer.valueOf((String)slides.get("minDiceSecondProductionArea"));
		       	globalVar.minDiceValueCouncilPalace = Integer.valueOf((String)slides.get("minDiceValueCouncilPalace"));
		       	globalVar.towerTax = Integer.valueOf((String)slides.get("towerTax"));
		       	globalVar.victoryPointsFirstMilitaryPower = Integer.valueOf((String)slides.get("victoryPointsFirstMilitaryPower"));
		       	globalVar.victoryPointsSecondMilitaryPower = Integer.valueOf((String)slides.get("victoryPointsSecondMilitaryPower"));
		       	globalVar.maxVictoryPoints = Integer.valueOf((String)slides.get("maxVictoryPoints"));
		       	globalVar.maxMilitaryPoints = Integer.valueOf((String)slides.get("maxMilitaryPoints"));
		       	globalVar.maxFaithPoints = Integer.valueOf((String)slides.get("maxFaithPoints"));
		       	globalVar.endResourcesToVictoryPoint = Integer.valueOf((String)slides.get("endResourcesToVictoryPoint"));
	        }   	
		       
		    			
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        InitCouncilPrivilege cpi = new InitCouncilPrivilege();
		cpi.readCouncilPrivilege();
		GlobalVariables.councilPrivilegeEffect = cpi.getMultChoice();       			       	
       	
		
		InitMilitaryPointsRequired mpi = new InitMilitaryPointsRequired();
		mpi.readMilitaryPointsRequired();
		GlobalVariables.militaryPointsRequired = mpi.getMilitaryPointsRequired();
        
    	InitEndPoints iep = new InitEndPoints();
    	iep.readJson();
    	GlobalVariables.endCharacterVictoryPoints = iep.getEndCharacterPoints();
    	GlobalVariables.endTerritoryVictoryPoints = iep.getEndTerritoryPoints();
    	
       	System.out.println("inizializzate tutte le variabili globali");
    }
}