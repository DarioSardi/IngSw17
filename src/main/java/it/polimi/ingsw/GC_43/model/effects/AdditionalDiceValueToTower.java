package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.actionSpace.TowerColors;

public class AdditionalDiceValueToTower extends Effect{
	private TowerColors towerType;
	private int additionalValue;
	
	public AdditionalDiceValueToTower(TowerColors towerType, int additionalValue){
		this.towerType= towerType;
		this.additionalValue= additionalValue;
	}
	
	public String toString(){

		String towerType = new String();
		
		if(this.towerType==TowerColors.BUILDINGS_TOWER){
			towerType="Building Tower";	
		}
		if(this.towerType==TowerColors.VENTURES_TOWER){
			towerType="Venture Tower";	
		}
		if(this.towerType==TowerColors.TERRITORIES_TOWER){
			towerType="Territory Tower";	
		}
		if(this.towerType==TowerColors.CHARACTERS_TOWER){
			towerType="Character Tower";	
		}
		String toString="Player will have an additional Dice Value on: "+towerType+ "of value: "+this.additionalValue;
		
		return toString;

	}
	
	public void executeEffect(){
	
	}
	
    public static void main(String args[]) {
        String a = "c";
        String b = "iao";
        String c = a + b;
        System.out.println(c);
    }
}
	
	//TODO to understand how to reference the correct tower


