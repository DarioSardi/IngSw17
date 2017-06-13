package it.polimi.ingsw.GC_43.model.effects;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.GlobalVariables;

public class MultipleCouncilPrivileges extends Effect {
	private int numberOfCopies;
	private MultipleChoiceEffect privilegeChoices;

	
	
	public String toString(){
		String toString = "Player can take "+this.numberOfCopies+ "different council privilege choice effect:\n ";
		toString=toString+this.getPrivilegeChoices().toString();
		return toString;
	}
	
	public MultipleCouncilPrivileges(int numberOfCopies) {
		this.numberOfCopies=numberOfCopies;
		this.privilegeChoices=GlobalVariables.councilPrivilegeEffect;
	}
	

	public void executeEffect(FamilyMember familyMember){
		
	}
	public void executeEffect(FamilyMember familyMember, int playerChoice){
		this.getPrivilegeChoices().executeEffect(familyMember, playerChoice);
	}
	
	
	
	public int getNumberOfCopies() {
		return numberOfCopies;
	}


	public MultipleChoiceEffect getPrivilegeChoices() {
		return privilegeChoices;
	}

	public void setPrivilegeChoices(MultipleChoiceEffect privilegeChoices) {
		this.privilegeChoices = privilegeChoices;
	}

	public void setNumberOfCopies(int numberOfCopies) {
		this.numberOfCopies = numberOfCopies;
	}
	

	
}
