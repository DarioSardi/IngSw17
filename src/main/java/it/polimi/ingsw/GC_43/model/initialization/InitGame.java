package it.polimi.ingsw.GC_43.model.initialization;

import it.polimi.ingsw.GC_43.model.Board;

public class InitGame {
	private Board board;
	private InitActionSpaces initActionSpaces;
	
	public InitGame(Board boardReceved){
		this.board = boardReceved;
		initCards();
		this.initActionSpaces = new InitActionSpaces();
		this.initActionSpaces.readJson();
		setFaithVictoryPointsToBoard();
		setMarketToBoard();		
		setTowersToBoard();
		//setExcommunicationTilesToBoard();
		setCouncilPalaceToBoard();
	}
	
	private void initCards(){
		new InitCards().readJson(this.board);
	}
	
	private void setFaithVictoryPointsToBoard(){
		this.board.setFaithVictoryPoints(this.initActionSpaces.getFaithPoints());
	}
	
	private void setMarketToBoard(){
		this.board.setMarket(this.initActionSpaces.getMarket());
	}
	
	private void setTowersToBoard(){
		this.board.setTowers(this.initActionSpaces.getTowers());
	}
	/*private void setExcommunicationTilesToBoard(){
		this.board.setExcommunicationTiles(new InitExcommunicationTiles().getMalusExcommunicationSelected());
	}
	*/private void setCouncilPalaceToBoard(){
		this.board.setCouncilPalace(this.initActionSpaces.getCouncilPalace());
	}
	

	
}