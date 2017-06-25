package it.polimi.ingsw.GC_43.model.actionPerforms;

import java.util.HashMap;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actionCreations.CommonActionCreatorRoutine;
import it.polimi.ingsw.GC_43.model.actionSpace.Floor;
import it.polimi.ingsw.GC_43.model.actionSpace.Tower;
import it.polimi.ingsw.GC_43.model.actions.TowerAction;
import it.polimi.ingsw.GC_43.model.cards.Card;
import it.polimi.ingsw.GC_43.model.cards.CardHandler;
import it.polimi.ingsw.GC_43.model.cards.VentureCard;
import it.polimi.ingsw.GC_43.model.effects.Effect;
import it.polimi.ingsw.GC_43.model.effects.MultipleChoiceEffect;
import it.polimi.ingsw.GC_43.model.effects.MultipleCouncilPrivileges;

public class TowerActionPerformerRoutine implements ActionPerformer {
	private TowerAction towerAction;
	private boolean checkResult;
	private Board board;
	private int index;

	// README
	// @require player instance inside the productionAction to be changed into
	// the real one on the server
	// decide if the match of player is better inside here

	public TowerActionPerformerRoutine(TowerAction towerAction, Board board) {

		this.towerAction = towerAction;
		this.checkResult = true;
		this.board = board;
		this.index = 0;
		// decide if to launch directly here in creation the performAction();

	}

	@Override
	public boolean performAction() {
		System.out.println("starting tower performing inside perform action..");
		this.checkResult = true;

		Player player = this.towerAction.getPlayer();
		System.out.println("Player is " + player.getPlayerName() + "..");

		FamilyMember familyMember = CommonActionPerformerRoutine.matchFamilyMember(player,
				this.towerAction.getFamilyMemberColor());
		HashMap<String, Integer> playerResourcesCopy = CommonActionPerformerRoutine.copyPlayerResources(player);
		System.out.println("Adding eventual extra discount..");

		addEventualDiscountedResources(player, this.towerAction.getResourceDiscounts());

		System.out.println("Entering in check and try of the action..");

		checkAndTryAction(player, familyMember);
		System.out.println("Final result of the action is " + this.checkResult);

		if (checkResult == true) {
			return true;
		} else {
			System.out.println("Resetting old player resources & family member free..");
			player.setPlayerResources(playerResourcesCopy);
			familyMember.setAlreadyPlaced(false);
			return false;
		}

	}

	private void addEventualDiscountedResources(Player player, HashMap<String, Integer> resourceDiscounts) {
		player.addResource("coin", resourceDiscounts.get("coin"));
		player.addResource("servant", resourceDiscounts.get("servant"));
		player.addResource("stone", resourceDiscounts.get("stone"));
		player.addResource("wood", resourceDiscounts.get("wood"));
		player.addResource("victoryPoint", resourceDiscounts.get("victoryPoint"));
		player.addResource("militaryPoint", resourceDiscounts.get("militaryPoint"));
		player.addResource("faithPoint", resourceDiscounts.get("faithPoint"));

	}

	private void checkAndTryAction(Player player, FamilyMember familyMember) {

		System.out.println("\nChecking family member already placed..");

		checkFamilyMemberAlreadyPlaced(familyMember);

		System.out.println("Checking servants used..");

		checkServantsUsed(player, familyMember);

		System.out.println("Checking chosen tower & floor..");

		Tower chosenTower = this.board.getTowers().get(this.towerAction.getTowerChoice());

		checkTowerAndFloor(familyMember, player, chosenTower);

		Floor towerFloor = chosenTower.getFloors().get(this.towerAction.getFloorChoice());

		System.out.println("Checking card buy..");

		// System.out.println("PLAYER RESOURCES BEFORE BUY COMPLETED " +
		// player.toString());

		checkCardBuy(player, familyMember, chosenTower, towerFloor, towerFloor.getCard());

		System.out.println("Checking effects of card..");

		checkEffectChoices(player, towerFloor.getCard());

		// System.out.println("PLAYER RESOURCES AFTER BUY COMPLETED
		// "+player.toString());

		if (this.checkResult == true) {
			System.out.println("Attempting to execute actions after buy in floor");
			CardHandler.actionsAfterBuy(towerFloor, familyMember);
		}
		// System.out.println("PLAYER CARDS AFTER BUY COMPLETED
		// "+player.getPlayerCards().toString());
		System.out.println("Check and try finished\n");

	}

	private void executeMultipleCouncilPrivilege(MultipleCouncilPrivileges multipleEffect, Player player) {
		MultipleCouncilPrivileges effect = CommonActionCreatorRoutine
				.copyMultiplePrivileges(multipleEffect.getNumberOfCopies());

		System.out.println("\nMultiple council privilege choice detected, checking choices of player");
		int numberOfCopies = effect.getNumberOfCopies();

		try {
			while (numberOfCopies > 0) {

				System.out.println("Tower choices index is " + this.index + " and player choice is"
						+ this.towerAction.getEffectChoices().get(index));
				int playerChoice = this.towerAction.getEffectChoices().get(index);

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
		int playerChoice = this.towerAction.getEffectChoices().get(index);

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

	private void checkEffectChoices(Player player, Card card) {
		if (!card.getInstantBonus().isEmpty()) {
			try {
				System.out.println("Instant bonus != null, now looking effect by effect");
				for (Effect effect : card.getInstantBonus()) {
					if (effect.getClass().toString().contains("MultipleCouncilPrivileges")) {
						System.out.println(
								"Multiple council privileges effect found, attempting to execute its routine..");
						executeMultipleCouncilPrivilege((MultipleCouncilPrivileges) effect, player);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Checking effect choices ended..");
	}

	private void checkCardBuy(Player player, FamilyMember familyMember, Tower chosenTower, Floor towerFloor,
			Card card) {
		try {
			if (card.getClass().toString().contains("VentureCard") && this.towerAction.getDoubleCostSelection() == 1) {
				System.out.println("Double cost venture card detected, executing it");
				VentureCard ventureCard = (VentureCard) card;
				if (player.getPlayerResource("militaryPoint") >= ventureCard.getMilitaryMin()
						&& ventureCard.getMilitaryCost() > 0
						&& player.getPlayerResource("militaryPoint") >= ventureCard.getMilitaryCost()) {
					System.out.println("Player military points satisfies requirements and costs..");

					player.subResource("militaryPoint", ventureCard.getMilitaryCost());
					boolean buyResult = CardHandler.buyCard(towerFloor, familyMember, card,
							chosenTower.check(familyMember), true);
					System.out.println(
							"Card handler process for double cost venture card finished and result is " + buyResult);
					this.checkResult = true;
				} else {
					this.checkResult = false;
				}
			} else {
				if (!(CardHandler.buyCard(towerFloor, familyMember, card, chosenTower.check(familyMember), false)))
					this.checkResult = false;

				System.out.println("Card handler process finished and result is " + this.checkResult);
			}

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	private void checkFamilyMemberAlreadyPlaced(FamilyMember familyMember) {
		try {
			if (familyMember.isAlreadyPlaced()) {
				this.checkResult = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void checkServantsUsed(Player player, FamilyMember familyMember) {
		int servantsUsed = this.towerAction.getServantsUsed();
		try {
			if (!CommonActionPerformerRoutine.checkServansUsed(player, servantsUsed, familyMember))
				this.checkResult = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void checkTowerAndFloor(FamilyMember familyMember, Player player, Tower tower) {
		try {
			System.out.println("Is tower occupied? " + tower.check(familyMember));
			if (tower.check(familyMember))
				player.subResource("coin", GlobalVariables.towerTax);

			System.out.println(
					"Has tower already a family member with the same colour ? " + tower.checkColor(familyMember));

			if (tower.checkColor(familyMember))
				this.checkResult = false;

			System.out.println("Checking started in tower floor ");

			if (tower.getFloors().get(this.towerAction.getFloorChoice()).check(familyMember))
				this.checkResult = false;
			System.out.println("Finished check in tower floor ");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
