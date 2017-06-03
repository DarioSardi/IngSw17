package it.polimi.ingsw.GC_43.playerActions;

import java.util.HashMap;

import it.polimi.ingsw.GC_43.model.Board;
import it.polimi.ingsw.GC_43.model.FamilyMember;
import it.polimi.ingsw.GC_43.model.Player;
import it.polimi.ingsw.GC_43.model.cards.BuildingCard;
import it.polimi.ingsw.GC_43.model.effects.Effect;


public class ProductionActionPerformer{
	private ProductionAction productionAction;
	private boolean checkResult;
	private Board board;

	
	//README
	//@require player instance inside the productionAction to be changed into the real one on the server
	
	
	
	
	
	public ProductionActionPerformer(ProductionAction productionAction, Board board){
		
		this.productionAction=productionAction;
		this.checkResult=false;
		this.board=board;
		//decide if to launch direcly here in creation the performAction();
		
	}
	
	
	public boolean performAction() {
		this.checkResult=true;
		
		Player player=this.productionAction.getPlayer();
		
	//	FamilyMember familyMember= matchFamilyMember(player, productionAction.getFamilyMemberColor());
	
		HashMap<String, Integer> playerResourcesCopy=CommonActionPerformerRoutine.copyPlayerResources(player);
		checkResult= CommonActionPerformerRoutine.checkResourceUsed(player,"servant",this.productionAction.getServantsUsed());

		
		if(checkResult){
			int servantsUsed=this.productionAction.getServantsUsed();
			this.productionAction.getPlayer().subResource("servant",servantsUsed);
			if(player.getPlayerBounusMalus().isTwoServantsCountAsOne()){
				servantsUsed=servantsUsed/2;
			    //familyMember.addDieValue(servantsUsed)
			}
			
		}
			
			
			
			
			
			// TODO README
			//ADDED REFERENCE TO BOARD, NOW ACCESSING PRODUCTION AREA FOR CHECK IS POSSIBILE.
			//CHECK IF SECONDARY CELLS FOR BOTH PRODUCTION AND HARVEST ARE NULL BECAUSE OF NUMBER OF PLAYERS
			
		//CHECK IF TWO PLAYER JUST ONE SPACE FOR PRODUCTION!!
			
		//TODO to ask for check die and if space occupied
		//TODO to ask for check die and if space occupied
		//TODO to ask for check die and if space occupied
		//TODO to ask for check die and if space occupied
		//TODO to ask for check die and if space occupied
		//TODO to ask for check die and if space occupied
		//TODO to ask for check die and if space occupied

	//	checkChoices(familyMember.getDiceValue()+player.getPlayerBounusMalus().getBonusProductionArea(), player);
	
	//	familyMember.
		// TODO to implement: getting inputs from class ProductionAction which will
		//come from the client to the server and will manage it performing it
		//with relative checks on things
		return false;
		
	}

	
	/*@require bonus malus on familyMember die to be already applied, but that's normal*/ 
	/*private boolean checkChoices(int dieValue,Player player ){
			 
			for(BuildingCard buildingCard: this.productionAction.getPlayer().getPlayerCards().getArrayBuildingCards()){
				if(this.productionAction.getFamilyMember().getDiceValue()+this.productionAction.getServantsUsed()+this.productionAction.getPlayer().getPlayerBounusMalus().getBonusProductionArea()>=buildingCard.getProductionDice()){
					for( Effect effect: buildingCard.getPermaBonus()){
						if(effect.getClass().toString().contains("MultipleChoiceEffect")){
							askForMultipleChoice(effect);	
						}
					
					else if (effect.getClass().toString().contains("ChoiceEffect")){
					askForASingleChoice(effect);
						}
					}
	        	}
			}
	       
		}
		return true;
	}


	private FamilyMember matchFamilyMember(Player player, int familyMemberColor) {
		return player.findFamilyMemberByColor(familyMemberColor);
	}
	
	*/
	
	

}
