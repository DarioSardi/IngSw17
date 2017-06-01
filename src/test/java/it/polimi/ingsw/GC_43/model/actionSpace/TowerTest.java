package it.polimi.ingsw.GC_43.model.actionSpace;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;

public class TowerTest {

	@Before
	public void initializeTest(){
	
	}
	
	@Test
	public void testTower() throws IllegalArgumentException {
		new Tower(null, 0);
		new Tower(TowerColors.BUILDINGS_TOWER,2);
		new Tower(null, -3);
	}

	@Test
	public void testGetTowerColor() {
		Tower t1=new Tower(TowerColors.BUILDINGS_TOWER,2);
		Tower t2=new Tower(null,2);
		assertEquals(TowerColors.BUILDINGS_TOWER, t1.getTowerColor());
		assertEquals(null, t2.getTowerColor());
	}

	@Test
	public void testGetFloors() {
		new Tower(null, 0).getFloors();
		new Tower(TowerColors.BUILDINGS_TOWER,2).getFloors();
		new Tower(null, -3).getFloors();
	}

	@Test
	public void testHasPlayerIn() {
		Tower t1=new Tower(TowerColors.BUILDINGS_TOWER,2);
		Player p1=new Player("coso", 4);
		FamilyMember f1= new FamilyMember(p1, 2);
		assertEquals(false, t1.hasPlayerIn(f1));
		//inserire giocatore e ricontrollare
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddFloor() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetTowerOccupied() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheck() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBonusOfArea() {
		fail("Not yet implemented");
	}

	@Test
	public void testResetArea() {
		fail("Not yet implemented");
	}

}
