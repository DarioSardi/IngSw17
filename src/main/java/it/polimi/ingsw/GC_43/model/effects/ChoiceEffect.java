package it.polimi.ingsw.GC_43.model.effects;

import java.util.ArrayList;
import it.polimi.ingsw.GC_43.model.resources.*;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;

public class ChoiceEffect extends Effect {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3980248365505372817L;

	private ArrayList<Resource> costs;
	private ArrayList<Resource> gains;

	public ChoiceEffect(ArrayList<Resource> costs, ArrayList<Resource> gains) {
		this.costs = new ArrayList<Resource>();
		this.gains = new ArrayList<Resource>();
		this.costs = costs;
		this.gains = gains;
	}

	public String toString() {

		String toString = "Costs = ";
		if (this.costs != null) {
			for (Resource cost : this.costs) {
				toString = toString + cost.getResourceType() + ": " + cost.getValue() + " ";
			}
		}

		toString = toString + "\n   gains = ";

		if (this.gains != null) {
			for (Resource gain : this.gains) {
				toString = toString + gain.getResourceType() + ": " + gain.getValue() + " ";
			}
		}
		return toString;

	}

	public boolean check(Player player) {
		boolean checkResult = true;
		System.out.println("Checking costs inside Choice Effect");
		if (!this.costs.isEmpty()) {
			System.out.println("Cost is not null , checking...");

			for (Resource resource : this.costs) {
				if (player.getPlayerResource(resource.getResourceType()) < resource.getValue())
					checkResult = false;
			}
		}
		System.out.println("Finished checking costs, result is " + checkResult);
		return checkResult;
	}

	public void executeEffect(FamilyMember familyMember) {
		System.out.println("Executing ChoiceEffect Effect");

		executeEffect(familyMember.getPlayer());
	}

	public void executeEffect(Player player) {
		System.out.println("Executing called execution ChoiceEffect Effect");
		System.out.println("Subtracting costs..");

		if (this.costs != null) {
			for (Resource resource : this.costs) {
				player.subResource(resource.getResourceType(), resource.getValue());

			}
		}
		System.out.println("Adding gains..");
		System.out.println("Message from ChoiceEffect: executing : this.gains= " + this.gains);

		for (Resource resource : this.gains) {
			if (!(resource.getClass().toString().contains("CouncilPrivilege"))) {
				System.out.println("Message from ChoiceEffect: executing resource gain " + resource.getResourceType());

				player.addResource(resource.getResourceType(), resource.getValue()
						+ player.getPlayerBounusMalus().getMalusOnAcquiringResources().get(resource.getResourceType()));
			}
		}
		System.out.println("Finished choice effect execution!");

	}

	public ArrayList<Resource> getCosts() {
		return costs;
	}

	public void setCosts(ArrayList<Resource> costs) {
		this.costs = costs;
	}

	public ArrayList<Resource> getGains() {
		return gains;
	}

	public void setGains(ArrayList<Resource> gains) {
		this.gains = gains;
	}

}
