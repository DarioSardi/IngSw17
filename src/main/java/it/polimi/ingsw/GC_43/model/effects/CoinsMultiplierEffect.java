package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;

public class CoinsMultiplierEffect extends Effect {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5652201306557534096L;
	
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
		System.out.println("Message from executing CoinMultiplierEffect: Adding "+this.multiplierFactor+" * coin for each "+ this.multiplierType);
		if(this.multiplierType.equals("buildingCards"))
			player.addResource("coin", this.multiplierFactor*player.getPlayerCards().getArrayBuildingCards().size()-Math.abs(player.getPlayerBounusMalus().getMalusOnAcquiringResources().get("coin")));
		
		if(this.multiplierType.equals("territoryCards"))
			player.addResource("coin", this.multiplierFactor*player.getPlayerCards().getArrayTerritoryCards().size()-Math.abs(player.getPlayerBounusMalus().getMalusOnAcquiringResources().get("coin")));
		
		System.out.println("CoinsMultiplier effect ended successfully");
	}
	
	public void executeEffect(FamilyMember familyMember){
		System.out.println("Executing CoinsMultiplierEffect Effect");
		getVictoryPoints(familyMember.getPlayer());
	}
}
