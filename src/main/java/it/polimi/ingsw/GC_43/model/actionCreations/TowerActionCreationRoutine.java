package it.polimi.ingsw.GC_43.model.actionCreations;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actionSpace.Tower;
import it.polimi.ingsw.GC_43.model.actionSpace.TowerColors;
import it.polimi.ingsw.GC_43.model.actions.TowerAction;
import it.polimi.ingsw.GC_43.model.cards.Card;
import it.polimi.ingsw.GC_43.model.cards.VentureCard;
import it.polimi.ingsw.GC_43.model.effects.ChoiceEffect;
import it.polimi.ingsw.GC_43.model.effects.Effect;
import it.polimi.ingsw.GC_43.model.effects.MultipleChoiceEffect;
import it.polimi.ingsw.GC_43.model.effects.MultipleCouncilPrivileges;

public class TowerActionCreationRoutine implements ActionCreation {

	/**
	* 
	*/
	private static final long serialVersionUID = -366294267217777321L;

	private TowerAction towerAction;
	private Board board;
	// player ID will be the ID of the instance of playerImpl != player of the
	// model

	public TowerActionCreationRoutine(String playerID, Player player, Board board) {
		this.towerAction = new TowerAction(playerID, player);
		this.board = board;
	}

	public boolean prepareAction() {

		if (!this.towerAction.isDefaultFamilyMember())
			this.towerAction
					.setFamilyMember(CommonActionCreatorRoutine.askForFamilyMemberChoice(this.towerAction.getPlayer()));

		this.towerAction.setFamilyMemberColor(this.towerAction.getFamilyMember().getColor());

		this.towerAction.setServantsUsed(CommonActionCreatorRoutine.askForServantsUsage(towerAction.getPlayer(),
				this.towerAction.getFamilyMember().getDiceValue()));

		checkIfDoubleDiscount(this.towerAction.getPlayer());

		if (selectTowerAndFloor(board.getTowers())) {
			System.out.println("\nTOWER ACTION ENDS HERE\n");
			return true;
		}

		System.out.println("\nTOWER ACTION ENDS HERE\n");

		return false;
	}

	private boolean selectTowerAndFloor(ArrayList<Tower> towers) {

		// TOWER CHOICE
		String question = this.board.towersToString();
		int towerChoice;

		if (!this.towerAction.isDefaultTowerChoice())
			towerChoice = CommonActionCreatorRoutine.askForSingleChoice(question, 0, towers.size());
		else
			towerChoice = this.towerAction.getTowerChoice();

		// FLOOR CHOICE
		question = towers.get(towerChoice).toString();
		int floorChoice = CommonActionCreatorRoutine.askForSingleChoice(question, 0,
				towers.get(towerChoice).getFloors().size());

		int servantsUsed = this.towerAction.getServantsUsed();
		int familyMemberDie = this.towerAction.getFamilyMember().getDiceValue();
		int floorMinDieValue = towers.get(towerChoice).getFloors().get(floorChoice).getMinDiceValue();
		int extraBonusOnTower = lookForExtraBonus(towers.get(towerChoice));
		this.towerAction.getFamilyMember().addFamilyMemberValue(servantsUsed + extraBonusOnTower);
		;
		System.out.println("servants used: " + servantsUsed + " die value: " + familyMemberDie + " floor die value: "
				+ floorMinDieValue + " extra bonus on die: " + extraBonusOnTower);
		System.out.println(
				"\n tower boolean set to:" + towers.get(towerChoice).check(this.towerAction.getFamilyMember()));
		if (familyMemberDie + servantsUsed + extraBonusOnTower < floorMinDieValue
				|| towers.get(towerChoice).getFloors().get(floorChoice).check(this.towerAction.getFamilyMember())) {

			this.towerAction.getFamilyMember().subFamilyMemberValue(servantsUsed + extraBonusOnTower);
			;

			System.out.println("You can not access this floor, please try again");
			return selectTowerAndFloor(towers);
		}

		else {
			this.towerAction.setTowerChoice(towerChoice);
			this.towerAction.setFloorChoice(floorChoice);
			this.towerAction.getFamilyMember().subFamilyMemberValue(servantsUsed + extraBonusOnTower);
			;

		}
		if (this.board.getTowers().get(towerChoice).getFloors().get(floorChoice).getCard() != null) {
			System.out.println("checking if double cost card..");
			checkAndSelectIfDoubleCost(this.board.getTowers().get(towerChoice).getFloors().get(floorChoice).getCard());
			System.out.println("checking card instant effects..");
			checkCardInstantEffects(this.board.getTowers().get(towerChoice).getFloors().get(floorChoice).getCard());

			// VEDITI I CASI INSTANT BONUS E VEDI PER LE CHOICE !!
			return true;
		} else {
			return false;
		}
	}

	private void checkAndSelectIfDoubleCost(Card card) {
		if (card.getClass().toString().contains("VentureCard")) {
			VentureCard ventureCard = (VentureCard) card;
			if (ventureCard.getMilitaryCost() > 0) {
				if (!ventureCard.getCost().getCosts().isEmpty()) {
					String question = "\n Please input 0 to choose for this cost:\n " + ventureCard.getCost().toString()
							+ "\n otherwise 1 for :\n military Points needed: " + ventureCard.getMilitaryMin()
							+ "\n spending military points: " + ventureCard.getMilitaryCost();
					this.towerAction
							.setDoubleCostSelection(CommonActionCreatorRoutine.askForSingleChoice(question, 0, 2));
				} else {
					this.towerAction.setDoubleCostSelection(1);

				}
			}

		}
	}

	private void checkCardInstantEffects(Card card) {
		System.out.println("\nCard instant bonus requiring choices checks...\n");
		for (Effect effect : card.getInstantBonus()) {
			System.out.println("effect to string: " + effect.getClass().toString());
			if (effect.getClass().toString().contains("MultipleCouncilPrivileges")) {
				System.out.println("Multiple council privileges detected");
				askForMultipleCouncilPrivilege((MultipleCouncilPrivileges) effect);
			}
		}
	}

	private int lookForExtraBonus(Tower tower) {
		int extraBonusOnDie = 0;
		if (tower.getTowerColor() == TowerColors.BUILDINGS_TOWER)
			extraBonusOnDie = this.towerAction.getPlayer().getPlayerBounusMalus().getBonusDiceBuildingTower();

		if (tower.getTowerColor() == TowerColors.CHARACTERS_TOWER)
			extraBonusOnDie = this.towerAction.getPlayer().getPlayerBounusMalus().getBonusDiceCharacterTower();

		if (tower.getTowerColor() == TowerColors.TERRITORIES_TOWER)
			extraBonusOnDie = this.towerAction.getPlayer().getPlayerBounusMalus().getBonusDiceTerritoryTower();

		if (tower.getTowerColor() == TowerColors.VENTURES_TOWER)
			extraBonusOnDie = this.towerAction.getPlayer().getPlayerBounusMalus().getBonusDiceVentureTower();

		return extraBonusOnDie;
	}

	private void askForMultipleCouncilPrivilege(MultipleCouncilPrivileges multipleEffect) {
		System.out.println("Checking council privilege is not null..");
		System.out.println("Council privileges: " + multipleEffect.toString());
		MultipleCouncilPrivileges effect = CommonActionCreatorRoutine
				.copyMultiplePrivileges(multipleEffect.getNumberOfCopies());
		int numberOfCopies = effect.getNumberOfCopies();
		while (numberOfCopies > 0) {
			System.out.println("Launching asking for multiple choice..");
			int choice = askForMultipleChoice(effect.getPrivilegeChoices());
			System.out.println("asking for multiple choice ended with player choice: " + choice);
			if (choice != -1) {
				effect.getPrivilegeChoices().getChoices().remove(choice);
				System.out.println("choice removed..");

			}
			numberOfCopies--;
			System.out.println("remaining number of copies " + numberOfCopies);

		}
	}

	private int askForMultipleChoice(MultipleChoiceEffect effect) {
		int maxRange = effect.getChoices().size();
		System.out.println("Choices effect size is: " + maxRange);
		String question = "Please select the exchange effect you want to perform. Input -1 as do nothing:\n"
				+ effect.toString();
		int choice = CommonActionCreatorRoutine.askForSingleChoice(question, -1, maxRange);
		System.out.println("DEBUG REASON . CHECK FAM MEMBER AFTER ASK FOR SINGLE CHOICE IN ASKmULT CH: "
				+ this.towerAction.getFamilyMember().toString());
		if (effect.checkChoice(choice, this.towerAction.getPlayer())) {

			System.out.println("Choice effect passed the check..");
			this.towerAction.getEffectChoices().add(choice);
			System.out.println("Choice effect added to getChoices..");
			ChoiceEffect choiceEffect = effect.getChoices().get(choice);

			// Ask per privilege council SE nello scambio, MAX DA RIVEDERE;
			System.out.println("Checking nature of choice effect..");

			if (choiceEffect.getGains().get(0).getClass().toString().contains("privilegeCouncil"))
				askForMultipleCouncilPrivilege(new MultipleCouncilPrivileges(1));
		} else {
			question = "\nYou can't do this action because you do not have enough resources. Insert 0 to leave this choice or 1 to retry";
			choice = CommonActionCreatorRoutine.askForSingleChoice(question, -1, maxRange);
			if (choice == 0) {
				System.out.println("\nChoice skipped");
			} else {
				return askForMultipleChoice(effect);
			}
		}
		System.out.println("Multiple choice effect selection routine finished..");
		return choice;
	}

	// DA RIVEDERE NEL CASO
	private void checkIfDoubleDiscount(Player player) {
		if (player.getPlayerBounusMalus().isDoubleChoiceDiscountOnBuildingCard()) {
			String question = "You have the possibility to choose for a discount:\nInput 0 for: 1 stone discount\ninput 1 for: 1 wood discount";
			int choice = CommonActionCreatorRoutine.askForSingleChoice(question, 0, 1);
			if (choice == 0) {
				this.towerAction.getResourceDiscounts().put("stone", 1);
			} else {
				this.towerAction.getResourceDiscounts().put("wood", 1);
			}
		}
	}

	public TowerAction getTowerAction() {
		return towerAction;
	}

	public void setTowerAction(TowerAction towerAction) {
		this.towerAction = towerAction;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

}
