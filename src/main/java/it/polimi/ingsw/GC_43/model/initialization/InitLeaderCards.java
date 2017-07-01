package it.polimi.ingsw.GC_43.model.initialization;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.cards.LeaderCard;
import it.polimi.ingsw.GC_43.model.effects.Effect;
 
public class InitLeaderCards {
	private ArrayList<LeaderCard> leaderCards;
	private ArrayList<Effect> requirements;
	private ArrayList<Effect> ability;
    private String name;
    private boolean isPermanentAbility;
    private LeaderCardRequirementsAndEffect cardRequirementsAndEffectIterators;
	
	public InitLeaderCards(){
		this.cardRequirementsAndEffectIterators = new LeaderCardRequirementsAndEffect();
		this.leaderCards = new ArrayList<>();
    	this.requirements = new ArrayList<>();		
    	this.ability = new ArrayList<>();		

	}
    
	public void readLeaderCards() {
        JSONParser parser = new JSONParser();
 
        try {
       	 Object obj;
      
	   		obj = parser.parse(new FileReader("src/main/java/it/polimi/ingsw/GC_43/model/initialization/LeadersCards.jar"));
       	 	
            JSONObject jsonObject = (JSONObject) obj;
            
            JSONArray cardContent = (JSONArray) jsonObject.get("Cards");
            Iterator<?> iterator1 = cardContent.iterator();

            while (iterator1.hasNext()) {
           	JSONObject slides = (JSONObject) iterator1.next();
            
                this.name = (String) slides.get("name");
                String typeBonus = (String) slides.get("typeBonus");
                if ("permanentBonus".equals(typeBonus)) this.isPermanentAbility = true;
                else this.isPermanentAbility = false;
                
                JSONArray requirementsArray = (JSONArray) slides.get("Requirements");
                Iterator<?> requirementsIterator = requirementsArray.iterator();
                while (requirementsIterator.hasNext()) {
         			JSONObject slide = (JSONObject) requirementsIterator.next(); 

         			String typeReq = (String)slide.get("typeReq");
         			float valueFloat = Float.parseFloat((String)slide.get("valueReq"));
                    this.cardRequirementsAndEffectIterators.iterator(this.requirements, typeReq, valueFloat);
                }
                
                JSONArray effectArray = (JSONArray) slides.get("Bonus");
                Iterator<?> effectIterator = effectArray.iterator();
                while (effectIterator.hasNext()) {
         			JSONObject slide = (JSONObject) effectIterator.next(); 

         			String effect = (String)slide.get("effect");
        			float valueFloat = Float.parseFloat((String)slide.get("valueEffect"));
                    this.cardRequirementsAndEffectIterators.iterator(this.ability, effect, valueFloat);

                }
                
                this.leaderCards.add(new LeaderCard(this.name, this.requirements, this.ability, this.isPermanentAbility));
    			
                this.requirements = new ArrayList<Effect>();
                this.ability = new ArrayList<Effect>();
            }
            
            selectRandomCards();
        } catch (Exception e) {
    		System.out.println("Exception on read leader cards");
            e.printStackTrace();
        }
        
    }
	
	public ArrayList<LeaderCard> getLeaderCards(){
		return this.leaderCards;
	}
	
	
	/**
	 * @return selectRandomCards Return the cards selected for the game.
	 */
	private void selectRandomCards() throws ExceptionInInitializerError{
		
		if (this.leaderCards.size() < GlobalVariables.numOfLeaderCardsForPlayer * GlobalVariables.numberOfPlayers) {
			throw new ExceptionInInitializerError("Insufficient number of cards!");
		}
		
		Collections.shuffle(this.leaderCards);
				
		while (this.leaderCards.size() > GlobalVariables.numOfLeaderCardsForPlayer * GlobalVariables.numberOfPlayers){
			this.leaderCards.remove(this.leaderCards.size()-1);
		}
	}
}