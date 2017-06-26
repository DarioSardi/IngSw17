package it.polimi.ingsw.GC_43.model.actionPerforms;

import java.util.HashMap;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actionCreations.CommonActionCreatorRoutine;
import it.polimi.ingsw.GC_43.model.actions.Action;
import it.polimi.ingsw.GC_43.model.actions.HarvestAction;
import it.polimi.ingsw.GC_43.model.actions.ProductionAction;
import it.polimi.ingsw.GC_43.model.cards.BuildingCard;
import it.polimi.ingsw.GC_43.model.effects.Effect;
import it.polimi.ingsw.GC_43.model.effects.MultipleChoiceEffect;
import it.polimi.ingsw.GC_43.model.effects.MultipleCouncilPrivileges;
import it.polimi.ingsw.GC_43.model.effects.ChoiceEffect;

public class ProductionActionPerformerRoutine implements ActionPerformer {
	private ProductionAction productionAction;
	private boolean checkResult;
	private Board board;
	private int index;

	// README
	// @require player instance inside the productionAction to be changed into
	// the real one on the server
	// decide if the match of player is better inside here

	public ProductionActionPerformerRoutine(ProductionAction productionAction, Board board) {

		this.productionAction = productionAction;
		this.checkResult = true;
		this.board = board;
		this.index = 0;
		// decide if to launch directly here in creation the performAction();

	}

	public boolean performAction() {

		this.checkResult = true;

		Player player = this.productionAction.getPlayer();

		FamilyMember familyMember;
		if (!this.productionAction.isDefaultFamilyMember())
			familyMember = CommonActionPerformerRoutine.matchFamilyMember(player,
					this.productionAction.getFamilyMemberColor());
		else
			familyMember = this.productionAction.getFamilyMember();

		HashMap<String, Integer> playerResourcesCopy = CommonActionPerformerRoutine.copyPlayerResources(player);

		checkAndTryAction(player, familyMember);
		System.out.println("check and try finished");

		if (checkResult == true) {
			if (this.productionAction.isPrimaryCellChosen())
				this.board.getProductionArea().getPrimarySpace().execute(familyMember);
			else
				this.board.getProductionArea().getSecondarySpace().execute(familyMember);
			
			System.out.println("\nPRODUCTION ACTION ENDED SUCCESSFULLY\n");

			return true;
		} else {
			System.out.println("Action Not valid, resetting player resources to inital ones and setting family member used free again..");
			player.setPlayerResources(playerResourcesCopy);
			familyMember.setAlreadyPlaced(false);
			return false;
		}
	}

	private void checkAndTryAction(Player player, FamilyMember familyMember) {

		checkFamilyMemberAlreadyPlaced(familyMember);

		checkServantsUsed(player, familyMember);

		checkProductionCellSelection(familyMember);

		checkProductionPerform(player, familyMember);

	}

	private void checkProductionCellSelection(FamilyMember familyMember) {
		try {
			if (this.productionAction.isPrimaryCellChosen()) {
				if (!this.board.getProductionArea().check(familyMember))
					this.checkResult = false;
			} else if (!this.productionAction.isPrimaryCellChosen()) {
				if (board.getProductionArea().getSecondarySpace() == null
						|| !(this.board.getProductionArea().check(familyMember)))
					this.checkResult = false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void checkFamilyMemberAlreadyPlaced(FamilyMember familyMember) {
		if (familyMember.isAlreadyPlaced()) {
			this.checkResult = false;
		}
	}

	private void checkServantsUsed(Player player, FamilyMember familyMember) {
		int servantsUsed = this.productionAction.getServantsUsed();
		try {
			if (!CommonActionPerformerRoutine.checkServansUsed(player, servantsUsed, familyMember))
				this.checkResult = false;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void checkProductionPerform(Player player, FamilyMember familyMember) {

		int dieValue = familyMember.getDiceValue() + this.productionAction.getServantsUsed()
				+ player.getPlayerBounusMalus().getBonusProductionArea();

		try {
			for (BuildingCard buildingCard : this.productionAction.getPlayer().getPlayerCards()
					.getArrayBuildingCards()) {
				if (dieValue >= buildingCard.getProductionDice()) {
					for (Effect effect : buildingCard.getPermaBonus()) {
						if (effect.getClass().toString().contains("MultipleChoiceEffect"))
							executeMultipleChoice((MultipleChoiceEffect) effect, player);
						if (effect.getClass().toString().contains("MultipleCouncilPrivileges")) {
							executeMultipleCouncilPrivilege((MultipleCouncilPrivileges) effect, player);
						}

						else
							effect.executeEffect(familyMember);

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void executeMultipleCouncilPrivilege(MultipleCouncilPrivileges multipleEffect, Player player) {
		MultipleCouncilPrivileges effect = CommonActionCreatorRoutine
				.copyMultiplePrivileges(multipleEffect.getNumberOfCopies());

		System.out.println("\nMultiple council privilege choice detected, checking choices of player");
		int numberOfCopies = effect.getNumberOfCopies();
		try {
			while (numberOfCopies > 0) {
				
				System.out.println("Multiple council privilege exection calling executeMultipleChoice..");

				executeMultipleChoice(effect.getPrivilegeChoices(), player);
				int playerChoice = this.productionAction.getProductionChoices().get(index-1);
				effect.getPrivilegeChoices().getChoices().remove(playerChoice);

				numberOfCopies--;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Finished executeMultipleCouncilPrivilege !\n");

	}

	private void executeMultipleChoice(MultipleChoiceEffect effect, Player player) {
		int playerChoice = this.productionAction.getProductionChoices().get(index);
		ChoiceEffect choiceEffect = (ChoiceEffect) effect.getChoices().get(playerChoice);
		try {
			if (playerChoice != -1) {
				if (choiceEffect.check(player)) {

					// Se risorsa == privilege council, nel execute effect di
					// choiceEffect verrà skippato il gain perchè già preso
					// prima

					if (choiceEffect.getGains().get(0).getClass().toString().contains("CouncilPrivilege")) {
						System.out.println(
								"Check choice effect passed but Multiple council privilege as resource detected, launching corresponding routine..");
						MultipleCouncilPrivileges privilege = new MultipleCouncilPrivileges(1);
						this.index++;
						executeMultipleCouncilPrivilege(privilege, player);
					} else {
						System.out.println(
								"Message from Production action performer: Choice effect check passed, executing it");
						effect.getChoices().get(playerChoice).executeEffect(player);
						System.out.println(
								"Message from Production action performer: Choice effect executed..");
						this.index++;


					}
				}

				else {
					this.checkResult = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}
