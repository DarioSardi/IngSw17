package model.actionSpace;

import java.util.ArrayList;

import model.Bonus;
import model.FamilyMember;

public class CouncilPalace extends MultipleActionSpace{
	private Bonus bonus; //TODO bonus di tipo pergamena+coin
	
	public CouncilPalace(Bonus bonus,Integer minDiceValue) {
		super();
		this.setMinDiceValue(minDiceValue);
		this.familiarsIn= new ArrayList<FamilyMember>();
		this.bonus = bonus;
	}

	
	public Bonus getBonus() {
		return bonus;
	}

	public void setBonus(Bonus bonus) {
		this.bonus = bonus;
	}
	
	/**
	 * check if is possible to enter the Council palace (no malus checked)
	 * @return true if is possible to enter this zone
	 */
	public boolean check(FamilyMember f) {
		return f.getDiceValue()>=this.getMinDiceValue();	
	}
	/**
	 * get the bonuses and add the family member to the array (set the current position of the family member)
	 */
	public boolean execute(FamilyMember f) {
		if(this.check(f)){			
			this.bonus.earnBonus(f); 
			this.addFamiliarsIn(f);;
			f.setAlreadyPlaced(true);
			f.setFamilyMemberPosition(this);
			return true;
		}
		else {return false;} //DARIO aggiungere eccezione?
	}
	/**
	 * print the bonus and the list of the player already in the council
	 */
	public String toString(){
		return bonus.toString()+familiarsIn.toString();		//DARIO metodo tostring di familiarIn
	}
}
