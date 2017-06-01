package it.polimi.ingsw.GC_43.model.effects;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.FamilyMember;

public class SingleCouncilPrivilege extends Effect {
	private ArrayList<Effect> choices;
	
	public SingleCouncilPrivilege(){
		//TODO decide if better to have the choices as a GlobalVariable, forse è meglio cosi la settiamo senza passarla
	}
	
	public void removeChoice(int choiceNumber){
		this.getChoices().remove(choiceNumber);
	}
	
	public String toString(){
		String toString = new String();
		int choiceNumber=1;
		for(Effect resourceEffect : choices){
			toString= toString+"choice "+choiceNumber+": "+ resourceEffect.toString();
			choiceNumber++;
		}
		return toString;
	}
	
	public void executeEffect(FamilyMember familyMember){
		String choicesToSend= this.toString();
	//chaccka	
		//TODO Implementa controller check del risultato farli in controller, deve essere un intero COMPRESO tra 1 e choices.size();
//		int playerChoice= familyMember.getPlayer().getController().askPlayerForChoice(choices.size(),choicesToSend);
//		choices.get(playerChoice-1).executeEffect(familyMember);
		
		}

	public ArrayList<Effect> getChoices() {
		return choices;
	}

	public void setChoices(ArrayList<Effect> choices) {
		this.choices = choices;
	}
	
	
	
}

