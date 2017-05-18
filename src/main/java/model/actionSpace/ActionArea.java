package model.actionSpace;

import java.util.ArrayList;


import model.FamilyMember;
import model.Player;

public abstract class ActionArea {
	private ArrayList<ActionSpace> spaces;
	
	public abstract boolean check(FamilyMember f);
	public abstract int getBonusOfArea(Player p);
	public ArrayList<ActionSpace> getSpaces(){
		return spaces;
	}
}
