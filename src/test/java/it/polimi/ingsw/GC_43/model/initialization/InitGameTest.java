package it.polimi.ingsw.GC_43.model.initialization;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.GlobalVariables;

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
	public void councilPrivilegeBonus(){
	}
}
