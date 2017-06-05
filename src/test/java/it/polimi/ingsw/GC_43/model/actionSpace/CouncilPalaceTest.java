package it.polimi.ingsw.GC_43.model.actionSpace;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.polimi.ingsw.GC_43.model.Coin;
import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.Stone;
import it.polimi.ingsw.GC_43.model.effects.ResourceEffect;

public class CouncilPalaceTest {
	
	ResourceEffect resource=new ResourceEffect(new Coin(1));
	CouncilPalace c=new CouncilPalace(resource);
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
		c.getCouncil().addFamiliarIn(f11);
		c.getCouncil().addFamiliarIn(f31);
		c.getCouncil().addFamiliarIn(f32);
		c.getCouncil().addFamiliarIn(f12);
		c.getCouncil().addFamiliarIn(f21);
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
	public void testCouncilPalace() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsEmpty() {
		fail("Not yet implemented");
	}

	@Test
	public void testEntryOrder() {
		System.out.println("ordine di ingresso:\n");
		c.entryOrder().stream().forEach(p->System.out.println(p.toString()+"\n"));
	}

}
