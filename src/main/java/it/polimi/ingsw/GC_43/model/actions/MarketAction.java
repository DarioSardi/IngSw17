package it.polimi.ingsw.GC_43.model.actions;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.Player;

public class MarketAction extends Action{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Integer> MarketChoices;

	public MarketAction(String playerID, Player player) {
		super(playerID, player);
		this.setActionID(4);
		this.MarketChoices= new ArrayList<Integer>();
	}

	public ArrayList<Integer> getMarketChoices() {
		return MarketChoices;
	}

	public void setMarketChoices(ArrayList<Integer> marketChoices) {
		MarketChoices = marketChoices;
	}
	
	
	
}
