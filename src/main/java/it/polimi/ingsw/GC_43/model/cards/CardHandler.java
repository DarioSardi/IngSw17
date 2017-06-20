package it.polimi.ingsw.GC_43.model.cards;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actionSpace.Floor;

public class CardHandler {
	
	private CardHandler(){}
	/**
	 * buy a card for the player,execute all istant bonus, add the card to the player hand and remove it from the tower
	 * @param p the player that want the card
	 * @param c	the card to buy
	 * @param f floor where the card is
	 * @return true if the card can be bought
	 */
	public static boolean buyCard(Floor f,FamilyMember fam, Card c,boolean towerTax,boolean payedWithMilitary){
		
		if (checkBuy(f,fam.getPlayer(), c, towerTax)) {
			if (c.getCost() != null&&payedWithMilitary) {
				c.getCost().executeEffect(fam);
			}
			c.getInstantBonus().stream().forEach(e -> e.executeEffect(fam));
			if (c instanceof CharacterCard) {
				c.getPermaBonus().stream().forEach(e -> e.executeEffect(fam));
			}
			if (c instanceof BuildingCard) {
				fam.getPlayer().getPlayerCards().addBuildingCard((BuildingCard) c);
			} else if (c instanceof CharacterCard) {
				fam.getPlayer().getPlayerCards().addCharacterCard((CharacterCard) c);
			} else if (c instanceof TerritoryCard) {
					fam.getPlayer().getPlayerCards().addTerritoryCard((TerritoryCard) c);
			} else if (c instanceof VentureCard) {
				fam.getPlayer().getPlayerCards().addVentureCard((VentureCard) c);
			} else {
				return false;
			}
			f.removeCard();
			return true;
		} 
		else return false;
	}
	
	//DARIO controllo quando non ho abbastanza spazi sulla plancia!!!
	
	public static boolean checkBuy(Floor f,Player p, Card c,boolean towerTax){
		
		if ((c instanceof BuildingCard  && p.getPlayerCards().getArrayBuildingCards().size()<GlobalVariables.maxNumberPlayerCards)	||
			(c instanceof CharacterCard && p.getPlayerCards().getArrayCharacterCards().size()<GlobalVariables.maxNumberPlayerCards)	||
		    (c instanceof TerritoryCard && p.getPlayerCards().getArrayTerritoryCards().size()<GlobalVariables.maxNumberPlayerCards	&&
		       GlobalVariables.militaryPointsRequired[p.getPlayerCards().getArrayBuildingCards().size()+1]<=p.getPlayerResource("militaryPoint"))||
		   	(c instanceof VentureCard && p.getPlayerCards().getArrayVentureCards().size()<GlobalVariables.maxNumberPlayerCards))
			{
				//is all ok, do nothing
			}
		else{
			return false; //something fails return false
			}
		
		if(towerTax){p.subResource("coin", GlobalVariables.towerTax);}  //DARIO  modificare il 3 in una globale
		if(c.getCost().check(p,c.getType())){
			return true;
		}
		else{
			if(towerTax){p.addResource("coin", GlobalVariables.towerTax);}
			return false;
		}
		
		
	}
	}
	
	


