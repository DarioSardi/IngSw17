package it.polimi.ingsw.GC_43.model.actionPerforms;

import java.util.HashMap;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actionCreations.CommonActionCreatorRoutine;
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

	public MarketActionPerformerRoutine(MarketAction marketAction, Board board) {
		this.marketAction = marketAction;
		this.checkResult = true;
		this.board = board;
		this.index = 0;
	}

	public boolean performAction() {
		System.out.println("\nEntered in Market performer routine");
		Player player = this.marketAction.getPlayer();
		System.out.println("Checking familyMember..");
		FamilyMember familyMember = CommonActionPerformerRoutine.matchFamilyMember(player,
				this.marketAction.getFamilyMemberColor());
		System.out.println("Copying player resources..");
		HashMap<String, Integer> playerResourcesCopy = CommonActionPerformerRoutine.copyPlayerResources(player);
		System.out.println("Starting check and try..");

		try {
			checkAndTryAction(player, familyMember);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (this.checkResult == true) {
			System.out.println("\nMARKET ACTION ENDED SUCCESSFULLY\n");

			return true;
		} else {
			try {
				System.out.println(
						"Action Not valid, resetting player resources to inital ones and setting family member used free again..");

				player.setPlayerResources(playerResourcesCopy);
				familyMember.setAlreadyPlaced(false);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
	}

	private void checkAndTryAction(Player player, FamilyMember familyMember) {

		System.out.println("\nchecking family member values..");

		checkFamilyMemberAlreadyPlaced(familyMember);
		System.out.println("ok family member values..\n" + this.marketAction.getFamilyMember().toString());

		checkServantsUsed(player, familyMember);
		System.out.println("ok servants number used " + this.marketAction.getServantsUsed());

		System.out.println("\nOLD PLAYER RESOURCES\n" + player.toString());

		checkMarketPerform(player, familyMember);

		System.out.println("\nNEW PLAYER RESOURCES\n" + player.toString());

	}

	private void checkMarketPerform(Player player, FamilyMember familyMember) {

		familyMember.addFamilyMemberValue(this.marketAction.getServantsUsed());

		try {
			if (!(player.getPlayerBounusMalus().isNoMarketActionSpaceBonus())
					&& this.board.getMarket().getMarketActionSpaces().get(this.marketAction.getMarketActionSpaceSelected()).execute(familyMember)) {

				for (Effect effect : this.board.getMarket().getMarketActionSpaces()
						.get(this.marketAction.getMarketActionSpaceSelected()).getBonus()) {

					if (effect.getClass().toString().contains("\nMultipleCouncilPrivileges")) {
						MultipleCouncilPrivileges multipleEffect = (MultipleCouncilPrivileges) effect;
						executeMultipleCouncilPrivilege(multipleEffect, player);
					}
				}
			} else {
				System.out.println("Market space is already occupied!");
				this.checkResult = false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		familyMember.subFamilyMemberValue(this.marketAction.getServantsUsed());

	}

	private void executeMultipleCouncilPrivilege(MultipleCouncilPrivileges multipleEffect, Player player) {
		MultipleCouncilPrivileges effect = CommonActionCreatorRoutine
				.copyMultiplePrivileges(multipleEffect.getNumberOfCopies());

		System.out.println("\nMultiple council privilege choice detected, checking choices of player");
		int numberOfCopies = effect.getNumberOfCopies();

		try {
			while (numberOfCopies > 0) {

				System.out.println("Market choices index is " + this.index + " and player choice is"
						+ this.marketAction.getMarketChoices().get(index));
				int playerChoice = this.marketAction.getMarketChoices().get(index);

				executeMultipleChoice(effect.getPrivilegeChoices(), player);

				effect.getPrivilegeChoices().getChoices().remove(playerChoice);
				numberOfCopies--;
				System.out.println("numberOfCopies: " + numberOfCopies);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Finished executeMultipleCouncilPrivilege !\n");

	}

	private void executeMultipleChoice(MultipleChoiceEffect effect, Player player) {
		int playerChoice = this.marketAction.getMarketChoices().get(index);

		try {
			if (playerChoice != -1) {

				if (effect.getChoices().get(playerChoice).check(player)) {
					effect.getChoices().get(playerChoice).executeEffect(player);
				} else
					this.checkResult = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.index++;
		System.out.println("index after multiple choice execution is: " + this.index);
	}

	private void checkFamilyMemberAlreadyPlaced(FamilyMember familyMember) {
		if (familyMember.isAlreadyPlaced()) {
			this.checkResult = false;
		}
	}

	private void checkServantsUsed(Player player, FamilyMember familyMember) {
		int servantsUsed = this.marketAction.getServantsUsed();
		try {
			if (!CommonActionPerformerRoutine.checkServansUsed(player, servantsUsed, familyMember))
				this.checkResult = false;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
