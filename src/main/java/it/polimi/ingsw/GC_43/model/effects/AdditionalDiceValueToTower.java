package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.actionSpace.TowerColors;

public class AdditionalDiceValueToTower extends Effect{
	private TowerColors towerColor;
	private int additionalValue;
	
	public AdditionalDiceValueToTower(TowerColors towerType, int additionalValue){
		this.towerColor= towerType;
		this.additionalValue= additionalValue;
	}
	
	public void executeEffect(){
	
	}
	
	//TODO to understand how to reference the correct tower

}
