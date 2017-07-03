package it.polimi.ingsw.GC_43.model.actionPerforms;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.GlobalVariables;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.actionCreations.CommonActionCreatorRoutine;
import it.polimi.ingsw.GC_43.model.actions.LeaderCardAction;
import it.polimi.ingsw.GC_43.model.cards.LeaderCard;
import it.polimi.ingsw.GC_43.model.effects.MultipleCouncilPrivileges;
import it.polimi.ingsw.GC_43.model.leaderCards.LorenzoEffect;

public class LeaderCardActionPerformerRoutine implements ActionPerformer {

	private LeaderCardAction leaderCardAction;
	private boolean checkResult;
	private Board board;
	private int index;

	public LeaderCardActionPerformerRoutine(LeaderCardAction leaderCardAction, Board board) {
		this.leaderCardAction = leaderCardAction;
		this.checkResult = false;
		this.board = board;

	}

	public boolean performAction() {

		System.out.println("\nEntered in Market performer routine");
		Player player = this.leaderCardAction.getPlayer();
		if(!this.getLeaderCardAction().isToDiscard()){
		System.out.println("Checking familyMember..");
		FamilyMember familyMember = CommonActionPerformerRoutine.matchFamilyMember(player,
				this.leaderCardAction.getFamilyMemberColor());

		System.out.println("Matching leader card..");
		// TODO to implement
		LeaderCard leaderCard = matchLeaderCard(this.leaderCardAction.getLeaderCardName());

		System.out.println("Checking leader card requirements & executing ability..");

		checkCardRequirementsAndExecute(leaderCard, this.leaderCardAction.getFamilyMember());
		}
		else{
			System.out.println("Discard leader card for council privilege choice detected");
			if(player.getPlayerCards().searchLeaderCardByName(this.getLeaderCardAction().getLeaderCardName())!=null){
				System.out.println("Removing leader card from player leader card");
				player.getPlayerCards().getArrayLeaderCards().remove(player.getPlayerCards().searchLeaderCardByName(this.getLeaderCardAction().getLeaderCardName()));
				System.out.println("Executing council privilege effect");
				GlobalVariables.councilPrivilegeEffect.executeEffect(player.getFamilyMember(1), this.getLeaderCardAction().getEventualChoice());
			}else
				this.checkResult=false;
		}
		
		System.out.println("Leader card action performer routine ends here!");
		
		return checkResult;

	}

	private void checkCardRequirementsAndExecute(LeaderCard leaderCard, FamilyMember familyMember) {

		System.out.println("Checking leader card requirements..");
		if (leaderCard.checkRequirements(familyMember)) {
			System.out.println("Executing leader card ability..");
			leaderCard.executeAbility(this.leaderCardAction.getFamilyMember());
			this.checkResult = true;

			if (leaderCard.getCardName().contains("Lorenzo") || leaderCard.getCardName().contains("Gonzaga")) {
				System.out.println("Special card detected, launching check for further choice..");
				lookForChoicesSelection(familyMember, familyMember.getPlayer());
				// REMEMBER LORENZO EFFECT IF EFFECT ALREADY COPIED, SOMEHOW IT
				// HAS TO BE EXECUTED FIRSTLY
				// TO BE COMPLETED
			}
		} else {
			this.checkResult = false;
			System.out.println("Requirements check not passed, action result is " + this.checkResult);
		}
	}

	private LeaderCard matchLeaderCard(String leaderCardName) {
		// TODO Auto-generated method stub
		return null;
	}

	private void lookForChoicesSelection(FamilyMember familyMember, Player player) {
		// TODO to complete

		if (this.leaderCardAction.getLeaderCardName().contains("Gonzaga")) {
			MultipleCouncilPrivileges multiplePrivilege = CommonActionCreatorRoutine.copyMultiplePrivileges(1);
			multiplePrivilege.executeEffect(familyMember, this.leaderCardAction.getEventualChoice());
		}
		
		if (this.leaderCardAction.getLeaderCardName().contains("Lorenzo")) {
			// WAIT FOR SAM FIND LEADER CARD BY NAME
			if (player.getPlayerCards().searchLeaderCardByName("Lorenzo de' Medici").getAbility().get(0).getClass()
					.toString().contains("LorenzoEffect")) {
				LorenzoEffect effect = (LorenzoEffect) player.getPlayerCards()
						.searchLeaderCardByName("Lorenzo de' Medici").getAbility().get(0);
				if (!this.board.getLeaderCardsPlayed().isEmpty()) {
						
						System.out.println("Copying effect of selected leader card " + this.board.getLeaderCardsPlayed()
								.get(this.leaderCardAction.getEventualChoice()).getCardName());
						
						player.getPlayerCards().searchLeaderCardByName("Lorenzo de' Medici").setAbility(this.board
								.getLeaderCardsPlayed().get(this.leaderCardAction.getEventualChoice()).getAbility());
						effect.setAlreadyChosen(true);
						
						System.out.println("Ability chosen and copied in Lorenzo leader card is: " + player
								.getPlayerCards().searchLeaderCardByName("Lorenzo de' Medici").getAbility().toString());
			
				} else
					this.checkResult = false;
			}
		}
	}

	public LeaderCardAction getLeaderCardAction() {
		return leaderCardAction;
	}

	public void setLeaderCardAction(LeaderCardAction leaderCardAction) {
		this.leaderCardAction = leaderCardAction;
	}

	public boolean isCheckResult() {
		return checkResult;
	}

	public void setCheckResult(boolean checkResult) {
		this.checkResult = checkResult;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	
	
	
}
