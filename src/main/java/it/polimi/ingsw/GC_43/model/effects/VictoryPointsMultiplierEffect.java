package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;

public class VictoryPointsMultiplierEffect extends Effect {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2021299320955602646L;

	private float multiplierFactor;
	private String multiplierType;
	
	public VictoryPointsMultiplierEffect(float multiplierFactor, String multiplierType){
		this.multiplierFactor= multiplierFactor;
		this.multiplierType = multiplierType;
	}
	
	public String toString(){
		String toString="Player will get an amount of victory points equal to "+this.multiplierFactor+" * "+this.multiplierType;
		return toString;
	}

	private void getVictoryPoints(Player player){
		int malusVictoryPoint=Math.abs(player.getPlayerBounusMalus().getMalusOnAcquiringResources().get("victoryPoint"));
		System.out.println("Message from VictoyPointsMultiplerEffect: player malus on acquiring victory points is = "+malusVictoryPoint);
		System.out.println("multiplier type is "+this.multiplierType);
		if(this.multiplierType.equals("ventureCards"))
			player.addResource("victoryPoint", (int)this.multiplierFactor*player.getPlayerCards().getArrayVentureCards().size()-malusVictoryPoint);
		
		if(this.multiplierType.equals("buildingCards"))
			player.addResource("victoryPoint", (int)this.multiplierFactor*player.getPlayerCards().getArrayBuildingCards().size()-malusVictoryPoint);
		
		if(this.multiplierType.equals("territoryCards"))
			player.addResource("victoryPoint", (int)this.multiplierFactor*player.getPlayerCards().getArrayTerritoryCards().size()-malusVictoryPoint);
		
		if(this.multiplierType.equals("characterCards"))
			player.addResource("victoryPoint", (int)this.multiplierFactor*player.getPlayerCards().getArrayCharacterCards().size()-malusVictoryPoint);
		
		
		if(this.multiplierType.equals("militaryPoint"))
			player.addResource("victoryPoint", (int)(this.multiplierFactor*player.getPlayerResource(this.multiplierType)));
		
		System.out.println("VictoryPointsMultiplier effect ended successfully");
	}

	
		
	public void executeEffect(FamilyMember familyMember){
		System.out.println("Executing VictoryPointsMultiplierEffect Effect");

		getVictoryPoints(familyMember.getPlayer());

	}

}
