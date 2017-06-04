package it.polimi.ingsw.GC_43.playerActions;

        import java.io.Serializable;
        import java.util.ArrayList;
        import java.util.Scanner;

        import it.polimi.ingsw.GC_43.model.FamilyMember;
        import it.polimi.ingsw.GC_43.model.GlobalVariables;
        import it.polimi.ingsw.GC_43.model.Player;
        import it.polimi.ingsw.GC_43.model.actionSpace.ProductionArea;
        import it.polimi.ingsw.GC_43.model.cards.BuildingCard;
        import it.polimi.ingsw.GC_43.model.effects.Effect;


public class ProductionAction extends Action implements Serializable {
    private Player player;
    private String playerID;
    private int familyMemberColor;
    private FamilyMember familyMember;
    private int ActionID;
    //THINK OF ACTION ID BEST PRACTICE WAY
    private int servantsUsed;
    private ArrayList<Integer> productionChoices;
    private boolean primaryCellChosen;

    public ProductionAction(String playerID, Player player){
        this.playerID=playerID;
        productionChoices=new ArrayList<>();
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
        return productionChoices;
    }
    public void setProductionChoices(ArrayList<Integer> productionChoices) {
        this.productionChoices = productionChoices;
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

