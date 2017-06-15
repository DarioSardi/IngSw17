package it.polimi.ingsw.GC_43.model.actionCreations;

        import java.util.HashMap;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actionSpace.ProductionArea;
import it.polimi.ingsw.GC_43.model.actions.ProductionAction;
import it.polimi.ingsw.GC_43.model.cards.BuildingCard;
import it.polimi.ingsw.GC_43.model.effects.ChoiceEffect;
import it.polimi.ingsw.GC_43.model.effects.Effect;
import it.polimi.ingsw.GC_43.model.effects.MultipleChoiceEffect;
import it.polimi.ingsw.GC_43.model.effects.MultipleCouncilPrivileges;

public class ProductionActionCreationRoutine implements ActionCreation {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProductionAction productionAction;
    private Board board;
    private HashMap<String,Integer>copyOfPlayerResource;
    //player ID will be the ID of the instance of playerImpl != player of the model


    public ProductionActionCreationRoutine(String playerID, Player player,Board board){
        this.productionAction=new ProductionAction(playerID, player);
        this.board=board;
        this.copyOfPlayerResource=CommonActionCreatorRoutine.copyPlayerResources(player);
    }


    public boolean prepareAction() {
    	
    	if(!this.productionAction.isDefaultFamilyMember())
    		this.productionAction.setFamilyMember(CommonActionCreatorRoutine.askForFamilyMemberChoice(this.productionAction.getPlayer()));
        
    	
    	this.productionAction.setFamilyMemberColor(this.productionAction.getFamilyMember().getColor());
        this.productionAction.setServantsUsed(CommonActionCreatorRoutine.askForServantsUsage(productionAction.getPlayer(),this.productionAction.getFamilyMember().getDiceValue()));

        selectProductionSpace(board.getProductionArea());
        getInputsForProduction(this.productionAction.getFamilyMember());

        return false;
    }
    private boolean selectProductionSpace(ProductionArea productionArea) {

        if(productionArea.getSpaces().isEmpty()){
            System.out.println("\nPrimary empty cell selected\n");
            this.productionAction.setPrimaryCellChosen(true);
        }
        else{
        	if(productionArea.getPrimarySpace().execute(this.productionAction.getFamilyMember())){

        	System.out.println("\nPrimary production cell occupied, secondary production cell selected. Family Member will receive a malus on die value of \n"+GlobalVariables.malusUnlimitedCells);
            this.productionAction.setPrimaryCellChosen(false);
			return productionArea.check((this.productionAction.getFamilyMember()));


        	}
       }
       return true;
    }

    
    
    
    
    /*@require bonus malus on familyMember die to be already applied, but that's normal*/
    private void getInputsForProduction(FamilyMember familyMember){
    	int dieValue=familyMember.getDiceValue()+this.productionAction.getServantsUsed()+familyMember.getPlayer().getPlayerBounusMalus().getBonusProductionArea();
        for(BuildingCard buildingCard: this.productionAction.getPlayer().getPlayerCards().getArrayBuildingCards()){
            if(dieValue>=buildingCard.getProductionDice()){
                for( Effect effect: buildingCard.getPermaBonus()){
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

    private int askForMultipleChoice(MultipleChoiceEffect effect) {
    	int maxRange=effect.getChoices().size();
        String question="Please select the exchange effect you want to perform. Input -1 as do nothing:\n"+effect.toString();
        int choice=CommonActionCreatorRoutine.askForSingleChoice(question,-1,maxRange);
        if(effect.check(this.productionAction.getFamilyMember())){
            this.productionAction.getProductionChoices().add(choice);
            ChoiceEffect choiceEffect=effect.getChoices().get(choice);
            
            //Ask per privilege council SE nello scambio, MAX DA RIVEDERE;
            
            if(choiceEffect.getGains().get(0).getClass().toString().contains("privilegeCouncil"))
            	askForMultipleCouncilPrivilege(new MultipleCouncilPrivileges(1));
        }
        else{
            question="\nYou can't do this action because you do not have enough resources. Insert 0 to leave this choice or 1 to retry";
            choice= CommonActionCreatorRoutine.askForSingleChoice(question,-1,maxRange);
            if(choice==0){
                System.out.println("\nChoice skipped");
            }
            else{
                return askForMultipleChoice(effect);
            }
        }
        return choice;
    }


	public ProductionAction getProductionAction() {
		return productionAction;
	}


	public void setProductionAction(ProductionAction productionAction) {
		this.productionAction = productionAction;
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

