package it.polimi.ingsw.GC_43.model.cards;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Resource;

public class Card {
	private String cardName;
	private int cardEra;
	private ArrayList<Resource> instantBonus;  //DARIO Pensare alla gestione bonus

	public Card(String cardName,int cardEra){
		this.cardName = cardName;
		this.cardEra = cardEra;
		this.instantBonus = new ArrayList<Resource>(); //DARIO Pensare alla gestione bonus
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