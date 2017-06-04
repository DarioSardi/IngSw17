package it.polimi.ingsw.GC_43.playerActions;

        import it.polimi.ingsw.GC_43.model.Player;

public abstract class Action {
    private String playerID;
    private Player player;
    private int ActionID;

    public String getPlayerID() {
        return playerID;
    }
    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public int getActionID() {
        return ActionID;
    }
    public void setActionID(int actionID) {
        ActionID = actionID;
    }







}

