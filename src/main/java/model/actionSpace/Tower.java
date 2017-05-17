package model.actionSpace;

import java.util.ArrayList;
import java.util.stream.IntStream;

import model.Bonus;
import model.FamilyMember;

public class Tower {
	private String towerColor;//TODO tipo enum!da camabiare! (TERRITORY_TOWER)
	private boolean towerOccupied;
	private ArrayList<Floor> floors;
	
	/**
	 * generate a empty tower 
	 * @param towerColor define the tower colors
	 */
	public Tower(String towerColor) {
		super();
		this.towerColor = towerColor;	
		this.towerOccupied= false;
	}

	// getters and setters
	public String getTowerColor() {
		return towerColor;
	}

	public void setTowerColor(String towerColor) {
		this.towerColor = towerColor;
	}

	public boolean isTowerOccupied() {
		return towerOccupied;
	}

	public void setTowerOccupied(boolean towerOccupied) {
		this.towerOccupied = towerOccupied;
	}

	public ArrayList<Floor> getFloors() {
		return floors;
	}

	public void setFloors(ArrayList<Floor> floors) {
		this.floors = floors;
	}

	public boolean hasPlayerIn(FamilyMember f) {
		return false; //DARIO array di boolean per vedere? 
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Floor f : floors) {
			sb.append(f.toString());
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * add a floor to the tower
	 * 
	 * @param bonus
	 *            bonus of the floor
	 * @param minDiceValue
	 */
	public void addFloor(Bonus bonus, Integer minDiceValue) {
		this.floors.add(new Floor(bonus, this, minDiceValue));

	}

}
