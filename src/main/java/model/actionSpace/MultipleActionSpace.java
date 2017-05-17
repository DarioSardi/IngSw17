package model.actionSpace;

import java.util.ArrayList;

import model.Bonus;
import model.FamilyMember;

public abstract class MultipleActionSpace extends ActionSpace {
	protected ArrayList<FamilyMember> familiarsIn;
	private Bonus bonus;
	
	public ArrayList<FamilyMember> getFamiliarsIn() {
		return familiarsIn;
	}
	public void addFamiliarsIn(FamilyMember f) {
		this.familiarsIn.add(f);
	}
	
	public void resetFamiliarIn(){
		this.familiarsIn.clear();
	}
	
	public Bonus getBonus() {
		return this.bonus;
	}
	public void setBonus(Bonus bonus) {
		this.bonus = bonus;
	}
	
	public String familiarsInToString(){
		StringBuilder sb = new StringBuilder();
		for (FamilyMember f : familiarsIn) { 		//SAMUEL-DARIO aggiungere metodo toString di familyMember
			sb.append(f.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
	
	

}
