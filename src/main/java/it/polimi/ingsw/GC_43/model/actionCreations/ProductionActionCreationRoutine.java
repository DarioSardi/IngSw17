package it.polimi.ingsw.GC_43.model.actionCreations;

import java.util.HashMap;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actionSpace.ProductionArea;
import it.polimi.ingsw.GC_43.model.actions.ProductionAction;
import it.polimi.ingsw.GC_43.model.cards.BuildingCard;
import it.polimi.ingsw.GC_43.model.effects.ChoiceEffect;
import it.polimi.ingsw.GC_43.model.effects.Effect;
import it.polimi.ingsw.GC_43.model.effects.MultipleChoiceEffect;
import it.polimi.ingsw.GC_43.model.effects.MultipleCouncilPrivileges;

public class ProductionActionCreationRoutine implements ActionCreation {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4848735135668204658L;

	private ProductionAction productionAction;
	private Board board;
	private HashMap<String, Integer> copyOfPlayerResource;
	// player ID will be the ID of the instance of playerImpl != player of the
	// model

	public ProductionActionCreationRoutine(String playerID, Player player, Board board) {
		try {
			this.productionAction = new ProductionAction(playerID, player);
			this.board = board;
			this.copyOfPlayerResource = CommonActionCreatorRoutine.copyPlayerResources(player);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean prepareAction() {

		if (!this.productionAction.isDefaultFamilyMember())
			this.productionAction.setFamilyMember(
					CommonActionCreatorRoutine.askForFamilyMemberChoice(this.productionAction.getPlayer()));

		this.productionAction.setFamilyMemberColor(this.productionAction.getFamilyMember().getColor());
		int servantsChosed = CommonActionCreatorRoutine.askForServantsUsage(productionAction.getPlayer(),
				this.productionAction.getFamilyMember().getDiceValue());
		this.productionAction.setServantsUsed(servantsChosed);
		selectProductionSpace(board.getProductionArea());
		getInputsForProduction(this.productionAction.getFamilyMember());

		System.out.println("\nPRODUCTION ACTION ENDS HERE\n");
		return true;
	}

	private boolean selectProductionSpace(ProductionArea productionArea) {

		try {
			if (!productionArea.getSpaces().get(0).isOccupied()) {
				System.out.println("\nPrimary empty cell selected\n");
				this.productionAction.setPrimaryCellChosen(true);
			} else {
				if (productionArea.getPrimarySpace().execute(this.productionAction.getFamilyMember())) {

					System.out.println(
							"\nPrimary production cell occupied, secondary production cell selected. Family Member will receive a malus on die value of \n"
									+ GlobalVariables.malusUnlimitedCells);
					this.productionAction.setPrimaryCellChosen(false);

					return productionArea.check((this.productionAction.getFamilyMember()));

				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}

	/*
	 * @require bonus malus on familyMember die to be already applied, but
	 * that's normal
	 */
	private void getInputsForProduction(FamilyMember familyMember) {
		int malusOnSecondarySpace = 0;
		if (!this.productionAction.isPrimaryCellChosen())
			malusOnSecondarySpace = GlobalVariables.malusUnlimitedCells;
		int dieValue = familyMember.getDiceValue() + this.productionAction.getServantsUsed()
				+ familyMember.getPlayer().getPlayerBounusMalus().getBonusProductionArea() + malusOnSecondarySpace;
		try {
			for (BuildingCard buildingCard : this.productionAction.getPlayer().getPlayerCards()
					.getArrayBuildingCards()) {
				if (dieValue >= buildingCard.getProductionDice()) {
					for (Effect effect : buildingCard.getPermaBonus()) {
						if (effect.getClass().toString().contains("MultipleChoiceEffect")) {
							System.out.println("MultipleChoiceEffect deteced, asking for choice..");
							askForMultipleChoice((MultipleChoiceEffect) effect);
						}
						if (effect.getClass().toString().contains("MultipleCouncilPrivileges")) {
							System.out.println("MultipleCouncilPrivileges deteced, asking for choice..");
							askForMultipleCouncilPrivilege((MultipleCouncilPrivileges) effect);
						}
					}
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.productionAction.getFamilyMember().addFamilyMemberValue(GlobalVariables.malusUnlimitedCells);

	}

	private void askForMultipleCouncilPrivilege(MultipleCouncilPrivileges multipleEffect) {
		MultipleCouncilPrivileges effect = CommonActionCreatorRoutine
				.copyMultiplePrivileges(multipleEffect.getNumberOfCopies());
		System.out.println("Multiple council privilege copy created, executing effect");
		int numberOfCopies = effect.getNumberOfCopies();
		try {
			while (numberOfCopies > 0) {
				int choice = askForMultipleChoice(effect.getPrivilegeChoices());
				if (choice != -1) {
					effect.getPrivilegeChoices().getChoices().remove(choice);
					System.out.println("\n\n\nMult copy size is " + effect.getPrivilegeChoices().getChoices().size());

					System.out.println(
							"While static one is size of " + multipleEffect.getPrivilegeChoices().getChoices().size());

				}

				numberOfCopies--;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private int askForMultipleChoice(MultipleChoiceEffect effect) {

		int maxRange = effect.getChoices().size();
		String question = "Please select the exchange effect you want to perform. Input -1 as do nothing:\n"
				+ effect.toString();
		int choice = CommonActionCreatorRoutine.askForSingleChoice(question, -1, maxRange);
		if (effect.checkChoice(choice, this.productionAction.getPlayer())) {
			System.out.println("effect check is ok, adding player choice..");
			this.productionAction.getProductionChoices().add(choice);
			System.out.println("Selecting choice effect to check..");
			ChoiceEffect choiceEffect = effect.getChoices().get(choice);

			System.out.println("Effect is : " + choiceEffect.toString());

			if (choiceEffect.getGains().get(0).getClass().toString().contains("CouncilPrivilege")) {
				System.out.println("Privilege council as resource detected.. Executing routine..");
				askForMultipleCouncilPrivilege(new MultipleCouncilPrivileges(1));
			}
		} else {
			question = "\nYou can't do this action because you do not have enough resources. Insert 0 to leave this choice or 1 to retry";
			choice = CommonActionCreatorRoutine.askForSingleChoice(question, -1, maxRange);
			if (choice == 0) {
				System.out.println("\nChoice skipped");
			} else {
				return askForMultipleChoice(effect);
			}
		}
		return choice;

	}

	public ProductionAction getProductionAction() {
		return productionAction;
	}

	public void setProductionAction(ProductionAction productionAction) {
		this.productionAction = productionAction;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public HashMap<String, Integer> getCopyOfPlayerResource() {
		return copyOfPlayerResource;
	}

	public void setCopyOfPlayerResource(HashMap<String, Integer> copyOfPlayerResource) {
		this.copyOfPlayerResource = copyOfPlayerResource;
	}

}
