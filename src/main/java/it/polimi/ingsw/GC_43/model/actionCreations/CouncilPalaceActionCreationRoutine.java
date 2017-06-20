package it.polimi.ingsw.GC_43.model.actionCreations;

import java.util.HashMap;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actions.CouncilPalaceAction;
import it.polimi.ingsw.GC_43.model.effects.Effect;
import it.polimi.ingsw.GC_43.model.effects.MultipleChoiceEffect;
import it.polimi.ingsw.GC_43.model.effects.MultipleCouncilPrivileges;

public class CouncilPalaceActionCreationRoutine implements ActionCreation{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 5363739742293403421L;

	
	private CouncilPalaceAction councilPalaceAction;
    private Board board;
    private HashMap<String,Integer>copyOfPlayerResource;


    public CouncilPalaceActionCreationRoutine(String playerID, Player player,Board board){
        this.councilPalaceAction=new CouncilPalaceAction(playerID, player);
        this.board=board;
        this.copyOfPlayerResource=CommonActionCreatorRoutine.copyPlayerResources(player);
    }
    
    @Override
    public boolean prepareAction() {
        this.councilPalaceAction.setFamilyMember(CommonActionCreatorRoutine.askForFamilyMemberChoice(this.councilPalaceAction.getPlayer()));
        this.councilPalaceAction.setFamilyMemberColor(this.councilPalaceAction.getFamilyMember().getColor());
        this.councilPalaceAction.setServantsUsed(CommonActionCreatorRoutine.askForServantsUsage(councilPalaceAction.getPlayer(),this.councilPalaceAction.getFamilyMember().getDiceValue()));
        
        getInputsForCouncilPalace(this.councilPalaceAction.getFamilyMember());

        return true;
    }

	private void getInputsForCouncilPalace(FamilyMember familyMember) {
    	int dieValue=familyMember.getDiceValue()+this.councilPalaceAction.getServantsUsed();
    	if(dieValue>=this.board.getCouncilPalace().getCouncil().getMinDiceValue()){
 /*   	for(Effect effect: this.board.getCouncilPalace().getCouncil().getBonus()){
    		  if(effect.getClass().toString().contains("MultipleCouncilPrivileges")){
                  askForMultipleCouncilPrivilege((MultipleCouncilPrivileges)effect);
              }
    	}*/
    		
    }
    	
 }
    private int askForMultipleChoice(MultipleChoiceEffect effect) {
    	int maxRange=effect.getChoices().size();
        String question="Please select the exchange effect you want to perform. Input -1 as do nothing:\n"+effect.toString();
        int choice=CommonActionCreatorRoutine.askForSingleChoice(question,-1,maxRange);
        return choice;
    }

	   private void askForMultipleCouncilPrivilege(MultipleCouncilPrivileges effect) {
	    	int numberOfCopies=effect.getNumberOfCopies();
	    	while(numberOfCopies>0){
	    		int choice= askForMultipleChoice(effect.getPrivilegeChoices());
	    		if(choice!=-1){
	    			effect.getPrivilegeChoices().getChoices().remove(choice);
	    		}

	    		numberOfCopies--;
	    	}
		}

	public CouncilPalaceAction getCouncilPalaceAction() {
		return councilPalaceAction;
	}

	public void setCouncilPalaceAction(CouncilPalaceAction councilPalaceAction) {
		this.councilPalaceAction = councilPalaceAction;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public HashMap<String, Integer> getCopyOfPlayerResource() {
		return copyOfPlayerResource;
	}

	public void setCopyOfPlayerResource(HashMap<String, Integer> copyOfPlayerResource) {
		this.copyOfPlayerResource = copyOfPlayerResource;
	}
	   
	   

}
