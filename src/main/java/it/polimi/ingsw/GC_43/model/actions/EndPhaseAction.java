package it.polimi.ingsw.GC_43.model.actions;

import it.polimi.ingsw.GC_43.model.Player;

public class EndPhaseAction extends Action{

	public EndPhaseAction(String playerID, Player player) {
		super(playerID, player);
		this.setActionID(-1);
		
	}

}
