package it.polimi.ingsw.GC_43.playerActions;

        import java.util.HashMap;
import java.util.Scanner;

        import it.polimi.ingsw.GC_43.model.Board;
        import it.polimi.ingsw.GC_43.model.FamilyMember;
        import it.polimi.ingsw.GC_43.model.GlobalVariables;
        import it.polimi.ingsw.GC_43.model.Player;
        import it.polimi.ingsw.GC_43.model.actionSpace.ProductionArea;
        import it.polimi.ingsw.GC_43.model.cards.BuildingCard;
import it.polimi.ingsw.GC_43.model.effects.ChoiceEffect;
import it.polimi.ingsw.GC_43.model.effects.Effect;
import it.polimi.ingsw.GC_43.model.effects.MultipleChoiceEffect;

public class ProductionActionCreationRoutine implements ActionCreation {
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
        this.productionAction.setFamilyMember(CommonActionCreatorRoutine.askForFamilyMemberChoice(this.productionAction.getPlayer()));
        this.productionAction.setFamilyMemberColor(this.productionAction.getFamilyMember().getColor());
        this.productionAction.setServantsUsed(CommonActionCreatorRoutine.askForServantsUsage(productionAction.getPlayer(),this.productionAction.getFamilyMember().getDiceValue()));

        // TODO to decide if to implements check even on actionPrepare  this.productionAction.getPlayer().subResource("servant",this.productionAction.getServantsUsed());
        selectProductionSpace(board.getProductionArea());
        getInputsForProduction(this.productionAction.getFamilyMember());

        return false;
    }
//TODO ricordati di fare execute effect sugli space se l'azione va buon fine
    private void selectProductionSpace(ProductionArea productionArea) {

        if(productionArea.getSpaces().isEmpty()){
            System.out.println("\nPrimary empty cell selected\n");
            this.productionAction.setPrimaryCellChosen(true);
        }
        else{
//			System.out.println("\nPrimary production cell occupied, secondary production cell selected. Family Member will receive a malus on die value of \n"+GlobalVariables.malusUnlimitedCells);
            this.productionAction.setPrimaryCellChosen(false);
//			this.productionAction.getFamilyMember().subFamilyMemberValue(GlobalVariables.malusUnlimitedCells);
        }

    }

    
//ARRIVATO QUI 5/06/17  sottrai costi mano a mano da risorse (decommenta sopra con global variable e di a SAM per inizializzazione)
    
    
    
    /*@require bonus malus on familyMember die to be already applied, but that's normal*/
    private void getInputsForProduction(FamilyMember familyMember){
    	int dieValue=familyMember.getDiceValue()+this.productionAction.getServantsUsed()+familyMember.getPlayer().getPlayerBounusMalus().getBonusProductionArea();
        for(BuildingCard buildingCard: this.productionAction.getPlayer().getPlayerCards().getArrayBuildingCards()){
            if(dieValue>=buildingCard.getProductionDice()){
                for( Effect effect: buildingCard.getPermaBonus()){
                    if(effect.getClass().toString().contains("MultipleChoiceEffect")){
                        askForMultipleChoice((MultipleChoiceEffect)effect);
                    }

                    else if (effect.getClass().toString().contains("ChoiceEffect")){
                        askForASingleChoice(effect);
                    }
                }
            }
        }

    }


    private void askForASingleChoice(Effect effect) {
        String question="Do you want to perform this resource exchange?\n Input 1 for yes I do, 0 for I don't\n";
        int choice=CommonActionCreatorRoutine.askForSingleChoice(question,0,1);
        this.productionAction.getProductionChoices().add(choice);

    }

    private void askForMultipleChoice(MultipleChoiceEffect effect) {
    	int maxRange=effect.getChoices().size();
        String question="Please select the exchange effect you want to perform. Input -1 as do nothing:\n"+effect.toString();
        int choice=CommonActionCreatorRoutine.askForSingleChoice(question,-1,maxRange);
        if(effect.check(this.productionAction.getFamilyMember())){
            this.productionAction.getProductionChoices().add(choice);
        }
        else{
            question="\nYou can't do this action because you do not have enough resources. Insert 0 to leave this choice or 1 to retry";
            choice= CommonActionCreatorRoutine.askForSingleChoice(question,-1,maxRange);
            if(choice==0){
                System.out.println("\nChoice skipped");
            }
            else{
                askForMultipleChoice(effect);
            }
        }
    }



}

