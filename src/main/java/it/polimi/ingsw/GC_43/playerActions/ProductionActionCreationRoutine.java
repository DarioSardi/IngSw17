package it.polimi.ingsw.GC_43.playerActions;

import java.util.Scanner;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.cards.BuildingCard;
import it.polimi.ingsw.GC_43.model.effects.Effect;

public class ProductionActionCreationRoutine implements ActionCreation {
	private ProductionAction productionAction;
	//player ID will be the ID of the instance of playerImpl != player of the model 
	public ProductionActionCreationRoutine(String playerID, Player player){	
		this.productionAction=new ProductionAction(playerID, player);
	}
	
	
	public boolean prepareAction() {
		this.productionAction.setFamilyMember(askForFamilyMemberChoice());
		this.productionAction.setFamilyMemberColor(this.productionAction.getFamilyMember().getColor());
		askForServantsUsage();
		getInputsForProduction();
		
		return false;
	}

	private void askForServantsUsage() {
		// TODO check the boolean 2 servants count as one !!!!!!
		
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
	
	
	

	private int askForSingleChoice(String question,int minRange, int maxRange) {
		boolean choiceOk=false;
		int choiceTaken=0;
		while(!choiceOk){
			Scanner reader= new Scanner(System.in);

			System.out.println("Please select the familiar you want to use to perform the action:\n");
		
			try{
				int choice=reader.nextInt();
				if(choice>=0&&choice<=GlobalVariables.numberOfFamilyMembers){
					choiceOk=true;
					choiceTaken=choice;
				}
			}
			catch(Exception e){
				e.printStackTrace();
				System.out.println("\ninput choice not valid");
			}
		}
		return choiceTaken;
	}
	
	
	private void askForASingleChoice(Effect effect) {
		String question="Do you want to perform this resource exchange?\n Input 1 for yes I do, 0 for I don't\n";
		int choice=askForSingleChoice(question,0,1);	
		this.productionAction.getProductionChoices().add(choice);
		
	}
	
	private void askForMultipleChoice(Effect effect) {		
		String question="Please select the exchange effect you want to perform. Input -1 as do nothing:\n"+effect.toString();
		int choice=askForSingleChoice(question,-1,1);
		if(effect.check(this.productionAction.getFamilyMember())){
			this.productionAction.getProductionChoices().add(choice);
		}
		else{
			question="\nYou can't do this action because you do not have enough resources. Insert 0 to leave this choice or 1 to retry";
			choice= askForSingleChoice(question,0,1);
			if(choice==0){
				System.out.println("\nChoice skipped");
			}
			else{
				askForMultipleChoice(effect);
			}
		}
	}
	
	private FamilyMember askForFamilyMemberChoice() {
			String question="Please select the familiar you want to use to perform the action:\n")jbn;
			//+this.player.printFreeFamiliars();
			int choice=askForSingleChoice(question,0,GlobalVariables.numberOfFamilyMembers);
			
			return this.productionAction.getPlayer().getFamilyMember(choice);
	}

	

}
