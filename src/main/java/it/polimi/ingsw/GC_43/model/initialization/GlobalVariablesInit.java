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
		       	GlobalVariables.numberOfFamilyMembers = Integer.valueOf((String)slides.get("numberOfFamilyMembers"));
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
		       	GlobalVariables.initialFifthPlayerCoins = Integer.valueOf((String)slides.get("initialFifthPlayerCoins"));
		       	// Add here for add coins to another player   	
		       	GlobalVariables.initialVictoryPoints = Integer.valueOf((String)slides.get("initialVictoryPoints"));
		       	GlobalVariables.initialMilitaryPoints = Integer.valueOf((String)slides.get("initialMilitaryPoints"));
		       	GlobalVariables.initialFaithPoints = 10; //Integer.valueOf((String)slides.get("initialFaithPoints"));
		       	GlobalVariables.minDiceFirstHarvestArea = Integer.valueOf((String)slides.get("minDiceFirstHarvestArea"));
		       	GlobalVariables.minDiceSecondHarvestArea = Integer.valueOf((String)slides.get("minDiceSecondHarvestArea"));
		       	GlobalVariables.minDiceFirstProductionArea = Integer.valueOf((String)slides.get("minDiceFirstProductionArea"));
		       	GlobalVariables.minDiceSecondProductionArea = Integer.valueOf((String)slides.get("minDiceSecondProductionArea"));
		       	GlobalVariables.minDiceValueCouncilPalace = Integer.valueOf((String)slides.get("minDiceValueCouncilPalace"));
		       	GlobalVariables.malusOnSecondHarvestArea = Integer.valueOf((String)slides.get("malusOnSecondHarvestArea"));
		       	GlobalVariables.malusOnSecondProductionArea = Integer.valueOf((String)slides.get("malusOnSecondProductionArea"));
		       	GlobalVariables.towerTax = Integer.valueOf((String)slides.get("towerTax"));
		       	GlobalVariables.victoryPointsFirstMilitaryPower = Integer.valueOf((String)slides.get("victoryPointsFirstMilitaryPower"));
		       	GlobalVariables.victoryPointsSecondMilitaryPower = Integer.valueOf((String)slides.get("victoryPointsSecondMilitaryPower"));
		       	GlobalVariables.maxVictoryPoints = Integer.valueOf((String)slides.get("maxVictoryPoints"));
		       	GlobalVariables.maxMilitaryPoints = Integer.valueOf((String)slides.get("maxMilitaryPoints"));
		       	GlobalVariables.maxFaithPoints = Integer.valueOf((String)slides.get("maxFaithPoints"));
		       	GlobalVariables.numOfLeaderCardsForPlayer = Integer.valueOf((String)slides.get("numOfLeaderCardsForPlayer"));
		       	GlobalVariables.totalNumOfLeaderCards = Integer.valueOf((String)slides.get("totalNumOfLeaderCards"));
		       	GlobalVariables.endResourcesToVictoryPoint = Integer.valueOf((String)slides.get("endResourcesToVictoryPoint"));
	        }   	
		       
		    			
        } catch (Exception e) {
    		System.out.println("Exception on read Global Variables");
            e.printStackTrace();
        }
        
        InitCouncilPrivilege cpi = new InitCouncilPrivilege();
		cpi.readCouncilPrivilege();
		GlobalVariables.councilPrivilegeEffect = cpi.getMultChoice();       		
		System.out.println("Council privilege initialized");
       	
		
		InitMilitaryPointsRequired mpi = new InitMilitaryPointsRequired();
		mpi.readMilitaryPointsRequired();
		GlobalVariables.militaryPointsRequired = mpi.getMilitaryPointsRequired();
		System.out.println("Military points required for buy territory card initialized");
		
		InitFaithPointExcomRequired fperi = new InitFaithPointExcomRequired();
		fperi.readFaithPointExcomRequired();
		GlobalVariables.faithPointExcomRequired = fperi.getFaithPointExcom();
		System.out.println("Faith points required to not be excommunicated initialized");
        
    	InitEndPoints iep = new InitEndPoints();
    	iep.readJson();
    	GlobalVariables.endCharacterVictoryPoints = iep.getEndCharacterPoints();
    	GlobalVariables.endTerritoryVictoryPoints = iep.getEndTerritoryPoints();
		System.out.println("End victory points for character and territory cards initialized");

    }
}