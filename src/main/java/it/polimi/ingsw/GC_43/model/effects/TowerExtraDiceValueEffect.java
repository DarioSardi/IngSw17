package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.FamilyMember;

public class TowerExtraDiceValueEffect extends Effect {
	private String towerType;
	private int extraValue;
	
	public TowerExtraDiceValueEffect(String towerType,int extraValue) {
		this.towerType=towerType;
		this.extraValue=extraValue;
	}
	
	
	public void executeEffect(FamilyMember familyMember){
		if(towerType.equals("ventureTower")){
			int oldValue=familyMember.getPlayer().getPlayerBounusMalus().getBonusDiceVentureTower();
			familyMember.getPlayer().getPlayerBounusMalus().setBonusDiceVentureTower(oldValue+this.extraValue);
		}
		if(towerType.equals("territoryTower")){
			int oldValue=familyMember.getPlayer().getPlayerBounusMalus().getBonusDiceTerritoryTower();
			familyMember.getPlayer().getPlayerBounusMalus().setBonusDiceTerritoryTower(oldValue+this.extraValue);
		}
		if(towerType.equals("buildingTower")){
			int oldValue=familyMember.getPlayer().getPlayerBounusMalus().getBonusDiceBuildingTower();
			familyMember.getPlayer().getPlayerBounusMalus().setBonusDiceBuildingTower(oldValue+this.extraValue);
		}
		if(towerType.equals("characterTower")){
			int oldValue=familyMember.getPlayer().getPlayerBounusMalus().getBonusDiceCharacterTower();
			familyMember.getPlayer().getPlayerBounusMalus().setBonusDiceCharacterTower(oldValue+this.extraValue);
		}
	}

}
