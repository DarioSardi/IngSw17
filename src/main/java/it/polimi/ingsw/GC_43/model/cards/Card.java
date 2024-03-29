package it.polimi.ingsw.GC_43.model.cards;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.effects.CostEffect;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public abstract class Card implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3230715478707094192L;
	private String cardName;
	private int cardPeriod;
	private CostEffect cost;
	private String cardIcon;
	private ArrayList<Effect> instantBonus;
	private ArrayList<Effect> permaBonus;

	public Card(String cardName, int cardEra, CostEffect cost, ArrayList<Effect> instantBonus,
			ArrayList<Effect> permaBonus,String cardIcon) {
		super();
		this.cardName = cardName;
		this.cardPeriod = cardEra;
		this.cost = cost;
		this.instantBonus = instantBonus;
		this.permaBonus = permaBonus;
		this.cardIcon=cardIcon;
	}
	
	public abstract String getType();
	
	public String getCardName() {
		return this.cardName;
	}

	public int getCardEra() {
		return this.cardPeriod;
	}

	public CostEffect getCost() {
		return this.cost;
	}

	public ArrayList<Effect> getInstantBonus() {
		return this.instantBonus;
	}

	public ArrayList<Effect> getPermaBonus() {
		return this.permaBonus;
	}
	
	public String getCardIcon(){
		return this.cardIcon;
	}
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("\n......................\n");
		sb.append("card name: "+this.getCardName()+"\n");
		sb.append("card type: "+this.getType()+"\n");
		
		sb.append("era: "+ this.getCardEra()+"\n");
		//cost
		if(this.getCost()!=null){
			//sb.append("card cost:\n");
			sb.append(this.getCost().toString());
		}
		else{sb.append("this card is cost free!\n");}
		//Instant bonus
		if(this.getInstantBonus().size()>0){
			sb.append("\n");
			sb.append("card rapid bonus:\n");
			this.getInstantBonus().stream().forEach(e->sb.append(e.toString()));
			sb.append("\n");
		}
		else{sb.append("no rapid bonus!\n");}
		//Permanent bonus
		if(this.getPermaBonus().size()>0){
			sb.append("\n");
			sb.append("card permanent bonus:\n");
			this.getPermaBonus().stream().forEach(e->sb.append(e.toString()));
			sb.append("\n");
		}
		else{sb.append("no permanent bonus!\n");}
		sb.append("......................\n");
		return sb.toString();
	}
}