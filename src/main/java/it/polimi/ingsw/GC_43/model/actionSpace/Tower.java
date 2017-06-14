package it.polimi.ingsw.GC_43.model.actionSpace;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.ws.RequestWrapper;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class Tower implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2676592258729347365L;
	private final static Logger LOGGER = Logger.getLogger(Tower.class.getName());
	private TowerColors towerColor;
	private boolean towerOccupied;
	private ArrayList<Floor> floors;
	private int floorsNumber;
	private int maxFloors;

	/**
	 * generate a tower with some empty floors
	 * 
	 * @param towerColor
	 *            define the tower colors
	 */

	public Tower(TowerColors towerColor, int maxFloors) throws ExceptionInInitializerError {
		if (maxFloors < 0) {
			throw new ExceptionInInitializerError("negative floor value!");
		}
		this.towerColor = towerColor;
		this.towerOccupied = false;
		this.maxFloors = maxFloors;
		this.floors = new ArrayList<>();
		this.floorsNumber = 0;
	}

	// getters and setters
	public TowerColors getTowerColor() {
		return towerColor;
	}

	public List<Floor> getFloors() {
		return floors;
	}

	/**
	 * check if the tower have already the player in
	 * 
	 * @param f
	 * @return true if has not the player in
	 */
	public boolean hasPlayerIn(FamilyMember f) {
		if (f.getColor() == 0) {
			return true;
		} else {
			for (Floor floor : this.floors) {
				for (FamilyMember fam : floor.getFamiliarIn()) {
					if (f.getPlayer() == fam.getPlayer()) {
						return false;
					}
				}
			}
			return true;
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getTowerColor().toString() + " info:\n");
		for (Floor floor : this.floors) {
			sb.append(floor.toString());
			sb.append("\n");
		}
		return sb.toString();

	}

	public String toStringAll() {
		StringBuilder sb = new StringBuilder();
		int floorNumber = 0;
		sb.append(this.getTowerColor().toString() + " info:\n");
		sb.append("number of floors on this tower: " + this.floorsNumber);
		sb.append("\n");
		sb.append("do you have to pay the tax to enter here? " + this.towerOccupied);
		sb.append("\n");
		sb.append("-----------------------\n");
		for (Floor floor : this.floors) {
			sb.append("FLOOR NUMBER: " + floorNumber + "\n");
			sb.append(floor.toStringAll());
			sb.append("\n");
			floorNumber++;
		}
		return sb.toString();
	}

	/**
	 * add a floor to the tower with a bonus
	 * 
	 * @param bonus
	 *            bonus of the floor
	 * @param minDiceValue
	 * @throws Exception
	 */
	public void addFloor(Effect bonus, Integer minDiceValue) {
		try {
			if (this.floorsNumber < maxFloors) {
				this.floors.add(new Floor(bonus, this, minDiceValue));
				this.floorsNumber = this.floorsNumber + 1;
				// DARIO card? no?

			} else {
				throw new Exception("no space for another floor");
			}
		} catch (Exception e) {
			LOGGER.throwing("Tower","addFloorWithBonus",e);
		}
	}

	public void addFloor(Integer minDiceValue) {
		try {
			if (this.floorsNumber < maxFloors) {
				this.floors.add(new Floor(null, this, minDiceValue));
				this.floorsNumber = this.floorsNumber + 1;
			} else {
				throw new Exception("no space for another floor");
			}
		} catch (Exception e) {
			LOGGER.throwing("Tower","addFloorWithoutBonus",e);
		}
		// DARIO card? no?
	}

	public void setTowerOccupied(boolean set) {
		towerOccupied = set;
	}

	/**
	 * check if the tower is occupied
	 * @param f DO NOT PASS NULL AS PARAM!
	 */

	public boolean check(FamilyMember f) {
		if (f!=null) {
			return towerOccupied;
		}
		return false;
	}

	/**
	 * check if the player have a familiar in the tower yet
	 * 
	 * @param f
	 *            familiar for the action in the floor
	 * @return true if there are no other familiar of the same player in the
	 *         tower
	 */
	public boolean checkColor(FamilyMember f) {
		if (f.getColor() == 0) {
			return true;
		} else {
			for (Floor floor : this.floors)
				if (floor.checkColor(f)) {
					return false;
				}
		}
		return true;
	}

	/**
	 * function to get the tower dice bonus
	 * @param p player that tries to get the bonus
	 * @return the int value of the bonus dice.
	 */
	public int getBonusOfArea(Player p) {
		if(towerColor.equals(TowerColors.BUILDINGS_TOWER)){
				return p.getPlayerBounusMalus().getBonusDiceBuildingTower();
		}
		else if(towerColor.equals(TowerColors.CHARACTERS_TOWER)){
			return p.getPlayerBounusMalus().getBonusDiceCharacterTower();
		}
		else if(towerColor.equals(TowerColors.TERRITORIES_TOWER)){
			return p.getPlayerBounusMalus().getBonusDiceTerritoryTower();
		}
		else if(towerColor.equals(TowerColors.VENTURES_TOWER)){
			return p.getPlayerBounusMalus().getBonusDiceVentureTower();
		}
		else return 0;
	}

	public void resetArea() {
		this.towerOccupied = false;
		this.floors.stream().forEach(space->space.resetSpace());
	}

}
