package model;

public class MarketActionSpace extends ActionSpace {
	private FamilyMember familiarIn;
	private Bonus bonus;
	//DARIO generare costruttore
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
	
	
	public boolean check() {
		return true;	//DARIO compilare codice
	}
	
	public boolean execute() {
		return true;	//DARIO compilare codice
	}
	
	public String toString(){
		return "";		//DARIO compilare codice
	}

}
