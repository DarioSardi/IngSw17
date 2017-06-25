package it.polimi.ingsw.GC_43.model.actionSpace;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class MarketActionSpaceTest {
	
	MarketActionSpace ma;
	FamilyMember f1;
	ArrayList<Effect> e;	
	@Test
	public void testCheck() {
		ArrayList<Effect> e=new ArrayList<>();
		FamilyMember f1=new FamilyMember(new Player("Dario",5), 0);
		f1.setDieToFamilyMember(4);
		MarketActionSpace ma=new MarketActionSpace(e, 1);
		assertTrue(ma.check(f1));
	}

}
