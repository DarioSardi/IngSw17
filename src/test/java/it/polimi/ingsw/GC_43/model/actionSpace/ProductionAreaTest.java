package it.polimi.ingsw.GC_43.model.actionSpace;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class ProductionAreaTest {

	ProductionArea pa;
	FamilyMember f1;
	ArrayList<Effect> e;	
	Player p;
	@Test
	public void testCheck() {
		p=new Player("Dario",5);
		FamilyMember f1=new FamilyMember(p, 0);
		f1.setDieToFamilyMember(4);
		pa=new ProductionArea();
		assertTrue(pa.check(f1));
	}

	@Test
	public void testGetBonusOfArea() {
		p=new Player("Dario",5);
		pa=new ProductionArea();
		assertEquals(p.getPlayerBounusMalus().getBonusProductionArea(),pa.getBonusOfArea(p));
	}

	@Test
	public void testGetPrimarySpace() {
		pa=new ProductionArea();
		assertEquals(pa.getSpaces().get(0),pa.getPrimarySpace());
	}

	@Test
	public void testGetSecondarySpace() {
		pa=new ProductionArea();
		assertEquals(null,pa.getSecondarySpace());
		GlobalVariables.numberOfPlayers=3;
		pa=new ProductionArea();
		assertEquals(pa.getSpaces().get(1),pa.getSecondarySpace());
		
	}

	@Test
	public void testToString() {
		pa=new ProductionArea();
		pa.toString();
	}

}
