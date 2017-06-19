package it.polimi.ingsw.GC_43.model.actions;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;

public class CouncilPalaceAction extends Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Integer> councilPalaceChoices;

	public CouncilPalaceAction(String playerID, Player player) {
		super(playerID, player);
		this.councilPalaceChoices = new ArrayList<>();
		this.setActionID(3);

	}

	public ArrayList<Integer> getCouncilPalaceChoices() {
		return councilPalaceChoices;
	}

	public void setCouncilPalaceChoices(ArrayList<Integer> councilPalaceChoices) {
		this.councilPalaceChoices = councilPalaceChoices;
	}
	
	public String toString(){
		return super.toString()+"Action performed: Council Palace Action\n";
		
	}

}
