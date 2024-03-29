package it.polimi.ingsw.GC_43.model.actionSpace;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.effects.Effect;
import it.polimi.ingsw.GC_43.model.effects.ResourceEffect;
import it.polimi.ingsw.GC_43.model.resources.*;

public class CouncilPalaceTest {
	
	ArrayList<Effect> resourceA=new ArrayList<>();
	CouncilPalace c;
	Player p1=new Player("Dario", 5);
		FamilyMember f11=new FamilyMember(p1, 1);
		FamilyMember f12=new FamilyMember(p1, 2);
	Player p2=new Player("Sam", 5);
		FamilyMember f21=new FamilyMember(p2, 1);
		FamilyMember f22=new FamilyMember(p2, 2);
	Player p3=new Player("Francesco", 5);
		FamilyMember f31=new FamilyMember(p3, 1);
		FamilyMember f32=new FamilyMember(p3, 2);
	Player p4=new Player("Coso", 5);
	
	@Before
	public void initPalace(){
		resourceA.add(new ResourceEffect(new Coin(1)));
		c=new CouncilPalace(resourceA);
		c.getCouncil().addFamiliarIn(f11);
		c.getCouncil().addFamiliarIn(f31);
		c.getCouncil().addFamiliarIn(f32);
		c.getCouncil().addFamiliarIn(f12);
		c.getCouncil().addFamiliarIn(f21);
	}

	@Test
	public void testCheck() {
		assertEquals(true,c.check(f22));
		assertEquals(false,c.check(null));
		
	}

	@Test
	public void testGetBonusOfArea() {
		assertEquals(0,c.getBonusOfArea(p1));
	}

	@Test
	public void testToString() {
		c.toString();
	}

	@After
	public void testIsEmpty() {
		assertEquals(false, c.isEmpty());
		c.resetArea();
		assertEquals(true, c.isEmpty());

	}


	@Test
	public void testEntryOrder() {
		System.out.println("ordine di ingresso:\n");
		c.entryOrder().stream().forEach(p->System.out.println(p.toString()+"\n"));
	}

}
