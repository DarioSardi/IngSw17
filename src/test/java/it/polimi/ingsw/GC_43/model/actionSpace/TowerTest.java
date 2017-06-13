package it.polimi.ingsw.GC_43.model.actionSpace;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.cards.BuildingCard;
import it.polimi.ingsw.GC_43.model.effects.CostEffect;
import it.polimi.ingsw.GC_43.model.effects.Effect;
import it.polimi.ingsw.GC_43.model.effects.ResourceEffect;
import it.polimi.ingsw.GC_43.model.resources.*;

public class TowerTest {

	public Tower t1=new Tower(TowerColors.BUILDINGS_TOWER,2);
	public ResourceEffect re=new ResourceEffect(new Stone(2));
	public ArrayList<Effect> reA=new ArrayList<>();
	public ArrayList<Resource> costs=new ArrayList<Resource>();
	public CostEffect cost=new CostEffect(costs);
	public ArrayList<Effect> effects= new ArrayList<Effect>();
	public  BuildingCard card=new BuildingCard("cimitero", 2, cost, effects, effects, 4);
	public Player p1=new Player("coso1", 4);
	public Player p2=new Player("coso2", 4);
	public FamilyMember f1= new FamilyMember(p1, 2);
	
	
	@Before
	public void initializeTest() throws Exception{
		reA.add(re);
		t1.addFloor(2);
		t1.addFloor(reA, 5);
		effects.add(new ResourceEffect(new Wood(2)));
		costs.add(new Stone(2));
		t1.getFloors().get(0).setCard(card);
	}
	
	@Test
	public void testTower(){
		new Tower(null, 0);
		new Tower(TowerColors.BUILDINGS_TOWER,2);
		
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
	}
	
	@Test(expected=ExceptionInInitializerError.class)
	public void testInvalidTowers(){
		new Tower(null, -3);
	}

	@Test
	public void testHasPlayerIn() throws Exception {
		FamilyMember f2= new FamilyMember(p1, 1);
		assertEquals(true, t1.hasPlayerIn(f1));
		//aggiunti in modo brutale...migliorare con inizializzazione sensata del gioco
		t1.getFloors().get(0).getFamiliarIn().add(f1);
		t1.getFloors().get(1).getFamiliarIn().add(f2);
		assertEquals(false, t1.hasPlayerIn(f2));
	
	}

	@Test
	public void testToStringAll() {
		System.out.println(t1.toStringAll());
	}
	
	@Test
	public void testToString() {
		System.out.println(t1.toString());
	}

	@Test(expected=Exception.class)
	public void testAddFloor() throws Exception {
		t1.addFloor(4);
	}

	@Test
	public void testSetTowerOccupied() {
		t1.setTowerOccupied(true);
		t1.setTowerOccupied(true);
		t1.setTowerOccupied(false);
	}

	@Test
	public void testCheck() throws Exception {
		assertFalse(t1.check(f1));
		t1.setTowerOccupied(true);
		assertTrue(t1.check(f1));
	}

	@Test
	public void testGetBonusOfArea() {
		System.out.println(t1.getBonusOfArea(p1));
	}

	@Test
	public void testResetArea() {
		t1.setTowerOccupied(true);
		t1.resetArea();
		assertFalse(t1.check(f1));
	}
	
	@Test
	public void testColorCheck(){
		Floor floor=t1.getFloors().get(0);
		assertEquals(true, t1.checkColor(f1));
		floor.addFamiliarIn(f1);
		assertEquals(false, t1.checkColor(f1));
	}

}
