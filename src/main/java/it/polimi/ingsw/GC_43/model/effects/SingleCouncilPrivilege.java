package it.polimi.ingsw.GC_43.model.effects;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.FamilyMember;

public class SingleCouncilPrivilege {
	private ArrayList<ResourceEffect> choices;
	
	public SingleCouncilPrivilege(){
		//TODO decide if better to have the choices as a GlobalVariable, forse è meglio cosi la settiamo senza passarla
	}
	
	public void removeChoice(int choiceNumber){
		this.getChoices().remove(choiceNumber);
	}
	
	public String toString(){
		String choicesForPlayer = new String();
		int choiceNumber=1;
		for(ResourceEffect resourceEffect : choices){
			choicesForPlayer= choicesForPlayer+"choice "+choiceNumber+": "+ resourceEffect.toString();
			choiceNumber++;
		}
		return choicesForPlayer;
	}
	
	public void executeEffect(FamilyMember familyMember){
		String choicesToSend= this.toString();
	//chaccka	
		//TODO Implementa controller check del risultato farli in controller, deve essere un intero COMPRESO tra 1 e choices.size();
//		int playerChoice= familyMember.getPlayer().getController().askPlayerForChoice(choices.size(),choicesToSend);
//		choices.get(playerChoice-1).executeEffect(familyMember);
		
		}

	public ArrayList<ResourceEffect> getChoices() {
		return choices;
	}

	public void setChoices(ArrayList<ResourceEffect> choices) {
		this.choices = choices;
	}
	
	
	
}

