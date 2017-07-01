package it.polimi.ingsw.GC_43.model.actions;

import it.polimi.ingsw.GC_43.model.Player;

public class EndPhaseAction extends Action{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6981915180734333445L;

	public EndPhaseAction(String playerID, Player player) {
		super(playerID, player);
		this.setActionID(-1);
		
	}

}
