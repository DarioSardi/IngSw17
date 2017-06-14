package it.polimi.ingsw.GC_43.model.initialization;

import java.util.ArrayList;
import org.junit.Test;
import it.polimi.ingsw.GC_43.model.Board;

public class InitGameTest {

	@Test
	public void testInitGame() {
		ArrayList<String> players = new ArrayList<>();
		players.add("Samuel");
		Board board = new Board(players);
		new InitGame(board);
		
		board.getBuildingCardPool().stream().forEach(c->System.out.println(c.toString()));
		System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
		board.getCharacterCardPool().stream().forEach(c->System.out.println(c.toString()));
		System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
		board.getTerritoryCardPool().stream().forEach(c->System.out.println(c.toString()));
		System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
		board.getVentureCardPool().stream().forEach(c->System.out.println(c.toString()));
		
	}

	@Test
	public void testMain() {
		//fail("Not yet implemented");
	}

}
