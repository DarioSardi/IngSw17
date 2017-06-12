package it.polimi.ingsw.GC_43.model.initialization;

import static org.junit.Assert.*;

import org.junit.Test;

public class InitGameTest {

	@Test
	public void testInitGame() {
		InitGame game=new InitGame();
		game.initCards.getBuildingCards().stream().forEach(c->System.out.println(c.toString()));
		game.initCards.getCharacterCards().stream().forEach(c->System.out.println(c.toString()));
		game.initCards.getTerritoryCards().stream().forEach(c->System.out.println(c.toString()));
		game.initCards.getVentureCards().stream().forEach(c->System.out.println(c.toString()));
	}

	@Test
	public void testMain() {
		//fail("Not yet implemented");
	}

}
