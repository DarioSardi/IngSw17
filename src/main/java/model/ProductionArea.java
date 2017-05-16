package model;

public class ProductionArea extends ActionSpace {
	public class HarvestArea extends ActionSpace{
		private FamilyMember FamiliarIn;
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

}
