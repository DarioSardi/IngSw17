package it.polimi.ingsw.GC_43.model.actionCreations;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.Player;

import it.polimi.ingsw.GC_43.model.actions.LeaderCardAction;
import it.polimi.ingsw.GC_43.model.cards.LeaderCard;
import it.polimi.ingsw.GC_43.model.effects.MultipleCouncilPrivileges;
import it.polimi.ingsw.GC_43.model.leaderCards.LorenzoEffect;

public class LeaderCardActionCreationRoutine implements ActionCreation {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2296517721917669081L;
	private LeaderCardAction leaderCardAction;
	private Board board;
	private int choice;
	boolean checkResult = true;

	// player ID will be the ID of the instance of playerImpl != player of the
	// model

	public LeaderCardActionCreationRoutine(String playerID, Player player, Board board) {
		this.leaderCardAction = new LeaderCardAction(playerID, player);
		this.board = board;
	}

	public boolean prepareAction() {
		selectLeaderCard(this.leaderCardAction.getPlayer());

		askIfToPlayOrDiscard();

		if (!this.getLeaderCardAction().isToDiscard()) {

			this.leaderCardAction.setFamilyMember(
					CommonActionCreatorRoutine.askForFamilyMemberChoice(this.leaderCardAction.getPlayer()));

			this.leaderCardAction.setFamilyMemberColor(this.leaderCardAction.getFamilyMember().getColor());

			lookForChoiceChance(this.leaderCardAction.getPlayer());
		} else
			askForCouncilPrivilege();
		System.out.println("Action preparation result is "+this.checkResult);
		return checkResult;
	}

	private void askIfToPlayOrDiscard() {
		String question = "Do you want to play or discard this leader card for a council privilege?\nInput 0 to discard the card and 1 to play it";
		int choice = CommonActionCreatorRoutine.askForSingleChoice(question, 0, 2);
		if (choice == 0) {
			System.out.println("Discard option setted");
			this.getLeaderCardAction().setToDiscard(true);
		}

	}

	private void askForCouncilPrivilege() {
		MultipleCouncilPrivileges multiplePrivilege = CommonActionCreatorRoutine.copyMultiplePrivileges(1);
		String question = multiplePrivilege.toString();
		int maxRange = multiplePrivilege.getPrivilegeChoices().getChoices().size();
		this.leaderCardAction.setEventualChoice(CommonActionCreatorRoutine.askForSingleChoice(question, 0, maxRange));

	}

	private void lookForChoiceChance(Player player) {

		if (this.leaderCardAction.getLeaderCardName().contains("Gonzaga")) {
			askForCouncilPrivilege();

		}
		if (this.leaderCardAction.getLeaderCardName().contains("Lorenzo")) {
			System.out.println("OHHHH MAN YOU HAVE CHOSEN THE KING OF THIS GAME, MUCHISSIMO!");
			LorenzoEffect effect = (LorenzoEffect) player.getPlayerCards().getArrayLeaderCards().get(this.choice)
					.getAbility().get(0);
			if (!this.board.getLeaderCardsPlayed().isEmpty()) {
				System.out.println("SOme leader cards have been already played, now that's your turn to choose!");
				if (!effect.isAlreadyChosen()) {
					String question = "Please choose a leader card whose effect you want to copy:\n\n"
							+ this.board.leaderCardsPlayedToString();
					int maxRange = this.board.getLeaderCardsPlayed().size();
					this.leaderCardAction
							.setEventualChoice(CommonActionCreatorRoutine.askForSingleChoice(question, 0, maxRange));
				}
			} else
				this.checkResult = false;
		}
	}

	private void selectLeaderCard(Player player) {
		String question = "Please select which leader card you want to use:\n";
		int i = 0;
		for (LeaderCard leaderCard : player.getPlayerCards().getArrayLeaderCards()) {
			question = question + i + ")" + leaderCard.toString() + "\n";
			i++;
		}
		this.choice = CommonActionCreatorRoutine.askForSingleChoice(question, 0,
				player.getPlayerCards().getArrayLeaderCards().size());
		this.leaderCardAction
				.setLeaderCardName(player.getPlayerCards().getArrayLeaderCards().get(choice).getCardName());

	}

	public LeaderCardAction getLeaderCardAction() {
		return leaderCardAction;
	}

	public void setLeaderCardAction(LeaderCardAction leaderCardAction) {
		this.leaderCardAction = leaderCardAction;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public int getChoice() {
		return choice;
	}

	public void setChoice(int choice) {
		this.choice = choice;
	}

	public boolean isCheckResult() {
		return checkResult;
	}

	public void setCheckResult(boolean checkResult) {
		this.checkResult = checkResult;
	}

}
