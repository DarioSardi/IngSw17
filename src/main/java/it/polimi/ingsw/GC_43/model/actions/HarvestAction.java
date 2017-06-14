package it.polimi.ingsw.GC_43.model.actions;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;

public class HarvestAction extends Action {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Integer> harvestChoices;
    private boolean primaryCellChosen;
	private boolean defaultFamilyMember;

    public HarvestAction(String playerID, Player player){
    	super(playerID,player);
        this.harvestChoices=new ArrayList<>();
        this.setActionID(2);
    }


    public ArrayList<Integer> getHarvestChoices() {
		return harvestChoices;
	}

	public void setHarvestChoices(ArrayList<Integer> harvestChoices) {
		this.harvestChoices = harvestChoices;
	}
	public boolean isPrimaryCellChosen() {
		return primaryCellChosen;
	}
	public void setPrimaryCellChosen(boolean primaryCellChosen) {
		this.primaryCellChosen = primaryCellChosen;
	}


	public boolean isDefaultFamilyMember() {
		return defaultFamilyMember;
	}


	public void setDefaultFamilyMember(boolean defaultFamilyMember) {
		this.defaultFamilyMember = defaultFamilyMember;
	}
	

}



