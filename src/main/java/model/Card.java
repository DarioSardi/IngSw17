package model;

import java.util.ArrayList;

public class Card {
	private String cardName;
	private int cardEra;
	private ArrayList<Resource> instantBonus;

	public Card(String cardName,int cardEra){
		this.cardName = cardName;
		this.cardEra = cardEra;
		this.instantBonus = new ArrayList<Resource>();
	}

	public String getCardName() {
		return cardName;
	}

	public int getCardEra() {
		return cardEra;
	}

	public boolean canIBuy(familyMember familiar, boolean towerTax){
		//SAMUEL da fare
		return false;
	}
	
	public boolean canIBuy(familyMember familiar, boolean towerTax){
		//SAMUEL da fare
		return false;
	}
	
	
}