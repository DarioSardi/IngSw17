package it.polimi.ingsw.GC_43.model.actions;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.Player;

public class MarketAction extends Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7726659991783448046L;

	
	private ArrayList<Integer> MarketChoices;
	private int marketActionSpaceSelected;

	public MarketAction(String playerID, Player player) {
		super(playerID, player);
		this.setActionID(4);
		this.MarketChoices = new ArrayList<Integer>();
		this.marketActionSpaceSelected=0;
	}

	public ArrayList<Integer> getMarketChoices() {
		return MarketChoices;
	}

	public void setMarketChoices(ArrayList<Integer> marketChoices) {
		MarketChoices = marketChoices;
	}
	public String toString(){
		return super.toString()+"Action performed: Market Action\n";
	}

	public int getMarketActionSpaceSelected() {
		return marketActionSpaceSelected;
	}

	public void setMarketActionSpaceSelected(int marketActionSpaceSelected) {
		this.marketActionSpaceSelected = marketActionSpaceSelected;
	}
	
	
}
