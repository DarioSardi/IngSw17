package it.polimi.ingsw.GC_43.model.actionSpace;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.Resource;
import it.polimi.ingsw.GC_43.model.Stone;
import it.polimi.ingsw.GC_43.model.Wood;
import it.polimi.ingsw.GC_43.model.cards.BuildingCard;
import it.polimi.ingsw.GC_43.model.effects.CostEffect;
import it.polimi.ingsw.GC_43.model.effects.Effect;
import it.polimi.ingsw.GC_43.model.effects.ResourceEffect;

public class TowerTest {

	public Tower t1=new Tower(TowerColors.BUILDINGS_TOWER,2);
	public ResourceEffect re=new ResourceEffect(new Stone(2));
	public ArrayList<Resource> costs=new ArrayList<Resource>();
	public CostEffect cost=new CostEffect(costs);
	public ArrayList<Effect> effects= new ArrayList<Effect>();
	public  BuildingCard card=new BuildingCard("cimitero", 2, cost, effects, effects, 4);
	@Before
	public void initializeTest(){
		t1.addFloor(2);
		t1.addFloor(re, 5);
		effects.add(new ResourceEffect(new Wood(2)));
		costs.add(new Stone(2));
		t1.getFloors().get(0).setCard(card);
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
		System.out.println(t1.toStringAll());
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
