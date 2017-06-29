package it.polimi.ingsw.GC_43.controller;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.PlayerPersonalBonus;

public class DefaultBonusChoiceMessage implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2039103900053881356L;
	private int choice;
	String playerUsername;
	ArrayList<PlayerPersonalBonus> advDefBonus;
	
	
	public DefaultBonusChoiceMessage(ArrayList<PlayerPersonalBonus> advDefBonus, String playerUsername){
		this.advDefBonus=advDefBonus;
		this.playerUsername=playerUsername;
		
	}


	public int getChoice() {
		return choice;
	}


	public void setChoice(int choice) {
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
	
	
	
	

}
