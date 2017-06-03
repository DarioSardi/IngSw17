package it.polimi.ingsw.GC_43.playerActions;

import java.util.Scanner;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.cards.BuildingCard;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class ProductionActionCreationRoutine implements ActionCreation {
	private ProductionAction productionAction;
	private CommonActionCreatorRoutine creatorRoutine;
	//player ID will be the ID of the instance of playerImpl != player of the model 
	
	
	public ProductionActionCreationRoutine(String playerID, Player player){	
		this.productionAction=new ProductionAction(playerID, player);
	}
	
	
	public boolean prepareAction() {
		this.productionAction.setFamilyMember(CommonActionCreatorRoutine.askForFamilyMemberChoice(this.productionAction.getPlayer()));
		this.productionAction.setFamilyMemberColor(this.productionAction.getFamilyMember().getColor());
		productionAction.setServantsUsed(CommonActionCreatorRoutine.askForServantsUsage(productionAction.getPlayer(),this.productionAction.getFamilyMember().getDiceValue()));
		getInputsForProduction();
		
		return false;
	}
	
	/*@require bonus malus on familyMember die to be already applied, but that's normal*/ 
	private void getInputsForProduction(){
		 
		for(BuildingCard buildingCard: this.productionAction.getPlayer().getPlayerCards().getArrayBuildingCards()){
			if(this.productionAction.getFamilyMember().getDiceValue()+this.productionAction.getServantsUsed()+this.productionAction.getPlayer().getPlayerBounusMalus().getBonusProductionArea()>=buildingCard.getProductionDice()){
				for( Effect effect: buildingCard.getPermaBonus()){
					if(effect.getClass().toString().contains("MultipleChoiceEffect")){
						askForMultipleChoice(effect);	
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
	
	private void askForMultipleChoice(Effect effect) {		
		String question="Please select the exchange effect you want to perform. Input -1 as do nothing:\n"+effect.toString();
		int choice=CommonActionCreatorRoutine.askForSingleChoice(question,-1,1);
		if(effect.check(this.productionAction.getFamilyMember())){
			this.productionAction.getProductionChoices().add(choice);
		}
		else{
			question="\nYou can't do this action because you do not have enough resources. Insert 0 to leave this choice or 1 to retry";
			choice= CommonActionCreatorRoutine.askForSingleChoice(question,0,1);
			if(choice==0){
				System.out.println("\nChoice skipped");
			}
			else{
				askForMultipleChoice(effect);
			}
		}
	}

	

}
