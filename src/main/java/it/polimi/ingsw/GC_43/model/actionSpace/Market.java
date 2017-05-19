package it.polimi.ingsw.GC_43.model.actionSpace;

import java.util.ArrayList;

public class Market {
//DARIO generare costruttore
	private ArrayList<MarketActionSpace> marketActionSpaces;

	public ArrayList<MarketActionSpace> getMarketActionSpaces() {
		return marketActionSpaces; //DARIO ritorno tutto l'array?
	}

	public void setMarketActionSpaces(ArrayList<MarketActionSpace> marketActionSpaces) {
		this.marketActionSpaces = marketActionSpaces;
	}
	
	public String toString(){
		return "";		//DARIO compilare codice
	}
	
}
