package it.polimi.ingsw.GC_43.model.actionCreations;


import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.Player;

import it.polimi.ingsw.GC_43.model.actions.LeaderCardAction;

public class LeaderCardActionCreationRoutine implements ActionCreation {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2296517721917669081L;
	private LeaderCardAction leaderCardAction;
    private Board board;

    //player ID will be the ID of the instance of playerImpl != player of the model


    public LeaderCardActionCreationRoutine(String playerID, Player player,Board board){
        this.leaderCardAction=new LeaderCardAction(playerID, player);
        this.board=board;
    }
    
    public boolean prepareAction(){
    	boolean checkResult=true;
    	
    	selectLeaderCard(this.leaderCardAction.getPlayer());
    	
    	lookForChoiceChance();
    	
    	
    	return checkResult;
    }

    
    
    
	private void lookForChoiceChance() {
		// TODO Auto-generated method stub
		
	}

	private void selectLeaderCard(Player player) {
		
	}

}
