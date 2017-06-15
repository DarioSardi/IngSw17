package it.polimi.ingsw.GC_43.model.initialization;

import it.polimi.ingsw.GC_43.model.Board;

public class InitGame {
	private InitActionSpaces initActionSpaces;
	
	public InitGame(Board board){
		initGlobalVariables();
		initCards(board);
		board.setPhase(0);
		board.setRound(0);
		board.setPeriod(0);
		this.initActionSpaces = new InitActionSpaces();
		this.initActionSpaces.readJson(board);
		setFaithVictoryPointsToBoard(board);
		setMarketToBoard(board);		
		setTowersToBoard(board);
	}
    
	private void initGlobalVariables(){
		GlobalVariablesInit.readGlobalVariables();
	}
	
	private void initCards(Board board){
		new InitCards().readJson(board);
	}
	
	private void setFaithVictoryPointsToBoard(Board board){
		board.setFaithVictoryPoints(this.initActionSpaces.getFaithPoints());
	}
	
	private void setMarketToBoard(Board board){
		board.setMarket(this.initActionSpaces.getMarket());
	}
	
	private void setTowersToBoard(Board board){
		board.setTowers(this.initActionSpaces.getTowers());
	}

	
}