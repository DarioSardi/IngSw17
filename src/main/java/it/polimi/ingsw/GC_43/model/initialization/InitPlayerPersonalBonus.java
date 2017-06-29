package it.polimi.ingsw.GC_43.model.initialization;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.PlayerPersonalBonus;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class InitPlayerPersonalBonus {
	private PlayerPersonalBonus basePersonalTile;
	private ArrayList<PlayerPersonalBonus> allAdvancedPersonalTile;
	
	InitPlayerPersonalBonus(){		
		
		this.basePersonalTile = new PlayerPersonalBonus(null, null);
		this.allAdvancedPersonalTile = new ArrayList<>();
	}
	
	/**
	 * Initialize players personal bonus
	 */
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
                
                ArrayList<Effect> basePersonalProductionTile = new ArrayList<>();
                while (baseProductionIterator.hasNext()) {
        			JSONObject slide2 = (JSONObject) baseProductionIterator.next();
        			String typeBonus = (String)slide2.get("bonus");
        			int value = Integer.parseInt((String)slide2.get("value")); 	
        			basePersonalProductionTile.add(new AddGainAndCostResources().retResourceEffect(typeBonus, value));
        		 }
                
                JSONArray baseHarvestArray = (JSONArray) slide1.get("Harvest");
                Iterator<?> baseHarvestIterator = baseHarvestArray.iterator();
                
                ArrayList<Effect> basePersonalHarvestTile = new ArrayList<>();
                while (baseHarvestIterator.hasNext()) {
        			JSONObject slide2 = (JSONObject) baseHarvestIterator.next();
        			String typeBonus = (String)slide2.get("bonus");
        			int value = Integer.parseInt((String)slide2.get("value"));
        			basePersonalHarvestTile.add(new AddGainAndCostResources().retResourceEffect(typeBonus, value));
        		 }
                
        		this.basePersonalTile = new PlayerPersonalBonus(basePersonalProductionTile, basePersonalHarvestTile);

     		 }
             
             JSONArray advancedDefaultBonusArray = (JSONArray) slide.get("AdvancedDefaultBonus");
             Iterator<?> advancedDefaultBonusIterator = advancedDefaultBonusArray.iterator();
 
             PlayerPersonalBonus advancedPersonalTile;
             while (advancedDefaultBonusIterator.hasNext()) {
     			JSONObject slide1 = (JSONObject) advancedDefaultBonusIterator.next();     			
     			
     			JSONArray baseProductionArray = (JSONArray) slide1.get("Production");
                Iterator<?> baseProductionIterator = baseProductionArray.iterator();
                
                ArrayList<Effect> advancedPersonalProductionTile = new ArrayList<>();
        		
                while (baseProductionIterator.hasNext()) {
        			JSONObject slide2 = (JSONObject) baseProductionIterator.next();
        			String typeBonus = (String)slide2.get("bonus");
        			int value = Integer.parseInt((String)slide2.get("value")); 	
        			advancedPersonalProductionTile.add(new AddGainAndCostResources().retResourceEffect(typeBonus, value));
        		}
                
                JSONArray baseHarvestArray = (JSONArray) slide1.get("Harvest");
                Iterator<?> baseHarvestIterator = baseHarvestArray.iterator();
                
                ArrayList<Effect> advancedPersonalHarvestTile = new ArrayList<>();
        		
                while (baseHarvestIterator.hasNext()) {
        			JSONObject slide2 = (JSONObject) baseHarvestIterator.next();
        			String typeBonus = (String)slide2.get("bonus");
        			int value = Integer.parseInt((String)slide2.get("value")); 	
        			advancedPersonalHarvestTile.add(new AddGainAndCostResources().retResourceEffect(typeBonus, value));
        		}
                
                advancedPersonalTile = new PlayerPersonalBonus(advancedPersonalProductionTile, advancedPersonalHarvestTile);  
                this.allAdvancedPersonalTile.add(advancedPersonalTile);
     		 }
         }
        
        if (GlobalVariables.numberOfPlayers < 5)
        	this.allAdvancedPersonalTile.remove(this.allAdvancedPersonalTile.size()-1);
           	 	
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
	
	public PlayerPersonalBonus getBasePersonalTile(){
		return this.basePersonalTile;
	}
		
	public ArrayList<PlayerPersonalBonus> getAllAdvancedPersonalTile(){
		return this.allAdvancedPersonalTile;
	}
}