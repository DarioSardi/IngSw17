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
import it.polimi.ingsw.GC_43.model.effects.ResourceEffect;
import it.polimi.ingsw.GC_43.model.resources.*;

public class InitActionSpaces {
	private int[] faithPoints;
	private Market market;
	private CouncilPalace councilPalace;
    private ArrayList <Tower> towers;
	private Integer[] militaryPointsRequired;
	
	
	InitActionSpaces(){
		this.faithPoints = new int[GlobalVariables.maxFaithPoints+1];
        this.market = new Market(new ArrayList<MarketActionSpace>());
        this.towers = new ArrayList<>();
        this.towers.add(new Tower(TowerColors.TERRITORIES_TOWER, GlobalVariables.floorsPerTower));
        this.towers.add(new Tower(TowerColors.CHARACTERS_TOWER, GlobalVariables.floorsPerTower));
        this.towers.add(new Tower(TowerColors.BUILDINGS_TOWER, GlobalVariables.floorsPerTower));
        this.towers.add(new Tower(TowerColors.VENTURES_TOWER, GlobalVariables.floorsPerTower));
        this.militaryPointsRequired = new Integer[GlobalVariables.maxNumberPlayerCards+1];
        
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
             
             //SAMUEL inizializzare faithPoints su Board
             
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
        			
        			resources.add(retResourceEffect(typeBonus, valueBonus));
       	 	 	}                
                marketSpaces.add(new MarketActionSpace(resources, minDiceValue));
    	 	 }
             
             this.market.setMarketActionSpaces(marketSpaces);
           
             
             JSONArray councilPalaceArray = (JSONArray) slides.get("CouncilPalaceBonus");
             Iterator<?> councilPalaceIterator = councilPalaceArray.iterator();
             ArrayList<Effect> councilPalaceBonus = new ArrayList<>();
             while (councilPalaceIterator.hasNext()) {
      			JSONObject slide1 = (JSONObject) councilPalaceIterator.next();
      			String type = (String)slide1.get("type");
     			int value = Integer.parseInt((String)slide1.get("value")); 
     			
     			councilPalaceBonus.add(retResourceEffect(type, value));    			
    	 	 }
             this.councilPalace = new CouncilPalace(councilPalaceBonus);
             
             //SAMUEL inizializzare market su Board

             JSONArray territoryFloorsBonusArray = (JSONArray) slides.get("FloorsBonusTerritoryTower");
             addFloorsToTower(territoryFloorsBonusArray, this.towers.get(0));
             
             JSONArray characterFloorsBonusArray = (JSONArray) slides.get("FloorsBonusCharacterTower");
             addFloorsToTower(characterFloorsBonusArray, this.towers.get(1));
             
             JSONArray buildingFloorsBonusArray = (JSONArray) slides.get("FloorsBonusBuildingTower");
             addFloorsToTower(buildingFloorsBonusArray, this.towers.get(2));
                          
             JSONArray ventureFloorsBonusArray = (JSONArray) slides.get("FloorsBonusVentureTower");
             addFloorsToTower(ventureFloorsBonusArray, this.towers.get(3));

             //SAMUEL inizializzato floors su towers su Board
             
             
             JSONArray militaryPointsRequiredArray = (JSONArray) slides.get("MilitaryPointsRequired");
             Iterator<?> militaryPointsRequiredIterator = militaryPointsRequiredArray.iterator();
             
             
             while (militaryPointsRequiredIterator.hasNext()) {
      			JSONObject slide1 = (JSONObject) militaryPointsRequiredIterator.next();
      			int numTerritoryCards = Integer.parseInt((String)slide1.get("numTerritoryCards"));
     			int required = Integer.parseInt((String)slide1.get("militaryPointsRequired")); 
     			
     			if (numTerritoryCards >= 0 && numTerritoryCards <= GlobalVariables.maxNumberPlayerCards){
     				this.militaryPointsRequired[numTerritoryCards] = required;
     			}		
    	 	 }   
         }
         
    	 System.out.println("inizializzati tutti gli spazi azione");

           	 	
        } catch (Exception e) {
            e.printStackTrace();
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
	     				ResourceEffect effect = new ResourceEffect(null);
		    			effect = retResourceEffect(bonus, value);
		    			tower.addFloor(effect, minTowerDiceValue);
	     			}
	     			while (floorsBonusIterator.hasNext())
		      			slide = (JSONObject) floorsBonusIterator.next();
     			}
   	 	 	}
            
            // Exception se il numero di torri è minore del massimo??
		}
	}
	
	
	private ResourceEffect retResourceEffect(String type, int value){
		ResourceEffect resEff;		
		if ("coin".equals(type)) resEff = (new ResourceEffect(new Coin(value)));
     	else if ("servant".equals(type)) resEff =  (new ResourceEffect(new Servant(value)));
     	else if ("stone".equals(type)) resEff =  (new ResourceEffect(new Stone(value)));
     	else if ("wood".equals(type)) resEff =  (new ResourceEffect(new Wood(value)));
     	else if ("faithPoint".equals(type)) resEff =  (new ResourceEffect(new FaithPoint(value)));
     	else if ("militaryPoint".equals(type)) resEff =  (new ResourceEffect(new MilitaryPoint(value)));
     	else if ("victoryPoint".equals(type)) resEff =  (new ResourceEffect(new VictoryPoint(value)));
     	else if ("councilPrivilege".equals(type)) resEff =  (new ResourceEffect(new CouncilPrivilege(value)));
     	else resEff=null;
		return resEff;
		
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