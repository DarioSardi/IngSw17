package model;

<<<<<<< HEAD
public class PlayerCards{
	private Array[] militaryPointsRequirements;
	private ArraList<Card> buildingCards;
	private ArraList<Card> ventureCards;
	private ArraList<Card> characterCards;
	private ArraList<Card> territoryCards;
	private ArraList<Bonus> defaultBonusHarvest;
	private ArraList<Bonus> defaultBonusBuildings;
	
	public PlayerCards (){
		//SAMUEL Capire a cosa servano defaultBonusHarvest e Buildings
	}
	
	public void harvestProduction(){
		//SAMUEL Capire a cosa serva metodo harvestProduction
	}
	
	public void buildingProduction(){
		//SAMUEL Capire a cosa serva metodo buildingProduction
	}
	
	public Array[] getMilitaryPointsRequirements(Array[] requirements){
		this.militaryPointsRequirements = requirements;
	}
	
	public void setMilitaryPointsRequirements(){
=======
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
>>>>>>> branch 'master' of https://github.com/DarioSardi/IngSw17.git
		return this.militaryPointsRequirements;
	}

	
	
}