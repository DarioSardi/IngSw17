package it.polimi.ingsw.GC_43.model.actionPerforms;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.actions.LeaderCardAction;

public class LeaderCardActionPerformerRoutine implements ActionPerformer{
	
	private LeaderCardAction leaderCardAction;
	private boolean checkResult;
	private Board board;
	private int index;

	public LeaderCardActionPerformerRoutine(LeaderCardAction leaderCardAction, Board board) {
		this.leaderCardAction = leaderCardAction;
		this.checkResult = false;
		this.board = board;

	}
	
	public boolean performAction(){
		
		//MATCH LEADER CARD ON MODEL
		//LOOK IF ALREADY USED IF ONCE PER RUND OR PERMANENT
		//CHECK REQUIREMENTS
		//EXECUTE EFFECT
		
		return checkResult;
		
	}

}
