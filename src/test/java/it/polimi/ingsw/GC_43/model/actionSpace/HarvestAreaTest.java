package it.polimi.ingsw.GC_43.model.actionSpace;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class HarvestAreaTest {

	HarvestArea ha;
	FamilyMember f1;
	ArrayList<Effect> e;	
	Player p;
	@Test
	public void testCheck() {
		
		p=new Player("Dario",5);
		FamilyMember f1=new FamilyMember(p, 0);
		f1.setDieToFamilyMember(4);
		ha=new HarvestArea();
		assertTrue(ha.check(f1));
	}

	@Test
	public void testGetBonusOfArea() {
		p=new Player("Dario",5);
		ha=new HarvestArea();
		assertEquals(p.getPlayerBounusMalus().getBonusProductionArea(),ha.getBonusOfArea(p));
	}

	@Test
	public void testGetPrimarySpace() {
		ha=new HarvestArea();
		assertEquals(ha.getSpaces().get(0),ha.getPrimarySpace());
	}

	@Test
	public void testGetSecondarySpace() {
		ha=new HarvestArea();
		assertEquals(null,ha.getSecondarySpace());
		GlobalVariables.numberOfPlayers=3;
		ha=new HarvestArea();
		assertEquals(ha.getSpaces().get(1),ha.getSecondarySpace());
		
	}

	@Test
	public void testToString() {
		ha=new HarvestArea();
		ha.toString();
	}

}
