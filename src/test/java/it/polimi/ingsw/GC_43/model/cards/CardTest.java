package it.polimi.ingsw.GC_43.model.cards;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import it.polimi.ingsw.GC_43.model.effects.CostEffect;
import it.polimi.ingsw.GC_43.model.effects.Effect;
import it.polimi.ingsw.GC_43.model.effects.ResourceEffect;
import it.polimi.ingsw.GC_43.model.resources.Coin;
import it.polimi.ingsw.GC_43.model.resources.Resource;
import it.polimi.ingsw.GC_43.model.resources.Stone;
import it.polimi.ingsw.GC_43.model.resources.Wood;

public class CardTest {

	BuildingCard bc;
	CharacterCard cc;
	TerritoryCard tc;
	VentureCard vc;
	CostEffect ce;
	ArrayList<Effect> arrayEffect;
	@Before
	public void testCard() {
		ResourceEffect effect=new ResourceEffect(new Stone(2));
		arrayEffect=new ArrayList<>();
		ArrayList<Resource> arrayCost=new ArrayList<>();
		
		arrayEffect.add(effect);
		arrayCost.add(new Wood(3));
		ce=new CostEffect(arrayCost);
		bc=new BuildingCard("Cattedrale", 1, ce, arrayEffect, arrayEffect, 2, null);
		cc=new CharacterCard("principessa", 2, ce, arrayEffect, arrayEffect, null);
		tc=new TerritoryCard("piazza", 3, arrayEffect, arrayEffect, 1, null);
		vc=new VentureCard("avventura", 1, ce, 15, 12, arrayEffect, arrayEffect, null);
	}

	@Test
	public void testGetType() {
		assertEquals("TerritoryCard", tc.getType());
		assertEquals("VentureCard", vc.getType());
		assertEquals("BuildingCard", bc.getType());
		assertEquals("CharacterCard", cc.getType());
	}

	@Test
	public void testGetCardName() {
		assertEquals("Cattedrale", bc.getCardName());
		assertEquals("principessa", cc.getCardName());
		assertEquals("piazza", tc.getCardName());
		assertEquals("avventura", vc.getCardName());
	}

	@Test
	public void testGetCardEra() {
		assertEquals(1, bc.getCardEra());
		assertEquals(2, cc.getCardEra());
		assertEquals(3, tc.getCardEra());
		assertEquals(1, vc.getCardEra());
	}

	@Test
	public void testGetCost() {
		assertEquals(ce,bc.getCost());
		assertEquals(ce,cc.getCost());
		assertEquals(null,tc.getCost());
		assertEquals(ce,vc.getCost());
	}

	@Test
	public void testGetInstantBonus() {
		assertEquals(arrayEffect,bc.getInstantBonus());
		assertEquals(arrayEffect,cc.getInstantBonus());
		assertEquals(arrayEffect,tc.getInstantBonus());
		assertEquals(arrayEffect,vc.getInstantBonus());
	}

	@Test
	public void testGetPermaBonus() {
		assertEquals(arrayEffect,bc.getPermaBonus());
		assertEquals(arrayEffect,cc.getPermaBonus());
		assertEquals(arrayEffect,tc.getPermaBonus());
		assertEquals(arrayEffect,vc.getPermaBonus());
	}

	@Test
	public void testToString() {
		System.out.println(bc.toString());
		System.out.println(cc.toString());
		System.out.println(tc.toString());
		System.out.println(vc.toString());
	}

}
