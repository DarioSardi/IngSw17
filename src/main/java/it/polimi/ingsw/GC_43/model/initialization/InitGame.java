package it.polimi.ingsw.GC_43.model.initialization;

import it.polimi.ingsw.GC_43.model.Board;

public class InitGame {
	
	public InitGame(Board board){
		initGlobalVariables();
		initCards(board);
	}
    
	private void initGlobalVariables(){
		GlobalVariablesInit.readGlobalVariables();
	}
	
	private void initCards(Board board){
		new InitCards().readJson(board);
	}
	
}