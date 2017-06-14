package it.polimi.ingsw.GC_43.model.actionPerforms;

import java.util.HashMap;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actions.Action;
import it.polimi.ingsw.GC_43.model.actions.HarvestAction;
import it.polimi.ingsw.GC_43.model.cards.BuildingCard;
import it.polimi.ingsw.GC_43.model.cards.TerritoryCard;
import it.polimi.ingsw.GC_43.model.effects.Effect;
import it.polimi.ingsw.GC_43.model.effects.MultipleChoiceEffect;
import it.polimi.ingsw.GC_43.model.effects.MultipleCouncilPrivileges;

public class HarvestActionPerformerRoutine implements ActionPerformer {
	private HarvestAction harvestAction;
	private boolean checkResult;
	private Board board;
	private int index;
	
	public HarvestActionPerformerRoutine(HarvestAction harvestAction, Board board){
		this.harvestAction=harvestAction;
		this.checkResult=false;
		this.board=board;
		this.index=0;

		
	}

	@Override
	public boolean performAction() {
		this.checkResult=true;

		Player player=this.harvestAction.getPlayer();
		
		
		FamilyMember familyMember;
		if(!this.harvestAction.isDefaultFamilyMember())
			familyMember= CommonActionPerformerRoutine.matchFamilyMember(player, this.harvestAction.getFamilyMemberColor());
		else
			familyMember=this.harvestAction.getFamilyMember();
		
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
		
		checkHarvestCellSelection(familyMember);
		
		checkHarvestPerform(player, familyMember);
		
		
		
		

	
}







private void checkHarvestCellSelection(FamilyMember familyMember) {
	if(this.harvestAction.isPrimaryCellChosen()){
		if(!this.board.getHarvestArea().getPrimarySpace().execute(familyMember))
			this.checkResult=false;
	}
	else if(!this.harvestAction.isPrimaryCellChosen()){
		if(board.getHarvestArea().getSecondarySpace()==null||!(this.board.getHarvestArea().getSecondarySpace().execute(familyMember)))
			this.checkResult=false;
	}
}



private void checkFamilyMemberAlreadyPlaced(FamilyMember familyMember) {
	if(familyMember.isAlreadyPlaced()){
		this.checkResult=false;
	}		
}



private void checkServantsUsed(Player player, FamilyMember familyMember) {
	int servantsUsed=this.harvestAction.getServantsUsed();
	if(!CommonActionPerformerRoutine.checkServansUsed(player,servantsUsed,familyMember))
		this.checkResult=false;
	
	else{
		player.subResource("servant", servantsUsed);
	}		
}
	




		
private void checkHarvestPerform(Player player, FamilyMember familyMember) {
	
	int dieValue=familyMember.getDiceValue()+this.harvestAction.getServantsUsed()+player.getPlayerBounusMalus().getBonusHarvestArea();
	
		 
	for(TerritoryCard territoryCard: this.harvestAction.getPlayer().getPlayerCards().getArrayTerritoryCards()){
		if(dieValue>=territoryCard.getProductionDice()){
			for( Effect effect: territoryCard.getPermaBonus()){
				if(effect.getClass().toString().contains("MultipleChoiceEffect"))
					executeMultipleChoice((MultipleChoiceEffect) effect, player);
				if(effect.getClass().toString().contains("MultipleCouncilPrivileges"))
					executeMultipleCouncilPrivilege((MultipleCouncilPrivileges) effect, player);
				
				else
					effect.executeEffect(familyMember);
			
			}
		}
	}
}
private void executeMultipleCouncilPrivilege(MultipleCouncilPrivileges effect,Player player) {
	int numberOfCopies=effect.getNumberOfCopies();
	while(numberOfCopies>0){
		executeMultipleChoice(effect.getPrivilegeChoices(),player);
		int playerChoice=this.harvestAction.getHarvestChoices().get(index-1);
		effect.getPrivilegeChoices().getChoices().remove(playerChoice);
		}

		numberOfCopies--;
	}




private void executeMultipleChoice(MultipleChoiceEffect effect, Player player) {
	int playerChoice=this.harvestAction.getHarvestChoices().get(index);
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
