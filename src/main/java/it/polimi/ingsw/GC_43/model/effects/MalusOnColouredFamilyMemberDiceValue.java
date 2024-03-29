package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.FamilyMember;

public class MalusOnColouredFamilyMemberDiceValue extends Effect{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2559893639950369774L;


	private int valueDecreasedBy;
	
	public MalusOnColouredFamilyMemberDiceValue(int valueDecreasedBy){
		this.valueDecreasedBy=valueDecreasedBy;
	}
	
	//FRANCESCO To decide wheteher to set a variable directly into FamilyMember of the player
	public String toString(){
		String toString="Malus on coloured family Member: their die value will be decreased permanently by "+this.valueDecreasedBy;
		return toString;
	}
	public void executeEffect(FamilyMember familyMember){
		System.out.println("Executing MalusOnColouredFamilyMemberDiceValue Effect");

		familyMember.getPlayer().getPlayerBounusMalus().setMalusCoulouredFamiliarsDiceValue(this.valueDecreasedBy);
		
	}

}
