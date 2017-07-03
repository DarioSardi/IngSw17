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
		
		System.out.println("Player cards size are: characters' "+cSize+" buildings' "+bSize+" territories' "+tSize+" ventures' "+vSize);

		System.out.println("Card type is " +this.cardType);
		
		if(this.cardType.contains("ventureCard")){
			if(vSize<this.numberOfCards){
				checkResult=false;
			}
		}
		else if(this.cardType.contains("buildingCard")){
			if(bSize<this.numberOfCards){
				checkResult=false;
			}
		}
		else if(this.cardType.contains("territoryCard")){
			if(tSize<this.numberOfCards){
				checkResult=false;
			}
		}
		else if(this.cardType.contains("characterCard")){
			if(cSize<this.numberOfCards){
				checkResult=false;
			}
		}
		
		else if(this.cardType.contains("any")){
			if(bSize>=this.numberOfCards||cSize>=this.numberOfCards||tSize>=this.numberOfCards||vSize>=this.numberOfCards)
				checkResult=true;
		}else{
			System.out.println("Card type not found!");
			checkResult=false;
		}
		
		System.out.println("Cards Requirement : Check result = "+checkResult);
		return checkResult;
	}
	
	

}
