package it.polimi.ingsw.GC_43.model.actionSpace;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class Tower{
	private TowerColors towerColor;
	private boolean towerOccupied;	
	private ArrayList<Floor> floors;
	private int floorsNumber;
	/**
	 * generate a tower with some empty floors
	 * @param towerColor define the tower colors
	 */
	public Tower(TowerColors towerColor,int floorsNumber) {
		this.towerColor = towerColor;	
		this.towerOccupied= false;
		this.floorsNumber=floorsNumber;
		this.floors=new ArrayList<Floor>();
		this.floorsNumber=0;
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
		else{for(Floor floor: this.floors){
			if(f.getColor()==floor.getFamiliarIn().get(0).getColor()){return false;}
		}
		return true;}
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(this.getTowerColor().toString()+" info:\n");
		for (Floor floor: this.floors) {
			sb.append(floor.toString());
			sb.append("\n");
		}
		return sb.toString();
		
	}
	
	public String toStringAll(){
		StringBuilder sb = new StringBuilder();
		int floorNumber=0;
		sb.append(this.getTowerColor().toString()+" info:\n");
		sb.append("number of floors on this tower: "+this.floorsNumber);
		sb.append("\n");
		sb.append("did you have to pay the tax to enter here? "+this.towerOccupied);
		sb.append("\n");
		sb.append("-----------------------\n");
		for (Floor floor: this.floors) {
			sb.append("FLOOR NUMBER: "+floorNumber+"\n");
			sb.append(floor.toStringAll());
			sb.append("\n");
			floorNumber++;
		}
		return sb.toString();
	}

	/**
	 * add a floor to the tower with a bonus
	 * @param bonus bonus of the floor
	 * @param minDiceValue
	 */
	public void addFloor(Effect bonus, Integer minDiceValue) {
		this.floors.add(new Floor(bonus, this, minDiceValue));
		this.floorsNumber=this.floorsNumber+1;
		//DARIO card? no?
	}
	
	public void addFloor(Integer minDiceValue) {
		this.floors.add(new Floor(null, this, minDiceValue));
		this.floorsNumber=this.floorsNumber+1;
		//DARIO card? no?
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
