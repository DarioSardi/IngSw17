package it.polimi.ingsw.GC_43.model;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.cards.BuildingCard;
import it.polimi.ingsw.GC_43.model.cards.VentureCard;

public class FinalCalculationVictoryPoints {
	private ArrayList<Integer> victoryPointsForTerritoryCards;
	private ArrayList<Integer> victoryPointsForCharacterCards;
	
	
	public FinalCalculationVictoryPoints() {
		this.victoryPointsForCharacterCards=new ArrayList<Integer>();
		this.victoryPointsForTerritoryCards=new ArrayList<Integer>();
		
		this.victoryPointsForCharacterCards.add(1);
		this.victoryPointsForCharacterCards.add(3);
		this.victoryPointsForCharacterCards.add(6);
		this.victoryPointsForCharacterCards.add(10);
		this.victoryPointsForCharacterCards.add(15);
		this.victoryPointsForCharacterCards.add(21);
		
		this.victoryPointsForTerritoryCards.add(0);
		this.victoryPointsForTerritoryCards.add(0);
		this.victoryPointsForTerritoryCards.add(1);
		this.victoryPointsForTerritoryCards.add(4);
		this.victoryPointsForTerritoryCards.add(10);
		this.victoryPointsForTerritoryCards.add(20);
	}
	
	public void calculateFinalVictoryPoints(ArrayList<Player> players){
		for(Player player: players){
			
			//Malus on victoryPoints
			int playerVictoryPoints= player.getPlayerResource("victoryPoint");
			int victoryPointsToSubtractIfMalus= calculateVictoryPointsFactor(playerVictoryPoints);
			if(player.getPlayerBounusMalus().getMalusOnFinalVictoryPoints().get("victoryPoitns"))
				player.subResource("victoryPoint", victoryPointsToSubtractIfMalus);

			//Malus on militaryPoints	
			if(player.getPlayerBounusMalus().getMalusOnFinalVictoryPoints().get("militaryPoints"))
					player.subResource("victoryPoints", player.getPlayerResource("militaryPoints"));
		

			//Malus on resources
			int totalResourcesOfPlayer;
			totalResourcesOfPlayer= totalResources(player);
			if(player.getPlayerBounusMalus().getMalusOnFinalVictoryPoints().get("resource"))
				player.subResource("victoryPoints", totalResourcesOfPlayer);
			
			//Bonus on resources
			player.addResource("victoryPoint", calculateVictoryPointsFactor(totalResourcesOfPlayer));
			
			//Bonus on Cards
			//Venture cards has just one permEffect so I consider just element 0;
			if(!player.getPlayerBounusMalus().getMalusOnFinalVictoryPoints().get("ventureCard")){
				for(VentureCard ventureCard: player.getPlayerCards().getArrayVentureCards())
					ventureCard.getPermaBonus().get(0).executeEffect();
			}
					
				
			if(!player.getPlayerBounusMalus().getMalusOnFinalVictoryPoints().get("characterCard"))
				player.addResource("victoryPoint", this.victoryPointsForCharacterCards.get(player.getPlayerCards().getArrayCharacterCards().size()));
		
			if(!player.getPlayerBounusMalus().getMalusOnFinalVictoryPoints().get("territoryCard"))
				player.addResource("victoryPoint", this.victoryPointsForTerritoryCards.get(player.getPlayerCards().getArrayTerritoryCards().size()));
			
			if(player.getPlayerBounusMalus().getMalusOnFinalVictoryPoints().get("buildingCardCost")){
				int victoryPointsToSubtract= calculateBuildindCardsCost(player);
				player.subResource("victoryPoint", victoryPointsToSubtract);
			}
		}
	}
	//TODO to finish calculateBuildindCardsCost
	public int calculateBuildindCardsCost(Player player){
		int victoryPointsToSubtract=0;
		for(BuildingCard buildingCard: player.getPlayerCards().getArrayBuildingCards()){
			for(Resource resource: buildingCard.getCost().getCosts())
				if(resource.getResourceType().equals("wood")|| resource.getResourceType().equals("stone"))
					victoryPointsToSubtract= victoryPointsToSubtract+resource.getValue();
		}
		return victoryPointsToSubtract;
	}
	
	
//TODO more compact form da inserire in caso già sopra, tipo un /5 e castare a intero	
	public int calculateVictoryPointsFactor(int playerResources){
		
		int victoryPointsToSubtract=0;
		while(playerResources>=5){
			victoryPointsToSubtract++;
			playerResources= playerResources-5;
		}
		return victoryPointsToSubtract;
		
	}
	
	public int totalResources(Player player){
		int totalResources=0;
		totalResources= totalResources+player.getPlayerResource("wood");
		totalResources= totalResources+player.getPlayerResource("stone");
		totalResources= totalResources+player.getPlayerResource("servant");
		totalResources= totalResources+player.getPlayerResource("coin");
		
		return totalResources;
		
	}
	
	//TODO to be finished with militaryPoints victory pints (5 for forst 2 for second), compara player e stabilisci vincitore, se pari merito vedi turno
/*	public decideForWinner(ArrayList<Player> players){
		calculateFinalVictoyPoints(ArrayList<Player> players);
		
		
		
	}
*/
}
