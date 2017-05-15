package model;

import java.util.ArrayList;

import decorator3.Resource;

public class Player {
	private String playerName;
	private ArrayList<boolean> excommunications;
	
	public Player(String name){
		this.playerName = name;
	}
	
	public ArrayList<boolean> getExcommunications(){
		return this.excommunications;
	}
	
	public void setExcommunications(ArrayList<boolean> excommunications){
		this.excommunications = excommunications;
	}
	
	public void printFamiliars(){
		//SAMUEL da fare printFamiliars
	}
	
	public void toString(){
		//SAMUEL
	}
}
