package it.polimi.ingsw.GC_43.model.actionSpace;

import java.io.Serializable;
import java.util.ArrayList;

public class Market implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5422861341875183612L;

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
		int i=0;
		for (MarketActionSpace marketActionSpace: this.getMarketActionSpaces()) {
			sb.append("MARKET N°"+i);
			sb.append(marketActionSpace.toString());
			sb.append("\n");
			i++;
		}
		return sb.toString();
	}
	
	public  void resetArea(){
		this.marketActionSpaces.stream().forEach(space->space.resetSpace());
	}
}
