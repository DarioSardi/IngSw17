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

	public ArrayList<Resource> getInstantBonus() {
		return instantBonus;
	}

	public void generateInstantBonus(Resource resource) {
		this.instantBonus.add(resource);
	}
	
	public void cardBought(Player p){
		int i=0;
		while (i<this.instantBonus.size()){
			p.addRisorsa(this.instantBonus.get(i).getTipoRisorsa(), this.instantBonus.get(i).getValue());
			i++;
		}
	}
	
	
}