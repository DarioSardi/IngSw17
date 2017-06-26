package it.polimi.ingsw.GC_43.model.actionCreations;

import java.util.HashMap;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actionSpace.HarvestArea;
import it.polimi.ingsw.GC_43.model.actions.HarvestAction;
import it.polimi.ingsw.GC_43.model.cards.TerritoryCard;
import it.polimi.ingsw.GC_43.model.effects.ChoiceEffect;
import it.polimi.ingsw.GC_43.model.effects.Effect;
import it.polimi.ingsw.GC_43.model.effects.MultipleChoiceEffect;
import it.polimi.ingsw.GC_43.model.effects.MultipleCouncilPrivileges;

public class HarvestActionCreationRoutine implements ActionCreation {
    /**
	 * 
	 */
	private static final long serialVersionUID = 789823128986699469L;

	
	private HarvestAction harvestAction;
    private Board board;
    private HashMap<String,Integer>copyOfPlayerResource;

    //player ID will be the ID of the instance of playerImpl != player of the model


    public HarvestActionCreationRoutine(String playerID, Player player,Board board){
        this.harvestAction=new HarvestAction(playerID, player);
        this.board=board;
        this.copyOfPlayerResource=CommonActionCreatorRoutine.copyPlayerResources(player);
        this.harvestAction.setDefaultFamilyMember(false);
    }

    
    @Override
    public boolean prepareAction() {
    	if(!this.harvestAction.isDefaultFamilyMember())
    		this.harvestAction.setFamilyMember(CommonActionCreatorRoutine.askForFamilyMemberChoice(this.harvestAction.getPlayer()));
        
    	this.harvestAction.setFamilyMemberColor(this.harvestAction.getFamilyMember().getColor());
        this.harvestAction.setServantsUsed(CommonActionCreatorRoutine.askForServantsUsage(harvestAction.getPlayer(),this.harvestAction.getFamilyMember().getDiceValue()));

        // TODO to decide if to implements check even on actionPrepare  this.productionAction.getPlayer().subResource("servant",this.productionAction.getServantsUsed());
        boolean check=selectHarvestSpace(board.getHarvestArea());
        getInputsForHarvest(this.harvestAction.getFamilyMember());
		System.out.println("\nHARVEST ACTION ENDS HERE\n");


        return check;
    }
    
    private void getInputsForHarvest(FamilyMember familyMember) {
    	int dieValue=familyMember.getDiceValue()+this.harvestAction.getServantsUsed()+familyMember.getPlayer().getPlayerBounusMalus().getBonusHarvestArea();
    	for(TerritoryCard territoryCard: this.harvestAction.getPlayer().getPlayerCards().getArrayTerritoryCards()){
    		if(dieValue>=territoryCard.getProductionDice()){
    			for( Effect effect: territoryCard.getPermaBonus()){
    				if(effect.getClass().toString().contains("MultipleChoiceEffect")){
    					askForMultipleChoice((MultipleChoiceEffect)effect);
    				}
    				if(effect.getClass().toString().contains("MultipleCouncilPrivileges")){
    					askForMultipleCouncilPrivilege((MultipleCouncilPrivileges)effect);
    				}
    			}
    		}
    	}
    }



//TODO ricordati di fare execute effect sugli space se l'azione va buon fine
    private boolean selectHarvestSpace(HarvestArea harvestArea) {

        if(!harvestArea.getSpaces().get(0).isOccupied()){
            System.out.println("\nPrimary empty cell selected\n");
            this.harvestAction.setPrimaryCellChosen(true);
        }
        else{

			System.out.println("\nPrimary harvest cell occupied, secondary harvest cell selected. Family Member will receive a malus on die value of \n"+GlobalVariables.malusUnlimitedCells);
            this.harvestAction.setPrimaryCellChosen(false);
			this.harvestAction.getFamilyMember().subFamilyMemberValue(GlobalVariables.malusUnlimitedCells);
			return harvestArea.check((this.harvestAction.getFamilyMember()));

        	}
        return true;

        }

    
    
    
    
	private void askForMultipleCouncilPrivilege(MultipleCouncilPrivileges multipleEffect) {
		MultipleCouncilPrivileges effect = CommonActionCreatorRoutine
				.copyMultiplePrivileges(multipleEffect.getNumberOfCopies());
		int numberOfCopies = effect.getNumberOfCopies();
		while (numberOfCopies > 0) {

			int choice = askForMultipleChoice(effect.getPrivilegeChoices());

			if (choice != -1) {
				effect.getPrivilegeChoices().getChoices().remove(choice);
				System.out.println("choice removed..");
				this.harvestAction.getHarvestChoices().add(choice);

			}
			System.out.println("remaining number of copies" + numberOfCopies);
			numberOfCopies--;
		}
	}

	private int askForMultipleChoice(MultipleChoiceEffect effect) {
		int maxRange = effect.getChoices().size();
		String question = "Please select the exchange effect you want to perform. Input -1 as do nothing:\n"
				+ effect.toString();
		int choice = CommonActionCreatorRoutine.askForSingleChoice(question, -1, maxRange);
		if (effect.check(this.harvestAction.getFamilyMember())) {
			this.harvestAction.getHarvestChoices().add(choice);
			ChoiceEffect choiceEffect = effect.getChoices().get(choice);

			// Ask per privilege council SE nello scambio, MAX DA RIVEDERE;

			if (choiceEffect.getGains().get(0).getClass().toString().contains("privilegeCouncil"))
				askForMultipleCouncilPrivilege(new MultipleCouncilPrivileges(1));
		} else {
			question = "\nYou can't do this action because you do not have enough resources. Insert 0 to leave this choice or 1 to retry";
			choice = CommonActionCreatorRoutine.askForSingleChoice(question, -1, maxRange);
			if (choice == 0) {
				System.out.println("\nChoice skipped");
			} else {
				return askForMultipleChoice(effect);
			}
		}
		return choice;
	}
    


	private void askForASingleChoice(Effect effect) {
        String question="Do you want to perform this resource exchange?\n Input 1 for yes I do, 0 for I don't\n";
        int choice=CommonActionCreatorRoutine.askForSingleChoice(question,0,1);
        this.harvestAction.getHarvestChoices().add(choice);

    }



	public HarvestAction getHarvestAction() {
		return harvestAction;
	}


	public void setHarvestAction(HarvestAction harvestAction) {
		this.harvestAction = harvestAction;
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