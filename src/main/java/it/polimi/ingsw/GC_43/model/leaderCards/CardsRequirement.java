package it.polimi.ingsw.GC_43.model.leaderCards;
import java.io.Serializable;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class CardsRequirement extends Effect {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2055849720367954231L;
	private String cardType;
	private int numberOfCards;
	
	
	public CardsRequirement(String cardType, int numberOfCards){
		this.cardType=cardType;
		this.numberOfCards= numberOfCards;
		
	}
	
	public boolean check(FamilyMember familyMember){
		return check(familyMember.getPlayer());
	}
	
	public String toString() {
		String toString = this.numberOfCards+" "+this.cardType+" are required to play this card";
		return toString;
	}
	
	public boolean check(Player player){
		System.out.println("Executing check CardsRequirement");

		boolean checkResult=true;
		int cSize=player.getPlayerCards().getArrayCharacterCards().size();
		int bSize=player.getPlayerCards().getArrayBuildingCards().size();
		int tSize=player.getPlayerCards().getArrayTerritoryCards().size();
		int vSize=player.getPlayerCards().getArrayVentureCards().size();


		if(cardType.equals("ventureCard"))
			if(vSize<=this.numberOfCards)
				checkResult=false;
		if(cardType.equals("buildingCard"))
			if(bSize<=this.numberOfCards)
				checkResult=false;
		if(cardType.equals("territoryCard"))
			if(tSize<=this.numberOfCards)
				checkResult=false;
		if(cardType.equals("characterCard"))
			if(cSize<=this.numberOfCards)
				checkResult=false;
		
		if(cardType.equals("any")){
			if(bSize>=this.numberOfCards||cSize>=this.numberOfCards||tSize>=this.numberOfCards||vSize>=this.numberOfCards)
				checkResult=true;
		}else{
			checkResult=false;
		}
		
		
		return checkResult;
	}
	
	

}
