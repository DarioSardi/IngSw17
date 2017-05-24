package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.actionSpace.TowerColors;

public class AdditionalDiceValueToTower {
	private TowerColors towerColor;
	private int additionalValue;
	
	public AdditionalDiceValueToTower(TowerColors towerType, int additionalValue){
		this.towerColor= towerType;
		this.additionalValue= additionalValue;
	}
	
	//TODO to understand how to reference the correct tower

}
