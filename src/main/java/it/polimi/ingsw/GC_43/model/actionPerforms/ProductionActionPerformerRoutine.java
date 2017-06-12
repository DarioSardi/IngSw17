package it.polimi.ingsw.GC_43.model.actionPerforms;

import java.util.HashMap;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actions.Action;
import it.polimi.ingsw.GC_43.model.actions.HarvestAction;
import it.polimi.ingsw.GC_43.model.actions.ProductionAction;
import it.polimi.ingsw.GC_43.model.cards.BuildingCard;
import it.polimi.ingsw.GC_43.model.effects.Effect;
import it.polimi.ingsw.GC_43.model.effects.MultipleChoiceEffect;
import it.polimi.ingsw.GC_43.model.effects.ChoiceEffect;


public class ProductionActionPerformerRoutine implements ActionPerformer{
	private ProductionAction productionAction;
	private boolean checkResult;
	private Board board;
	private int index;

	
	//README
	//@require player instance inside the productionAction to be changed into the real one on the server
	//decide if the match of player is better inside here
	
	public ProductionActionPerformerRoutine(ProductionAction productionAction, Board board){
		
		this.productionAction=productionAction;
		this.checkResult=false;
		this.board=board;
		this.index=0;
		//decide if to launch directly here in creation the performAction();
		
	}
	
	
	
	public boolean performAction() {
		
		this.checkResult=true;

		Player player=this.productionAction.getPlayer();
		FamilyMember familyMember= CommonActionPerformerRoutine.matchFamilyMember(player, this.productionAction.getFamilyMemberColor());
		HashMap<String,Integer> playerResourcesCopy=CommonActionPerformerRoutine.copyPlayerResources(player);


		checkAndTryAction(player, familyMember);
		
		
		if(checkResult==true){
			return true;
		}
		else{
			player.setPlayerResources(playerResourcesCopy);
			familyMember.setAlreadyPlaced(false);
			return false;
		}
	}
		
	
	
	


	private void checkAndTryAction(Player player, FamilyMember familyMember){
		
		

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
			if(board.getProductionArea().getSecondarySpace()==null||!(this.board.getProductionArea().getSecondarySpace().execute(familyMember)))
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
		
    	int dieValue=familyMember.getDiceValue()+this.productionAction.getServantsUsed()+player.getPlayerBounusMalus().getBonusProductionArea();
    	
  		 
		for(BuildingCard buildingCard: this.productionAction.getPlayer().getPlayerCards().getArrayBuildingCards()){
			if(dieValue>=buildingCard.getProductionDice()){
				for( Effect effect: buildingCard.getPermaBonus()){
					if(effect.getClass().toString().contains("MultipleChoiceEffect"))
						executeMultipleChoice((MultipleChoiceEffect) effect, player);	
					
					else
						effect.executeEffect(familyMember);
				
				}
			}
		}
	}



	private void executeMultipleChoice(MultipleChoiceEffect effect, Player player) {
		int playerChoice=this.productionAction.getProductionChoices().get(index);
		if(playerChoice!=-1){
			if(effect.getChoices().get(playerChoice).check(player)){
				effect.getChoices().get(playerChoice).executeEffect(player);
			}
			else
				this.checkResult=false;
			}
		index++;
	}





}
  
	


