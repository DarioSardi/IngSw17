package model;

import java.util.ArrayList;

import decorator3.Resource;

public class Player {
	private String playerName;
	private ArrayList<Resource> playerResouces;
	private int victoryPoints;
	private int militaryPoints;
	private int faithPoints;
	private ArrayList<boolean> excommunications;
	private PlayerCards playerCards;
	
	public PlayerCards getPlayerCards() {
		return playerCards;
	}

	public void setPlayerCards(PlayerCards playerCards) {
		this.playerCards = playerCards;
	}

	public Player(String name){
		this.playerName = name;
	}
	
	public void setPlayerResources(){
		//SAMUEL setPlayerResources
	}
	
	public int getVictoryPoints(){
		return this.victoryPoints;
	}
	
	public void setVictoryPoints(int points){
		this.victoryPoints;
	}
	
	public int getMilitaryPoints(){
		return this.militaryPoints;
	}
	
	public void setMilitaryPoints(int points){
		this.militaryPoints;
	}
	
	public int getFaithPoints(){
		return this.faithPoints;
	}
	
	public void setFaithPoints(int points){
		this.faithPoints;
	}	
	
	public ArrayList<boolean> getExcommunications(){
		return this.excommunications;
	}
	
	public void setExcommunications(ArrayList<boolean> excommunications){
		this.
	}
	
	public void printFamiliars(){
		//SAMUEL da fare printFamiliars
	}
	
	public void toString(){
		//SAMUEL
	}
}
