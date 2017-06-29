package it.polimi.ingsw.GC_43.model.leaderCards;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class LeaderBonusDieFamiliars extends Effect {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3334961634560975522L;
	private boolean coloured;
	private int additionalValue;

	public LeaderBonusDieFamiliars(boolean coloured, int additionalValue) {
		this.coloured = coloured;
		this.additionalValue = additionalValue;
	}
	
	public String toString() {
		String toString;
		if(this.coloured)
			toString= "Each player couloured familiar will receive an additional die value of "+this.additionalValue;
		else
			toString= "Player neutral familiar will receive an additional die value of "+this.additionalValue;
		
		return toString;
	}
	

	public void executeEffect(FamilyMember familyMember) {
		System.out.println("Executing effect LeaderBonusDieFamiliars");

		if (this.coloured) {
			for (FamilyMember fam : familyMember.getPlayer().getFamilyMembers()) {
				if (fam.getColor() != 0) {
					int newValue = fam.getMalusOnDie() + this.additionalValue;
					fam.setMalusOnDie(newValue);
				}
			}
		} else {
			int newValue = familyMember.getPlayer().findFamilyMemberByColor(0).getMalusOnDie() + this.additionalValue;
			familyMember.getPlayer().findFamilyMemberByColor(0).setMalusOnDie(newValue);
		}

	}

}
