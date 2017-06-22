package it.polimi.ingsw.GC_43.model;

import java.util.ArrayList;
import java.util.Arrays;

import it.polimi.ingsw.GC_43.model.cards.BuildingCard;
import it.polimi.ingsw.GC_43.model.cards.VentureCard;
import it.polimi.ingsw.GC_43.model.resources.Resource;

public class FinalCalculationVictoryPoints{

	
	
	public FinalCalculationVictoryPoints() {

	}
	
//@require playerList in order of turn 
	public Player decideForWinner(ArrayList<Player> players){
		calculateFinalVictoryPoints(players);
		assignVictoryPointsForMilitaryPower(players,"militaryPoint");
	
		
		int firstMaxVictoryPoint=0;
		int secondMaxVictoryPoint=0;
		int positionMax1=0;
		int positionMax2=0;
		
		ArrayList<Integer> victoryPointsPlayers= new ArrayList();
		for(Player player:players)
			victoryPointsPlayers.add(player.getPlayerResource("victoryPoint"));
		
		positionMax1=maximumPositionCalculator(victoryPointsPlayers);
		firstMaxVictoryPoint=victoryPointsPlayers.get(positionMax1);

		//TODO to finish, look for player with maximum victory points
		return players.get(positionMax1);
		
		
	}

	public void calculateFinalVictoryPoints(ArrayList<Player> players){
		for(Player player: players){
			
			//Malus on victoryPoints
			int playerVictoryPoints= player.getPlayerResource("victoryPoint");
			int victoryPointsToSubtractIfMalus= calculateVictoryPointsFactor(playerVictoryPoints);
			if(player.getPlayerBounusMalus().getMalusOnFinalVictoryPoints().get("victoryPoint"))
				player.subResource("victoryPoint", victoryPointsToSubtractIfMalus);

			//Malus on militaryPoints	
			if(player.getPlayerBounusMalus().getMalusOnFinalVictoryPoints().get("militaryPoints"))
					player.subResource("victoryPoint", player.getPlayerResource("militaryPoints"));
		

			//Malus on resources
			int totalResourcesOfPlayer;
			totalResourcesOfPlayer= totalResources(player);
			if(player.getPlayerBounusMalus().getMalusOnFinalVictoryPoints().get("resource"))
				player.subResource("victoryPoint", totalResourcesOfPlayer);
			
			//Bonus on resources
			player.addResource("victoryPoint", calculateVictoryPointsFactor(totalResourcesOfPlayer));
			
			//Bonus on Cards
			//Venture cards has just one permEffect so I consider just element 0;
			if(!player.getPlayerBounusMalus().getMalusOnFinalVictoryPoints().get("ventureCard")){
				for(VentureCard ventureCard: player.getPlayerCards().getArrayVentureCards())
					
					//assigned a random familyMember, doesn't matter
					ventureCard.getPermaBonus().get(0).executeEffect(player.getFamilyMember(1));
			}
					
				
			if(!player.getPlayerBounusMalus().getMalusOnFinalVictoryPoints().get("characterCard"))
				player.addResource("victoryPoint", GlobalVariables.endCharacterVictoryPoints[player.getPlayerCards().getArrayCharacterCards().size()]);
		
			if(!player.getPlayerBounusMalus().getMalusOnFinalVictoryPoints().get("territoryCard"))
				player.addResource("victoryPoint", GlobalVariables.endTerritoryVictoryPoints[player.getPlayerCards().getArrayTerritoryCards().size()]);
			
			if(player.getPlayerBounusMalus().getMalusOnFinalVictoryPoints().get("buildingCardCost")){
				int victoryPointsToSubtract= calculateBuildindCardsCost(player);
				player.subResource("victoryPoint", victoryPointsToSubtract);
			}
		}
	}
	public int calculateBuildindCardsCost(Player player){
		int victoryPointsToSubtract=0;
		for(BuildingCard buildingCard: player.getPlayerCards().getArrayBuildingCards()){
			for(Resource resource: buildingCard.getCost().getCosts())
				if(resource.getResourceType().equals("wood")|| resource.getResourceType().equals("stone"))
					victoryPointsToSubtract= victoryPointsToSubtract+resource.getValue();
		}
		return victoryPointsToSubtract;
	}
	
	
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

	private int maximumPositionCalculator(ArrayList <Integer> numbers){
		int max=-1;
		int i=0;
		int positionInArray=0;
		while(i<numbers.size()){
			if(numbers.get(i)>max){
				max=numbers.get(i);
				positionInArray=i;
			}
		}
		return positionInArray;
	}
	

//TODO Works, think to render it more dynamical
	private void assignVictoryPointsForMilitaryPower(ArrayList<Player> players,String resource) {
		ArrayList<Integer> arr= new ArrayList<Integer>();
		for(Player player:players)
			arr.add(player.getPlayerResource(resource));
			
		int posMax1=maximumPositionCalculator(arr);
		int maximum1=players.get(posMax1).getPlayerResource(resource);
		arr.remove(posMax1);
		
		int posMax2=maximumPositionCalculator(arr);
		int maximum2=players.get(posMax2).getPlayerResource(resource);
		arr.remove(posMax2);
		
		if(arr.isEmpty()){
			if(maximum1==maximum2){
				players.get(posMax1).addResource("victoryPoint", GlobalVariables.victoryPointsFirstMilitaryPower);
				players.get(posMax2).addResource("victoryPoint", GlobalVariables.victoryPointsFirstMilitaryPower);
			}
			else{
				players.get(posMax1).addResource("victoryPoint", GlobalVariables.victoryPointsFirstMilitaryPower);
				players.get(posMax2).addResource("victoryPoint", GlobalVariables.victoryPointsSecondMilitaryPower);

			}
		}
		else{
			int posMax3=maximumPositionCalculator(arr);
			int maximum3=players.get(posMax3).getPlayerResource(resource);
			
			if(maximum1==maximum2){
				players.get(posMax1).addResource("victoryPoint", GlobalVariables.victoryPointsFirstMilitaryPower);
				players.get(posMax2).addResource("victoryPoint", GlobalVariables.victoryPointsFirstMilitaryPower);
			}
			else if(maximum2==maximum3){
				players.get(posMax1).addResource("victoryPoint", GlobalVariables.victoryPointsFirstMilitaryPower);
				players.get(posMax2).addResource("victoryPoint", GlobalVariables.victoryPointsSecondMilitaryPower);
				players.get(posMax2).addResource("victoryPoint", GlobalVariables.victoryPointsSecondMilitaryPower);

			}
			else{
				players.get(posMax1).addResource("victoryPoint", GlobalVariables.victoryPointsFirstMilitaryPower);
				players.get(posMax2).addResource("victoryPoint", GlobalVariables.victoryPointsSecondMilitaryPower);
			}
		
		}
	}	


}
