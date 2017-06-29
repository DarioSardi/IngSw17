package it.polimi.ingsw.GC_43.model.actions;

import it.polimi.ingsw.GC_43.model.Player;

public class LeaderCardAction extends Action {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4854540790393548661L;
	private String leaderCardName;
	private int eventualChoice;

	public LeaderCardAction(String playerID, Player player) {
		super(playerID, player);
		this.setActionID(6);

	}

	public String getLeaderCardName() {
		return leaderCardName;
	}

	public void setLeaderCardName(String leaderCardName) {
		this.leaderCardName = leaderCardName;
	}

	public int getEventualChoice() {
		return eventualChoice;
	}

	public void setEventualChoice(int eventualChoice) {
		this.eventualChoice = eventualChoice;
	}
	
	
	
	

}