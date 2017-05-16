package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PlayerCards{
	private Array[] militaryPointsRequirements;
	private ArrayList<Card> buildingCards;
	private ArrayList<Card> ventureCards;
	private ArrayList<Card> characterCards;
	private ArrayList<Card> territoryCards;
	private ArrayList<Bonus> defaultBonusHarvest;
	private ArrayList<Bonus> defaultBonusBuildings;
	
	public PlayerCards (){
		//SAMUEL Capire a cosa servano defaultBonusHarvest e Buildings
	}
	
	public void harvestProduction(){
		//SAMUEL Capire a cosa serva metodo harvestProduction
	}
	
	public void buildingProduction(){
		//SAMUEL Capire a cosa serva metodo buildingProduction
	}
	
	public void setMilitaryPointsRequirements(Array[] requirements){
		this.militaryPointsRequirements = requirements;
	}
	
	public Array[] getMilitaryPointsRequirements(){
		return this.militaryPointsRequirements;
	}

	
	
}