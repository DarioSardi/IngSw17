package it.polimi.ingsw.GC_43.model.effects;

import java.util.ArrayList;

import it.polimi.ingsw.GC_43.model.FamilyMember;

public class SingleCouncilPrivilege {
	private ArrayList<ResourceEffect> choices;
	
	public SingleCouncilPrivileges(){
		//TODO decide if better to have the choices as a GlobalVariable, forse è meglio cosi la settiamo senza passarla
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
		String choicesToSend= new String(this.toString());
		
		//TODO I check del risultato farli in controller, deve essere un intero COMPRESO tra 1 e choices.size();
		int playerChoice= familyMember.getPlayer().getController().askPlayerForChoice(choicesToSend);
		choices.get(playerChoice-1).executeEffect(familyMember);
		
		}
}

