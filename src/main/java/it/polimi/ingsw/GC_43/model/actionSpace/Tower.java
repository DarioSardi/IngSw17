package it.polimi.ingsw.GC_43.model.actionSpace;

import java.util.ArrayList;

import javax.xml.ws.RequestWrapper;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class Tower{
	private TowerColors towerColor;
	private boolean towerOccupied;	
	private ArrayList<Floor> floors;
	private int floorsNumber;
	private int maxFloors;
	/**
	 * generate a tower with some empty floors
	 * @param towerColor define the tower colors
	 */
	
	public Tower(TowerColors towerColor,int maxFloors) throws ExceptionInInitializerError{
		if(maxFloors<0){
			throw new ExceptionInInitializerError("negative floor value!");
		}
		this.towerColor = towerColor;	
		this.towerOccupied= false;
		this.maxFloors=maxFloors;
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
			for(FamilyMember fam:floor.getFamiliarIn()){
			if(f.getPlayer()==fam.getPlayer()){return false;}
			}
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
		sb.append("do you have to pay the tax to enter here? "+this.towerOccupied);
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
	 * @throws Exception 
	 */
	public void addFloor(ArrayList<Effect> bonus, Integer minDiceValue) throws Exception {
		if (this.floorsNumber<maxFloors) {
			this.floors.add(new Floor(bonus, this, minDiceValue));
			this.floorsNumber = this.floorsNumber + 1;
			//DARIO card? no?
			
		}
		else{throw new Exception("no space for another floor");}
	}
	
	public void addFloor(Integer minDiceValue) throws Exception {
		if (this.floorsNumber<maxFloors) {
			this.floors.add(new Floor(null, this, minDiceValue));
			this.floorsNumber=this.floorsNumber+1;
		}
		else{throw new Exception("no space for another floor");}
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
