package it.polimi.ingsw.GC_43.model.actionPerforms;

import java.util.HashMap;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actionCreations.CommonActionCreatorRoutine;
import it.polimi.ingsw.GC_43.model.actions.CouncilPalaceAction;
import it.polimi.ingsw.GC_43.model.effects.Effect;
import it.polimi.ingsw.GC_43.model.effects.MultipleChoiceEffect;
import it.polimi.ingsw.GC_43.model.effects.MultipleCouncilPrivileges;

public class CouncilPalacePerformerRoutine implements ActionPerformer {

	private CouncilPalaceAction councilPalaceAction;
	private boolean checkResult;
	private Board board;
	private int index;

	// README
	// @require player instance inside the productionAction to be changed into
	// the real one on the server
	// decide if the match of player is better inside here

	public CouncilPalacePerformerRoutine(CouncilPalaceAction councilPalaceAction, Board board) {

		this.councilPalaceAction = councilPalaceAction;
		this.checkResult = false;
		this.board = board;
		this.index = 0;
		// decide if to launch directly here in creation the performAction();

	}

	public boolean performAction() {
		this.checkResult = true;
		System.out.println("\n Entered in Council Palace Action Performing Routine:\n");
		
		System.out.println("Matching player");

		Player player = this.councilPalaceAction.getPlayer();
		
		System.out.println("Matching familyMember of player "+player.getPlayerName());

		FamilyMember familyMember = CommonActionPerformerRoutine.matchFamilyMember(player,
				this.councilPalaceAction.getFamilyMemberColor());
		
		System.out.println("Copying resource of player "+player.getPlayerName());

		
		HashMap<String, Integer> playerResourcesCopy = CommonActionPerformerRoutine.copyPlayerResources(player);
		
		System.out.println("Check and try Action starting");

		try {
			checkAndTryAction(player, familyMember);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (checkResult == true) {
			return true;
		} else {
			System.out.println("action not valid, resetting parameters on server");

			player.setPlayerResources(playerResourcesCopy);
			familyMember.setAlreadyPlaced(false);
			return false;
		}

	}

	private void checkAndTryAction(Player player, FamilyMember familyMember) {
		
		System.out.println("Checking servants used");

		checkServantsUsed(player, familyMember);
		
		System.out.println("Checking Action parameters");

		checkCouncilPalacePerform(player, familyMember);
		
		System.out.println("\nCheck and try finished");

	}

	private void checkServantsUsed(Player player, FamilyMember familyMember) {
		int servantsUsed = this.councilPalaceAction.getServantsUsed();
		if (!CommonActionPerformerRoutine.checkServansUsed(player, servantsUsed, familyMember))
			this.checkResult = false;
	}

	private void checkCouncilPalacePerform(Player player, FamilyMember familyMember) {

		int dieValue = familyMember.getDiceValue() + this.councilPalaceAction.getServantsUsed();
		System.out.println("Checking Council Palace bonus choice");

		try {
			if (dieValue >= this.board.getCouncilPalace().getCouncil().getMinDiceValue()) {
				
				System.out.println("Minimum die value to enter in this action space is "+this.board.getCouncilPalace().getCouncil().getMinDiceValue());

				for (Effect effect : this.board.getCouncilPalace().getCouncil().getBonus()) {
					if(effect==null){
						System.out.println("Effect is null");

					}
					System.out.println("Bonus in in council palace action are "+this.board.getCouncilPalace().getCouncil().getBonus().toString());

					if (effect.getClass().toString().contains("MultipleCouncilPrivileges"))
						executeMultipleCouncilPrivilege((MultipleCouncilPrivileges) effect, player);

				}
				
				this.board.getCouncilPalace().getCouncil().execute(familyMember);

				System.out.println("check choices ended");

				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	
	private void executeMultipleCouncilPrivilege(MultipleCouncilPrivileges multipleEffect,Player player) {
		MultipleCouncilPrivileges effect=CommonActionCreatorRoutine.copyMultiplePrivileges(multipleEffect.getNumberOfCopies());

		System.out.println("\nMultiple council privilege choice detected, checking choices of player");
		int numberOfCopies=effect.getNumberOfCopies();

		try {
			while(numberOfCopies>0){

				System.out.println("Council Palace choices index is "+this.index+" and player choice is"+this.councilPalaceAction.getCouncilPalaceChoices().get(index));
				int playerChoice=this.councilPalaceAction.getCouncilPalaceChoices().get(index);

				executeMultipleChoice(effect.getPrivilegeChoices(),player);

				effect.getPrivilegeChoices().getChoices().remove(playerChoice);
				numberOfCopies--;
				System.out.println("numberOfCopies: "+numberOfCopies);


				
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Finished executeMultipleCouncilPrivilege !\n");

	}





	private void executeMultipleChoice(MultipleChoiceEffect effect, Player player) {
		int playerChoice=this.councilPalaceAction.getCouncilPalaceChoices().get(index);

		try {
			if(playerChoice!=-1){

				if(effect.getChoices().get(playerChoice).check(player)){
					effect.getChoices().get(playerChoice).executeEffect(player);
				}
				else
					this.checkResult=false;
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.index++;
		System.out.println("index after multiple choice execution is: "+this.index);
	}

}
