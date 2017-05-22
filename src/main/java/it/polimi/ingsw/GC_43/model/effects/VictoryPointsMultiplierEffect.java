package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;

public class VictoryPointsMultiplierEffect {
	private int multiplierFactor;
	private String cardType;
	
	public VictoryPointsMultiplierEffect(int multiplierFactor, String cardType){
		this.multiplierFactor= multiplierFactor;
		this.cardType = cardType;
	}
//FRANCESCO to finish with samuel late modifications	
	private void getVictoryPoints(Player player){
		if(this.cardType== "ventureCards")
			player.setPlayerResource("victoryPoint", player.getPlayerResource("victoryPoint")+this.multiplierFactor*player.getPlayerCards().getVentureCards().size());
		}
		if(this.cardType== "buildingCards");
			player.setPlayerResource("victoryPoint", player.getPlayerResource("victoryPoint")+this.multiplierFactor*player.getPlayerCards().getBuildingCards().size());
		}
		if(this.cardType== "territoryCards");
			player.setPlayerResource("victoryPoint", player.getPlayerResource("victoryPoint")+this.multiplierFactor*player.getPlayerCards().getTerritoryCards().size());
		}
		if(this.cardType== "ventureCards");
			player.setPlayerResource("victoryPoint", player.getPlayerResource("victoryPoint")+this.multiplierFactor*player.getPlayerCards().getCharacterCards().size());
		}
		
	public void executeEffect(FamilyMember familyMember){
		getVictoryPoints(familyMember.getPlayer());

	}

}
