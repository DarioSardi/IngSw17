package it.polimi.ingsw.GC_43.model.initialization;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.actionSpace.*;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class InitActionSpaces {
	private int[] faithPoints;
	private Market market;
	private CouncilPalace councilPalace;
    private ArrayList <Tower> towers;	
	
	InitActionSpaces(){
		this.faithPoints = new int[GlobalVariables.maxFaithPoints+1];
        this.market = new Market(new ArrayList<MarketActionSpace>());
        this.towers = new ArrayList<>();
        this.towers.add(new Tower(TowerColors.TERRITORIES_TOWER, GlobalVariables.floorsPerTower));
        this.towers.add(new Tower(TowerColors.CHARACTERS_TOWER, GlobalVariables.floorsPerTower));
        this.towers.add(new Tower(TowerColors.BUILDINGS_TOWER, GlobalVariables.floorsPerTower));
        this.towers.add(new Tower(TowerColors.VENTURES_TOWER, GlobalVariables.floorsPerTower));     
        //Add here a new tower
	}
	
	public void readJson() {
        JSONParser parser = new JSONParser();
 
        try {
       	 Object obj;
      
	   	 obj = parser.parse(new FileReader("src/main/java/it/polimi/ingsw/GC_43/model/initialization/ActionSpaces.jar"));
 	
         JSONObject jsonObject = (JSONObject) obj;
         
         JSONArray actionSpaces = (JSONArray) jsonObject.get("ActionSpaces");
         Iterator<?> actionSpaceIterator = actionSpaces.iterator();

         while (actionSpaceIterator.hasNext()) {
        	 JSONObject slides = (JSONObject) actionSpaceIterator.next();
        	 
        	 faithAreaInit(slides);
             marketInit(slides);
             councilPalaceBonusInit(slides);
             

             JSONArray territoryFloorsBonusArray = (JSONArray) slides.get("FloorsBonusTerritoryTower");
             addFloorsToTower(territoryFloorsBonusArray, this.towers.get(0));
             
             JSONArray characterFloorsBonusArray = (JSONArray) slides.get("FloorsBonusCharacterTower");
             addFloorsToTower(characterFloorsBonusArray, this.towers.get(1));
             
             JSONArray buildingFloorsBonusArray = (JSONArray) slides.get("FloorsBonusBuildingTower");
             addFloorsToTower(buildingFloorsBonusArray, this.towers.get(2));
                          
             JSONArray ventureFloorsBonusArray = (JSONArray) slides.get("FloorsBonusVentureTower");
             addFloorsToTower(ventureFloorsBonusArray, this.towers.get(3));
         }
         
    	 System.out.println("inizializzati tutti gli spazi azione");

           	 	
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
	
	
	public void councilPalaceBonusInit(JSONObject slides){
		JSONArray councilPalaceArray = (JSONArray) slides.get("CouncilPalaceBonus");
        Iterator<?> councilPalaceIterator = councilPalaceArray.iterator();
        ArrayList<Effect> councilPalaceBonus = new ArrayList<>();
        while (councilPalaceIterator.hasNext()) {
 			JSONObject slide1 = (JSONObject) councilPalaceIterator.next();
 			String type = (String)slide1.get("type");
			int value = Integer.parseInt((String)slide1.get("value")); 
			
			councilPalaceBonus.add(new AddGainAndCostResources().retResourceEffect(type, value));
	 	 }
        this.councilPalace = new CouncilPalace(councilPalaceBonus);
	}
	
	public void marketInit(JSONObject slides){
		JSONArray marketArray = (JSONArray) slides.get("Market");
	    Iterator<?> marketIterator = marketArray.iterator();
	    
	    int minDiceValue;     
	    ArrayList<MarketActionSpace> marketSpaces = new ArrayList<>();
	    while (marketIterator.hasNext()) {
			JSONObject slide1 = (JSONObject) marketIterator.next();
			minDiceValue = Integer.valueOf((String)slide1.get("minDiceValue"));
			
			JSONArray marketBonus = (JSONArray) slide1.get("Bonus");
	       Iterator<?> marketBonusIterator = marketBonus.iterator(); 
	       
			ArrayList<Effect> resources = new ArrayList<>();
	
	       while (marketBonusIterator.hasNext()) {
				JSONObject slide2 = (JSONObject) marketBonusIterator.next();
				String typeBonus = (String)slide2.get("type");
				int valueBonus = Integer.parseInt((String)slide2.get("value"));
				
				resources.add(new AddGainAndCostResources().retResourceEffect(typeBonus, valueBonus));
		 	 	}                
	       marketSpaces.add(new MarketActionSpace(resources, minDiceValue));
		 }
	    ArrayList<MarketActionSpace> tempMarketSpaces = new ArrayList<>();
	    switch (GlobalVariables.numberOfPlayers) {     	
	    case 2: 
	    case 3: tempMarketSpaces = setTempMarketSpaces(marketSpaces, 2); break;
	    case 4: tempMarketSpaces = setTempMarketSpaces(marketSpaces, 4); break;
	    case 5: tempMarketSpaces = setTempMarketSpaces(marketSpaces, 5); break;
	    default: System.out.println("Number of players unexpected");
	    }
	    
	    this.market.setMarketActionSpaces(tempMarketSpaces);
	}
  
    
	
	public void faithAreaInit(JSONObject slides){
		JSONArray faithArea = (JSONArray) slides.get("FaithPoints");
        Iterator<?> faithAreaIterator = faithArea.iterator();
            	             
        int position;
        int victoryPoints;
        
        while (faithAreaIterator.hasNext()) {
			JSONObject slide = (JSONObject) faithAreaIterator.next();
			position = Integer.parseInt((String)slide.get("position"));
			victoryPoints = Integer.parseInt((String)slide.get("victoryPoint"));
			if(position >= 0 && position <= GlobalVariables.maxFaithPoints)
				this.faithPoints[position] = victoryPoints;
		 }
	}
	
	private void addFloorsToTower(JSONArray floorsBonusArray, Tower tower){
		for (int i=1; i <= GlobalVariables.floorsPerTower; i++){
            Iterator<?> floorsBonusIterator = floorsBonusArray.iterator();
            
            while (floorsBonusIterator.hasNext()) {
     			JSONObject slide = (JSONObject) floorsBonusIterator.next();
     			int floor = Integer.parseInt((String)slide.get("floor")); 
     			
     			if(floor==i){
	      			String bonus = (String)slide.get("bonus");
	     			int value = Integer.parseInt((String)slide.get("value"));
	     			int minTowerDiceValue = Integer.parseInt((String)slide.get("minDiceValue")); 
	     			
	     			if ("noBonus".equals(bonus)){
	     				tower.addFloor(minTowerDiceValue);
	     			}
	     			else{
		    			Effect effect = new AddGainAndCostResources().retResourceEffect(bonus, value);
		    			tower.addFloor(effect, minTowerDiceValue);
	     			}
	     			while (floorsBonusIterator.hasNext())
		      			slide = (JSONObject) floorsBonusIterator.next();
     			}
   	 	 	}
		}
	}
	
	private ArrayList<MarketActionSpace> setTempMarketSpaces(ArrayList<MarketActionSpace> marketSpaces, int numberOfMarketSpaces){

	    ArrayList<MarketActionSpace> tempMarketSpaces = new ArrayList<>();
		try {
			for (int i=0; i < numberOfMarketSpaces; i++){
				tempMarketSpaces.add(marketSpaces.get(i));
			}
		} catch (Exception e) {
			System.out.println("Insufficient number of market space in the ActionSpace.jar file");
			e.printStackTrace();
		}
		return tempMarketSpaces;
	}
	
	public ArrayList<Tower> getTowers(){
		return this.towers;
	}
	
	public int[] getFaithPoints() {
		return this.faithPoints;
	}
	
	public Market getMarket() {
		return this.market;
	}
	
	public CouncilPalace getCouncilPalace(){
		return this.councilPalace;
	}
}