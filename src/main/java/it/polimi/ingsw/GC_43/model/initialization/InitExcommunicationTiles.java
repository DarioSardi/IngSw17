package it.polimi.ingsw.GC_43.model.initialization;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.effects.Effect;
 
public class InitExcommunicationTiles {
	private ArrayList<Effect> malusExcommunicationTiles;
	private ArrayList<Effect> malusExcommunicationSelected;
	private ExcommunicationTilesIterators excommTilesIterators;
	
	InitExcommunicationTiles(){
		this.malusExcommunicationTiles = new ArrayList<>();
		this.malusExcommunicationSelected = new ArrayList<>();
	}
	public void readExcommunicationTiles() {
		
        JSONParser parser = new JSONParser();
 
        try {
       	 Object obj;
  
   		obj = parser.parse(new FileReader("src/main/java/it/polimi/ingsw/GC_43/model/initialization/ExcommunicationTiles.jar"));

         JSONObject jsonObject = (JSONObject) obj;
         
         JSONArray excommunicationTiles = (JSONArray) jsonObject.get("ExcommunicationTiles");
         
         for (int i=1; i <= GlobalVariables.totalNumberOfPeriods; i++){
	         Iterator<?> excommunicationTilesIterator = excommunicationTiles.iterator();
	         
	 		 this.excommTilesIterators = new ExcommunicationTilesIterators();
	         while (excommunicationTilesIterator.hasNext()) {

	        	 JSONObject slides = (JSONObject) excommunicationTilesIterator.next();
	        	 
	        	 int period = Integer.parseInt((String)slides.get("period"));
	
	             JSONArray malus = (JSONArray) slides.get("Malus");
	             if (i == period){
	            	 this.excommTilesIterators.iterator(malus.iterator());    
	             }
	         }

	         this.malusExcommunicationTiles = this.excommTilesIterators.getMalus();

	         selectRandomExcommTiles();
         }
        
    	 System.out.println("inizializzate tutte le carte scomunica");

           	 	
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	
	private void selectRandomExcommTiles(){
			Collections.shuffle(this.malusExcommunicationTiles);
			this.malusExcommunicationSelected.add(this.malusExcommunicationTiles.get(0));
	}
	
	public ArrayList<Effect> getMalusExcommunicationSelected(){
		return this.malusExcommunicationSelected;
	}

}