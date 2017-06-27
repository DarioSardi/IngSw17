package it.polimi.ingsw.GC_43.model.actionSpace;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public abstract class ActionSpace implements Serializable{
	private int minDiceValue;
	private ArrayList<FamilyMember> familiarIn=new ArrayList<>();
	private ArrayList<Effect> bonus;
	
	public abstract boolean check(FamilyMember f);

	public abstract boolean familiarValueCheck(FamilyMember f);
	
	public abstract boolean checkColor(FamilyMember f);

	public int getMinDiceValue() {
		return minDiceValue;
	}

	public void setMinDiceValue(int minDiceValue) {
		this.minDiceValue = minDiceValue;
	}

	public ArrayList<FamilyMember> getFamiliarIn() {
		return familiarIn;
	}

	public void setFamiliarIn(ArrayList<FamilyMember> familiarIn) {
		this.familiarIn = familiarIn;
	}
	
	/*
	 * only for testing
	 */
	public void addFamiliarIn(FamilyMember f){
		this.familiarIn.add(f);
	}

	public ArrayList<Effect> getBonus() {
		return this.bonus;
	}

	public void setBonus(ArrayList<Effect> bonus) {
		this.bonus = bonus;
	}
	
	/**
	 * check if the action is possible, if it is give the player the bonus 
	 * @param f
	 * @return
	 */
	public boolean execute(FamilyMember f){
		if(check(f)){
			if (this.bonus!=null) {
				for (Effect effect : this.bonus){
					effect.executeEffect(f);
					System.out.println("bonus in this action space is "+effect.toString());
				}
			}
			else{
				System.out.println("no bonus here to take!");
			}
			this.getFamiliarIn().add(f);
			f.setAlreadyPlaced(true);
			f.setFamilyMemberPosition(this);
			return true;
		}
		else return false;
	}
	
	
	public void removeAllFamiliars(){
		this.familiarIn.clear();
	}
	
	public void resetSpace(){
		this.familiarIn.clear();
	}
	
	/**
	 * 
	 * @return true if the space is occupied
	 */
	public boolean isOccupied(){
		return !this.familiarIn.isEmpty();
	}
	
}
