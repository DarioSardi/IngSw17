package it.polimi.ingsw.GC_43.model.actions;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;

public class HarvestAction extends Action {
    private Player player;
    private String playerID;
    private int familyMemberColor;
    private FamilyMember familyMember;
    private int ActionID;
    //THINK OF ACTION ID BEST PRACTICE WAY
    private int servantsUsed;
    private ArrayList<Integer> harvestChoices;
    private boolean primaryCellChosen;

    public HarvestAction(String playerID, Player player){
    	super(playerID,player);
        this.harvestChoices=new ArrayList<>();
        this.ActionID=1;
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
	


}



