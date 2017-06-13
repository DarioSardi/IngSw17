package it.polimi.ingsw.GC_43.model.initialization;

import java.util.ArrayList;
import org.junit.Test;
import it.polimi.ingsw.GC_43.model.Board;

public class InitActionSpacesTest {

	@Test
	public void testInitActionSpaces() {
		ArrayList<String> players = new ArrayList<>();
		players.add("Samuel");
		Board board = new Board(players);
		new InitGame(board);
		InitActionSpaces init = new InitActionSpaces();
		init.getTowers().get(1).getFloors().stream().forEach(c->System.out.println(c.toString()));
	}

}
