package it.polimi.ingsw.GC_43.model.actionPerforms;

import java.util.HashMap;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actions.CouncilPalaceAction;
import it.polimi.ingsw.GC_43.model.actions.ProductionAction;
import it.polimi.ingsw.GC_43.model.cards.TerritoryCard;
import it.polimi.ingsw.GC_43.model.effects.Effect;
import it.polimi.ingsw.GC_43.model.effects.MultipleChoiceEffect;

public class CouncilPalacePerformerRoutine implements ActionPerformer {
	
	private CouncilPalaceAction councilPalaceAction;
	private boolean checkResult;
	private Board board;
	private int index;

	
	//README
	//@require player instance inside the productionAction to be changed into the real one on the server
	//decide if the match of player is better inside here
	
	public CouncilPalacePerformerRoutine(CouncilPalaceAction councilPalaceAction, Board board){
		
		this.councilPalaceAction=councilPalaceAction;
		this.checkResult=false;
		this.board=board;
		this.index=0;
		//decide if to launch directly here in creation the performAction();
		
	}
/*	
public void performAction(){
	this.checkResult=true;

	Player player=this.councilPalaceAction.getPlayer();
	FamilyMember familyMember= CommonActionPerformerRoutine.matchFamilyMember(player, this.councilPalaceAction.getFamilyMemberColor());
	HashMap<String,Integer> playerResourcesCopy=CommonActionPerformerRoutine.copyPlayerResources(player);


	checkAndTryAction(player, familyMember);
	
/*	
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
			
			else
				effect.executeEffect(familyMember);
		
		}
	}
}
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

*/


	@Override
	public boolean performAction() {
		// TODO Auto-generated method stub
		return false;
	}


}

