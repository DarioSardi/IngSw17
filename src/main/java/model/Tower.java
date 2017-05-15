package model;

import java.util.ArrayList;

public class Tower {
	private String towerColor;//TODO tipo string?
	private boolean towerOccupied = false;
	private ArrayList<Floor> floors;
	//DARIO generare costruttore 
	
	public String getTowerColor() {
		return towerColor;
	}
	public void setTowerColor(String towerColor) {
		this.towerColor = towerColor;
	}
	public boolean isTowerOccupied() {
		return towerOccupied;
	}
	public void setTowerOccupied(boolean towerOccupied) {
		this.towerOccupied = towerOccupied;
	}
	
	public boolean hasPlayerIn(FamilyMember f){
		return false; //DARIO compilare codice		
	}
	
	public String toString(){
		return "";		//DARIO compilare codice
	}
	
}
