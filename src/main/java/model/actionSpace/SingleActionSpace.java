package model.actionSpace;

import model.Bonus;
import model.FamilyMember;

public abstract class SingleActionSpace extends ActionSpace{
	protected FamilyMember familiarIn;
	private Bonus bonus;
	public FamilyMember getFamiliarIn() {
		return familiarIn;
	}
	public void setFamiliarIn(FamilyMember familiarIn) {
		this.familiarIn = familiarIn;
	}
	public Bonus getBonus() {
		return bonus;
	}
	public void setBonus(Bonus bonus) {
		this.bonus = bonus;
	}
	

}
