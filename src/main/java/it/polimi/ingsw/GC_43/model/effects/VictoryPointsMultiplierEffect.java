package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;

public class VictoryPointsMultiplierEffect extends Effect {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private float multiplierFactor;
	private String multiplierType;
	
	public VictoryPointsMultiplierEffect(float multiplierFactor, String multiplierType){
		this.multiplierFactor= multiplierFactor;
		this.multiplierType = multiplierType;
	}
	
	public String toString(){
		String toString="Player will get an amount of victory points equal to "+this.multiplierFactor+" "+this.multiplierType;
		return toString;
	}

	private void getVictoryPoints(Player player){
		int malusVictoryPoint=Math.abs(player.getPlayerBounusMalus().getMalusOnAcquiringResources().get("victoryPoint"));
		if(this.multiplierType== "ventureCards")
			player.addResource("victoryPoint", (int)this.multiplierFactor*player.getPlayerCards().getArrayVentureCards().size()-malusVictoryPoint);
		
		if(this.multiplierType== "buildingCards");
			player.addResource("victoryPoint", (int)this.multiplierFactor*player.getPlayerCards().getArrayBuildingCards().size()-malusVictoryPoint);
		
		if(this.multiplierType== "territoryCards");
			player.addResource("victoryPoint", (int)this.multiplierFactor*player.getPlayerCards().getArrayTerritoryCards().size()-malusVictoryPoint);
		
		if(this.multiplierType== "characterCards");
			player.addResource("victoryPoint", (int)this.multiplierFactor*player.getPlayerCards().getArrayCharacterCards().size()-malusVictoryPoint);
		
		
		if(this.multiplierType== "militaryPoint");
			player.addResource("victoryPoint", (int)(this.multiplierFactor*player.getPlayerResource(this.multiplierType)));
		}

	
		
	public void executeEffect(FamilyMember familyMember){
		getVictoryPoints(familyMember.getPlayer());

	}

}
