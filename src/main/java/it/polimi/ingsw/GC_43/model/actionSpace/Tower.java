package it.polimi.ingsw.GC_43.model.actionSpace;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class Tower{
	private TowerColors towerColor;
	private boolean towerOccupied;	
	private ArrayList<Floor> floors;
	/**
	 * generate a tower with some empty floors
	 * @param towerColor define the tower colors
	 */
	public Tower(TowerColors towerColor,int floors) {
		this.towerColor = towerColor;	
		this.towerOccupied= false;
		
		for(int i=0;i<floors;i++){
		this.addFloor(null,0);
		}
		
	}

	// getters and setters
	public TowerColors getTowerColor() {
		return towerColor;
	}

	public ArrayList<Floor> getFloors() {
		return floors;
	}

	/**
	 * check if the tower have already the player in
	 * @param f
	 * @return true if has not the player in
	 */
	public boolean hasPlayerIn(FamilyMember f) {
		if(f.getColor()==0){return true;}
		else{for(ActionSpace floor: this.floors){
			if(f.getColor()==floor.getFamiliarIn().get(0).getColor()){return false;}
		}
		return true;}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (ActionSpace floor: this.floors) {
			sb.append(floor.toString());
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * add a floor to the tower
	 * 
	 * @param bonus bonus of the floor
	 * @param minDiceValue
	 */
	public void addFloor(Effect bonus, Integer minDiceValue) {
		this.floors.add(new Floor(bonus, this, minDiceValue));

	}
	
	public void setTowerOccupied(boolean set){
		towerOccupied=set;
	}
	
	/**
	 * check if the tower is occupied
	 */

	public boolean check(FamilyMember f) {
		return towerOccupied;
	}

	public int getBonusOfArea(Player p) {
		// DARIO come divido i bonus torre?
		return 0;
	}
	
	public void resetArea(){
		this.towerOccupied=false;
		this.floors.stream().forEach(space->space.resetSpace());
	}

}
