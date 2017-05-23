package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.actionSpace.TowerColors;

public class PickExtraCardFromTowers {
	private TowerColors towerColor;
	
	public PickExtraCardFromTowers(TowerColors towerColor){
		this.towerColor=towerColor;
	}
	
	public void executeEffect(FamilyMember familyMember){
		//	Controller.executeExtraAction("extraHarvestProduction");
		//  Player riferimento a controller familyMember.getPlayer().getController().doExtraAction(this.towerColor,int DiceValue)
		// Remember to ask Player if he wants to use extra servants to increase Die value	
		//TODO to implement	
			
		}
	}

