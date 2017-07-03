package it.polimi.ingsw.GC_43.model.initialization;

import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.cards.*;
import it.polimi.ingsw.GC_43.model.effects.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
 
 
public class CreateCards {
	private ArrayList<Card> cards;
	private ArrayList<Effect> instantBonus;
	private ArrayList<Effect> permanentBonus;
    private String name;
    private String icon;
    private String type;
    private int period;
    private CostEffect costEffect;
    private CardBonusIterators cardBonusIterators;
    private CardCostIterators cardCostIterators;
	
	public CreateCards(){
		this.cards = new ArrayList<>();
    	this.instantBonus = new ArrayList<>();		
    	this.permanentBonus = new ArrayList<>();
    	this.cardBonusIterators = new CardBonusIterators();
	}
    
	/**
	 * Import all the cards of a specific tower
	 * @param obj
	 * @return type
	 */
	public ArrayList<Card> readCards(Object obj) { 
    	
         JSONObject jsonObject = (JSONObject) obj;
         
         JSONArray cardContent = (JSONArray) jsonObject.get("Cards");
         Iterator<?> iterator1 = cardContent.iterator();

         while (iterator1.hasNext()) {
        	 JSONObject slides = (JSONObject) iterator1.next();
         
             this.name = (String) slides.get("Name");
             this.icon = (String) slides.get("icon");
             this.type = (String) slides.get("Type");
        	 this.period = Integer.valueOf((String)slides.get("Period"));


             JSONArray instantBonusIterator = (JSONArray) slides.get("InstantBonus");
             this.cardBonusIterators.iterator(this.instantBonus, instantBonusIterator.iterator());
             
             JSONArray permanentBonusIterator = (JSONArray) slides.get("PermanentBonus");
             this.cardBonusIterators.iterator(this.permanentBonus, permanentBonusIterator.iterator());            
             
             if (type.equals("TerritoryCard")){
            	 
            	 int harvestDice = Integer.valueOf((String)slides.get("HarvestDice"));
                 this.cards.add(new TerritoryCard(this.name, this.period, this.instantBonus, this.permanentBonus, harvestDice, this.icon));
             }
             else{
            	 
            	 JSONArray cardCostsIterator = (JSONArray) slides.get("Cost");
            	 this.cardCostIterators=new CardCostIterators(this.costEffect, cardCostsIterator.iterator());
            	 this.costEffect=new CostEffect(this.cardCostIterators.getCosts());
            	
            	 
            	 if (type.equals("CharacterCard")){
            		 this.cards.add(new CharacterCard(this.name, this.period, this.costEffect, this.instantBonus, this.permanentBonus, this.icon));
	             }
            	 
            	 else
            	 if (type.equals("BuildingCard")){
	            	 int productionDice = Integer.valueOf((String)slides.get("ProductionDice"));
	            	 this.cards.add(new BuildingCard(this.name, this.period, this.costEffect, this.instantBonus,
		             			this.permanentBonus, productionDice, this.icon));
	            	 
	             }
            	 
            	 else
            	 if (type.equals("VentureCard")){
	            	 int militaryCost = Integer.valueOf((String)slides.get("MilitaryCost"));
	            	 int militaryMin = Integer.valueOf((String)slides.get("MilitaryMin"));
	                 this.cards.add(new VentureCard(this.name, this.period, this.costEffect, militaryCost, militaryMin, this.instantBonus,
		             			this.permanentBonus, this.icon));
	             }	           
             }

             this.instantBonus = new ArrayList<Effect>();
             this.permanentBonus = new ArrayList<Effect>();
             
           
         }
        
         return selectRandomCards();
    }
	
	/**
	 * @return selectRandomCards Return the cards selected for the game.
	 */
	private ArrayList<Card> selectRandomCards() throws ExceptionInInitializerError{
		ArrayList<Card> cardsByType = new ArrayList<>();
		ArrayList<Card> cardsByPeriod;
		for(int i=1; i <= GlobalVariables.totalNumberOfPeriods; i++){
			cardsByPeriod = new ArrayList<>();
			
			for(int j=0; j < this.cards.size(); j++){
				if(cards.get(j).getCardEra() == i) cardsByPeriod.add(cards.get(j));
			}
			
				if (cardsByPeriod.size() < GlobalVariables.towerCardsPerPeriod) {
					throw new ExceptionInInitializerError("Insufficient number of cards!");
				}
			Collections.shuffle(cardsByPeriod);
			while (cardsByPeriod.size() > GlobalVariables.towerCardsPerPeriod){
				cardsByPeriod.remove(cardsByPeriod.size()-1);
			}
			 
			 cardsByType.addAll(cardsByPeriod);
		}
		 return cardsByType;
	}
}