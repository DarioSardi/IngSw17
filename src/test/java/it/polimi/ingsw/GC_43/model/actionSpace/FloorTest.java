package it.polimi.ingsw.GC_43.model.actionSpace;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.cards.BuildingCard;
import it.polimi.ingsw.GC_43.model.effects.CostEffect;
import it.polimi.ingsw.GC_43.model.effects.Effect;
import it.polimi.ingsw.GC_43.model.effects.ResourceEffect;
import it.polimi.ingsw.GC_43.model.initialization.InitGame;
import it.polimi.ingsw.GC_43.model.resources.Coin;
import it.polimi.ingsw.GC_43.model.resources.Resource;
import it.polimi.ingsw.GC_43.model.resources.Stone;
import it.polimi.ingsw.GC_43.model.resources.Wood;

public class FloorTest {

	public Tower t1=new Tower(TowerColors.BUILDINGS_TOWER,2);
	public ResourceEffect re=new ResourceEffect(new Stone(2));
	public ArrayList<Effect> reA=new ArrayList<>();
	public ArrayList<Resource> costs=new ArrayList<Resource>();
	public CostEffect cost=new CostEffect(costs);
	public ArrayList<Effect> effects= new ArrayList<Effect>();
	public  BuildingCard card=new BuildingCard("cimitero", 2, cost, effects, effects, 4, null);
	public Player p1=new Player("coso1", 4);
	public Player p2=new Player("coso2", 4);
	public FamilyMember f1= new FamilyMember(p1, 2);
	
	@Before
	public void initializeTest() throws Exception{
		f1.setDieToFamilyMember(3);
		reA.add(re);
		t1.addFloor(2);
		t1.addFloor(re, 5);
		effects.add(new ResourceEffect(new Wood(2)));
		costs.add(new Coin(2));
		t1.getFloors().get(0).setCard(card);
	}
	@Test
	public void testFamiliarValueCheck() {
		Floor floor=t1.getFloors().get(0);
		assertEquals(true, floor.familiarValueCheck(f1));
		f1.setDieToFamilyMember(0);
		assertEquals(false, floor.familiarValueCheck(f1));
	}

	@Test
	public void testCheckColor() {
		Floor floor=t1.getFloors().get(0);
		Floor floor1=t1.getFloors().get(1);
		
		assertEquals(false, floor.checkColor(f1));
		floor.addFamiliarIn(f1);
		assertEquals(true, floor.checkColor(f1));
	}

	@Test
	public void testExecute() {
		
	}

	@Test
	public void testResetSpace() {
		Floor floor=t1.getFloors().get(0);
		assertEquals(card, floor.getCard());
		floor.resetSpace();
		assertEquals(null, floor.getCard());
	}

	@Test
	public void testIsFloorOccupied() {
		Floor floor=t1.getFloors().get(0);
		floor.resetSpace();
		assertEquals(false, floor.isFloorOccupied());
		floor.addFamiliarIn(f1);
		assertEquals(true, floor.isFloorOccupied());
	}


	@Test
	public void testRemoveCard() {
		Floor floor=t1.getFloors().get(0);
		assertEquals(card, floor.getCard());
		floor.removeCard();
		assertEquals(null, floor.getCard());
	}

	@Test
	public void testToString() {
		Floor floor=t1.getFloors().get(0);
		System.out.println(floor.toString());
	}

	@Test
	public void testToStringAll() {
		Floor floor=t1.getFloors().get(0);
		System.out.println(floor.toStringAll());
	}

}
