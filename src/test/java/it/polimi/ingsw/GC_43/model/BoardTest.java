package it.polimi.ingsw.GC_43.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_43.model.actionCreations.LeaderCardActionCreationRoutine;
import it.polimi.ingsw.GC_43.model.actionPerforms.LeaderCardActionPerformerRoutine;
import it.polimi.ingsw.GC_43.model.cards.BuildingCard;
import it.polimi.ingsw.GC_43.model.cards.CharacterCard;
import it.polimi.ingsw.GC_43.model.cards.LeaderCard;
import it.polimi.ingsw.GC_43.model.cards.TerritoryCard;
import it.polimi.ingsw.GC_43.model.cards.VentureCard;
import it.polimi.ingsw.GC_43.model.effects.Effect;
import it.polimi.ingsw.GC_43.model.initialization.GlobalVariablesInit;
import it.polimi.ingsw.GC_43.model.initialization.InitCards;
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
		board.setAdvancedGame(true);
		new InitGame(board);
		System.out.println("fine di tutto !!!!");
		board.initialize();

		System.out.println("Attenzione !!!!");
		System.out.println("\n" + board.towersToString());
		for (Effect effect : this.board.getCouncilPalace().getSpaces().get(0).getBonus())
			System.out.println("\n" + effect.toString() + "\n");

		this.board.excommunicatePlayer(this.board.getPlayers().get(1));

		this.board.getPlayers().get(0).getPlayerCards()
				.addTerritoryCard((TerritoryCard) board.getTowers().get(0).getFloors().get(0).getCard());
		this.board.getPlayers().get(0).getPlayerCards()
				.addTerritoryCard((TerritoryCard) board.getTowers().get(0).getFloors().get(1).getCard());
		this.board.getPlayers().get(0).getPlayerCards()
				.addTerritoryCard((TerritoryCard) board.getTowers().get(0).getFloors().get(2).getCard());
		this.board.getPlayers().get(0).getPlayerCards()
				.addTerritoryCard((TerritoryCard) board.getTowers().get(0).getFloors().get(3).getCard());
		this.board.getPlayers().get(0).getPlayerCards()
				.addTerritoryCard((TerritoryCard) board.getTerritoryCardPool().get(4));
		this.board.getPlayers().get(0).getPlayerCards()
		.addTerritoryCard((TerritoryCard) board.getTerritoryCardPool().get(5));
		this.board.getPlayers().get(0).getPlayerCards()
		.addTerritoryCard((TerritoryCard) board.getTerritoryCardPool().get(6));
		System.out.println("TERRITORY CARD F PLAYER ARE "+this.board.getPlayers().get(0).getPlayerCards().getArrayTerritoryCards().size());
		
		this.board.getPlayers().get(0).getPlayerCards()
		.addBuildingCard((BuildingCard) board.getBuildingCardPool().get(0));
		this.board.getPlayers().get(0).getPlayerCards()
		.addBuildingCard((BuildingCard) board.getBuildingCardPool().get(1));
		this.board.getPlayers().get(0).getPlayerCards()
		.addBuildingCard((BuildingCard) board.getBuildingCardPool().get(2));
		this.board.getPlayers().get(0).getPlayerCards()
		.addBuildingCard((BuildingCard) board.getBuildingCardPool().get(3));
		this.board.getPlayers().get(0).getPlayerCards()
		.addBuildingCard((BuildingCard) board.getBuildingCardPool().get(4));
		this.board.getPlayers().get(0).getPlayerCards()
		.addBuildingCard((BuildingCard) board.getBuildingCardPool().get(5));
		
		this.board.getPlayers().get(0).getPlayerCards()
		.addCharacterCard((CharacterCard) board.getCharacterCardPool().get(0));
		this.board.getPlayers().get(0).getPlayerCards()
		.addCharacterCard((CharacterCard) board.getCharacterCardPool().get(1));
		this.board.getPlayers().get(0).getPlayerCards()
		.addCharacterCard((CharacterCard) board.getCharacterCardPool().get(2));
		this.board.getPlayers().get(0).getPlayerCards()
		.addCharacterCard((CharacterCard) board.getCharacterCardPool().get(3));
		this.board.getPlayers().get(0).getPlayerCards()
		.addCharacterCard((CharacterCard) board.getCharacterCardPool().get(4));
		this.board.getPlayers().get(0).getPlayerCards()
		.addCharacterCard((CharacterCard) board.getCharacterCardPool().get(5));
		
		this.board.getPlayers().get(0).getPlayerCards()
		.addVentureCard((VentureCard) board.getVentureCardPool().get(0));	
		this.board.getPlayers().get(0).getPlayerCards()
		.addVentureCard((VentureCard) board.getVentureCardPool().get(1));
		this.board.getPlayers().get(0).getPlayerCards()
		.addVentureCard((VentureCard) board.getVentureCardPool().get(2));
		this.board.getPlayers().get(0).getPlayerCards()
		.addVentureCard((VentureCard) board.getVentureCardPool().get(3));
		this.board.getPlayers().get(0).getPlayerCards()
		.addVentureCard((VentureCard) board.getVentureCardPool().get(4));
		this.board.getPlayers().get(0).getPlayerCards()
		.addVentureCard((VentureCard) board.getVentureCardPool().get(5));
		
		System.out.println("\n\nFinished adding territory cards\n\n");

		this.board.getPlayers().get(0).getPlayerCards().addLeaderCard(board.getLeaderCardPool().get(0));
		this.board.getPlayers().get(0).getPlayerCards().addLeaderCard((LeaderCard) board.getLeaderCardPool().get(1));
		this.board.getPlayers().get(0).getPlayerCards().addLeaderCard((LeaderCard) board.getLeaderCardPool().get(2));
		this.board.getPlayers().get(0).getPlayerCards().addLeaderCard((LeaderCard) board.getLeaderCardPool().get(3));
		this.board.getPlayers().get(0).getPlayerCards().addLeaderCard(board.getLeaderCardPool().get(4));
		this.board.getPlayers().get(0).getPlayerCards().addLeaderCard((LeaderCard) board.getLeaderCardPool().get(5));
		this.board.getPlayers().get(0).getPlayerCards().addLeaderCard((LeaderCard) board.getLeaderCardPool().get(6));
		this.board.getPlayers().get(0).getPlayerCards().addLeaderCard((LeaderCard) board.getLeaderCardPool().get(7));

		System.out.println("\n\nFinished adding cards\n\n");
		this.board.getPlayers().get(0).addResource("coin", 30);

		this.board.getPlayers().get(0).addResource("servant", 30);

		this.board.getPlayers().get(0).addResource("stone", 30);

		this.board.getPlayers().get(0).addResource("wood", 30);
		
		this.board.getPlayers().get(0).addResource("militaryPoint", 30);
		
		this.board.getPlayers().get(0).addResource("victoryPoint", 30);

		this.board.getPlayers().get(0).addResource("faithPoint", 30);

		
		LeaderCardActionCreationRoutine la = new LeaderCardActionCreationRoutine(
				board.getPlayers().get(0).getPlayerName(), board.getPlayers().get(0), board);
		la.prepareAction();
		LeaderCardActionPerformerRoutine lp = new LeaderCardActionPerformerRoutine(la.getLeaderCardAction(), board);
		System.out.println("\n\nstarting to perform\n\n");
		lp.performAction();

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
		 * board.getPlayers().get(2).getPlayerCards()
		 * .addBuildingCard((BuildingCard)
		 * board.getTowers().get(2).getFloors().get(0).getCard());
		 * board.getPlayers().get(2).getPlayerCards()
		 * .addBuildingCard((BuildingCard)
		 * board.getTowers().get(2).getFloors().get(1).getCard());
		 * board.getPlayers().get(2).getPlayerCards()
		 * .addBuildingCard((BuildingCard)
		 * board.getTowers().get(2).getFloors().get(2).getCard());
		 * board.getPlayers().get(2).getPlayerCards()
		 * .addBuildingCard((BuildingCard)
		 * board.getTowers().get(2).getFloors().get(3).getCard());
		 * board.getPlayers().get(2).getPlayerCards()
		 * .addBuildingCard((BuildingCard) board.getBuildingCardPool().get(4));
		 * board.getPlayers().get(2).getPlayerCards()
		 * .addBuildingCard((BuildingCard) board.getBuildingCardPool().get(5));
		 * board.getPlayers().get(2).getPlayerCards()
		 * .addBuildingCard((BuildingCard) board.getBuildingCardPool().get(6));
		 * board.getPlayers().get(2).getPlayerCards()
		 * .addBuildingCard((BuildingCard) board.getBuildingCardPool().get(7));
		 * 
		 * System.out.println( "\n\nPLAYER BUILDING CARDS NOW ARE :\\n\n" +
		 * board.getPlayers().get(0).getPlayerCards().toString());
		 * 
		 * ProductionActionCreationRoutine p = new
		 * ProductionActionCreationRoutine(
		 * board.getPlayers().get(0).getPlayerName(), board.getPlayers().get(0),
		 * board); p.prepareAction();
		 * System.out.println("\nProduction action creation done!\n");
		 * ProductionActionPerformerRoutine pp = new
		 * ProductionActionPerformerRoutine(p.getProductionAction(), board);
		 * pp.performAction();
		 * 
		 * System.out.println("\n\nPLAYER RESOURCES NOW ARE :\\n\n"+board.
		 * getPlayers().get(0).toString()+"\n"); System.out.
		 * println("Message from board: Production action perform ended!");
		 * 
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
		 * 
		 * TowerActionCreationRoutine t = new
		 * TowerActionCreationRoutine(board.getPlayers().get(0).getPlayerName(),
		 * board.getPlayers().get(0), board); t.prepareAction();
		 * TowerActionPerformerRoutine tp= new
		 * TowerActionPerformerRoutine(t.getTowerAction(),board);
		 * System.out.println("\n\nstarting to perform\n\n");
		 * tp.performAction();
		 * 
		 * 
		 * System.out.println("\n\n\nBOARD TEST FINISHED !"); /*
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

		// TEST FOR FINAL CALCULATION ON VICTORY POINTS
		/*
		 * board.getPlayers().get(2).getPlayerCards().addCharacterCard((
		 * CharacterCard)board.getTowers().get(1).getFloors().get(0).getCard());
		 * board.getPlayers().get(2).getPlayerCards().addCharacterCard((
		 * CharacterCard)board.getTowers().get(1).getFloors().get(1).getCard());
		 * board.getPlayers().get(2).getPlayerCards().addCharacterCard((
		 * CharacterCard)board.getTowers().get(1).getFloors().get(2).getCard());
		 * board.getPlayers().get(2).getPlayerCards().addCharacterCard((
		 * CharacterCard)board.getTowers().get(1).getFloors().get(3).getCard());
		 * 
		 * 
		 * System.out.println("\nDECIDING FOR WINNER");
		 * FinalCalculationVictoryPoints fc= new
		 * FinalCalculationVictoryPoints();
		 * System.out.println(fc.decideForWinner(this.board.getPlayers()));
		 * 
		 * 
		 */
	}

	@Test
	public void test() {
	}

}
