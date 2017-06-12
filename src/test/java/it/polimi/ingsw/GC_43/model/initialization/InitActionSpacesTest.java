package it.polimi.ingsw.GC_43.model.initialization;

import static org.junit.Assert.*;

import org.junit.Test;

public class InitActionSpacesTest {

	@Test
	public void testInitActionSpaces() {
		InitActionSpaces init = new InitActionSpaces();
		init.readJson();
	}

}
