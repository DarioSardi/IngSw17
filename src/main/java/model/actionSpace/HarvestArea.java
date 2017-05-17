package model.actionSpace;

import model.FamilyMember;

public class HarvestArea{
	private FamilyMember FamiliarIn;
	private SingleActionSpace baseZone;
	private MultipleActionSpace extraZone;
	//FRANCESCO-DARIO serve una variabile globale per il mindicevalue delle production areas!
	public HarvestArea(boolean enoughPlayers) {
		super();
		this.baseZone = new HarvestSingleActionSpace();
		this.extraZone = new HarvestMultipleActionSpace();
	}
	//DARIO DA RIFARE D:
	//DARIO generare costruttore
	//DARIO logica relativa alle 2 aree
	public FamilyMember getFamiliarIn() {
		return FamiliarIn;
	}

	public void setFamiliarIn(FamilyMember familiarIn) {
		FamiliarIn = familiarIn;
	}
	
	public boolean check() {
		return true; //DARIO compilare codice
	}

	public boolean execute() {
		return true; //DARIO compilare codice
	}



 
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   