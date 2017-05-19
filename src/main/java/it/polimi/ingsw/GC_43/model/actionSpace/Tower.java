package it.polimi.ingsw.GC_43.model.actionSpace;

import it.polimi.ingsw.GC_43.model.Bonus;
import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;

public class Tower extends ActionArea{
	private TowerColors towerColor;
	private boolean towerOccupied;	
	/**
	 * generate a empty tower 
	 * @param towerColor define the tower colors
	 */
	public Tower(TowerColors towerColor,int floors) {
		this.towerColor = towerColor;	
		this.towerOccupied= false;
	}

	// getters and setters
	public TowerColors getTowerColor() {
		return towerColor;
	}

	/**
	 * check if the tower have already the player in
	 * @param f
	 * @return true if has not the player in
	 */
	public boolean hasPlayerIn(FamilyMember f) {
		if(f.getColor()==0){return true;}
		else{for(ActionSpace floor: this.getSpaces()){
			if(f.getColor()==floor.getFamiliarIn().get(0).getColor()){return false;}
		}
		return true;}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (ActionSpace floor: this.getSpaces()) {
			sb.append(floor.toString());
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
		this.getSpaces().add(new Floor(bonus, this, minDiceValue));

	}
	
	/**
	 * check if the tower is occupied
	 */
	@Override
	public boolean check(FamilyMember f) {
		return towerOccupied;
	}

	@Override
	public int getBonusOfArea(Player p) {
		// DARIO come divido i bonus torre?
		return 0;
	}

}
