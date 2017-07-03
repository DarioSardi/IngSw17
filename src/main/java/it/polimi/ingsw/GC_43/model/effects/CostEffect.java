package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.resources.*;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.Player;

public class CostEffect extends Effect {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1415260103830730178L;

	private ArrayList<Resource> Costs;

	public CostEffect(ArrayList<Resource> Costs) {
		this.Costs = Costs;
	}

	public String toString() {
		String toString = "The cost is equal to: \n ";
		for (Resource resource : Costs) {
			toString = toString + resource.getResourceType() + ": " + resource.getValue() + "\n ";
		}
		return toString;
	}

	public boolean check(Player player, String cardType) {
		if (!this.getCosts().isEmpty()) {
			System.out.println("Message from CostEffect: cost is not null");
			for (Resource resource : Costs) {
				if (resource.getResourceType().equals("coin")) {
					if (player.getPlayerResource(resource.getResourceType()) < resource.getValue()
							- player.getPlayerBounusMalus().getBonusCoinsOnBuyInTowers().get(cardType)) {
						return false;
					}
				} else {
					if (player.getPlayerResource(resource.getResourceType()) < resource.getValue())
						return false;
				}

			}
		}

		return true;
	}

	public void executeEffect(Player player, String cardType) {
		System.out.println("Executing CostEffect Effect");

		for (Resource resource : Costs) {
			if (resource.getResourceType().equals("coin")) {
				int oldValue=player.getPlayerResource("coin");
				player.subResource(resource.getResourceType(),
						resource.getValue() - player.getPlayerBounusMalus().getBonusCoinsOnBuyInTowers().get(cardType)-player.getPlayerBounusMalus().getCoinDiscountOnCards());
				
				if(player.getPlayerResource("coin")>oldValue){
					
					player.subResource("coin", player.getPlayerResource("coin"));
					player.addResource("coin", oldValue);
				}
				
			} else {
				player.subResource(resource.getResourceType(), resource.getValue());
			}
		}
	}

	public void executeEffect(FamilyMember familyMember) {
		System.out.println("Executing WRONG CostEffect method");
	}

	public ArrayList<Resource> getCosts() {
		return Costs;
	}

	public void setCosts(ArrayList<Resource> costs) {
		Costs = costs;
	}

}
