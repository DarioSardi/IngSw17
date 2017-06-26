package it.polimi.ingsw.GC_43.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_43.model.actionCreations.CouncilPalaceActionCreationRoutine;
import it.polimi.ingsw.GC_43.model.actionCreations.HarvestActionCreationRoutine;
import it.polimi.ingsw.GC_43.model.actionCreations.MarketActionCreationRoutine;
import it.polimi.ingsw.GC_43.model.actionCreations.ProductionActionCreationRoutine;
import it.polimi.ingsw.GC_43.model.actionCreations.TowerActionCreationRoutine;
import it.polimi.ingsw.GC_43.model.actionPerforms.CouncilPalacePerformerRoutine;
import it.polimi.ingsw.GC_43.model.actionPerforms.HarvestActionPerformerRoutine;
import it.polimi.ingsw.GC_43.model.actionPerforms.MarketActionPerformerRoutine;
import it.polimi.ingsw.GC_43.model.actionPerforms.ProductionActionPerformerRoutine;
import it.polimi.ingsw.GC_43.model.actionPerforms.TowerActionPerformerRoutine;
import it.polimi.ingsw.GC_43.model.cards.BuildingCard;
import it.polimi.ingsw.GC_43.model.cards.CharacterCard;
import it.polimi.ingsw.GC_43.model.cards.TerritoryCard;
import it.polimi.ingsw.GC_43.model.effects.Effect;
import it.polimi.ingsw.GC_43.model.initialization.GlobalVariablesInit;
import it.polimi.ingsw.GC_43.model.initialization.InitGame;

public class BoardTest {

	public Board board;

	@Before
	public void testBoard() {
		ArrayList<String> playersID = new ArrayList<>();
		playersID.add("ermione");
		playersID.add("gigi");
		playersID.add("ruben");

		GlobalVariablesInit.readGlobalVariables();

		board = new Board(playersID);

		new InitGame(board);
		System.out.println("fine di tutto !!!!");
		board.initialize();
		System.out.println("Attenzione !!!!");
		// System.out.println("\n"+board.towersToString());
		for (Effect effect : this.board.getCouncilPalace().getSpaces().get(0).getBonus())
			System.out.println("\n" + effect.toString() + "\n");

		//this.board.excommunicatePlayer(this.board.getPlayers().get(1));
		
		  board.getPlayers().get(2).getPlayerCards().addTerritoryCard((
		  TerritoryCard)board.getTowers().get(0).getFloors().get(0).getCard());
		  board.getPlayers().get(2).getPlayerCards().addTerritoryCard((
		  TerritoryCard)board.getTowers().get(0).getFloors().get(1).getCard());
		 board.getPlayers().get(2).getPlayerCards().addTerritoryCard((
		  TerritoryCard)board.getTowers().get(0).getFloors().get(2).getCard());
		  board.getPlayers().get(2).getPlayerCards().addTerritoryCard((
		  TerritoryCard)board.getTowers().get(0).getFloors().get(3).getCard());
		/*
		 * System.out.println("\n\nPLAYER HARVEST CARDS NOW ARE :\\n\n"+board.
		 * getPlayers().get(0).getPlayerCards().toString());
		 * 
		 * HarvestActionCreationRoutine ha = new
		 * HarvestActionCreationRoutine(board.getPlayers().get(0).getPlayerName(
		 * ), board.getPlayers().get(0), board); ha.prepareAction();
		 * HarvestActionPerformerRoutine hp= new
		 * HarvestActionPerformerRoutine(ha.getHarvestAction(),board);
		 * System.out.println("\n\nstarting to perform\n\n");
		 * hp.performAction();
		 * 
		 * System.out.println("\n\nPLAYER RESOURCES NOW ARE :\\n\n"+board.
		 * getPlayers().get(0).toString());
		 * 
		 */
/*
		board.getPlayers().get(2).getPlayerCards()
				.addBuildingCard((BuildingCard) board.getTowers().get(2).getFloors().get(0).getCard());
		board.getPlayers().get(2).getPlayerCards()
				.addBuildingCard((BuildingCard) board.getTowers().get(2).getFloors().get(1).getCard());
		board.getPlayers().get(2).getPlayerCards()
				.addBuildingCard((BuildingCard) board.getTowers().get(2).getFloors().get(2).getCard());
		board.getPlayers().get(2).getPlayerCards()
				.addBuildingCard((BuildingCard) board.getTowers().get(2).getFloors().get(3).getCard());
		board.getPlayers().get(2).getPlayerCards()
		.addBuildingCard((BuildingCard) board.getBuildingCardPool().get(4));
		board.getPlayers().get(2).getPlayerCards()
		.addBuildingCard((BuildingCard) board.getBuildingCardPool().get(5));
		board.getPlayers().get(2).getPlayerCards()
		.addBuildingCard((BuildingCard) board.getBuildingCardPool().get(6));
		board.getPlayers().get(2).getPlayerCards()
		.addBuildingCard((BuildingCard) board.getBuildingCardPool().get(7));
		
		System.out.println(
				"\n\nPLAYER BUILDING CARDS NOW ARE :\\n\n" + board.getPlayers().get(0).getPlayerCards().toString());

		ProductionActionCreationRoutine p = new ProductionActionCreationRoutine(
				board.getPlayers().get(0).getPlayerName(), board.getPlayers().get(0), board);
		p.prepareAction();
		System.out.println("\nProduction action creation done!\n");
		ProductionActionPerformerRoutine pp = new ProductionActionPerformerRoutine(p.getProductionAction(), board);
		pp.performAction();
		
		System.out.println("\n\nPLAYER RESOURCES NOW ARE :\\n\n"+board. getPlayers().get(0).toString()+"\n");		
		System.out.println("Message from board: Production action perform ended!");
		/*
		 * MarketActionCreationRoutine m = new
		 * MarketActionCreationRoutine(board.getPlayers().get(0).getPlayerName()
		 * , board.getPlayers().get(0), board);
		 * 
		 * m.prepareAction();
		 * 
		 * MarketActionPerformerRoutine mp= new
		 * MarketActionPerformerRoutine(m.getMarketAction(),board);
		 * System.out.println("started performing market action in board test");
		 * mp.performAction();
		 * System.out.println("finished performing market action in board test"
		 * );
		 * 
		 * 
		 */
		 TowerActionCreationRoutine t = new
		 TowerActionCreationRoutine(board.getPlayers().get(0).getPlayerName(),
		 board.getPlayers().get(0), board); t.prepareAction();
		 TowerActionPerformerRoutine tp= new
		 TowerActionPerformerRoutine(t.getTowerAction(),board);
		 System.out.println("\n\nstarting to perform\n\n");
		 tp.performAction();
		 
		  
		  System.out.println("\n\n\nBOARD TEST FINISHED !");
		 /* 
		 * CouncilPalaceActionCreationRoutine c = new
		 * CouncilPalaceActionCreationRoutine(board.getPlayers().get(0).
		 * getPlayerName(), board.getPlayers().get(0), board);
		 * c.prepareAction(); CouncilPalacePerformerRoutine cp= new
		 * CouncilPalacePerformerRoutine(c.getCouncilPalaceAction(),board);
		 * System.out.println("\n\n\n\nstarting to perform\n\n\n\n\n\n\n");
		 * cp.performAction();
		 * System.out.println("bonus "+this.board.getCouncilPalace().getCouncil(
		 * ).getMinDiceValue());
		 */
		
		
		
		
		
		//TEST FOR FINAL CALCULATION ON VICTORY POINTS
		/*
		  board.getPlayers().get(2).getPlayerCards().addCharacterCard((
				  CharacterCard)board.getTowers().get(1).getFloors().get(0).getCard());
		  board.getPlayers().get(2).getPlayerCards().addCharacterCard((
				  CharacterCard)board.getTowers().get(1).getFloors().get(1).getCard());
		  board.getPlayers().get(2).getPlayerCards().addCharacterCard((
				  CharacterCard)board.getTowers().get(1).getFloors().get(2).getCard());
		  board.getPlayers().get(2).getPlayerCards().addCharacterCard((
				  CharacterCard)board.getTowers().get(1).getFloors().get(3).getCard());
		*/
		
		System.out.println("\nDECIDING FOR WINNER");
		FinalCalculationVictoryPoints fc= new FinalCalculationVictoryPoints();
		System.out.println(fc.decideForWinner(this.board.getPlayers()));
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
