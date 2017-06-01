package it.polimi.ingsw.GC_43.model.cards;

import javax.xml.bind.JAXBElement.GlobalScope;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actionSpace.Floor;

public class CardHandler {
	
	/**
	 * buy a card for the player,execute all istant bonus, add the card to the player hand and remove it from the tower
	 * @param p the player that want the card
	 * @param c	the card to buy
	 * @param f floor where the card is
	 * @return true if the card can be bought
	 */
	public static boolean buyCard(Floor f,FamilyMember fam, Card c){
		if(c.getCost()!=null){
			c.getCost().executeEffect(fam);
		}
		c.getInstantBonus().stream().forEach(e->e.executeEffect(fam));
		if(c.getType()=="CharacterCard"){
			c.getPermaBonus().stream().forEach(e->e.executeEffect(fam));
		}
		//DARIO far pagare la tassa
		//DARIO aspettare sam p.getPlayerCards().addCard(c);
		f.removeCard();
		
		return true; 
	}
	
	//DARIO controllo quando non ho abbastanza spazi sulla plancia!!!
	
	public static boolean checkBuy(Floor f,Player p, Card c,boolean towerTax){
		if(towerTax){p.subResource("coin", 3);}  //DARIO  modificare il 3 in una globale
		return towerTax;
		
	}
	}
	
	


