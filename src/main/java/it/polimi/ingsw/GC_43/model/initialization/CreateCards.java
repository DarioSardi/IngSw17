package it.polimi.ingsw.GC_43.model.initialization;

import it.polimi.ingsw.GC_43.model.cards.*;
import it.polimi.ingsw.GC_43.model.effects.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
 
 
public class CreateCards {
	private ArrayList<Effect> instantBonus;
	private ArrayList<Effect> permanentBonus;
    private String name;
    private String type;
    private int period;
    private CostEffect costEffect;
    private CardBonusIterators cardBonusIterators;
    private CardCostIterators cardCostIterators;
	
	public CreateCards(){		
    	this.instantBonus = new ArrayList<Effect>();		
    	this.permanentBonus = new ArrayList<Effect>();
    	this.costEffect = new CostEffect(null);
    	this.cardBonusIterators = new CardBonusIterators();
    	this.cardCostIterators = new CardCostIterators();
	}
    
	public void readCards(Object obj, ArrayList<Card> cards) { 
    	 int i=0;
         JSONObject jsonObject = (JSONObject) obj;
         
         JSONArray cardContent = (JSONArray) jsonObject.get("Cards");
         Iterator<?> iterator1 = cardContent.iterator();

         while (iterator1.hasNext()) {
        	 JSONObject slides = (JSONObject) iterator1.next();
         
             // First I take the global data
             this.name = (String) slides.get("Name");
             this.type = (String) slides.get("Type");
        	 this.period = Integer.valueOf((String)slides.get("Period"));


             // Now we try to take the data from "presentationSlides" array
             JSONArray instantBonusIterator = (JSONArray) slides.get("InstantBonus");
             this.cardBonusIterators.iterator(this.instantBonus, instantBonusIterator.iterator());
             
             JSONArray permanentBonusIterator = (JSONArray) slides.get("PermanentBonus");
             this.cardBonusIterators.iterator(this.permanentBonus, permanentBonusIterator.iterator());            
             
             if (type.equals("TerritoryCard")){
            	 
            	 int harvestDice = Integer.valueOf((String)slides.get("HarvestDice"));
                 cards.add(new TerritoryCard(this.name, this.period, this.instantBonus, this.permanentBonus, harvestDice));
             }
             else{
            	 
            	 JSONArray cardCostsIterator = (JSONArray) slides.get("Cost");
            	 this.cardCostIterators.iterator(this.costEffect, cardCostsIterator.iterator());

            	 
            	 if (type.equals("CharacterCard")){
            		 cards.add(new CharacterCard(this.name, this.period, this.costEffect, this.instantBonus, this.permanentBonus));
	             }
            	 
            	 else
            	 if (type.equals("BuildingCard")){
	            	 int productionDice = Integer.valueOf((String)slides.get("ProductionDice"));
	                 
	            	 cards.add(new BuildingCard(this.name, this.period, this.costEffect, this.instantBonus,
		             			this.permanentBonus, productionDice));
	             }
            	 
            	 else
            	 if (type.equals("VentureCard")){
	            	 int militaryCost = Integer.valueOf((String)slides.get("MilitaryCost"));
	            	 int militaryMin = Integer.valueOf((String)slides.get("MilitaryMin"));
	                 cards.add(new VentureCard(this.name, this.period, this.costEffect, militaryCost, militaryMin, this.instantBonus,
		             			this.permanentBonus));
	             }	           
             }

             this.instantBonus = new ArrayList<Effect>();
             this.permanentBonus = new ArrayList<Effect>();
             
             System.out.println(i); i++;
         }     
         selectRandomCards(cards);
    }
	
	private void selectRandomCards(ArrayList<Card> cards){
		ArrayList<Card> cardsByType = new ArrayList<Card>();
		ArrayList<Card> cardsByPeriod;
		for(int i=1; i <= GlobalVariablesInit.totalNumberOfPeriods; i++){
			cardsByPeriod = new ArrayList<>();
			
			for(int j=0; j < cards.size(); j++){
				if(cards.get(j).getCardEra() == i) cardsByPeriod.add(cards.get(j));
			}
			
			Collections.shuffle(cardsByPeriod);
			while (cardsByPeriod.size() > GlobalVariablesInit.towerCardsPerPeriod){
				cardsByPeriod.remove(cardsByPeriod.size()-1);
			}
			
			 cardsByType.addAll(cardsByPeriod);
			 cards = cardsByType;
		}
	}
}