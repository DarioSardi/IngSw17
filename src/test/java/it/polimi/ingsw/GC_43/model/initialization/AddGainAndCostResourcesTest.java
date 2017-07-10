package it.polimi.ingsw.GC_43.model.initialization;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;

import it.polimi.ingsw.GC_43.model.effects.Effect;
import it.polimi.ingsw.GC_43.model.resources.Resource;

public class AddGainAndCostResourcesTest {

	@Test
	public void testRetResourceEffect() {
		String effectType = "faithPoint";
		int valueEffect = 1;
		Effect effect = new AddGainAndCostResources().retResourceEffect(effectType, valueEffect);
		System.out.println(effect);
		
		effectType = "victoryPoint";
		valueEffect = 1;
		effect = new AddGainAndCostResources().retResourceEffect(effectType, valueEffect);
		System.out.println(effect);
		
		effectType = "sabbia"; //non presente
		valueEffect = 1;
		effect = new AddGainAndCostResources().retResourceEffect(effectType, valueEffect);
		System.out.println(effect);
	}

	@Test
	public void testAddGainAndCostResources() {
		ArrayList<Resource> resources = new ArrayList<>(); //non presente
		String type = "sabbia";
		int value = 1;
		new AddGainAndCostResources().addGainAndCostResources(resources, type, value);
		System.out.println(resources);
	}

}
