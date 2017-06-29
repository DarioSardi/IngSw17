package it.polimi.ingsw.GC_43.model.actionPerforms;

import java.util.HashMap;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actionCreations.CommonActionCreatorRoutine;
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

	public HarvestActionPerformerRoutine(HarvestAction harvestAction, Board board) {
		this.harvestAction = harvestAction;
		this.checkResult = false;
		this.board = board;
		this.index = 0;

	}

	@Override
	public boolean performAction() {
		this.checkResult = true;

		Player player = this.harvestAction.getPlayer();

		FamilyMember familyMember;
		if (!this.harvestAction.isDefaultFamilyMember())
			familyMember = CommonActionPerformerRoutine.matchFamilyMember(player,
					this.harvestAction.getFamilyMemberColor());
		else
			familyMember = this.harvestAction.getFamilyMember();

		HashMap<String, Integer> playerResourcesCopy = CommonActionPerformerRoutine.copyPlayerResources(player);

		System.out.println("Entering check and try of harvest perform action");

		checkAndTryAction(player, familyMember);

		System.out.println("\nReceiving player default bonus on harvest");

		receiveDefaultHarvestBonus(player, familyMember);

		System.out.println("\n\nFinal result of harvest action is " + this.checkResult);
		if (checkResult == true) {
			if (this.harvestAction.isPrimaryCellChosen())
				this.board.getHarvestArea().getPrimarySpace().execute(familyMember);
			else
				this.board.getHarvestArea().getSecondarySpace().execute(familyMember);

			System.out.println("\nHARVEST ACTION ENDED SUCCESSFULLY\n");

			return true;
		} else {
			System.out.println(
					"Action Not valid, resetting player resources to inital ones and setting family member used free again..");
			player.setPlayerResources(playerResourcesCopy);
			familyMember.setAlreadyPlaced(false);
			return false;
		}

	}

	private void receiveDefaultHarvestBonus(Player player, FamilyMember familyMember) {
		if (!player.getPersonalHarvestBonus().isEmpty()) {
			System.out.println("Executing player personal harvest bonus");
			for (Effect effect : player.getPersonalHarvestBonus())
				effect.executeEffect(familyMember);
		}

	}

	private void checkAndTryAction(Player player, FamilyMember familyMember) {

		System.out.println("Checking family member..");
		checkFamilyMemberAlreadyPlaced(familyMember);
		System.out.println("check result is = " + this.checkResult);
		System.out.println("Checking servants used..");
		checkServantsUsed(player, familyMember);
		System.out.println("check result is = " + this.checkResult);
		System.out.println("Checking harvest cell selection..");
		checkHarvestCellSelection(familyMember);
		System.out.println("check result is = " + this.checkResult);
		System.out.println("Checking harvest perform..");
		checkHarvestPerform(player, familyMember);
		System.out.println("check result is = " + this.checkResult);

	}

	private void checkHarvestCellSelection(FamilyMember familyMember) {
		
			if (this.harvestAction.isPrimaryCellChosen()) {
				if (!this.board.getHarvestArea().check(familyMember)
						|| this.board.getHarvestArea().getSpaces().get(0).isOccupied() == true &&!familyMember.getPlayer().getPlayerBounusMalus().isOkPlaceOccupied())
					this.checkResult = false;
			} else if (!this.harvestAction.isPrimaryCellChosen()) {
				if (board.getHarvestArea().getSecondarySpace() == null
						|| !(this.board.getHarvestArea().check(familyMember)))
					this.checkResult = false;
			}
		
	}

	private void checkFamilyMemberAlreadyPlaced(FamilyMember familyMember) {
		if (familyMember.isAlreadyPlaced()) {
			this.checkResult = false;
		}
	}

	private void checkServantsUsed(Player player, FamilyMember familyMember) {
		int servantsUsed = this.harvestAction.getServantsUsed();
		if (!CommonActionPerformerRoutine.checkServansUsed(player, servantsUsed, familyMember))
			this.checkResult = false;
	}

	private void checkHarvestPerform(Player player, FamilyMember familyMember) {
		int malusOnSecondarySpace = 0;
		if (!this.harvestAction.isPrimaryCellChosen())
			malusOnSecondarySpace = GlobalVariables.malusOnSecondHarvestArea;
		int dieValue = familyMember.getDiceValue() + this.harvestAction.getServantsUsed()
				+ player.getPlayerBounusMalus().getBonusHarvestArea() + malusOnSecondarySpace;

		for (TerritoryCard territoryCard : this.harvestAction.getPlayer().getPlayerCards().getArrayTerritoryCards()) {
			if (dieValue >= territoryCard.getProductionDice()) {

				System.out.println(
						"Executing permanent bonus of " + territoryCard.getCardName() + " card which min die value is "
								+ territoryCard.getProductionDice() + " & familiar die value is " + dieValue);
				for (Effect effect : territoryCard.getPermaBonus()) {
					if (effect.getClass().toString().contains("MultipleChoiceEffect"))
						executeMultipleChoice((MultipleChoiceEffect) effect, player);
					if (effect.getClass().toString().contains("MultipleCouncilPrivileges"))
						executeMultipleCouncilPrivilege((MultipleCouncilPrivileges) effect, player);

					else
						effect.executeEffect(familyMember);

				}
			}
		}
		this.harvestAction.getFamilyMember().addFamilyMemberValue(GlobalVariables.malusOnSecondHarvestArea);

	}

	private void executeMultipleCouncilPrivilege(MultipleCouncilPrivileges multipleEffect, Player player) {
		MultipleCouncilPrivileges effect = CommonActionCreatorRoutine
				.copyMultiplePrivileges(multipleEffect.getNumberOfCopies());

		System.out.println("\nMultiple council privilege choice detected, checking choices of player");
		int numberOfCopies = effect.getNumberOfCopies();

		try {
			while (numberOfCopies > 0) {

				System.out.println("Tower choices index is " + this.index + " and player choice is"
						+ this.harvestAction.getHarvestChoices().get(index));
				int playerChoice = this.harvestAction.getHarvestChoices().get(index);

				executeMultipleChoice(effect.getPrivilegeChoices(), player);

				effect.getPrivilegeChoices().getChoices().remove(playerChoice);
				numberOfCopies--;
				System.out.println("numberOfCopies: " + numberOfCopies);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Finished executing MultipleCouncilPrivilege !\n");

	}

	private void executeMultipleChoice(MultipleChoiceEffect effect, Player player) {
		int playerChoice = this.harvestAction.getHarvestChoices().get(index);
		if (playerChoice != -1) {
			if (effect.getChoices().get(playerChoice).check(player)) {
				effect.getChoices().get(playerChoice).executeEffect(player);
			} else
				this.checkResult = false;
		}
		index++;
	}

}
