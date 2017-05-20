package it.polimi.ingsw.GC_43.model;

import it.polimi.ingsw.GC_43.model.actionSpace.ActionSpace;

public class FamilyMember{
    //TODO generata per risolvere le dipendenze
    private int color;
    private boolean alreadyPlaced;
    private ActionSpace familyMemberPosition;
    private Player player;
    private Die die;
    int malus;

    public FamilyMember(Player player, int color){
        this.color = color;
        this.alreadyPlaced = false;
        this.familyMemberPosition = null;
        this.player = player;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public int getColor() {
        return color;
    }
    public void setColor(int color) {
        this.color = color;
    }
    
    public int getMalus() {
        return this.malus;
    }
    public void setMalus(int malus) {
        this.malus = malus;
    }
    
	/**
	 * Check if the player is in an action space
	 * @return if true, the family member is situated in an action space
	 */
    public boolean isAlreadyPlaced() {
        return alreadyPlaced;
    }
    
    public void setAlreadyPlaced(boolean alreadyPlaced) {
        this.alreadyPlaced = alreadyPlaced;
    }

    public int getFamilyMemberValue() {
        return this.die.getDieValue()+this.malus;
    }
    
    public void setDieToFamilyMember(Die die) {
        this.die = die;
        this.die.setFamilyMemberToDie(this);
    }

    private void printFamilyMember(){
        //TODO to implement ( da passare alla view, ritornerà una stringa in realtà)
    }
    public ActionSpace getFamilyMemberPosition() {
        return familyMemberPosition;
    }
    public void setFamilyMemberPosition(ActionSpace actionSpace) {
        this.familyMemberPosition = actionSpace;
     }
    


}












