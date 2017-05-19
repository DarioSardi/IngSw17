package it.polimi.ingsw.GC_43.model;

import java.util.ArrayList;

public class Card {
	private String cardName;
	private int cardEra;
	private ArrayList<Resource> instantBonus;  //SAMUEL Pensare alla gestione bonus

	public Card(String cardName,int cardEra){
		this.cardName = cardName;
		this.cardEra = cardEra;
		this.instantBonus = new ArrayList<Resource>(); //SAMUEL Pensare alla gestione bonus
	}

	public String getCardName() {
		return cardName;
	}

	public int getCardEra() {
		return cardEra;
	}

	public boolean canIBuy(FamilyMember familiar, boolean towerTax){
		//SAMUEL da fare
		return false;
	}
	
	public boolean Buy(FamilyMember familiar, boolean towerTax){
		//SAMUEL da fare
		return false;
	}
	
	
}