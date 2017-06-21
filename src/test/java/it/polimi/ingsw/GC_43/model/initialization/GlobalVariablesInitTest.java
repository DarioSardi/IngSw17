package it.polimi.ingsw.GC_43.model.initialization;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_43.model.GlobalVariables;

public class GlobalVariablesInitTest {

	@Test
	public void testReadGlobalVariables() {
		GlobalVariablesInit.readGlobalVariables();
		System.out.println(GlobalVariables.councilPrivilegeEffect);
	}

}
