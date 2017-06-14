package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.Player;

public class CoinsMultiplierEffect extends Effect {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int multiplierFactor;
	private String multiplierType;
	
	public CoinsMultiplierEffect(int multiplierFactor, String multiplierType){
		this.multiplierFactor= multiplierFactor;
		this.multiplierType= multiplierType;
	}

	public String toString(){
		String toString="Player will receive an amount of victory points equal to "+this.multiplierFactor+" * "+this.multiplierType;
		return toString;
	}
	
	private void getVictoryPoints(Player player){
		
		if(this.multiplierType== "buildingCards");
			player.addResource("coin", this.multiplierFactor*player.getPlayerCards().getArrayBuildingCards().size());
		
		if(this.multiplierType== "territoryCards");
			player.addResource("coin", this.multiplierFactor*player.getPlayerCards().getArrayTerritoryCards().size());

	}
}
