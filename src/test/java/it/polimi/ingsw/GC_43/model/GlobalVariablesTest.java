package it.polimi.ingsw.GC_43.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import it.polimi.ingsw.GC_43.model.initialization.GlobalVariablesInit;
import it.polimi.ingsw.GC_43.model.initialization.InitGame;

public class GlobalVariablesTest {

	@Test
	public void testCreateCopyGlobalVariables() {
		CopyOfGlobalVariables copy = new CopyOfGlobalVariables();
		GlobalVariablesInit.readGlobalVariables();
		new GlobalVariables().createCopyGlobalVariables(copy);
	}

}
