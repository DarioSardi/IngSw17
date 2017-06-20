package it.polimi.ingsw.GC_43.model.actions;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.Player;

public class TowerAction extends Action {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3415208390394786168L;

	
	
	private ArrayList<Integer> effectChoices;
	private int towerChoice;
	private int floorChoice;
	private int doubleCostSelection;
	private boolean defaultFamilyMember;
	private boolean defaultTowerChoice;
	private HashMap<String, Integer> resourceDiscounts;

	public TowerAction(String playerID, Player player) {
		super(playerID, player);
		this.effectChoices = new ArrayList<Integer>();
		this.setActionID(5);
		this.defaultFamilyMember = false;
		this.defaultTowerChoice = false;
		this.resourceDiscounts = new HashMap<String, Integer>();
		this.resourceDiscounts.put("coin", 0);
		this.resourceDiscounts.put("servant", 0);
		this.resourceDiscounts.put("stone", 0);
		this.resourceDiscounts.put("wood", 0);
		this.resourceDiscounts.put("victoryPoint", 0);
		this.resourceDiscounts.put("militaryPoint", 0);
		this.resourceDiscounts.put("faithPoint", 0);
		this.defaultFamilyMember = false;
		this.defaultTowerChoice = false;

	}

	public ArrayList<Integer> getEffectChoices() {
		return effectChoices;
	}

	public void setEffectChoices(ArrayList<Integer> towerChoices) {
		this.effectChoices = towerChoices;
	}

	public int getTowerChoice() {
		return towerChoice;
	}

	public int getEffectChoice() {
		return towerChoice;
	}

	public void setTowerChoice(int towerChoice) {
		this.towerChoice = towerChoice;
	}

	public int getFloorChoice() {
		return floorChoice;
	}

	public void setFloorChoice(int floorChoice) {
		this.floorChoice = floorChoice;
	}

	public int getDoubleCostSelection() {
		return doubleCostSelection;
	}

	public void setDoubleCostSelection(int doubleCostSelection) {
		this.doubleCostSelection = doubleCostSelection;
	}

	public boolean isDefaultFamilyMember() {
		return defaultFamilyMember;
	}

	public void setDefaultFamilyMember(boolean defaultFamilyMember) {
		this.defaultFamilyMember = defaultFamilyMember;
	}

	public boolean isDefaultTowerChoice() {
		return defaultTowerChoice;
	}

	public void setDefaultTowerChoice(boolean defaultTowerChoice) {
		this.defaultTowerChoice = defaultTowerChoice;
	}

	public HashMap<String, Integer> getResourceDiscounts() {
		return resourceDiscounts;
	}

	public void setResourceDiscounts(HashMap<String, Integer> resourceDiscounts) {
		this.resourceDiscounts = resourceDiscounts;
	}
	
	public String toString(){
		return super.toString()+"Action performed: Tower Action\n"+"Tower chosen :"+this.towerChoice+"\nFloor Choice: "+this.floorChoice;
	}

}
