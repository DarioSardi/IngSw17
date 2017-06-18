package it.polimi.ingsw.GC_43.model.actions;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;

public abstract class Action implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Player player;
	private String playerID;
	private int familyMemberColor;
	private FamilyMember familyMember;
	private int ActionID;
	// THINK OF ACTION ID BEST PRACTICE WAY
	private int servantsUsed;
	private ArrayList<Integer> Choices;

	public Action(String playerID, Player player) {
		this.servantsUsed = 0;
		this.player = player;
		this.playerID = playerID;

	}

	public String getPlayerID() {
		return playerID;
	}

	public void setPlayerID(String playerID) {
		this.playerID = playerID;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getActionID() {
		return ActionID;
	}

	public void setActionID(int actionID) {
		ActionID = actionID;
	}

	public int getFamilyMemberColor() {
		return familyMemberColor;
	}

	public void setFamilyMemberColor(int familyMemberColor) {
		this.familyMemberColor = familyMemberColor;
	}

	public FamilyMember getFamilyMember() {
		return familyMember;
	}

	public void setFamilyMember(FamilyMember familyMember) {
		this.familyMember = familyMember;
	}

	public int getServantsUsed() {
		return servantsUsed;
	}

	public void setServantsUsed(int servantsUsed) {
		this.servantsUsed = servantsUsed;
	}

	public ArrayList<Integer> getChoices() {
		return Choices;
	}

	public void setChoices(ArrayList<Integer> choices) {
		Choices = choices;
	}

}
