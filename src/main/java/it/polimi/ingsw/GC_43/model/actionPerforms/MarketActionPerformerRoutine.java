package it.polimi.ingsw.GC_43.model.actionPerforms;

import java.util.HashMap;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actions.HarvestAction;
import it.polimi.ingsw.GC_43.model.actions.MarketAction;
import it.polimi.ingsw.GC_43.model.cards.TerritoryCard;
import it.polimi.ingsw.GC_43.model.effects.Effect;
import it.polimi.ingsw.GC_43.model.effects.MultipleChoiceEffect;
import it.polimi.ingsw.GC_43.model.effects.MultipleCouncilPrivileges;

public class MarketActionPerformerRoutine implements ActionPerformer {
	private MarketAction marketAction;
	private boolean checkResult;
	private Board board;
	private int index;
	
	public MarketActionPerformerRoutine(MarketAction marketAction, Board board){
		this.marketAction=marketAction;
		this.checkResult=false;
		this.board=board;
		this.index=0;	
	}

	
	public boolean performAction(){
		Player player=this.marketAction.getPlayer();
		FamilyMember familyMember= CommonActionPerformerRoutine.matchFamilyMember(player, this.marketAction.getFamilyMemberColor());
		HashMap<String,Integer> playerResourcesCopy=CommonActionPerformerRoutine.copyPlayerResources(player);


		checkAndTryAction(player, familyMember);
		
		
		if(this.checkResult==true){
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
				
		checkMarketPerform(player, familyMember);
		
		
	
}







private void checkMarketPerform(Player player, FamilyMember familyMember) {

	int dieValue=familyMember.getDiceValue()+this.marketAction.getServantsUsed();
	int minDiceValue=this.board.getMarket().getMarketActionSpaces().get(this.marketAction.getMarketChoices().get(index)).getMinDiceValue();
	if(dieValue>=minDiceValue && !(player.getPlayerBounusMalus().isNoMarketActionSpaceBonus())){
		for(Effect effect: this.board.getMarket().getMarketActionSpaces().get(this.marketAction.getMarketChoices().get(this.index)).getBonus()){
			if(effect.getClass().toString().contains("MultipleCouncilPrivileges"))
				executeMultipleCouncilPrivilege((MultipleCouncilPrivileges)effect, player);						
			else
				effect.executeEffect(familyMember);	
			}
		}
	}

private void executeMultipleCouncilPrivilege(MultipleCouncilPrivileges effect,Player player) {
	int numberOfCopies=effect.getNumberOfCopies();
	while(numberOfCopies>0){
		executeMultipleChoice(effect.getPrivilegeChoices(),player);
		int playerChoice=this.marketAction.getMarketChoices().get(index-1);
		effect.getPrivilegeChoices().getChoices().remove(playerChoice);
		}

		numberOfCopies--;
	}




private void executeMultipleChoice(MultipleChoiceEffect effect, Player player) {
	int playerChoice=this.marketAction.getMarketChoices().get(index);
	if(playerChoice!=-1){
		if(effect.getChoices().get(playerChoice).check(player)){
			effect.getChoices().get(playerChoice).executeEffect(player);
		}
		else
			this.checkResult=false;
		}
	index++;
}


private void checkFamilyMemberAlreadyPlaced(FamilyMember familyMember) {
	if(familyMember.isAlreadyPlaced()){
		this.checkResult=false;
	}		
}



private void checkServantsUsed(Player player, FamilyMember familyMember) {
	int servantsUsed=this.marketAction.getServantsUsed();
	if(!CommonActionPerformerRoutine.checkServansUsed(player,servantsUsed,familyMember))
		this.checkResult=false;
	
	else{
		player.subResource("servant", servantsUsed);
	}		
}
	

	
	
	

}
