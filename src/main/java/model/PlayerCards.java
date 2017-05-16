package model;

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
		return this.militaryPointsRequirements;
	}

	
	
}