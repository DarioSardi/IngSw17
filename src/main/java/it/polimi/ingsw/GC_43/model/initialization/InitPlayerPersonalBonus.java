package it.polimi.ingsw.GC_43.model.initialization;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import it.polimi.ingsw.GC_43.model.effects.Effect;

public class InitPlayerPersonalBonus {
	private ArrayList<Effect> basePersonalProductionBonusTile;
	private ArrayList<Effect> basePersonalHarvestBonusTile;
	private ArrayList<Effect> advancedPersonalProductionBonusTile;
	private ArrayList<Effect> advancedPersonalHarvestBonusTile;
	private ArrayList<ArrayList<Effect>> allAdvancedPersonalProductionBonusTiles;
	private ArrayList<ArrayList<Effect>> allAdvancedPersonalHarvestBonusTiles;
	InitPlayerPersonalBonus(){
		this.basePersonalProductionBonusTile = new ArrayList<>();
		this.basePersonalHarvestBonusTile = new ArrayList<>();
		this.allAdvancedPersonalProductionBonusTiles = new ArrayList<>();
		this.allAdvancedPersonalHarvestBonusTiles = new ArrayList<>();
	}
	
	public void readJson() {
        JSONParser parser = new JSONParser();
 
        try {
       	 Object obj;
      
	   	 obj = parser.parse(new FileReader("src/main/java/it/polimi/ingsw/GC_43/model/initialization/PlayerDefaultBonus.jar"));
 	
         JSONObject jsonObject = (JSONObject) obj;
         
         JSONArray defaultBonusArray = (JSONArray) jsonObject.get("DefaultBonus");
         Iterator<?> defaultBonusIterator = defaultBonusArray.iterator();

         while (defaultBonusIterator.hasNext()) {
        	 JSONObject slide = (JSONObject) defaultBonusIterator.next();
        	 
             JSONArray baseDefaultBonusArray = (JSONArray) slide.get("BaseDefaultBonus");
             Iterator<?> baseDefaultBonusIterator = baseDefaultBonusArray.iterator();
 
             while (baseDefaultBonusIterator.hasNext()) {
     			JSONObject slide1 = (JSONObject) baseDefaultBonusIterator.next();     			
     			
     			JSONArray baseProductionArray = (JSONArray) slide1.get("Production");
                Iterator<?> baseProductionIterator = baseProductionArray.iterator();
      
                while (baseProductionIterator.hasNext()) {
        			JSONObject slide2 = (JSONObject) baseProductionIterator.next();
        			String typeBonus = (String)slide2.get("bonus");
        			int value = Integer.parseInt((String)slide2.get("value")); 	
        			this.basePersonalProductionBonusTile.add(new AddGainAndCostResources().retResourceEffect(typeBonus, value));
        		 }
                
                JSONArray baseHarvestArray = (JSONArray) slide1.get("Harvest");
                Iterator<?> baseHarvestIterator = baseHarvestArray.iterator();
                
                while (baseHarvestIterator.hasNext()) {
        			JSONObject slide2 = (JSONObject) baseHarvestIterator.next();
        			String typeBonus = (String)slide2.get("bonus");
        			int value = Integer.parseInt((String)slide2.get("value"));
        			this.basePersonalHarvestBonusTile.add(new AddGainAndCostResources().retResourceEffect(typeBonus, value));
        		 }
     		 }
             JSONArray advancedDefaultBonusArray = (JSONArray) slide.get("AdvancedDefaultBonus");
             Iterator<?> advancedDefaultBonusIterator = advancedDefaultBonusArray.iterator();
 
             while (advancedDefaultBonusIterator.hasNext()) {
     			JSONObject slide1 = (JSONObject) advancedDefaultBonusIterator.next();     			
     			
     			JSONArray baseProductionArray = (JSONArray) slide1.get("Production");
                Iterator<?> baseProductionIterator = baseProductionArray.iterator();
                
        		this.advancedPersonalProductionBonusTile = new ArrayList<>();
        		
                while (baseProductionIterator.hasNext()) {
        			JSONObject slide2 = (JSONObject) baseProductionIterator.next();
        			String typeBonus = (String)slide2.get("bonus");
        			int value = Integer.parseInt((String)slide2.get("value")); 	
        			this.advancedPersonalProductionBonusTile.add(new AddGainAndCostResources().retResourceEffect(typeBonus, value));;

        		}
                this.allAdvancedPersonalProductionBonusTiles.add(this.advancedPersonalProductionBonusTile);
                
                JSONArray baseHarvestArray = (JSONArray) slide1.get("Harvest");
                Iterator<?> baseHarvestIterator = baseHarvestArray.iterator();
                
        		this.advancedPersonalHarvestBonusTile = new ArrayList<>();
        		
                while (baseHarvestIterator.hasNext()) {
        			JSONObject slide2 = (JSONObject) baseHarvestIterator.next();
        			String typeBonus = (String)slide2.get("bonus");
        			int value = Integer.parseInt((String)slide2.get("value")); 	
        			this.advancedPersonalHarvestBonusTile.add(new AddGainAndCostResources().retResourceEffect(typeBonus, value));
        		}
                this.allAdvancedPersonalHarvestBonusTiles.add(this.advancedPersonalHarvestBonusTile);

     		 }
         }
           	 	
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
	
	public ArrayList<Effect> getBasePersonalProductionBonusTile(){
		return this.basePersonalProductionBonusTile;
	}
	
	public ArrayList<Effect> getBasePersonalHarvestBonusTile(){
		return this.basePersonalHarvestBonusTile;
	}
	
	public ArrayList<ArrayList<Effect>> getAllAdvancedPersonalProductionBonusTile(){
		return this.allAdvancedPersonalProductionBonusTiles;
	}
	
	public ArrayList<ArrayList<Effect>> getAllAdvancedPersonalHarvestBonusTile(){
		return this.allAdvancedPersonalHarvestBonusTiles;
	}
}