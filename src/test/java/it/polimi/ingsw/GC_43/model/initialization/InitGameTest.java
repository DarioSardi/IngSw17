package it.polimi.ingsw.GC_43.model.initialization;

import java.util.ArrayList;
import org.junit.Test;
import it.polimi.ingsw.GC_43.model.Board;

public class InitGameTest {

	@Test
	public void testInitGame() {
		GlobalVariablesInit.readGlobalVariables();
		ArrayList<String> players = new ArrayList<>();
		players.add("Samuel");
		players.add("Dario");
		players.add("Francesco");
		Board board = new Board(players);
		new InitGame(board);
		
		System.out.println(board.getCouncilPalace());
	}
}
