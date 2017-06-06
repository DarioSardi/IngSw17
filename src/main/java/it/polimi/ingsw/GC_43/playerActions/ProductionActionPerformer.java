package it.polimi.ingsw.GC_43.playerActions;

import java.util.HashMap;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.cards.BuildingCard;
import it.polimi.ingsw.GC_43.model.effects.Effect;


public class ProductionActionPerformer{
	private ProductionAction productionAction;
	private boolean checkResult;
	private Board board;

	
	//README
	//@require player instance inside the productionAction to be changed into the real one on the server
	//decide if the match of player is better inside here
	
	public ProductionActionPerformer(ProductionAction productionAction, Board board){
		
		this.productionAction=productionAction;
		this.checkResult=false;
		this.board=board;
		//decide if to launch directly here in creation the performAction();
		
	}
	
	
	
	public boolean performAction() {
		this.checkResult=true;
		Player player=this.productionAction.getPlayer();
		FamilyMember familyMember= CommonActionPerformerRoutine.matchFamilyMember(player, productionAction.getFamilyMemberColor());


		checkAndTryAction(player, familyMember);
		
		
		if(checkResult==true){
			executeAction();
			return true;
		}
		else{
		//decide if to make a class message notification
		return false;
		}
	}
		
	
	
	
	private void executeAction() {
		// TODO Auto-generated method stub
		
	}


	private void checkAndTryAction(Player player, FamilyMember familyMember){
		
		
			HashMap<String, Integer> playerResourcesCopy=CommonActionPerformerRoutine.copyPlayerResources(player);

			checkFamilyMemberAlreadyPlaced(familyMember);
			
			checkServantsUsed(player, familyMember);
			
			checkProductionCellSelection(familyMember);
			
			checkProductionPerform(player, familyMember);
			
			
			
	
		
	}







	private void checkProductionCellSelection(FamilyMember familyMember) {
		if(this.productionAction.isPrimaryCellChosen()){
			if(!this.board.getProductionArea().getPrimarySpace().execute(familyMember))
				this.checkResult=false;
		}
		else if(!this.productionAction.isPrimaryCellChosen()){
			if(!this.board.getProductionArea().getSecondarySpace().execute(familyMember))
				this.checkResult=false;
		}
	}



	private void checkFamilyMemberAlreadyPlaced(FamilyMember familyMember) {
		if(familyMember.isAlreadyPlaced()){
			this.checkResult=false;
		}		
	}



	private void checkServantsUsed(Player player, FamilyMember familyMember) {
		int servantsUsed=this.productionAction.getServantsUsed();
		if(!CommonActionPerformerRoutine.checkServansUsed(player,servantsUsed,familyMember))
			this.checkResult=false;
		
		else{
			player.subResource("servant", servantsUsed);
		}		
	}
		
	
	
	
	
			
	private void checkProductionPerform(Player player, FamilyMember familyMember) {
		
    	int dieValue=familyMember.getDiceValue()+this.productionAction.getServantsUsed()+player.getPlayerBounusMalus().getBonusProductionArea();}}
    	
/*  		 
		for(BuildingCard buildingCard: this.productionAction.getPlayer().getPlayerCards().getArrayBuildingCards()){
			if(familyMember.getDiceValue()+this.productionAction.getServantsUsed()+player.getPlayerBounusMalus().getBonusProductionArea()>=buildingCard.getProductionDice()){
				for( Effect effect: buildingCard.getPermaBonus()){
					if(effect.getClass().toString().contains("MultipleChoiceEffect")){
						askForMultipleChoice(effect);	
					}
				
					else if (effect.getClass().toString().contains("ChoiceEffect")){
						askForASingleChoice(effect);
					}
					else(){
						effect.executeEffect(familyMember);
				}
        	}
		}
       
	
	return true;
}		
	}
		*/	
		//RESET FAMILY MEMBER TO NOT ALREADY PLACED IF CHECKRESULT IS FALSE	INT THE END
			
			// TODO README
			//CHECK IF SECONDARY CELLS FOR BOTH PRODUCTION AND HARVEST ARE NULL BECAUSE OF NUMBER OF PLAYERS
			
			
		//TODO to ask for check die and if space occupied
		//TODO to ask for check die and if space occupied
		//TODO to ask for check die and if space occupied
		//TODO to ask for check die and if space occupied
		//TODO to ask for check die and if space occupied
		//TODO to ask for check die and if space occupied
		//TODO to ask for check die and if space occupied

	//	checkChoices(familyMember.getDiceValue()+player.getPlayerBounusMalus().getBonusProductionArea(), player);
	
	//	familyMember.
		// TODO to implement: getting inputs from class ProductionAction which will
		//come from the client to the server and will manage it performing it
		//with relative checks on things
		
	




	
	/*@require bonus malus on familyMember die to be already applied, but that's normal*/ 
/*	private boolean checkChoices(int dieValue,Player player ){

			 
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
		return true;
	}



	
	*/
	
	


