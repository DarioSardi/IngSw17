package it.polimi.ingsw.GC_43.controller;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.PlayerPersonalBonus;

public class DefaultBonusChoiceMessage implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2039103900053881356L;
	private Integer choice;
	String playerUsername;
	ArrayList<PlayerPersonalBonus> advDefBonus;
	
	
	public DefaultBonusChoiceMessage(ArrayList<PlayerPersonalBonus> advDefBonus, String playerUsername){
		this.advDefBonus=advDefBonus;
		this.playerUsername=playerUsername;
		
	}


	public int getChoice() {
		return choice;
	}


	public void setChoice(Integer choice) {
		this.choice = choice;
	}


	public String getPlayerUsername() {
		return playerUsername;
	}


	public void setPlayerUsername(String playerUsername) {
		this.playerUsername = playerUsername;
	}


	public ArrayList<PlayerPersonalBonus> getAdvDefBonus() {
		return advDefBonus;
	}


	public void setAdvDefBonus(ArrayList<PlayerPersonalBonus> advDefBonus) {
		this.advDefBonus = advDefBonus;
	}
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		for (int j = 0; j < advDefBonus.size(); j++) {
			sb.append("choice number " + j + ") " +advDefBonus.get(j).toString()+"\n ");
		}
		return sb.toString();
	}
	
	

}
