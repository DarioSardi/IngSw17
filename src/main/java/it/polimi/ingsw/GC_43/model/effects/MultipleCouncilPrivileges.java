package it.polimi.ingsw.GC_43.model.effects;

import java.util.ArrayList;

public class MultipleCouncilPrivileges extends Effect {
	private int numberOfCopies;
	private ArrayList<ResourceEffect> choices;

	
	//TODO decidere per resource effect , io terrei arrayList
	public String toString(){
		String toString = "Player can take "+this.numberOfCopies+ "different council privilege choice effect: ";
		int choiceNumber=1;
		for(ResourceEffect resourceEffect : choices){
			toString= toString+"choice "+choiceNumber+": "+ resourceEffect.toString();
			choiceNumber++;
		}
		return toString;
	}
	
	public MultipleCouncilPrivileges(int numberOfCopies) {
		this.numberOfCopies=numberOfCopies;
		this.choices=new ArrayList<ResourceEffect>();
	}
	
	public void executeEffect(){
		int i=0;
		SingleCouncilPrivilege singleCouncilPrivilege= new SingleCouncilPrivilege();
		while(i<this.getNumberOfCopies()){
			String choices= singleCouncilPrivilege.toString();
//			int playerChoice= familyMember.getPlayer().getController().askPlayerForChoice(choices.size(),choicesToSend);
//			choices.get(playerChoice-1).executeEffect(familyMember);
//			singleCouncilPrivilege.removeChoice(playerChoice);
			
			i++;
		}
		
	}
//TODO to implement
	public int getNumberOfCopies() {
		return numberOfCopies;
	}


	
}
