package it.polimi.ingsw.GC_43.model.initialization;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.GlobalVariables;

public class InitGame {
	private Board board;
	private InitActionSpaces initActionSpaces;
	
	public InitGame(Board boardReceved){
		this.board = boardReceved;
		setVariable5thPlayer();
		initCards();
		this.initActionSpaces = new InitActionSpaces();
		this.initActionSpaces.readJson();
		setFaithVictoryPointsToBoard();
		setMarketToBoard();		
		setTowersToBoard();
		setExcommunicationTilesToBoard();
		setCouncilPalaceToBoard();
		setPersonalBonusToPlayers();
		if (board.isAdvancedGame()) setLeaderCardsToBoard();
	}
	
	private void initCards(){
		new InitCards().readJson(this.board);
	}
		
	private void setVariable5thPlayer(){
		if (this.board.getPlayers().size() == 5){
			GlobalVariables.towerTax--;
			GlobalVariables.minDiceSecondHarvestArea--;
			GlobalVariables.minDiceSecondProductionArea--;
		}
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
	private void setExcommunicationTilesToBoard(){
		InitExcommunicationTiles iet = new InitExcommunicationTiles();
		iet.readExcommunicationTiles();
		this.board.setExcommunicationTiles(iet.getMalusExcommunicationSelected());
	}
	
	private void setCouncilPalaceToBoard(){
		this.board.setCouncilPalace(this.initActionSpaces.getCouncilPalace());
	}	
	
	private void setLeaderCardsToBoard(){
		InitLeaderCards ilc = new InitLeaderCards();
		ilc.readLeaderCards();
		this.board.setLeaderCardPool(ilc.getLeaderCards());
	}	
	
	private void setPersonalBonusToPlayers(){
		InitPlayerPersonalBonus initPersonalBonus = new InitPlayerPersonalBonus();
		initPersonalBonus.readJson();
//		this.board.setBasePersonalBonus(initPersonalBonus.getBasePersonalTile());
//		this.board.setAdvancedPersonalBonus(initPersonalBonus.getAllAdvancedPersonalTile());		
	}
}