package it.polimi.ingsw.GC_43.model.actionSpace;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;

public abstract class ActionArea {
	private ArrayList<ActionSpace> spaces=new ArrayList<>();
	
	public abstract boolean check(FamilyMember f);
	public abstract int getBonusOfArea(Player p);
	
	public  void resetArea(){
		this.getSpaces().stream().forEach(space->space.resetSpace());
	}
	
	public ArrayList<ActionSpace> getSpaces(){
		return spaces;
	}
	
	
}
