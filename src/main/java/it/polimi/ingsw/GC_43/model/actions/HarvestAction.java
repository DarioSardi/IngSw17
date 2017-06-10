package it.polimi.ingsw.GC_43.model.actions;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;

public class HarvestAction {
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
        this.playerID=playerID;
        this.harvestChoices=new ArrayList<>();
        this.servantsUsed=0;
        this.player=player;
        this.ActionID=0;
    }


    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public String getPlayerID() {
        return playerID;
    }
    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }
    public int getFamilyMemberColor() {
        return familyMemberColor;
    }
    public void setFamilyMemberColor(int familyMemberColor) {
        this.familyMemberColor = familyMemberColor;
    }
    public FamilyMember getFamilyMember() {
        return familyMember;
    }
    public void setFamilyMember(FamilyMember familyMember) {
        this.familyMember = familyMember;
    }
    public int getServantsUsed() {
        return servantsUsed;
    }
    public void setServantsUsed(int servantsUsed) {
        this.servantsUsed = servantsUsed;
    }
    public ArrayList<Integer> getProductionChoices() {
        return harvestChoices;
    }
    public void setProductionChoices(ArrayList<Integer> productionChoices) {
        this.harvestChoices = productionChoices;
    }


    public int getActionID() {
        return ActionID;
    }


    public void setActionID(int actionID) {
        ActionID = actionID;
    }


    public boolean isPrimaryCellChosen() {
        return primaryCellChosen;
    }


    public void setPrimaryCellChosen(boolean primaryCellChosen) {
        this.primaryCellChosen = primaryCellChosen;
    }


}



