package it.polimi.ingsw.GC_43.model.actionPerforms;

import java.util.HashMap;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actionSpace.Floor;
import it.polimi.ingsw.GC_43.model.actionSpace.Tower;
import it.polimi.ingsw.GC_43.model.actions.TowerAction;
import it.polimi.ingsw.GC_43.model.cards.Card;
import it.polimi.ingsw.GC_43.model.cards.CardHandler;
import it.polimi.ingsw.GC_43.model.cards.VentureCard;
import it.polimi.ingsw.GC_43.model.effects.ChoiceEffect;
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

		this.checkResult = true;

		Player player = this.towerAction.getPlayer();
		FamilyMember familyMember = CommonActionPerformerRoutine.matchFamilyMember(player,
				this.towerAction.getFamilyMemberColor());
		HashMap<String, Integer> playerResourcesCopy = CommonActionPerformerRoutine.copyPlayerResources(player);

		addEventualDiscountedResources(player, this.towerAction.getResourceDiscounts());

		checkAndTryAction(player, familyMember);

		if (checkResult == true) {
			return true;
		} else {
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

		checkFamilyMemberAlreadyPlaced(familyMember);

		checkServantsUsed(player, familyMember);

		Tower chosenTower = this.board.getTowers().get(this.towerAction.getTowerChoice());

		checkTowerAndFloor(familyMember, player, chosenTower);

		Floor towerFloor = chosenTower.getFloors().get(this.towerAction.getFloorChoice());

		checkCardBuy(player, familyMember, chosenTower, towerFloor, towerFloor.getCard());

		checkEffectChoices(player, towerFloor.getCard());

	}

	private void checkEffectChoices(Player player, Card card) {
		try {
			for (Effect effect : card.getInstantBonus()) {
				if (effect.getClass().toString().contains("MultipleCouncilPrivileges")) {
					executeMultipleCouncilPrivilege((MultipleCouncilPrivileges) effect, player);

				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void executeMultipleCouncilPrivilege(MultipleCouncilPrivileges effect, Player player) {
		int numberOfCopies = effect.getNumberOfCopies();
		while (numberOfCopies > 0) {
			executeMultipleChoice(effect.getPrivilegeChoices(), player);
			int playerChoice = this.towerAction.getEffectChoices().get(index - 1);
			effect.getPrivilegeChoices().getChoices().remove(playerChoice);
		}

		numberOfCopies--;
	}

	private void executeMultipleChoice(MultipleChoiceEffect effect, Player player) {
		int playerChoice = this.towerAction.getEffectChoices().get(index);
		ChoiceEffect choiceEffect = (ChoiceEffect) effect.getChoices().get(playerChoice);
		try {
			if (playerChoice != -1) {
				if (choiceEffect.check(player)) {

					if (choiceEffect.getGains().get(0).getClass().toString().contains("privilegeCouncil")) {
						MultipleCouncilPrivileges privilege = new MultipleCouncilPrivileges(1);
						executeMultipleCouncilPrivilege(privilege, player);
					}
					effect.getChoices().get(playerChoice).executeEffect(player);
				}

				else {
					this.checkResult = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.index++;

	}

	private void checkCardBuy(Player player, FamilyMember familyMember, Tower chosenTower, Floor towerFloor,
			Card card) {
		try {
			if (card.getClass().toString().contains("VentureCard")) {
				VentureCard ventureCard = (VentureCard) card;
				if (ventureCard.getMilitaryCost() > 0 && this.towerAction.getDoubleCostSelection() == 1) {
					if (player.getPlayerResource("militaryPoints") >= ventureCard.getMilitaryMin()
							&& player.getPlayerResource("militaryPoints") >= ventureCard.getMilitaryCost()) {
						player.subResource("militaryPoint", ventureCard.getMilitaryCost());
						if (!CardHandler.buyCard(towerFloor, familyMember, card, chosenTower.check(familyMember),
								true)) {
							this.checkResult = false;
						} else {
							towerFloor.actionsAfterBuy(familyMember);
						}
					}
					else{
						this.checkResult = false;
					}
					this.index++;

				} else {
					if (!(CardHandler.buyCard(towerFloor, familyMember, card, chosenTower.check(familyMember), false)))
						this.checkResult = false;
					this.index++;
				}

			}
		} catch (Exception e) {
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

			else {
				player.subResource("servant", servantsUsed);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void checkTowerAndFloor(FamilyMember familyMember, Player player, Tower tower) {
		try {
			if (tower.check(familyMember))
				player.subResource("coin", GlobalVariables.towerTax);

			if (tower.checkColor(familyMember))
				this.checkResult = false;

			if (tower.getFloors().get(this.towerAction.getFloorChoice()).check(familyMember))
				this.checkResult = false;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
