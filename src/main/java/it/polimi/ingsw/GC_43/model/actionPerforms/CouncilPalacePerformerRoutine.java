package it.polimi.ingsw.GC_43.model.actionPerforms;

import java.util.HashMap;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;
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

		Player player = this.councilPalaceAction.getPlayer();
		FamilyMember familyMember = CommonActionPerformerRoutine.matchFamilyMember(player,
				this.councilPalaceAction.getFamilyMemberColor());
		HashMap<String, Integer> playerResourcesCopy = CommonActionPerformerRoutine.copyPlayerResources(player);

		try {
			checkAndTryAction(player, familyMember);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (checkResult == true) {
			return true;
		} else {
			player.setPlayerResources(playerResourcesCopy);
			familyMember.setAlreadyPlaced(false);
			return false;
		}

	}

	private void checkAndTryAction(Player player, FamilyMember familyMember) {

		checkServantsUsed(player, familyMember);

		checkCouncilPalacePerform(player, familyMember);

	}

	private void checkServantsUsed(Player player, FamilyMember familyMember) {
		int servantsUsed = this.councilPalaceAction.getServantsUsed();
		if (!CommonActionPerformerRoutine.checkServansUsed(player, servantsUsed, familyMember))
			this.checkResult = false;

		else {
			player.subResource("servant", servantsUsed);
		}
	}

	private void checkCouncilPalacePerform(Player player, FamilyMember familyMember) {

		int dieValue = familyMember.getDiceValue() + this.councilPalaceAction.getServantsUsed();

		try {
			if (dieValue >= this.board.getCouncilPalace().getCouncil().getMinDiceValue()) {
				for (Effect effect : this.board.getCouncilPalace().getCouncil().getBonus()) {
					if (effect.getClass().toString().contains("MultipleCouncilPrivileges"))
						executeMultipleCouncilPrivileges((MultipleCouncilPrivileges) effect, player);

					else
						effect.executeEffect(familyMember);

				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void executeMultipleCouncilPrivileges(MultipleCouncilPrivileges effect, Player player) {
		executeMultipleChoice(effect.getPrivilegeChoices(), player);
	}

	private void executeMultipleChoice(MultipleChoiceEffect effect, Player player) {
		int playerChoice = this.councilPalaceAction.getCouncilPalaceChoices().get(index);
		try {
			if (playerChoice != -1) {
				if (effect.getChoices().get(playerChoice).check(player)) {
					effect.getChoices().get(playerChoice).executeEffect(player);
				} else
					this.checkResult = false;
			}
			index++;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
