package it.polimi.ingsw.GC_43.model.actionSpace;

import java.util.ArrayList;

public class Market {
	
	
	
	public Market(ArrayList<MarketActionSpace> marketActionSpaces) {
		super();
		this.marketActionSpaces = marketActionSpaces;
	}

	private ArrayList<MarketActionSpace> marketActionSpaces;

	public ArrayList<MarketActionSpace> getMarketActionSpaces() {
		return marketActionSpaces;
	}

	public void setMarketActionSpaces(ArrayList<MarketActionSpace> marketActionSpaces) {
		this.marketActionSpaces = marketActionSpaces;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (MarketActionSpace marketActionSpace: this.getMarketActionSpaces()) {
			sb.append(marketActionSpace.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
	
}
