package it.polimi.ingsw.GC_43.model.initialization;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_43.model.GlobalVariables;

public class GlobalVariablesInitTest {
	private GlobalVariables globalVar;

	@Test
	public void testReadGlobalVariables() {
		this.globalVar = new GlobalVariables();
		GlobalVariablesInit.readGlobalVariables(this.globalVar);
		System.out.println(GlobalVariables.numberOfFamilyMembers);
	}

}
