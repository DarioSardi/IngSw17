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
		System.out.println("Game initialized");
	}
	
	private void initCards(){
		new InitCards().readJson(this.board);
		System.out.println("Development cards initialized");
	}
		
	private void setVariable5thPlayer(){
		if (this.board.getPlayers().size() == 5){
			GlobalVariables.towerTax--;
			GlobalVariables.minDiceSecondHarvestArea--;
			GlobalVariables.minDiceSecondProductionArea--;
			System.out.println("5th player variables initialized");
		}
	}
	private void setFaithVictoryPointsToBoard(){
		this.board.setFaithVictoryPoints(this.initActionSpaces.getFaithPoints());
		System.out.println("Victory points for each faith point initialized");
	}
	
	private void setMarketToBoard(){
		this.board.setMarket(this.initActionSpaces.getMarket());
		System.out.println("Market spaces initialized");
	}
	
	private void setTowersToBoard(){
		this.board.setTowers(this.initActionSpaces.getTowers());
		System.out.println("Tower initialized");
	}
	private void setExcommunicationTilesToBoard(){
		InitExcommunicationTiles iet = new InitExcommunicationTiles();
		iet.readExcommunicationTiles();
		this.board.setExcommunicationTiles(iet.getMalusExcommunicationSelected());
		System.out.println("Excommunication tile initialized");
	}
	
	private void setCouncilPalaceToBoard(){
		this.board.setCouncilPalace(this.initActionSpaces.getCouncilPalace());
		System.out.println("Council palace initialized");
	}	
	
	private void setLeaderCardsToBoard(){
		InitLeaderCards ilc = new InitLeaderCards();
		ilc.readLeaderCards();
		this.board.setLeaderCardPool(ilc.getLeaderCards());
		System.out.println("Leader cards initialized");
	}	
	
	private void setPersonalBonusToPlayers(){
		InitPlayerPersonalBonus initPersonalBonus = new InitPlayerPersonalBonus();
		initPersonalBonus.readJson();
		this.board.setBasePersonalBonus(initPersonalBonus.getBasePersonalTile());
		this.board.setAdvancedPersonalBonus(initPersonalBonus.getAllAdvancedPersonalTile());	
		System.out.println("Personal bonus initialized");
	}
}