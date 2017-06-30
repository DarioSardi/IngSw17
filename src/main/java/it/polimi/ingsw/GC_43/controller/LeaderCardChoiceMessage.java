package it.polimi.ingsw.GC_43.controller;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.cards.LeaderCard;

public class LeaderCardChoiceMessage implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6991113773011277602L;
	private int choice;
	String playerUsername;
	ArrayList<LeaderCard> leaderCards;
	
	
	public LeaderCardChoiceMessage(ArrayList<LeaderCard> leaderCards, String playerUsername){
		this.leaderCards=leaderCards;
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


	public ArrayList<LeaderCard> getLeaderCards() {
		return leaderCards;
	}


	public void setLeaderCards(ArrayList<LeaderCard> leaderCards) {
		this.leaderCards = leaderCards;
	}
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		for (int j = 0; j < leaderCards.size(); j++) {
			sb.append("choice number " + j + ") " +leaderCards.get(j).toString()+"\n ");
		}
		return sb.toString();
	}
	

}
