package it.polimi.ingsw.GC_43.model.effects;

import it.polimi.ingsw.GC_43.model.FamilyMember;

public class TwoDifferentCouncilPrivileges {
	
	public void executeEffect(FamilyMember familyMember){
		SingleCouncilPrivilege singleCouncilPrivilege= new SingleCouncilPrivilege();
		String choicesToSend= new String(singleCouncilPrivilege.toString());
//TODO IMPLEMENT CONTROLLER, add player a reference to controller
//		int playerFirstChoice= familyMember.getPlayer().getController().askPlayerForChoice("Two choices needed, please select just the first choice:\n"+choicesToSend);
//		int playerSecondChoice= familyMember.getPlayer().getController().askPlayerForChoice("Please select your second choice: please remeber it has to be different from previous one:\n"+choicesToSend);

		
	}
}
