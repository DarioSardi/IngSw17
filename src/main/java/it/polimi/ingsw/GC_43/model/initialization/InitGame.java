package it.polimi.ingsw.GC_43.model.initialization;
 
public class InitGame {
	InitCards initCards;
	
	public InitGame(){
		initGlobalVariables();
		initCards();
	}
    
	private void initGlobalVariables(){
		GlobalVariablesInit.readGlobalVariables();
	}
	
	private void initCards(){
		initCards.readJson();
	}
		
}