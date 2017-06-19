package it.polimi.ingsw.GC_43.model.initialization;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_43.model.Board;

public class InitGameTest {
	private Board board;
	
	@Before
	public void testInitGame() {
		
		GlobalVariablesInit.readGlobalVariables();
		ArrayList<String> players = new ArrayList<>();
		players.add("Samuel");
		players.add("Dario");
		players.add("Francesco");
		this.board = new Board(players);
		new InitGame(board);
	}
	
	@Test
	public void testCards() {
		board.getBuildingCardPool().stream().forEach(c->System.out.println(c.toString()));
		System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
		board.getCharacterCardPool().stream().forEach(c->System.out.println(c.toString()));
		System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
		board.getTerritoryCardPool().stream().forEach(c->System.out.println(c.toString()));
		System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
		board.getVentureCardPool().stream().forEach(c->System.out.println(c.toString()));
		System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
	
	}
}
