package it.polimi.ingsw.GC_43.playerActions;

import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;


public class ProductionActionPerformer{
	private ProductionAction productionAction;
	
	//README
	//@require player instance inside the productionAction to be changed into the real one on the server
	
	
	
	
	
	public ProductionActionPerformer(ProductionAction productionAction){
		
		this.productionAction=productionAction;
		//decide if to launch direcly here in creation the performAction();

	}
	
	
	public boolean performAction() {
		// COPY ALL RESOURCES OF PLAYERS
		FamilyMember familyMember= matchFamilyMember(this.productionAction.getPlayer(), productionAction.getFamilyMemberColor());
		boolean checkResult= CommonActionPerformerRoutine.checkResourceUsed(this.productionAction.getPlayer(),"servant",this.productionAction.getServantsUsed());
//		checkResult = checkChoices();
	
		
		// TODO to implement: getting inputs from class ProductionAction which will
		//come from the client to the server and will manage it performing it
		//with relative checks on things
		return false;
	}

	//TODO WAIT FOR SAM
	private FamilyMember matchFamilyMember(Player player, int familyMemberColor) {
		// return player.getFamiliarByColor(familyMemberColor)
		return null;
	}
	
	
	
	

}
