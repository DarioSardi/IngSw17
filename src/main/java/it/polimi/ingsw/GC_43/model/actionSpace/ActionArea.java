package it.polimi.ingsw.GC_43.model.actionSpace;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;

public abstract class ActionArea implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1635419021981223653L;
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
