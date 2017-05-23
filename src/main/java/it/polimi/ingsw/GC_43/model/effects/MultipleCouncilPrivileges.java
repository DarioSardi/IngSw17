package it.polimi.ingsw.GC_43.model.effects;

public class MultipleCouncilPrivileges extends Effect {
	private int numberOfCopies;

	
	public MultipleCouncilPrivileges(int numberOfCopies) {
		this.numberOfCopies=numberOfCopies;
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

	public int getNumberOfCopies() {
		return numberOfCopies;
	}


	
}
