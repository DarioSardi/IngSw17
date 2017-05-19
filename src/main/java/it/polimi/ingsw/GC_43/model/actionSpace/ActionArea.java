package it.polimi.ingsw.GC_43.model.actionSpace;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;

public abstract class ActionArea {
	private ArrayList<ActionSpace> spaces;
	
	public abstract boolean check(FamilyMember f);
	public abstract int getBonusOfArea(Player p);
	public ArrayList<ActionSpace> getSpaces(){
		return spaces;
	}
}
