package it.polimi.ingsw.GC_43.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_43.model.actionCreations.MarketActionCreationRoutine;
import it.polimi.ingsw.GC_43.model.actionCreations.ProductionActionCreationRoutine;
import it.polimi.ingsw.GC_43.model.actionCreations.TowerActionCreationRoutine;
import it.polimi.ingsw.GC_43.model.actionPerforms.MarketActionPerformerRoutine;
import it.polimi.ingsw.GC_43.model.actionPerforms.TowerActionPerformerRoutine;
import it.polimi.ingsw.GC_43.model.initialization.GlobalVariablesInit;
import it.polimi.ingsw.GC_43.model.initialization.InitGame;

public class BoardTest {
	
	public Board board;
	
	@Before
	public void testBoard() {
		ArrayList<String> playersID= new ArrayList<>();
		playersID.add("ermione");
		playersID.add("gigi");
		playersID.add("ruben");
		GlobalVariablesInit.readGlobalVariables();
		board=new Board(playersID);
		new InitGame(board);
		System.out.println("fine di tutto !!!!");
		board.initialize();
		System.out.println("Attenzione !!!!");
		//System.out.println(board.towersToString());
		//ProductionActionCreationRoutine p = new ProductionActionCreationRoutine(board.getPlayers().get(0).getPlayerName(), board.getPlayers().get(0), board);
		//p.prepareAction();
		//System.out.println("production done!");
		//System.out.println("\n INITIAL servants of player\n" +board.getPlayers().get(0).toString());
		MarketActionCreationRoutine m = new MarketActionCreationRoutine(board.getPlayers().get(0).getPlayerName(), board.getPlayers().get(0), board);
		
		m.prepareAction();
		System.out.println("\nalmost starting performing\n");

		MarketActionPerformerRoutine mp= new MarketActionPerformerRoutine(m.getMarketAction(),board);
		System.out.println("started performing");

		mp.performAction();
		System.out.println("finished performing");
		System.out.println("\n FINAL servants of player\n" +board.getPlayers().get(0).toString()+"\n\nPlayer in memory "+board.getPlayers().get(0));
		
		
		/*TowerActionCreationRoutine t = new TowerActionCreationRoutine(board.getPlayers().get(0).getPlayerName(), board.getPlayers().get(0), board);
		t.prepareAction();
		TowerActionPerformerRoutine tp= new TowerActionPerformerRoutine(t.getTowerAction(),board);
		System.out.println("\n\n\n\nstarting to perform\n\n\n\n\n\n\n");
		tp.performAction();*/

	
	}

	@Test
	public void testAddPlayerID() {

	}

	@Test
	public void testInitialize() {

	}

	@Test
	public void testNextTurn() {

	}

	@Test
	public void testNextPlayerPhase() {

	}

	@Test
	public void testGetPhasePlayer() {

	}

	@Test
	public void testTowersToString() {

	}

	@Test
	public void testGetDice() {

	}

	@Test
	public void testSetDice() {

	}

	@Test
	public void testGetTerritoryCardPool() {

	}

	@Test
	public void testSetTerritoryCardPool() {

	}

	@Test
	public void testGetBuildingCardPool() {

	}

	@Test
	public void testSetBuildingCardPool() {

	}

	@Test
	public void testGetCharacterCardPool() {

	}

	@Test
	public void testSetCharacterCardPool() {

	}

	@Test
	public void testGetVentureCardPool() {

	}

	@Test
	public void testSetVentureCardPool() {

	}

	@Test
	public void testGetMarket() {

	}

	@Test
	public void testSetMarket() {

	}

	@Test
	public void testGetCouncilPalace() {

	}

	@Test
	public void testSetCouncilPalace() {
		
	}

	@Test
	public void testGetHarvestArea() {

	}

	@Test
	public void testSetHarvestArea() {

	}

	@Test
	public void testGetProductionArea() {

	}

	@Test
	public void testSetProductionArea() {

	}

	@Test
	public void testGetTowers() {
		board.towersToString();
	}

	@Test
	public void testSetTowers() {
	}

	@Test
	public void testGetPlayersID() {

	}

	@Test
	public void testSetPlayersID() {

	}

	@Test
	public void testGetPlayers() {

	}

	@Test
	public void testSetPlayers() {

	}

	@Test
	public void testGetPhase() {

	}

	@Test
	public void testSetPhase() {

	}

	@Test
	public void testGetRound() {

	}

	@Test
	public void testSetRound() {

	}

	@Test
	public void testGetPeriod() {

	}

	@Test
	public void testSetPeriod() {

	}

	@Test
	public void testGetExcommunicationTiles() {

	}

	@Test
	public void testSetExcommunicationTiles() {

	}

	@Test
	public void testGetFaithVictoryPoints() {

	}

	@Test
	public void testSetFaithVictoryPoints() {

	}

}
