package it.polimi.ingsw.GC_43;

import static org.junit.Assert.*;

import org.junit.Test;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actionSpace.Tower;
import it.polimi.ingsw.GC_43.model.actionSpace.TowerColors;

public class TowerTest {
	Tower testTower=new Tower(TowerColors.TERRITORIES_TOWER,4);
	Player p=new Player("pinuccio", 6);
	FamilyMember f = new FamilyMember(p, 1);
	
	@Test
	public void testCheck() {
		assertEquals("result",false,testTower.check(f));
		testTower.setTowerOccupied();
		assertEquals("result",true,testTower.check(f));
	}

	@Test
	public void testGetBonusOfArea() {
		assertEquals(0, testTower.getBonusOfArea(p));
	}

	@Test
	public void testGetTowerColor() {
		assertEquals(TowerColors.TERRITORIES_TOWER, testTower.getTowerColor());
	}

	@Test
	public void testHasPlayerIn() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddFloor() {
		fail("Not yet implemented");
	}

}
